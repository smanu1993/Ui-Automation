package utilities;

import com.aventstack.extentreports.ExtentReports;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import tests.BaseClass;

public class TestNGListener extends BaseClass implements ITestListener  {

	ExtentReports ext = ExtentManager.createExtentReports();

	@Override
	public synchronized void onStart(ITestContext context) {
		ext.createTest(context.getName())
				.info("suite execution starts");
		Reporter.log(context.getName()+" suite execution starts..",true);

	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		ext.createTest(context.getName())
				.info("suite execution ends");
		Reporter.log(context.getName()+" suite execution ends..",true);

	}

	@Override
	public synchronized void onTestStart(ITestResult result) {
		ext.createTest(result.getName())
				.info(result.getName()+"test starts");
		Reporter.log(result.getName()+" started..",true);
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		CommonUtility.getScreenShot(driver, passedTCSSPath+result.getName()+".png");
		ext.createTest(result.getName())
				.addScreenCaptureFromPath(passedTCSSPath)
				.info(result.getName()+"test success !!");
		Reporter.log(result.getName()+" success !!",true);
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		CommonUtility.getScreenShot(driver, failedTCSSPath+result.getName()+".png");
		ext.createTest(result.getName())
				.addScreenCaptureFromPath(failedTCSSPath)
				.fail(result.getName()+"test failed !!");
		Reporter.log(result.getName()+" test failed !!",true);
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		ext.createTest(result.getName())
				.skip(result.getName()+"test skipped !!");
		Reporter.log(result.getName()+" test skipped !!",true);
	}
}
