package br.com.jonasflesch.ensembledocking.core;

import br.com.jonasflesch.ensembledocking.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * Created by jonasflesch on 3/22/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class MGLToolsCallerTest {

    @Autowired
    private MGLToolsCaller mglToolsCaller;

    @Test
    public void prepareLigand4() throws IOException, InterruptedException {
        mglToolsCaller.prepareLigand("lol.pdb");
    }

}
