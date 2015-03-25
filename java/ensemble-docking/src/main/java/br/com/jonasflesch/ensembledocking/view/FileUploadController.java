package br.com.jonasflesch.ensembledocking.view;

/**
 * Created by jonasflesch on 3/24/15.
 */

import br.com.jonasflesch.ensembledocking.docking.DockService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
public class FileUploadController {

	@Inject
	private DockService dockService;

	@RequestMapping(value="/upload", method= RequestMethod.GET)
	public @ResponseBody String provideUploadInfo() {
		return "You can upload a file by posting to this same URL.";
	}

	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public @ResponseBody String handleFileUpload(@RequestParam("fileLigand") MultipartFile fileLigand,
												 @RequestParam("fileReceptor") MultipartFile fileReceptor){
		if (!fileLigand.isEmpty() && !fileReceptor.isEmpty()) {
			try {
				Path tempDir = Files.createTempDirectory("dock");

				byte[] bytesLigand = fileLigand.getBytes();
				File outputLigand = new File(tempDir.toString() + File.separator + fileLigand.getName() + ".pdb");
				FileUtils.copyInputStreamToFile(new ByteArrayInputStream(bytesLigand), outputLigand);

				byte[] bytesReceptor = fileReceptor.getBytes();
				File outputReceptor = new File(tempDir.toString() + File.separator + fileReceptor.getName() + ".pdb");
				FileUtils.copyInputStreamToFile(new ByteArrayInputStream(bytesReceptor), outputReceptor);

				//TODO refatorar para não enviar path completo
				String pngFile = dockService.dock(outputLigand, outputReceptor);

				return "<img src=\"http://localhost:8080/imagemdock/" + pngFile.replace(File.separator, "mussum") + "\"></img>";
			} catch (Exception e) {
				return "You failed to upload => " + e.getMessage();
			}
		} else {
			return "You failed to upload because the file was empty.";
		}
	}

	@RequestMapping(value = "/imagedock/{imagePath}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
	public @ResponseBody byte[] image(@PathVariable String imagePath) throws IOException {
		File file = new File(imagePath.replace("mussum", File.separator)+ ".png");
		return IOUtils.toByteArray(new FileInputStream(file));
	}

}