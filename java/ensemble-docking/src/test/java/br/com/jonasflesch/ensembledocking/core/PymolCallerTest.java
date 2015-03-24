package br.com.jonasflesch.ensembledocking.core;

import org.junit.Test;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Created by jonasflesch on 3/22/15.
 */
public class PymolCallerTest extends AbstractTest {

	@Inject
	private PymolCaller pymolCaller;

	@Test
	public void pymol() throws IOException, InterruptedException {
		pymolCaller.pymol("/home/jonasflesch/pymol/export_dock.pml");

		assertTrue("Png file should be created", new File("/home/jonasflesch/bio/work/docking.png").exists());
	}

}
