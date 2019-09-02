package com.telran.selenium;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class TestBase {

    WebDriver driver;

    @BeforeClass
    public void setUp()  {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        openSite("https://trello.com/");
        login("familymelniks@gmail.com","gnomikim");
    }


    @AfterClass
    public void tearDown () {
        driver.quit();
    }
    public void click(By locator) {
        driver.findElement(locator).click();
    }
    public void type (By locator,String string) {
        click(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(string);
    }

    public void openSite(String url) {
        driver.get(url);
    }

    public void login(String email,String password){
        click(By.cssSelector("[href='/login']"));
        type(By.cssSelector("[type=email]"),email);
        type(By.cssSelector("[type='password']"),password);
        click(By.id("login"));
    }
}


