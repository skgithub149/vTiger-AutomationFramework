package genericUtility;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenersImplementation implements ITestListener {

	ExtentReports report;
	ExtentTest test;

	public void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
//		System.out.println("--------" + methodName + "-Test Execution Started--------");

		test = report.createTest(methodName);
		test.log(Status.INFO, "Test Execution Started- " + methodName);

	}

	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
//		System.out.println("--------" + methodName + "-Test Execution Passed--------");
		test.log(Status.PASS, "Test Script Passed- " + methodName);

	}

	public void onTestFailure(ITestResult result) {
		JavaUtility jUtil = new JavaUtility();
		WebDriverUtility wUtil = new WebDriverUtility();

		String methodName = result.getMethod().getMethodName();
//		System.out.println("--------" + methodName + "-Test Execution Failed--------");
//		Throwable exception = result.getThrowable();
//		System.out.println("--------" + exception + " found");

		test.log(Status.FAIL, "Test Script Failed- " + methodName);
		test.log(Status.FAIL, result.getThrowable());

		String screenshotName = methodName + "-" + jUtil.getSystemDateInFormat();

		try {
			String path = wUtil.takeScreenshot(BaseClass.sdriver, screenshotName);
			test.addScreenCaptureFromPath(path);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println("--------" + methodName + "-Test Execution Skipped--------");
		Throwable exception = result.getThrowable();
		System.out.println("--------" + exception + " found");

		test.log(Status.SKIP, "Test Script Skipped");
		test.log(Status.SKIP, result.getThrowable());

	}

	public void onStart(ITestContext context) {
		System.out.println("--------Suit Execution Started--------");

		ExtentSparkReporter htmlReport = new ExtentSparkReporter(
				"./ExtentReport/Report " + new JavaUtility().getSystemDateInFormat() + ".html");
		htmlReport.config().setDocumentTitle("Vtiger_AutomationFramework");
		htmlReport.config().setReportName("Vtiger Execution Report");
		htmlReport.config().setTheme(Theme.STANDARD);

		report = new ExtentReports();
		report.attachReporter(htmlReport);
		report.setSystemInfo("Base URL", "http://localhost:8888");
		report.setSystemInfo("Base Browser", "Chrome");
		report.setSystemInfo("Base OS", "Windows");
		report.setSystemInfo("Reporter Name", "SURAJ");

	}

	public void onFinish(ITestContext context) {
		System.out.println("--------Suit Execution Finished--------");
		report.flush();

	}

}
