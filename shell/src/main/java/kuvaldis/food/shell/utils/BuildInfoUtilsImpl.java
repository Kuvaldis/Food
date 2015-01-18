package kuvaldis.food.shell.utils;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class BuildInfoUtilsImpl implements BuildInfoUtils {

    private Properties buildInfoProperties;

    @PostConstruct
    public void init() throws IOException {
        final Properties buildProperties = new Properties();
        InputStream buildInfoStream = ClassLoader.getSystemResourceAsStream("buildinfo.properties");
        if (buildInfoStream != null) {
            buildProperties.load(buildInfoStream);
        }
        buildInfoProperties = buildProperties;
    }

    @Override
    public String getBuildVersion() {
        return buildInfoProperties.getProperty("buildVersion");
    }
}
