package com.acuver.opsroom.automation.test;

import org.testng.annotations.Test;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.acuver.opsroom.automation.application.Application;
import com.acuver.opsroom.automation.application.page.ExceptionsPage;
import com.acuver.opsroom.automation.application.page.LogSearchPage;
import com.acuver.opsroom.automation.application.page.LoginPage;
import com.acuver.opsroom.automation.application.page.MonitorHomePage;
import com.acuver.opsroom.automation.application.page.WelcomePage;

public class ApplicationTest {

	private static final String ENVIRONMENT_TO_TEST_ENVIORNMENT_NAME = "OR_ENV_TO_TEST";
	
	private WebDriver webDriver;

	@BeforeTest
	public void beforeTest() {
		System.out.println(System.getProperty("user.dir"));
		System.setProperty("webdriver.chrome.driver", String.format("%s/%s", System.getProperty("user.dir"), "drivers/chromedriver.exe"));
		webDriver = new ChromeDriver();
	}

	@AfterTest
	public void afterTest() {
		webDriver.close();
	}

	@DataProvider(name = "externalDataProvider")
	public Object[][] externalDataProvider() {
		String testEnvironment = System.getenv(ENVIRONMENT_TO_TEST_ENVIORNMENT_NAME);

		if (testEnvironment == null || testEnvironment.trim().isEmpty()) {
			throw new RuntimeException(String.format("Provide valid environemnt to test using %s environment variable.",
					ENVIRONMENT_TO_TEST_ENVIORNMENT_NAME));
		}

		List<TestData> testDataList = new ExcelTestDataReader(String.format("%s/%s", System.getProperty("user.dir"), "test-data/test-data.xlsx")).getTestLines(testEnvironment);
		
		if (testDataList.size() == 0) {
			throw new RuntimeException(String.format("No test data found for environment %s", testEnvironment));
		}

		Object[][] returnValue = new Object[testDataList.size()][1];
		for (int i = 0; i < returnValue.length; i++) {
			returnValue[i][0] = testDataList.get(i);
		}

		return returnValue;
	}

	@Test(dataProvider = "externalDataProvider")
	public void testApplicationSanity(TestData testData) {
		Application application = new Application(webDriver, testData.getUrl(), testData.getPageLoadTimeoutInSeconds());

		LoginPage loginPage = application.goToLoginPage();
		loginPage.verify();

		WelcomePage welcomePage = loginPage.doLogin(testData.getUsername(), testData.getPassword());
		welcomePage.verify();

		ExceptionsPage exceptionPage = welcomePage.getMenu().goToExceptionsPage();
		exceptionPage.verify();

		LogSearchPage logSearchPage = exceptionPage.getMenu().goToLogSearchPage();
		logSearchPage.verify();

		welcomePage = application.goToWelcomePage();
		welcomePage.verify();

		MonitorHomePage monitorHomePage = welcomePage.getMenu().goToMonitorHomePage();
		monitorHomePage.verify();
	}
}
