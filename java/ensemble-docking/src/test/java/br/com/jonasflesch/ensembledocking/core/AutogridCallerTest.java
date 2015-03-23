package br.com.jonasflesch.ensembledocking.core;

import org.junit.Test;

import javax.inject.Inject;
import java.io.IOException;

/**
 * Created by jonasflesch on 3/22/15.
 */
public class AutogridCallerTest extends AbstractTest {

	@Inject
	private AutogridCaller autogridCaller;

	@Test
	public void autogrid() throws IOException, InterruptedException {
		autogridCaller.autogrid("/home/jonasflesch/bio/work/hsg1.gpf");
	}

}
