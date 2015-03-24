package br.com.jonasflesch.ensembledocking.core;

import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.IOException;

/**
 * Created by jonasflesch on 3/22/15.
 */
@Component
public class AutogridCaller {

	@Inject
	private CommandLineCaller commandLineCaller;

	public void autogrid(final String gpfFile) throws IOException, InterruptedException {
		commandLineCaller.call(gpfFile.substring(0, gpfFile.lastIndexOf('/')), true, "autogrid4", "-p", gpfFile);
	}
}
