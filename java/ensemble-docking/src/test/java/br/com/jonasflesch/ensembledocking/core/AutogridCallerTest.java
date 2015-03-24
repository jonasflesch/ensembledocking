package br.com.jonasflesch.ensembledocking.core;

import org.junit.Test;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Created by jonasflesch on 3/22/15.
 */
public class AutogridCallerTest extends AbstractTest {

	@Inject
	private AutogridCaller autogridCaller;

	@Test
	public void autogrid() throws IOException, InterruptedException {
		copyFile("hsg1.pdbqt");
		copyFile("hsg1.gpf");
		autogridCaller.autogrid(directory.getPath() + File.separator + "hsg1.gpf");

		assertTrue("Maps file should be created", new File(directory.getPath() + File.separator + "hsg1.maps.fld").exists());
	}

}
