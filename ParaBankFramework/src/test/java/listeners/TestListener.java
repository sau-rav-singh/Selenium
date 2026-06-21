package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestListener implements ITestListener {

    private static final Logger logger = LoggerFactory.getLogger(TestListener.class);

    private String getScenarioName(ITestResult result) {
        if (result.getMethod().getMethodName().equals("runScenario") && result.getParameters().length > 0) {
            Object[] params = result.getParameters();
            if (params[0] != null) {
                return params[0].toString();
            }
        }
        return result.getMethod().getMethodName();
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("========================================");
        logger.info("TEST SUITE STARTED: {}", context.getSuite().getName());
        logger.info("Test Name: {}", context.getName());
        logger.info("Start Time: {}", context.getStartDate());
        logger.info("========================================");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("========================================");
        logger.info("TEST SUITE FINISHED: {}", context.getSuite().getName());
        logger.info("Test Name: {}", context.getName());
        logger.info("End Time: {}", context.getEndDate());
        logger.info("Passed Tests: {}", context.getPassedTests().size());
        logger.info("Failed Tests: {}", context.getFailedTests().size());
        logger.info("Skipped Tests: {}", context.getSkippedTests().size());
        logger.info("========================================");
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("----------------------------------------");
        logger.info("TEST STARTED: {}", getScenarioName(result));
        logger.info("----------------------------------------");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("----------------------------------------");
        logger.info("TEST PASSED: {}", getScenarioName(result));
        logger.info("Duration: {} ms", (result.getEndMillis() - result.getStartMillis()));
        logger.info("----------------------------------------");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("----------------------------------------");
        logger.error("TEST FAILED: {}", getScenarioName(result));
        if (result.getThrowable() != null) {
            logger.error("Failure Reason: {}", result.getThrowable());
        }
        logger.error("Duration: {} ms", (result.getEndMillis() - result.getStartMillis()));
        logger.error("----------------------------------------");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("----------------------------------------");
        logger.warn("TEST SKIPPED: {}", getScenarioName(result));
        if (result.getThrowable() != null) {
            logger.warn("Skip Reason: {}", result.getThrowable());
        }
        logger.warn("----------------------------------------");
    }
}
