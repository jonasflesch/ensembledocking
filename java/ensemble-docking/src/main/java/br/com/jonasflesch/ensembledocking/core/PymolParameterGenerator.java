package br.com.jonasflesch.ensembledocking.core;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * Gera o arquivo .pml que servirá como parâmetro para o Pymol
 *
 * Created by jonasflesch on 3/24/15.
 */
@Component
public class PymolParameterGenerator {

	public String generatePml(String directory, String pngFile, String pdbFileLigand, String pdbFileReceptor) throws IOException {
		StringBuffer stringBuffer = new StringBuffer();

		stringBuffer.append("load ").append(pdbFileLigand).append(", ligand\n");
		stringBuffer.append("load ").append(pdbFileReceptor).append(", receptor\n");

		stringBuffer.append("hide lines\n");
		stringBuffer.append("show lines, receptor\n");
		stringBuffer.append("show sticks, ligand\n");
				stringBuffer.append("png ").append(pngFile).append(", dpi=300").append('\n');
		stringBuffer.append("log_close\n");
		stringBuffer.append("quit");

		File file = new File(directory + File.separator + "export_dock.pml");
		FileUtils.writeStringToFile(file, stringBuffer.toString());

		return file.getPath();
	}
}
