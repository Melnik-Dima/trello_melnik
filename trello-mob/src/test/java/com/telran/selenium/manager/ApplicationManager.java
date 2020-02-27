package com.telran.selenium.manager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class ApplicationManager {
    AppiumDriver driver;
    TeamHelper teamHelper;
    BoardHelper boardHelper;
    SessionHelper sessionHelper;
    UserHelper userHelper;
    private String browser;





    public void init() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Xiaomi_Redmi4X");
        capabilities.setCapability("platformVersion", "7.1.2");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "com.trello");
        capabilities.setCapability("appActivity", ".home.HomeActivity");
        capabilities.setCapability("app", "/Users/dmitrijmelnik/Documents/GitHub/trello_melnik/trello-mob/src/test/resources/Trello_new.apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);


        teamHelper = new TeamHelper(driver);
        boardHelper = new BoardHelper(driver);
        sessionHelper = new SessionHelper(driver);
        userHelper = new UserHelper(driver);

       // sessionHelper.login("familymelniks@gmail.com", "gnomikim");
    }


    public void stop() {
        driver.quit();
    }

    public TeamHelper getTeamHelper() {
        return teamHelper;
    }

    public BoardHelper getBoardHelper() {
        return boardHelper;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public UserHelper getUserHelper() {
        return userHelper;
    }
}

