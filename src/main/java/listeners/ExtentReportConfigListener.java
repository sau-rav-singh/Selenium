package listeners;

import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import reporting.ExtentReportManager;

public class ExtentReportConfigListener implements ITestListener {

	private static ExtentReports extentReports;
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	public void onStart(ITestContext context) {
		String fileName = ExtentReportManager.getReportNameWithTimeStamp();
		String fullReportPath = System.getProperty("user.dir") + "\\reports\\" + fileName;
		extentReports = ExtentReportManager.createInstance(fullReportPath, "Test API Automation Report",
				"Test ExecutionReport");
	}

	public void onFinish(ITestContext context) {
		if (extentReports != null)
			extentReports.flush();
	}

	public void onTestStart(ITestResult result) {
		ExtentTest test = extentReports.createTest(
				"Test Name " + result.getTestClass().getName() + " - " + result.getMethod().getMethodName());
		extentTest.set(test);
	}

	public void onTestFailure(ITestResult result) {
		ExtentReportManager.logFailureDetails(result.getThrowable().getMessage());
		String stackTrace = Arrays.toString(result.getThrowable().getStackTrace());
		stackTrace = stackTrace.replaceAll(",", "<br>");
		String formmatedTrace = "<details>\n" + "    <summary>Click Here To See Exception Logs</summary>\n" + "    "
				+ stackTrace + "\n" + "</details>\n";
		ExtentReportManager.logExceptionDetails(formmatedTrace);
	}
}