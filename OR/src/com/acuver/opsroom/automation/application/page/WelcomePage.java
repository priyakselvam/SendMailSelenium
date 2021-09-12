package com.acuver.opsroom.automation.application.page;

import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class WelcomePage {

	private final WebDriver webDriver;
	private final OpsRoomMenu menu;

	private static final By WELCOME_TEXT = By.xpath("//h1[contains(text(),'Welcome To Opsroom')]");

	public WelcomePage(WebDriver webDriver) {
		this.webDriver = webDriver;
		this.menu = new OpsRoomMenu(webDriver);
	}

	public void verify() {
		menu.verify();

		String pageName = "Welcome Page";
		By[] elementsToFind = new By[] { WELCOME_TEXT };

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

	public OpsRoomMenu getMenu() {
		return menu;
	}
}
