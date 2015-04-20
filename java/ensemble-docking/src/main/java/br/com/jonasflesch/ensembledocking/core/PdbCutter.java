package br.com.jonasflesch.ensembledocking.core;

import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by jonasflesch on 4/20/15.
 */
@Component
public class PdbCutter {

	@Inject
	private CommandLineCaller commandLineCaller;

	public void cutUntilFirstTerm(final String inputPdbFile, final String outputPdbFile) throws IOException, InterruptedException {
		String output = commandLineCaller.call("/bin/sh", "-c", "sed '/^TER/q' " + inputPdbFile);
		Files.write(Paths.get(outputPdbFile), output.getBytes());
	}

}
