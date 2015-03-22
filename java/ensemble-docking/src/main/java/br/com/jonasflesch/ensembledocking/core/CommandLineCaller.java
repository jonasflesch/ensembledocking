package br.com.jonasflesch.ensembledocking.core;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by jonasflesch on 3/22/15.
 */
@Component
public class CommandLineCaller {

	private static final Logger LOGGER = Logger.getLogger(CommandLineCaller.class);

	public void call(String... commands) throws IOException, InterruptedException {

		LOGGER.info("Command: " + Arrays.toString(commands));

		ProcessBuilder builder = new ProcessBuilder(commands);

		Process process = builder.start();

		process.waitFor();

		BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line;

		while ((line = input.readLine()) != null) {
			LOGGER.info(line);
		}

		BufferedReader inputError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
		String lineError;

		boolean error = false;
		while ((lineError = inputError.readLine()) != null) {
			LOGGER.error(lineError);
			error = true;
		}
		if(error){
			throw new RuntimeException("Ocorreu um erro ao chamar o prepareLigand");
		}
	}

}
