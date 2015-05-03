package br.com.jonasflesch.ensembledocking.view;

/**
 * Created by jonasflesch on 3/24/15.
 */

import br.com.jonasflesch.ensembledocking.core.DockSettings;
import br.com.jonasflesch.ensembledocking.docking.DockService;
import br.com.jonasflesch.ensembledocking.model.DockParametersDto;
import br.com.jonasflesch.ensembledocking.model.DockingResultDto;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@Inject
	private DockSettings dockSettings;

	@RequestMapping(value="/", method= RequestMethod.GET)
	public String provideUploadInfo() {
		return "upload";
	}

	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String handleFileUpload(@RequestParam("fileLigand") MultipartFile fileLigand,
								   @RequestParam("fileReceptor") MultipartFile fileReceptor,
								   @RequestParam("molecularDynamicsSteps") Integer molecularDynamicsSteps,
								   @RequestParam("molecularDynamicsOut") Integer molecularDynamicsOut,
								   Model model){
		if (!fileLigand.isEmpty() && !fileReceptor.isEmpty()) {
			try {
				Path tempDir = Files.createTempDirectory("dock");

				File outputLigand = saveFileToDirectory(fileLigand, tempDir);

				File outputReceptor = saveFileToDirectory(fileReceptor, tempDir);

				DockParametersDto dockParametersDto = new DockParametersDto();
				dockParametersDto.setPdbFileLigand(outputLigand);
				dockParametersDto.setPdbFileReceptor(outputReceptor);

				dockParametersDto.setMolecularDynamicsSteps(molecularDynamicsSteps);
				dockParametersDto.setMolecularDynamicsOut(molecularDynamicsOut);

				DockingResultDto dockingResultDto = dockService.dock(dockParametersDto);

				model.addAttribute("bestDockingImage", dockingResultDto.getBestDockingImage());
				model.addAttribute("freeEnergyOverTimeChart", dockingResultDto.getFreeEnergyOverTimeChart());

				return "result";
			} catch (Exception e) {
				return "You failed to upload => " + e.getMessage();
			}
		} else {
			return "You failed to upload because the file was empty.";
		}
	}

	private File saveFileToDirectory(MultipartFile fileLigand, Path tempDir) throws IOException {
		byte[] bytesLigand = fileLigand.getBytes();
		File outputLigand = new File(tempDir.toString() + File.separator + fileLigand.getName() + ".pdb");
		FileUtils.copyInputStreamToFile(new ByteArrayInputStream(bytesLigand), outputLigand);
		return outputLigand;
	}

	@RequestMapping(value = "/image/{imagePath}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
	public @ResponseBody byte[] image(@PathVariable String imagePath) throws IOException {
		File file = new File(dockSettings.getResultsDirectory() + File.separator + imagePath + ".png");
		return IOUtils.toByteArray(new FileInputStream(file));
	}

}