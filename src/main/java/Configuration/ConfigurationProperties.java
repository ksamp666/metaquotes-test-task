package Configuration;

import java.util.Properties;

import static Utils.Log.getLogger;

public class ConfigurationProperties extends Properties {
    private final static String PROPERTIES_FILE_PATH = "/main.properties";

    private static ConfigurationProperties configurationProperties;

    public static ConfigurationProperties getProperties() {
        if (configurationProperties == null) {
            configurationProperties = new ConfigurationProperties();
            try {
                configurationProperties.load(ConfigurationProperties.class.getResourceAsStream(PROPERTIES_FILE_PATH));
            } catch (Exception e) {
                getLogger().error("Failed load properties from file: " + PROPERTIES_FILE_PATH);
                throw new RuntimeException(e);
            }
        }
        return configurationProperties;
    }

    public String getUserAgent() {
        return getProperty("USER_AGENT");
    }

    public String getLstToBaseUrl() {
        return getProperty("LST_TO_BASE_URL");
    }
}
