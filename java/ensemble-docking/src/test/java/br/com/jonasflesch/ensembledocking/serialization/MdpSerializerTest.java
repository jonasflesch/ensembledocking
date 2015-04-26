package br.com.jonasflesch.ensembledocking.serialization;

import br.com.jonasflesch.ensembledocking.core.AbstractTest;
import br.com.jonasflesch.ensembledocking.model.Mdp;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by jonasflesch on 4/26/15.
 */
public class MdpSerializerTest extends AbstractTest {

	@Inject
	private MdpSerializer mdpSerializer;

	@Test
	public void serialize() throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		mdpSerializer.serialize(new Mdp(), outputStream);

		String output = outputStream.toString("UTF-8");
		Files.write(Paths.get("/home/jonasflesch/100ps_jonas.mdp"), output.getBytes("UTF-8"));
	}

}
