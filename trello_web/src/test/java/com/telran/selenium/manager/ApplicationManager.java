package com.telran.selenium.manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    EventFiringWebDriver driver;
    TeamHelper teamHelper;
    BoardHelper boardHelper;
    SessionHelper sessionHelper;
    UserHelper userHelper;
    private String browser;

    public static class MyListener extends AbstractWebDriverEventListener {
        Logger logger = LoggerFactory.getLogger(MyListener.class);
        private WebDriver wd;
        HelperBase hb = new HelperBase(wd);

        //        @Override
//        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
//           logger.info("Start search element "+by);
//        }
//
        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            logger.info(by + "found");
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {

            File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File screen = new File("src/test/resources/screenshots/screen" + System.currentTimeMillis() + ".png");
            try {
                Files.copy(tmp, screen);
            } catch (IOException e) {
                e.printStackTrace();
            }
            logger.error("error", throwable);


        }
    }

    public ApplicationManager(String browser) {

        this.browser = browser;
    }


    public void init() {
        if (browser.equals(BrowserType.CHROME)) {
            driver = new EventFiringWebDriver(new ChromeDriver());
        }
        if (browser.equals(BrowserType.FIREFOX)) {
            driver = new EventFiringWebDriver(new FirefoxDriver());
        }
        if (browser.equals(BrowserType.SAFARI)) {
            driver = new EventFiringWebDriver(new SafariDriver());
        }
        driver.register(new MyListener());




        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        teamHelper = new TeamHelper(driver);
        boardHelper = new BoardHelper(driver);
        sessionHelper = new SessionHelper(driver);
        userHelper = new UserHelper(driver);
        sessionHelper.openSite("https://trello.com/");
        sessionHelper.login("familymelniks@gmail.com", "gnomikim");
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

