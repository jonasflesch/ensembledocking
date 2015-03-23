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

	public void prepareLigand(final String pdbFileLigand) throws IOException, InterruptedException {
		commandLineCaller.call(pythonsh(), prepareLigand4(), " -l " + pdbFileLigand, " -o " + pdbFileLigand.substring(0, pdbFileLigand.lastIndexOf('.')) + ".pdbqt");
	}

	public void prepareReceptor(final String pdbFileReceptor) throws IOException, InterruptedException {
		commandLineCaller.call(pythonsh(), prepareReceptor4(), "-r " + pdbFileReceptor, "-A hydrogens", "-o " + pdbFileReceptor.substring(0, pdbFileReceptor.lastIndexOf('.')) + ".pdbqt");
	}

	public void prepareGrid(final String pdbqtFileLigand, final String pdbqtFileReceptor) throws IOException, InterruptedException {
		commandLineCaller.call(pythonsh(), prepareGpf4(), "-l " + pdbqtFileLigand, "-r " + pdbqtFileReceptor, "-o " + pdbqtFileReceptor.substring(0, pdbqtFileReceptor.lastIndexOf('.')) + ".gpf", "–p npts='60,60,66'");
	}

	public void prepareDockingParameter(final String pdbqtFileLigand, final String pdbqtFileReceptor) throws IOException, InterruptedException {
		commandLineCaller.call(pythonsh(), prepareDpf4(), "-l " + pdbqtFileLigand, "-r " + pdbqtFileReceptor, "-o " + pdbqtFileLigand.substring(0, pdbqtFileLigand.lastIndexOf('.')) + ".dpf", "–p ga_num_evals=250000");
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
