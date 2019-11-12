package com.telran.selenium.manager;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;

public class UserHelper extends HelperBase {
    public UserHelper(AppiumDriver driver) {
        super(driver);
    }

    public void clickOnAvatar() {
        click(By.cssSelector("[data-test-id='header-member-menu-button']"));
    }

    public void openProfileFromDropdown() {
        click(By.cssSelector("[data-test-id='header-member-menu-profile']"));
    }

    public void initAvatarChanging() {
        new Actions(driver).moveToElement(driver.findElement(By.cssSelector(".rsiNque2CCqtPE"))).click().perform();
    }

    public void addPicture() {
        attach(new File("/Users/dmitrijmelnik/Documents/GitHub/trello_melnik/trello_web/src/test/resources/15.03 #2.jpg"));

    }

    private void attach(File file) {
        driver.findElement(By.name("file")).sendKeys(file.getAbsolutePath());
    }
}
