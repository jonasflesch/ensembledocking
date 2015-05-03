package br.com.jonasflesch.ensembledocking.docking;

import br.com.jonasflesch.ensembledocking.core.*;
import br.com.jonasflesch.ensembledocking.model.DockParametersDto;
import br.com.jonasflesch.ensembledocking.model.DockingResultDto;
import br.com.jonasflesch.ensembledocking.model.MolecularDynamicsResultDto;
import br.com.jonasflesch.ensembledocking.moleculardynamics.MolecularDynamicsService;
import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

/**
 * Created by jonasflesch on 3/24/15.
 */
@Service
public class DockService {

	private static final Logger LOGGER = Logger.getLogger(DockService.class);

	@Inject
	private MGLToolsCaller mglToolsCaller;

	@Inject
	private AutogridCaller autogridCaller;

	@Inject
	private AutodockCaller autodockCaller;

	@Inject
	private ResultExtractor resultExtractor;

	@Inject
	private PymolParameterGenerator pymolParameterGenerator;

	@Inject
	private PymolCaller pymolCaller;

	@Inject
	private DockSettings dockSettings;

	@Inject
	private MolecularDynamicsService molecularDynamicsService;

	private static Random random = new Random();

	public DockingResultDto dock(final DockParametersDto dockParametersDto){
		try {
			String resultDirectory = dockParametersDto.getPdbFileLigand().getPath().substring(0, dockParametersDto.getPdbFileLigand().getPath().lastIndexOf(File.separator));
			MolecularDynamicsResultDto molecularDynamicsResultDto = molecularDynamicsService.molecularDynamics(dockParametersDto.getPdbFileReceptor(), dockParametersDto);

			String pdbqtFileLigand = mglToolsCaller.prepareLigand(dockParametersDto.getPdbFileLigand().getPath());
			String dockedPdbqtFile = null;

			XYSeries xySeries = new XYSeries("Energia Livre");

			for (int i = 0; i < molecularDynamicsResultDto.getConformations().length; i++){
				String conformation = molecularDynamicsResultDto.getConformations()[i];
				String pdfFileReceptorConformation = resultDirectory + File.separator + random.nextInt() + ".pdb";
				Files.write(Paths.get(pdfFileReceptorConformation), conformation.getBytes("UTF-8"));
				String pdbqtFileReceptor = mglToolsCaller.prepareReceptor(pdfFileReceptorConformation);
				String dpfFile = mglToolsCaller.prepareDockingParameter(pdbqtFileLigand, pdbqtFileReceptor);
				String gpfFile = mglToolsCaller.prepareGrid(pdbqtFileLigand, pdbqtFileReceptor);

				autogridCaller.autogrid(gpfFile);
				String dlgFile = autodockCaller.autodock(dpfFile);
				dockedPdbqtFile = resultExtractor.convertDlgToPdbqt(dlgFile);

				Double freeEnergy = freeEnergyFromPdbqt(dockedPdbqtFile);

				int stepTime = molecularDynamicsResultDto.getSteps()/molecularDynamicsResultDto.getConformations().length;
				int currentStepTime = stepTime * i;

				xySeries.add(currentStepTime, freeEnergy);
			}

			String energyOverTimeChartFile = createEnergyOverTimeChart(xySeries, resultDirectory);

			String dockedPdbFile = resultExtractor.convertPdbqtToPdb(dockedPdbqtFile);

			String pngFileName = generateResultImage(dockParametersDto.getPdbFileReceptor(), resultDirectory, dockedPdbFile);

			DockingResultDto dockingResultDto = new DockingResultDto();
			dockingResultDto.setBestDockingImage(pngFileName);
			dockingResultDto.setFreeEnergyOverTimeChart(energyOverTimeChartFile);
			return dockingResultDto;
		} catch (Exception e){
			LOGGER.error("Erro ao docar", e);
			return null;
		}
	}

	private Double freeEnergyFromPdbqt(final String pdbqtFile) throws IOException {

		String anchor = "Estimated Free Energy of Binding    =";

		try (BufferedReader br = new BufferedReader(new FileReader(pdbqtFile))) {
			String line;
			while ((line = br.readLine()) != null) {
				int indexOf = line.indexOf(anchor);
				if(indexOf >= 0){
					int begin = indexOf + anchor.length();
					return Double.valueOf(line.substring(begin, begin +8).trim());
				}
			}
		}

		return null;
	}

	private String createEnergyOverTimeChart(final XYSeries xySeries, final String resultDirectory) throws IOException {
		final XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(xySeries);

		// create the chart...
		final JFreeChart chart = ChartFactory.createXYLineChart(
				"Enegia Livre ao Longo do Tempo",      // chart title
				"Tempo (picosegundos)",                      // x axis label
				"Energia Livre",                      // y axis label
				dataset,                  // data
				PlotOrientation.VERTICAL,
				true,                     // include legend
				true,                     // tooltips
				false                     // urls
		);

		// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
		chart.setBackgroundPaint(Color.white);

//        final StandardLegend legend = (StandardLegend) chart.getLegend();
		//      legend.setDisplaySeriesShapes(true);

		// get a reference to the plot for further customisation...
		final XYPlot plot = chart.getXYPlot();
		plot.setBackgroundPaint(Color.lightGray);
		//    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);

		final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesLinesVisible(0, false);
		renderer.setSeriesShapesVisible(1, false);
		plot.setRenderer(renderer);

		// change the auto tick unit selection to integer units only...
		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());


		BufferedImage objBufferedImage=chart.createBufferedImage(800, 600);


		String outputFilePath = dockSettings.getResultsDirectory() + File.separator + random.nextInt() + ".png";
		File outputFile = new File(outputFilePath);

		ImageIO.write(objBufferedImage, "png", outputFile);

		return outputFilePath;
	}

	private String generateResultImage(File pdbFileReceptor, String resultDirectory, String dockedPdbFile) throws IOException, InterruptedException {
		String pngFileName = random.nextInt() + ".png";

		String pmlFile = pymolParameterGenerator.generatePml(resultDirectory, dockSettings.getResultsDirectory() + File.separator + pngFileName, dockedPdbFile, pdbFileReceptor.getPath());

		pymolCaller.pymol(pmlFile);
		return pngFileName;
	}

}
