package br.com.jonasflesch.ensembledocking.core;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * Created by jonasflesch on 3/22/15.
 */
@Component
public class CommandLineCaller {

	private static final Logger LOGGER = Logger.getLogger(CommandLineCaller.class);

	public String call(String... commands) throws IOException, InterruptedException {
		return call(null, false, commands);
	}

	public String call(String directory, boolean syncOutput, String... commands) throws IOException, InterruptedException {

		LOGGER.info("Command: " + Arrays.toString(commands));

		ProcessBuilder builder = new ProcessBuilder(commands);
		if(directory != null){
			builder.directory(new File(directory));
		}

		final Process process = builder.start();

		final StringWriter writerInfo = new StringWriter();
		final StringWriter writerError = new StringWriter();

		if(syncOutput){
			IOUtils.copy(process.getInputStream(), writerInfo);
			IOUtils.copy(process.getErrorStream(), writerError);
		} else {
			new Thread(new Runnable() {
				public void run() {
					try {
						IOUtils.copy(process.getInputStream(), writerInfo);
					} catch (IOException exception){
						LOGGER.error("Erro ao copiar processo", exception);
					}

				}
			}).start();
			new Thread(new Runnable() {
				public void run() {
					try {
						IOUtils.copy(process.getErrorStream(), writerError);
					} catch (IOException exception){
						LOGGER.error("Erro ao copiar processo", exception);
					}

				}
			}).start();

		}

		int returnCode = process.waitFor();

		if(!writerInfo.toString().isEmpty()){
			LOGGER.info(writerInfo.toString());
		}
		if(!writerError.toString().isEmpty()){
			LOGGER.error(writerError.toString());
		}

		if(returnCode > 0){
			throw new RuntimeException("Ocorreu um erro ao chamar " + commands[0]);
		}

		return writerInfo.toString();
	}

}
