package com.qcautomation.configurations;

public final class AppConfiguration {
    private static final String DEFAULT_BASE_URL = "https://www.saucedemo.com/";

    private AppConfiguration() {
    }

    public static String baseUrl() {
        String configuredUrl = System.getProperty("base.url");
        if (configuredUrl == null || configuredUrl.isBlank()) {
            configuredUrl = System.getenv("SAUCEDEMO_BASE_URL");
        }
        return configuredUrl == null || configuredUrl.isBlank() ? DEFAULT_BASE_URL : configuredUrl;
    }
}
