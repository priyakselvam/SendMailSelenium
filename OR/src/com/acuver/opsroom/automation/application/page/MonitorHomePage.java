package com.acuver.opsroom.automation.application.page;

import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class MonitorHomePage {

	private static final By DAHSBOARD_HEADER = By
			.xpath("//div[contains(@class, 'dashboard-header')]//span[text() = 'Home Dashboard']");

	private final WebDriver webDriver;

	public MonitorHomePage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public void verify() {
		String pageName = "Monitor Home Page";
		By[] elementsToFind = new By[] { DAHSBOARD_HEADER };

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
