package br.com.jonasflesch.ensembledocking.core;

import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.IOException;

/**
 * Classe que chama o MGL Tools
 *
 * Created by jonasflesch on 3/22/15.
 */
@Component
public class MGLToolsCaller {

	@Inject
	private CoreSettings coreSettings;

	@Inject
	private CommandLineCaller commandLineCaller;

	public String prepareLigand(final String pdbFileLigand) throws IOException, InterruptedException {
		String pdbqtFilePath = pdbFileLigand.substring(0, pdbFileLigand.lastIndexOf('.')) + ".pdbqt";
		commandLineCaller.call(pythonsh(), prepareLigand4(), " -l " + pdbFileLigand, " -o " + pdbqtFilePath);
		return pdbqtFilePath;
	}

	public String prepareReceptor(final String pdbFileReceptor) throws IOException, InterruptedException {
		String pdbqtFilePath = pdbFileReceptor.substring(0, pdbFileReceptor.lastIndexOf('.')) + ".pdbqt";
		commandLineCaller.call(pythonsh(), prepareReceptor4(), "-r " + pdbFileReceptor, "-A hydrogens", "-o " + pdbqtFilePath);
		return pdbqtFilePath;
	}

	public String prepareGrid(final String pdbqtFileLigand, final String pdbqtFileReceptor) throws IOException, InterruptedException {
		String gpfFilePath = pdbqtFileReceptor.substring(0, pdbqtFileReceptor.lastIndexOf('.')) + ".gpf";
		commandLineCaller.call(pythonsh(), prepareGpf4(), "-l " + pdbqtFileLigand, "-r " + pdbqtFileReceptor, "-o " + gpfFilePath, "–p npts='60,60,66'");
		return gpfFilePath;
	}

	public String prepareDockingParameter(final String pdbqtFileLigand, final String pdbqtFileReceptor) throws IOException, InterruptedException {
		String dpfFilePath = pdbqtFileLigand.substring(0, pdbqtFileLigand.lastIndexOf('.')) + ".dpf";
		commandLineCaller.call(pythonsh(), prepareDpf4(), "-l " + pdbqtFileLigand, "-r " + pdbqtFileReceptor, "-o " + dpfFilePath, "–p ga_num_evals=250000");
		return dpfFilePath;
	}

	private String pythonsh() {
		return coreSettings.getMglToolsDirectory() + "/bin/pythonsh";
	}

	private String prepareLigand4() {
		return coreSettings.getMglToolsDirectory() + "/MGLToolsPckgs/AutoDockTools/Utilities24/prepare_ligand4.py";
	}

	private String prepareReceptor4() {
		return coreSettings.getMglToolsDirectory() + "/MGLToolsPckgs/AutoDockTools/Utilities24/prepare_receptor4.py";
	}

	private String prepareGpf4() {
		return coreSettings.getMglToolsDirectory() + "/MGLToolsPckgs/AutoDockTools/Utilities24/prepare_gpf4.py";
	}

	private String prepareDpf4() {
		return coreSettings.getMglToolsDirectory() + "/MGLToolsPckgs/AutoDockTools/Utilities24/prepare_dpf4.py";
	}

}
