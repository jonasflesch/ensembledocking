package br.com.jonasflesch.ensembledocking.core;

import org.junit.Test;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Created by jonasflesch on 3/22/15.
 */
public class ResultExtractorTest extends AbstractTest {

	@Inject
	private ResultExtractor resultExtractor;

	@Test
	public void convertDlgToPdbqt() throws IOException, InterruptedException {
		resultExtractor.convertDlgToPdbqt("/home/jonasflesch/bio/work/ind.dlg");

		assertTrue("Pdbqt file should be created", new File("/home/jonasflesch/bio/work/ind_docked.pdbqt").exists());
	}

	@Test
	public void convertPdbqtToPdb() throws IOException, InterruptedException {
		resultExtractor.convertPdbqtToPdb("/home/jonasflesch/bio/work/ind_docked.pdbqt");

		assertTrue("Pdb file should be created", new File("/home/jonasflesch/bio/work/ind_docked.pdb").exists());
	}

}
