package br.com.jonasflesch.ensembledocking.core;

import br.com.jonasflesch.ensembledocking.Application;
import static org.junit.Assert.assertTrue;
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
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class MGLToolsCallerTest {

	@Autowired
	private MGLToolsCaller mglToolsCaller;

	@Test
	public void prepareLigand() throws IOException, InterruptedException {
		mglToolsCaller.prepareLigand("/home/jonasflesch/bio/work/ind.pdb");

		assertTrue("File pdbtqt should be created", new File("/home/jonasflesch/bio/work/ind.pdbqt").exists());
	}

	@Test
	public void prepareReceptor() throws IOException, InterruptedException {
		mglToolsCaller.prepareReceptor("/home/jonasflesch/bio/work/hsg1.pdb");

		assertTrue("File pdbtqt should be created", new File("/home/jonasflesch/bio/work/hsg1.pdbqt").exists());
	}

	@Test
	public void prepareGrid() throws IOException, InterruptedException {
		mglToolsCaller.prepareGrid("/home/jonasflesch/bio/work/ind.pdbqt", "/home/jonasflesch/bio/work/hsg1.pdbqt");

		assertTrue("Gpf file should be created", new File("/home/jonasflesch/bio/work/hsg1.gpf").exists());
	}

}
