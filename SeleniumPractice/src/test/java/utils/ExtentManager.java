package utils;

import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {
    private static final ThreadLocal<ExtentTest> testThreadLocal = new ThreadLocal<>();

    public static ExtentTest getExtentTest() {
        return testThreadLocal.get();
    }

    public static void setExtentTest(ExtentTest test) {
        testThreadLocal.set(test);
    }

    public static void removeExtentTest() {
        testThreadLocal.remove();
    }
}
