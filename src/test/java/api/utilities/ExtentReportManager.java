package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager implements ITestListener
{
    public ExtentSparkReporter sparkReporter; //responsible for UI of report
    public ExtentReports extent;  //responsible for env, os,time etc some common info
    public ExtentTest test;  //responsible for entries in the report

    String report;

    public void onStart(ITestContext testContext) //this method will run only once before starting the tests
    {
        String time=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        report="Test-Report"+time+".html";
        sparkReporter=new ExtentSparkReporter(".\\reports\\"+report); // here'.\\' also represents the current project location

        sparkReporter.config().setDocumentTitle("RestAssuredAutomation");
        sparkReporter.config().setReportName("Store API");
        sparkReporter.config().setTheme(Theme.DARK);

        extent=new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application","Pet Store API");
        extent.setSystemInfo("Operating System",System.getProperty("os.name"));
        extent.setSystemInfo("User Name",System.getProperty("user.name"));
        extent.setSystemInfo("Environment","QA");
        extent.setSystemInfo("user","Shishpal Rajput");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test=extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.PASS,"Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test=extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.FAIL,"Test Failed");
        test.log(Status.FAIL,result.getThrowable().getMessage());
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        test=extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.FAIL,"Test Skipped");
        test.log(Status.FAIL,result.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();  //it makes sure the report is complete and generates it if this is not called then report is not generated
    }
}