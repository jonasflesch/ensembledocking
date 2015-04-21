package br.com.jonasflesch.ensembledocking.core;

import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.IOException;

/**
 * Created by jonasflesch on 4/20/15.
 */
@Component
public class GromacsCaller {

	@Inject
	private CommandLineCaller commandLineCaller;

	public void pdb2gmx(final String pdbFile) throws IOException, InterruptedException {
		String fileName = pdbFile.substring(0, pdbFile.lastIndexOf('.'));

		commandLineCaller.call(pdbFile.substring(0, pdbFile.lastIndexOf('/')), true, "pdb2gmx", "-f", pdbFile, "-o", fileName + ".gro", "-p", fileName + ".top", "-ff", "gromos53a6", "-water", "spc");
	}

	public void editconf(final String groFile) throws IOException, InterruptedException {
		String fileName = groFile.substring(0, groFile.lastIndexOf('.'));

		commandLineCaller.call(fileName.substring(0, fileName.lastIndexOf('/')), true, "editconf", "-bt", "cubic", "-f", groFile, "-o", fileName + "_edt.gro", "-c", "-d", "1.0");
	}

	public void genbox(final String groFile, final String topologyFile) throws IOException, InterruptedException {
		String fileName = topologyFile.substring(0, topologyFile.lastIndexOf('.'));

		commandLineCaller.call(fileName.substring(0, fileName.lastIndexOf('/')), true, "genbox", "-cp", groFile, "-cs", "spc216.gro", "-o", fileName + "_box.gro", "-p", topologyFile);
	}

}
