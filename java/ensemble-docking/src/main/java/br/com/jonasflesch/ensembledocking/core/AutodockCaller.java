package br.com.jonasflesch.ensembledocking.core;

import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.IOException;

/**
 *
 * Classe que chama o Autodock
 *
 * Created by jonasflesch on 3/22/15.
 */
@Component
public class AutodockCaller {

	@Inject
	private CommandLineCaller commandLineCaller;

	public void autodock(final String autodockParameterFile) throws IOException, InterruptedException {
		commandLineCaller.call(autodockParameterFile.substring(0, autodockParameterFile.lastIndexOf('/')), false, "autodock4", "-p", autodockParameterFile);
	}

}
