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

	public String convertDlgToPdbqt(final String dlgFile) throws IOException, InterruptedException {
		String output = commandLineCaller.call("/bin/sh", "-c", "grep '^DOCKED' " + dlgFile + " | cut -c9- ");
		String pdbqtOutputFile = dlgFile.substring(0, dlgFile.lastIndexOf('.'))+ "_docked.pdbqt";
		Files.write(Paths.get(pdbqtOutputFile), output.getBytes());
		return pdbqtOutputFile;
	}

	public String convertPdbqtToPdb(final String pdbqtFile) throws IOException, InterruptedException {
		String output = commandLineCaller.call(null, true, "cut", "-c-66", pdbqtFile);

		String pdbFile = pdbqtFile.substring(0, pdbqtFile.lastIndexOf('.'))+ ".pdb";
		Files.write(Paths.get(pdbFile), output.getBytes(Charset.forName("UTF-8")));
		return pdbFile;
	}

}
