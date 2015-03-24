package br.com.jonasflesch.ensembledocking.core;

import org.junit.Test;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Created by jonasflesch on 3/22/15.
 */
public class AutodockCallerTest extends AbstractTest {

	@Inject
	private AutodockCaller autodockCaller;

	@Test
	public void autodock() throws IOException, InterruptedException {
		copyFile("hsg1.A.map");
		copyFile("hsg1.C.map");
		copyFile("hsg1.d.map");
		copyFile("hsg1.e.map");
		copyFile("hsg1.HD.map");
		copyFile("hsg1.maps.fld");
		copyFile("hsg1.maps.xyz");
		copyFile("hsg1.N.map");
		copyFile("hsg1.NA.map");
		copyFile("hsg1.OA.map");
		copyFile("ind.pdbqt");

		copyFile("ind.dpf");

		autodockCaller.autodock(directory.getPath() + File.separator + "ind.dpf");

		assertTrue("Dlg file should be created", new File(directory.getPath() + File.separator + "ind.dlg").exists());
	}

}
