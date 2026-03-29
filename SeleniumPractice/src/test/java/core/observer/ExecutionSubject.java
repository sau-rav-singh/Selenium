package core.observer;

import org.testng.ITestResult;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ExecutionSubject implements TestExecutionSubject {
    private final List<TestBehaviorObserver> observers = new ArrayList<>();

    @Override
    public void attach(TestBehaviorObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(TestBehaviorObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void preTestInit(ITestResult result, Method memberInfo) {
        observers.forEach(o -> o.preTestInit(result, memberInfo));
    }

    @Override
    public void postTestInit(ITestResult result, Method memberInfo) {
        observers.forEach(o -> o.postTestInit(result, memberInfo));
    }

    @Override
    public void preTestCleanup(ITestResult result, Method memberInfo) {
        observers.forEach(o -> o.preTestCleanup(result, memberInfo));
    }

    @Override
    public void postTestCleanup(ITestResult result, Method memberInfo) {
        observers.forEach(o -> o.postTestCleanup(result, memberInfo));
    }
}
