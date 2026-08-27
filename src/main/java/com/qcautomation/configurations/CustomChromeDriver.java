package com.qcautomation.configurations;

import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Map;

public class CustomChromeDriver implements DriverSource {
    @Override
    public WebDriver newDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--disable-features=PasswordLeakDetection,PasswordCheck",
                "--disable-save-password-bubble",
                "--password-store=basic"
        );
        options.setExperimentalOption("prefs", Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "profile.password_manager_leak_detection", false
        ));
        return new ChromeDriver(options);
    }

    @Override
    public boolean takesScreenshots() {
        return true;
    }
}
