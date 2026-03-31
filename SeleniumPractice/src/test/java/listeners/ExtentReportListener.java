package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import lombok.Getter;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class ExtentReportListener implements ISuiteListener {
    @Getter
    private static ExtentReports extent;

    @Override
    public void onStart(ISuite suite) {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @Override
    public void onFinish(ISuite suite) {
        if (extent != null) {
            extent.flush();
        }
    }
}