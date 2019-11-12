package com.telran.selenium.manager;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SessionHelper extends HelperBase {
    public SessionHelper(AppiumDriver driver) {
        super(driver);
    }

    public void openSite(String url) {
        driver.get(url);
    }

    public void login(String email, String password) {
        click(By.id("log_in_button"));
        type(By.id("user"), email);
        type(By.id("password"), password);
        click(By.id("authenticate"));
        //waitForElementAndClick(By.id("credential_save_reject"),10);//
    }

    public boolean isUserLoggedIn() {
        return isElementPresent(By.cssSelector("[data-test-id='header-member-menu-button']"));
    }
}
