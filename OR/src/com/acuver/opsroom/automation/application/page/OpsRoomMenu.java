package com.acuver.opsroom.automation.application.page;

import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class OpsRoomMenu {

	private static final By EXCEPTION_LINK = By
			.xpath("//a[contains(@class, 'nav-list-item')]//pre[text() = 'Exceptions']");
	private static final By LOG_SEARCH_LINK = By
			.xpath("//a[contains(@class, 'nav-list-item') and contains(., 'Log Search')]");
	private static final By MONITOR_HOME_LINK = By
			.xpath("//a[contains(@class, 'nav-list-item') and contains(., 'Dashboard')]");

	private final WebDriver webDriver;

	public OpsRoomMenu(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public void verify() {
		String pageName = "OpsRoom Menu";
		By[] elementsToFind = new By[] { EXCEPTION_LINK, LOG_SEARCH_LINK, MONITOR_HOME_LINK };

		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			for (By elementToFind : elementsToFind) {
				webDriver.findElement(elementToFind);
				webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			}
		} catch (NoSuchElementException e) {
			fail(String.format("Expected element not found in the %s.", pageName), e);
		} finally {
			webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		}
	}

	public ExceptionsPage goToExceptionsPage() {
		webDriver.findElement(EXCEPTION_LINK).click();

		return new ExceptionsPage(webDriver);
	}

	public LogSearchPage goToLogSearchPage() {
		webDriver.findElement(LOG_SEARCH_LINK).click();

		return new LogSearchPage(webDriver);
	}

	public MonitorHomePage goToMonitorHomePage() {
		webDriver.findElement(MONITOR_HOME_LINK).click();

		return new MonitorHomePage(webDriver);
	}
}
