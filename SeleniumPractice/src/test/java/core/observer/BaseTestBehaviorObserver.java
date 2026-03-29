package core.observer;

import org.testng.ITestResult;
import java.lang.reflect.Method;

/**
 * Default empty implementation of TestBehaviorObserver.
 * Concrete observers should extend this class and override only the lifecycle methods they need.
 */
public abstract class BaseTestBehaviorObserver implements TestBehaviorObserver {

    @Override
    public void preTestInit(ITestResult testResult, Method memberInfo) {
        // Default empty implementation
    }

    @Override
    public void postTestInit(ITestResult testResult, Method memberInfo) {
        // Default empty implementation
    }

    @Override
    public void preTestCleanup(ITestResult testResult, Method memberInfo) {
        // Default empty implementation
    }

    @Override
    public void postTestCleanup(ITestResult testResult, Method memberInfo) {
        // Default empty implementation
    }
}
