package tn.testlink.test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

public class AutomatedUpdateExample {

	public static final String DEVKEY = "3426f2a2a7e4f52c55ac374c2a544ac4";

	public static final String URL = "http://localhost:8080/testlink14/lib/api/xmlrpc/v1/xmlrpc.php";

	public static void reportResult(TestLinkAPIClient ap, String TestProject, String TestPlan, String Testcase,
			String Build, String Notes, String Result) throws TestLinkAPIException {

		ap.reportTestCaseResult(TestProject, TestPlan, Testcase, Build, Notes, Result);
	}

	@Test

	public void Test1() throws Exception

	{
		TestLinkAPIClient api = new TestLinkAPIClient(DEVKEY, URL);
		/**/
		//System.setProperty("webdriver.chrome.driver","G:\\TestingProject\\chromedriver_win32\\chromedriver.exe");
		//WebDriver driver = new ChromeDriver();
		/**/
		
		WebDriver driver = new FirefoxDriver();

		//WebDriverWait wait = new WebDriverWait(driver, 600);

		String testProject = "Gmail Test Application";

		String testPlan = "GmailPlan";

		String testCase = "LoginCase";

		String build = "Buid1";

		String notes = null;

		String result = null;

		try {

			driver.manage().window().maximize();

			//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//driver.navigate().to("https://accounts.google.com/ServiceLogin?service=mail&passive=true&rm=false&continue=https://mail.google.com/mail/&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1");
			driver.get("https://accounts.google.com/ServiceLogin?service=mail&passive=true&rm=false&continue=https://mail.google.com/mail/&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1");

			driver.findElement(By.id("Email")).sendKeys("glid2test");
			driver.findElement(By.id("next")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@id='Passwd']")).sendKeys("glidtest");
			//driver.findElement(By.id("Passwd")).sendKeys("glidtest");
			driver.findElement(By.id("signIn")).click();
			driver.switchTo().defaultContent();


			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("+Testlink")));
			

			result = TestLinkAPIResults.TEST_PASSED;

			notes = "Executed successfully";
			AutomatedUpdateExample.reportResult(api, testProject, testPlan, testCase, build, notes, result);


		}

		catch (Exception e) {

			result = TestLinkAPIResults.TEST_FAILED;

			notes = "Execution failed";

		}

		finally {

			AutomatedUpdateExample.reportResult(api, testProject, testPlan, testCase, build, notes, result);
			
			//driver.quit();

		}

	}

}
