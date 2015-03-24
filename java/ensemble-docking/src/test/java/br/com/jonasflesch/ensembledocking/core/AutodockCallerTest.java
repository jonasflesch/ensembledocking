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
		autodockCaller.autodock("/home/jonasflesch/bio/work/ind.dpf");

		assertTrue("Dlg file should be created", new File("/home/jonasflesch/bio/work/ind.dlg").exists());
	}

}
