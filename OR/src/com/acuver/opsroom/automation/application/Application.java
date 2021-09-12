package com.acuver.opsroom.automation.application;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.acuver.opsroom.automation.application.page.LoginPage;
import com.acuver.opsroom.automation.application.page.WelcomePage;

public class Application {

	private final WebDriver webDriver;
	private final String url;

	public Application(WebDriver webDriver, String url, int pageLoadWaitInSeconds) {
		this.webDriver = webDriver;
		this.url = url;
		webDriver.manage().window().maximize();
		webDriver.manage().timeouts().pageLoadTimeout(pageLoadWaitInSeconds, TimeUnit.SECONDS);
	}

	public LoginPage goToLoginPage() {
		webDriver.get(url);
		return new LoginPage(webDriver);
	}

	public WelcomePage goToWelcomePage() {
		webDriver.get(url);
		return new WelcomePage(webDriver);
	}
}
