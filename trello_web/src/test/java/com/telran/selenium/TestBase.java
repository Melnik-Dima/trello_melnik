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
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        openSite("https://trello.com/");
        login("familymelniks@gmail.com", "gnomikim");
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void type(By locator, String string) {
        click(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(string);
    }

    public void openSite(String url) {
        driver.get(url);
    }

    public void login(String email, String password) {
        click(By.cssSelector("[href='/login']"));
        type(By.cssSelector("[type=email]"), email);
        type(By.cssSelector("[type='password']"), password);
        click(By.id("login"));
    }

    public boolean isUserLoggedIn() {
        return isElementPresent(By.cssSelector("[data-test-id='header-member-menu-button']"));
    }

    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;

    }

    public void clickContinueButton() {
        click(By.cssSelector("[type='submit']"));
    }

    public void fillTeamCreationForm(String teamName, String description) {
        type(By.cssSelector("[data-test-id='header-create-team-name-input']"), teamName);
        type(By.cssSelector("textarea"), description);
    }

    public void selectCreateTeamFromDropDown() {
        click(By.cssSelector("[data-test-id='header-create-team-button']"));
    }

    public void clickOnPlusButtonOnHeader() {
        click(By.cssSelector("[data-test-id='header-create-menu-button']"));
    }

    public void clickXButton() {
        click(By.cssSelector("button.qb90FI2uVIybRy._2b_HpRl1Tyl1YK"));
    }

    public void submitBoardCreation() {
        click(By.xpath("//*[@type='submit']"));
    }

    public void typeNewBoardsName(String boardName) {

        type(By.cssSelector("div.form-container input.subtle-input"),boardName);
        if (isElementPresent(By.cssSelector(".subtle-chooser-trigger.unstyled-button.org-chooser-trigger"))) {
            click(By.cssSelector(".subtle-chooser-trigger.unstyled-button.org-chooser-trigger"));
            click(By.xpath("//ul[@class='pop-over-list org-chooser']/li[1]"));//no team
        }
    }

    public void ckickByCreateBoardButtonOfBoardsSection() {
        click(By.cssSelector(".board-tile.mod-add"));
    }

    public void confirmBoardCreation() {
        click(By.cssSelector("[data-test-id='header-create-board-submit-button']"));
    }

    public void fillBoardCreationForm(String boardName) {
        type(By.cssSelector("[data-test-id='header-create-board-title-input']"), boardName);

        if (isElementPresent(By.cssSelector(".W6rMLOx8U0MrPx"))) {
            click(By.cssSelector(".W6rMLOx8U0MrPx"));
            click(By.xpath("//nav[@class='SdlcRrTVPA8Y3K']//li[1]"));//no team
        }

    }

    public void selectCreateBoardFromDropDown() {
        click(By.cssSelector("[data-test-id='header-create-board-button']"));
    }

    public String getTeamNameFromTeamPage() {
        return driver.findElement(By.cssSelector("h1")).getText();
    }

    public void returnToHomePage() throws InterruptedException {
        click(By.cssSelector("a[href='/']"));
        Thread.sleep(6000);
        click(By.cssSelector("a[href='/']"));
        Thread.sleep(6000);

    }

    public int getTeamsCount() {
       return driver.findElements(By.xpath("//*[@class='_mtkwfAlvk6O3f']/../../..//li")).size();
    }

    public void clickOnPlusButtonFromLeftNavMenu() {
        click(By.cssSelector(".icon-add.icon-sm"));
    }

    public void clickByClosePopUpButton() {
        click(By.cssSelector(".no-back input"));
    }

    public void choseCloseBoard() {
        click(By.cssSelector(".js-close-board"));
    }

    public void expandMenu() {
        click(By.cssSelector(".js-open-more"));
    }

    public void openThirdBoard() {
        click(By.xpath("//*[@class='boards-page-board-section-list']/li[3]"));
    }

    public void confirmBoardDeletion() {
    }
}


