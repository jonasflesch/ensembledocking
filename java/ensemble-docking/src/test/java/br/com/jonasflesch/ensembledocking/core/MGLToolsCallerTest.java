package br.com.jonasflesch.ensembledocking.core;

import br.com.jonasflesch.ensembledocking.Application;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;

/**
 * Created by jonasflesch on 3/22/15.
 */

public class MGLToolsCallerTest extends AbstractTest {

	@Autowired
	private MGLToolsCaller mglToolsCaller;

	@Test
	public void prepareLigand() throws IOException, InterruptedException {
		copyFile("ind.pdb");

		mglToolsCaller.prepareLigand(directory.getPath() + File.separator + "ind.pdb");

		assertTrue("File pdbtqt should be created", new File(directory.getPath() + File.separator + "ind.pdbqt").exists());
	}

	@Test
	public void prepareReceptor() throws IOException, InterruptedException {
		copyFile("hsg1.pdb");

		mglToolsCaller.prepareReceptor(directory.getPath() + File.separator + "hsg1.pdb");

		assertTrue("File pdbtqt should be created", new File(directory.getPath() + File.separator + "hsg1.pdbqt").exists());
	}

	@Test
	public void prepareGrid() throws IOException, InterruptedException {
		copyFile("ind.pdbqt");
		copyFile("hsg1.pdbqt");

		mglToolsCaller.prepareGrid(directory.getPath() + File.separator + "ind.pdbqt", directory.getPath() + File.separator + "hsg1.pdbqt");

		assertTrue("Gpf file should be created", new File(directory.getPath() + File.separator + "hsg1.gpf").exists());
	}

	@Test
	public void prepareDockingParameter() throws IOException, InterruptedException {
		copyFile("ind.pdbqt");
		copyFile("hsg1.pdbqt");

		mglToolsCaller.prepareDockingParameter(directory.getPath() + File.separator + "ind.pdbqt", directory.getPath() + File.separator + "hsg1.pdbqt");

		assertTrue("Dpf file should be created", new File(directory.getPath() + File.separator + "ind.dpf").exists());
	}

}
