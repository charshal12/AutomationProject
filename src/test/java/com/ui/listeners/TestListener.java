package com.ui.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.tests.TestBase;
import com.utility.BrowserUtility;
import com.utility.ExtentReporterUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    Logger logger = LoggerUtility.getLogger(this.getClass());
    ExtentSparkReporter extentSparkReporter; //HTML: look and feel
    ExtentReports extentReports; // What data to dump data in the report(heavy lifting)
    ExtentTest extentTest; //Store the information of the test


    public void onTestStart(ITestResult result){
        logger.info(result.getMethod().getMethodName());
        logger.info(result.getMethod().getDescription());
      //  logger.info(Arrays.toString(result.toString().toCharArray()));
        ExtentReporterUtility.createExtentTest(result.getMethod().getMethodName());
    }
    public void onTestSuccess(ITestResult result) {
        logger.info(result.getMethod().getMethodName() + " PASSED" );
        ExtentReporterUtility.getTest().log(Status.PASS,result.getMethod().getMethodName()+ " PASSED");

    }
    public void onTestFailure(ITestResult result) {
        logger.error(result.getMethod().getMethodName() + " FAILED" );
        logger.error(result.getThrowable().getMessage());
        ExtentReporterUtility.getTest().log(Status.FAIL, result.getMethod().getMethodName() + " FAILED");
        ExtentReporterUtility.getTest().log(Status.FAIL, result.getThrowable().getMessage());
        Object testclass = result.getInstance();

        BrowserUtility browserUtility = ((TestBase)testclass).getInstance();
        logger.info("Capturing Screenshot for the failed tests");

        String screenshotPath = browserUtility.takeScreenShot(result.getMethod().getMethodName());
        logger.info("Attaching the Screenshot to the HTML file");
        ExtentReporterUtility.getTest().addScreenCaptureFromPath(screenshotPath);
    }
    public void onTestSkipped(ITestResult result) {
        logger.warn(result.getMethod().getMethodName()+ " " + "SKIPPED" );
        ExtentReporterUtility.getTest().log(Status.SKIP,result.getMethod().getMethodName() + " SKIPPED");
    }

    public void onStart(ITestContext context) {
        logger.info("Test Suite Started");
        ExtentReporterUtility.setupSparkReporter("report.html");
    }

    public void onFinish(ITestContext context) {
        logger.info("Test Suite Finished");
        ExtentReporterUtility.flushReport();
    }
}
