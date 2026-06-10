package api.base;

import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    @BeforeMethod
    public void setupEnvironment() {
        String env = System.getProperty("env", "DEV");
        Allure.label("environment", env);

        System.out.println("Running tests in ENV: " + env);
    }
}
