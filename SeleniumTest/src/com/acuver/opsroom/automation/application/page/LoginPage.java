package com.acuver.opsroom.automation.application.page;

import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	private static final By USERNAME_INPUT = By.xpath("//input[@id = 'username']");
	private static final By PASSWORD_INPUT = By.xpath("//input[@type = 'password']");
	private static final By SUMBIT_BUTTON = By.xpath("//input[@type = 'submit']");

	private final WebDriver webDriver;

	public LoginPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public void verify() {
		String pageName = "Login Page";
		By[] elementsToFind = new By[] { USERNAME_INPUT, PASSWORD_INPUT, SUMBIT_BUTTON };

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

	public WelcomePage doLogin(String username, String password) {
		webDriver.findElement(USERNAME_INPUT).sendKeys(username);
		webDriver.findElement(PASSWORD_INPUT).sendKeys(password);
		webDriver.findElement(SUMBIT_BUTTON).click();

		return new WelcomePage(webDriver);
	}
}
