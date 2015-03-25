package br.com.jonasflesch.ensembledocking.docking;

import br.com.jonasflesch.ensembledocking.core.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.File;

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

	public String dock(final File pdbFileLigand, final File pdbFileReceptor){
		try {
			String pdbqtFileLigand = mglToolsCaller.prepareLigand(pdbFileLigand.getPath());
			String pdbqtFileReceptor = mglToolsCaller.prepareReceptor(pdbFileReceptor.getPath());
			String dpfFile = mglToolsCaller.prepareDockingParameter(pdbqtFileLigand, pdbqtFileReceptor);
			String gpfFile = mglToolsCaller.prepareGrid(pdbqtFileLigand, pdbqtFileReceptor);

			autogridCaller.autogrid(gpfFile);
			String dlgFile = autodockCaller.autodock(dpfFile);
			String dockedPdbqtFile = resultExtractor.convertDlgToPdbqt(dlgFile);
			String dockedPdbFile = resultExtractor.convertPdbqtToPdb(dockedPdbqtFile);

			String resultDirectory = dockedPdbFile.substring(0, dockedPdbFile.lastIndexOf(File.separator));

			String pmlFile = pymolParameterGenerator.generatePml(resultDirectory, resultDirectory + File.separator + "docked.png", dockedPdbFile, pdbFileReceptor.getPath());

			pymolCaller.pymol(pmlFile);

			String pngFile = resultDirectory + File.separator + "docked.png";
			return pngFile;
		} catch (Exception e){
			LOGGER.error("Erro ao docar", e);
			return null;
		}
	}

}
