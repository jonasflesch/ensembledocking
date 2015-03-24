package br.com.jonasflesch.ensembledocking.core;

import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *
 * Converte o resultado do Autodock em DLG para um pdb
 *
 * Created by jonasflesch on 3/23/15.
 */
@Component
public class ResultExtractor {

	@Inject
	private CommandLineCaller commandLineCaller;

	public void convertDlgToPdbqt(final String dlgFile) throws IOException, InterruptedException {
		String output = commandLineCaller.call("/bin/sh", "-c", "grep '^DOCKED' " + dlgFile + " | cut -c9- ");
		Files.write(Paths.get(dlgFile.substring(0, dlgFile.lastIndexOf('.'))+ "_docked.pdbqt"), output.getBytes());
	}

	public void convertPdbqtToPdb(final String pdbqtFile) throws IOException, InterruptedException {
		String output = commandLineCaller.call(null, true, "cut", "-c-66", pdbqtFile);
		Files.write(Paths.get(pdbqtFile.substring(0, pdbqtFile.lastIndexOf('.'))+ ".pdb"), output.getBytes(Charset.forName("UTF-8")));
	}

}
