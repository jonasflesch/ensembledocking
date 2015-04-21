package br.com.jonasflesch.ensembledocking.core;

import org.junit.Test;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Created by jonasflesch on 4/20/15.
 */
public class GromacsCallerTest  extends AbstractTest {

	@Inject
	private GromacsCaller gromacsCaller;

	@Test
	public void pdb2gmx() throws IOException, InterruptedException {
		copyFile("free.pdb");

		String inputFilePath = directory.getPath() + File.separator + "free.pdb";

		gromacsCaller.pdb2gmx(inputFilePath);

		assertTrue("Coordinates file should be created", new File(directory.getPath() + File.separator + "free.gro").exists());
		assertTrue("Topology file should be created", new File(directory.getPath() + File.separator + "free.top").exists());
	}

	@Test
	public void editconf() throws IOException, InterruptedException {
		copyFile("free.gro");

		String inputFilePath = directory.getPath() + File.separator + "free.gro";

		gromacsCaller.editconf(inputFilePath);

		assertTrue("New gro file should be created", new File(directory.getPath() + File.separator + "free_edt.gro").exists());
	}

}
