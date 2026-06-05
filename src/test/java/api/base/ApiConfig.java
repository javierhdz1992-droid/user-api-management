package api.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApiConfig {

    private static final Properties properties = new Properties();

    /**
    static{
        try{
            InputStream inputStream = ApiConfig.class.getClassLoader().getResourceAsStream("config.properties");
            if (inputStream == null) {
                throw new RuntimeException("config.properties not found in classpath");
            }
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException("Error loading config.properties", e);
        }
    }**/

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

    public static String getDevBaseUrl(){
        return properties.getProperty("dev.base.url");
    }

    public static String getProdBaseUrl(){
        return properties.getProperty("prod.base.url");
    }

    public static String getAuthToken(){
        return properties.getProperty("auth.token");
    }
}
