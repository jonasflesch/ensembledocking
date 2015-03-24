package br.com.jonasflesch.ensembledocking.core;

import br.com.jonasflesch.ensembledocking.Application;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Created by jonasflesch on 3/22/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class AbstractTest {

	protected File directory;

	@Before
	public void createTempDir() throws IOException {
		directory = Files.createTempDirectory("trolol").toFile();
	}

	@After
	public void deleteTempDir() throws IOException {
		FileUtils.deleteDirectory(directory);
	}

	protected void copyFile(final String resourceFileToCopy) throws IOException{
		File resource = new File(getClass().getClassLoader().getResource("fixtures/"+resourceFileToCopy).getFile());
		Files.copy(resource.toPath(), new File(directory.getPath() + File.separator + resourceFileToCopy).toPath());
	}

}
