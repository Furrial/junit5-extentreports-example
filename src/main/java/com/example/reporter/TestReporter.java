package com.example.reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.example.util.ScreenshotUtil;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/*
 Usage:
 Test class should be annotated with @ExtendWith(TestReporter.class)
 */
public class TestReporter implements BeforeAllCallback, BeforeTestExecutionCallback, AfterTestExecutionCallback {
    private static ExtentReports extent = new ExtentReports();
    private ExtentSparkReporter spark = new ExtentSparkReporter("target\\report\\TestReport.html");
    private ExtentTest test;

    @Override
    public void beforeAll(ExtensionContext context) {
        extent.attachReporter(spark);
        context.getStore(ExtensionContext.Namespace.GLOBAL).put("TestReport", new CustomAfterSuite());
        test = extent.createTest(context.getDisplayName());
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        test.log(Status.INFO, context.getDisplayName() + " - started");
        test.createNode(context.getDisplayName());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        if (!context.getExecutionException().isPresent()) {
            test.pass(context.getDisplayName() + " - passed");
        } else {
            test.fail(context.getExecutionException().get().getLocalizedMessage());
            test.addScreenCaptureFromPath("../../" + ScreenshotUtil.takeScreenshot().getPath(), context.getDisplayName());
        }
    }

    //Used as no AfterSuite annotation available in Junit5 (as of 5.8.2 version)
    private static class CustomAfterSuite implements ExtensionContext.Store.CloseableResource {
        @Override
        public void close() {
            extent.flush();
        }
    }


}
