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

	public void prepareLigand(final String pdbFile) throws IOException, InterruptedException {
		commandLineCaller.call(pythonsh(), prepareLigand4(), " -l " + pdbFile, " -o " + pdbFile.substring(0, pdbFile.lastIndexOf('.')) + "pdbqt");
	}

	private String pythonsh() {
		return coreSettings.getMglToolsDirectory() + "/bin/pythonsh";
	}

	private String prepareLigand4() {
		return coreSettings.getMglToolsDirectory() + "/MGLToolsPckgs/AutoDockTools/Utilities24/prepare_ligand4.py";
	}

}
