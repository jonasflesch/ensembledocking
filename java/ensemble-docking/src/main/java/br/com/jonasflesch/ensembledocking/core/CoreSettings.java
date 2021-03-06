package br.com.jonasflesch.ensembledocking.core;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configurćão alteraćão
 *
 * Created by jonasflesch on 3/22/15.
 */
@Component
@ConfigurationProperties(prefix="app.core")
@Getter @Setter
public class CoreSettings {

	private String mglToolsDirectory;

}
