package br.com.jonasflesch.ensembledocking.prepare;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by jonasflesch on 3/22/15.
 */
@Component
@ConfigurationProperties(prefix="app.prepare")
@Getter @Setter
public class PrepareSettings {
    private String directory;
}
