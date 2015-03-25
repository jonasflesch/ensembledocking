package br.com.jonasflesch.ensembledocking.core;

import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.IOException;

/**
 * Chama o aplicativo Pymol para gerar imagem das docagens
 *
 * Created by jonasflesch on 3/23/15.
 */
@Component
public class PymolCaller {

	@Inject
	private CommandLineCaller commandLineCaller;

	public void pymol(final String pmlFile) throws IOException, InterruptedException {
		commandLineCaller.call("pymol", pmlFile);
	}

}
