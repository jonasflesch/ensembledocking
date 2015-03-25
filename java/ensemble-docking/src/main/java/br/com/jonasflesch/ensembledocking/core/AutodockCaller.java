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

	public String autodock(final String dpfFile) throws IOException, InterruptedException {
		commandLineCaller.call(dpfFile.substring(0, dpfFile.lastIndexOf('/')), false, "autodock4", "-p", dpfFile);
		return dpfFile.substring(0, dpfFile.lastIndexOf('.'))+".dlg";
	}

}
