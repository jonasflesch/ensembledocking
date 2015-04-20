package br.com.jonasflesch.ensembledocking.core;

import org.junit.Test;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by jonasflesch on 4/20/15.
 */
public class PdbCutterTest extends AbstractTest {

	@Inject
	private PdbCutter pdbCutter;

	@Test
	public void cutUntilFirstTerm() throws IOException, InterruptedException {
		copyFile("1OXR.pdb");

		String outputPath = directory.getPath() + File.separator + "cutted.pdb";

		pdbCutter.cutUntilFirstTerm(directory.getPath() + File.separator + "1OXR.pdb", outputPath);

		assertTrue("Pdb cutted file should be created", new File(outputPath).exists());
		assertThat("File should have 1334 lines", Files.newBufferedReader(Paths.get(outputPath)).lines().count(), equalTo(1334L));
	}

}
