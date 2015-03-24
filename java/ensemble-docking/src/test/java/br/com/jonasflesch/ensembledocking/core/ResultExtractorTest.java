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
		copyFile("ind.dlg");

		resultExtractor.convertDlgToPdbqt(directory.getPath() + File.separator + "ind.dlg");

		assertTrue("Pdbqt file should be created", new File(directory.getPath() + File.separator + "ind_docked.pdbqt").exists());
	}

	@Test
	public void convertPdbqtToPdb() throws IOException, InterruptedException {
		copyFile("ind_docked.pdbqt");

		resultExtractor.convertPdbqtToPdb(directory.getPath() + File.separator + "ind_docked.pdbqt");

		assertTrue("Pdb file should be created", new File(directory.getPath() + File.separator + "ind_docked.pdb").exists());
		assertTrue("Pdb file should bot be empty", new File(directory.getPath() + File.separator + "ind_docked.pdb").length() > 0);
	}

}
