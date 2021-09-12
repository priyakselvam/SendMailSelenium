package com.acuver.opsroom.automation.application.page;

import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class LogSearchPage {

	private static final By ALL_MESSAGES_LINK = By.xpath("//a[text() = 'All messages']");

	private final WebDriver webDriver;

	public LogSearchPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public void verify() {
		String pageName = "Log Search Page";
		By[] elementsToFind = new By[] { ALL_MESSAGES_LINK };

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
}
