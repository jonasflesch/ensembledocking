package br.com.jonasflesch.ensembledocking.core;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Arrays;

/**
 * Created by jonasflesch on 3/22/15.
 */
@Component
public class CommandLineCaller {

	private static final Logger LOGGER = Logger.getLogger(CommandLineCaller.class);

	public void call(String... commands) throws IOException, InterruptedException {
		callWithDirectory(null, commands);
	}

	public void callWithDirectory(String directory, String... commands) throws IOException, InterruptedException {

		LOGGER.info("Command: " + Arrays.toString(commands));

		ProcessBuilder builder = new ProcessBuilder(commands);
		if(directory != null){
			builder.directory(new File(directory));
		}

		final Process process = builder.start();

		final StringWriter writerInfo = new StringWriter();
		final StringWriter writerError = new StringWriter();

		new Thread(new Runnable() {
			public void run() {
				try {
					IOUtils.copy(process.getInputStream(), writerInfo);
					IOUtils.copy(process.getErrorStream(), writerError);
				} catch (IOException exception){
					LOGGER.error("Erro ao copiar processo", exception);
				}

			}
		}).start();

		int returnCode = process.waitFor();

		LOGGER.info(writerInfo.toString());
		LOGGER.error(writerError.toString());
		if(returnCode > 0){
			throw new RuntimeException("Ocorreu um erro ao chamar o" + commands[0]);
		}

	}

}
