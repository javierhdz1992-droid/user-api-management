package api.base;

import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod
    public void setup() {
        String env = System.getProperty("env", "DEV");

        Allure.getLifecycle().updateTestCase(testResult ->
                testResult.setName("[" + env + "] " + testResult.getName())
        );
    }
}
