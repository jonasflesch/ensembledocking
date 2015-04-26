package br.com.jonasflesch.ensembledocking.moleculardynamics;

import br.com.jonasflesch.ensembledocking.core.GromacsCaller;
import br.com.jonasflesch.ensembledocking.core.PdbCutter;
import br.com.jonasflesch.ensembledocking.model.Mdp;
import br.com.jonasflesch.ensembledocking.serialization.MdpSerializer;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;

/**
 * Created by jonasflesch on 4/26/15.
 */
@Service
public class MolecularDynamicsService {

	@Inject
	private PdbCutter pdbCutter;

	@Inject
	private GromacsCaller gromacsCaller;

	@Inject
	private MdpSerializer mdpSerializer;

	public String[] molecularDynamics(final File pdbFile) throws IOException, InterruptedException {

		String pdbFilePath = pdbFile.getPath();

		String freePdbFile = pdbFilePath.substring(0, pdbFilePath.lastIndexOf(File.separator) + 1) + "free_" + pdbFilePath.substring(pdbFilePath.lastIndexOf(File.separator) + 1);

		pdbCutter.cutUntilFirstTerm(pdbFile.getPath(), freePdbFile);

		gromacsCaller.pdb2gmx(freePdbFile);
		String groFilePath = freePdbFile.substring(0, freePdbFile.lastIndexOf('.')) + ".gro";
		String topFilePath = freePdbFile.substring(0, freePdbFile.lastIndexOf('.')) + ".top";

		gromacsCaller.editconf(groFilePath);
		String groEdtFilePath = groFilePath.substring(0, groFilePath.lastIndexOf('.')) + "_edt.gro";

		gromacsCaller.genbox(groEdtFilePath, topFilePath);
		String boxFilePath = topFilePath.substring(0, topFilePath.lastIndexOf('.')) + "_box.gro";

		String emMdpPath = pdbFilePath.substring(0, pdbFilePath.lastIndexOf(File.separator) +1) + "em.mdp";

		Mdp emMdp = new Mdp();
		emMdp.setToEm();

		mdpSerializer.serialize(emMdp, new FileOutputStream(emMdpPath));

		String tprFileMinimizationEnergy = pdbFilePath.substring(0, pdbFilePath.lastIndexOf(File.separator) +1) + "em.tpr";

		gromacsCaller.gromppMinimizationEnergy(emMdpPath, boxFilePath, tprFileMinimizationEnergy, topFilePath);

		String trrFileMinimizationEnergy = pdbFilePath.substring(0, pdbFilePath.lastIndexOf(File.separator) +1) + "em.trr";
		String groFileMinimizationEnergy = pdbFilePath.substring(0, pdbFilePath.lastIndexOf(File.separator) +1) + "em.gro";
		gromacsCaller.mdrunMinimizationEnergy(tprFileMinimizationEnergy, trrFileMinimizationEnergy, groFileMinimizationEnergy);

		String prMdpPath = pdbFilePath.substring(0, pdbFilePath.lastIndexOf(File.separator) +1) + "em.mdp";

		Mdp prMdp = new Mdp();
		prMdp.setToEm();

		mdpSerializer.serialize(prMdp, new FileOutputStream(prMdpPath));

		String tprFileMdWithPositionRestraint = pdbFilePath.substring(0, pdbFilePath.lastIndexOf(File.separator) +1) + "pr.tpr";
		gromacsCaller.gromppMdWithPositionRestraint(prMdpPath, tprFileMdWithPositionRestraint, groFileMinimizationEnergy, topFilePath);

		String trrFileMdWithPositionRestraint = pdbFilePath.substring(0, pdbFilePath.lastIndexOf(File.separator) +1) + "pr.trr";
		String groFileMdWithPositionRestraint = pdbFilePath.substring(0, pdbFilePath.lastIndexOf(File.separator) +1) + "pr.gro";
		String edrFileMdWithPositionRestraint = pdbFilePath.substring(0, pdbFilePath.lastIndexOf(File.separator) +1) + "pr.edr";
		gromacsCaller.mdrunMdWithPositionRestraint(tprFileMdWithPositionRestraint, edrFileMdWithPositionRestraint, trrFileMdWithPositionRestraint, groFileMdWithPositionRestraint);

		String ps100MdpPath = pdbFilePath.substring(0, pdbFilePath.lastIndexOf(File.separator) +1) + "100ps.mdp";

		Mdp ps100Mdp = new Mdp();
		ps100Mdp.setTo100ps();

		mdpSerializer.serialize(ps100Mdp, new FileOutputStream(ps100MdpPath));

		String tprFileMdWithoutPositionRestraint = pdbFilePath.substring(0, pdbFilePath.lastIndexOf(File.separator) +1) + "100ps.tpr";
		gromacsCaller.gromppMdWithoutPositionRestraint(ps100MdpPath, tprFileMdWithoutPositionRestraint, groFileMdWithPositionRestraint, topFilePath);

		String trrFileMdWithoutPositionRestraint = pdbFilePath.substring(0, pdbFilePath.lastIndexOf(File.separator) +1) + "100ps.trr";
		String groFileMdWithoutPositionRestraint = pdbFilePath.substring(0, pdbFilePath.lastIndexOf(File.separator) +1) + "100ps.gro";
		String edrFileMdWithoutPositionRestraint = pdbFilePath.substring(0, pdbFilePath.lastIndexOf(File.separator) +1) + "100ps.edr";
		gromacsCaller.mdrunMdWithoutPositionRestraint(tprFileMdWithoutPositionRestraint, edrFileMdWithoutPositionRestraint, trrFileMdWithoutPositionRestraint, groFileMdWithoutPositionRestraint);

		String trajectoryPdfFile = pdbFilePath.substring(0, pdbFilePath.lastIndexOf(File.separator) +1) + "trajectory.pdb";

		gromacsCaller.trjconv(trrFileMdWithoutPositionRestraint, tprFileMdWithoutPositionRestraint, trajectoryPdfFile);

		byte[] trajectoryBytes = Files.readAllBytes(Paths.get(trajectoryPdfFile));
		String trajectoryString = new String(trajectoryBytes, "UTF-8");
		return trajectoryString.split("(?<=ENDMDL\n)");
	}

}
