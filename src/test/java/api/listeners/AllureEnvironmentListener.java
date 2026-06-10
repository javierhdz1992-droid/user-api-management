package api.listeners;

import io.qameta.allure.Allure;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureEnvironmentListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        String env = System.getProperty("env", "DEV");

        // Agregar etiqueta de ambiente al test en Allure
        Allure.label("env", env);
        Allure.label("environment", env);

        // Agregar el ambiente al nombre del test en Allure
        String testName = result.getMethod().getMethodName();
        String newName = "[" + env + "] " + testName;

        Allure.getLifecycle().updateTestCase(testCase -> {
            testCase.setName(newName);
        });

        System.out.println("✅ Test iniciado con ambiente: " + env);
        System.out.println("   Nombre test: " + newName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // No se necesita hacer nada especial
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // No se necesita hacer nada especial
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // No se necesita hacer nada especial
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // No se necesita hacer nada especial
    }

    @Override
    public void onStart(ITestContext context) {
        // No se necesita hacer nada especial
    }

    @Override
    public void onFinish(ITestContext context) {
        // No se necesita hacer nada especial
    }
}

