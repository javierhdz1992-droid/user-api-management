package api.base;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApiConfig {

    private static final Properties properties = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream(
                    "src/test/resources/config.properties");
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException("Error loading config.properties", e);
        }
    }

    public static String getBaseUrl(){
        String env = System.getProperty("env", "DEV");

        switch (env.toUpperCase()) {
            case "PROD":
                return properties.getProperty("prod.base.url");
            case "DEV":
            default:
                return properties.getProperty("dev.base.url");
        }
    }

    public static String getAuthToken(){
        return properties.getProperty("auth.token");
    }
}
