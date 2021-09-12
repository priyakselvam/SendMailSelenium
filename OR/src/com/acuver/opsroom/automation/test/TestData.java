package com.acuver.opsroom.automation.test;

public class TestData {

	private final String dataId;
	private final String description;

	private final String url;
	private final String username;
	private final String password;
	private final int pageLoadTimeoutInSeconds;

	public TestData(String dataId, String description, String url, String username, String password,
			int pageLoadTimeoutInSeconds) {
		this.dataId = dataId;
		this.description = description;

		this.url = url;
		this.username = username;
		this.password = password;
		this.pageLoadTimeoutInSeconds = pageLoadTimeoutInSeconds;
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public int getPageLoadTimeoutInSeconds() {
		return pageLoadTimeoutInSeconds;
	}

	@Override
	public String toString() {
		return String.format("%s - %s", dataId, description);
	}

}
