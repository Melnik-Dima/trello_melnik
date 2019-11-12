package com.telran.selenium.manager;

import com.telran.selenium.model.BoardNames;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BoardHelper extends HelperBase {
    public BoardHelper(AppiumDriver driver) {
        super(driver);
    }

    public void submitBoardCreation() {
        click(By.xpath("//*[@type='submit']"));
    }

    public void typeNewBoardsName(BoardNames name) {

        type(By.cssSelector("div.form-container input.subtle-input"), name.getBoardName());
        if (isElementPresent(By.cssSelector(".subtle-chooser-trigger.unstyled-button.org-chooser-trigger"))) {
            click(By.cssSelector(".subtle-chooser-trigger.unstyled-button.org-chooser-trigger"));
            click(By.xpath("//ul[@class='pop-over-list org-chooser']/li[1]"));//no team
        }
    }

    public void clickByCreateBoardButtonOfBoardsSection() {
        click(By.cssSelector(".board-tile.mod-add"));
    }

    public void confirmBoardCreation() {
        click(By.id("button1"));
    }

    public void fillBoardCreationForm(BoardNames boardName1) {
        type(By.id("board_name"), boardName1.getBoardName());
        if (isElementPresent(By.id("team"))) {
            click(By.cssSelector("team"));
            click(By.xpath("text1"));//no team
        }
    }

    public void selectCreateBoardFromDropDown() {
        click(By.cssSelector("[data-test-id='header-create-board-button']"));
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

    public void openThirstBoard() {
        new WebDriverWait(driver, 40).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='boards-page-board-section-list']/li[1]")));
        click(By.xpath("//*[@class='boards-page-board-section-list']/li[1]"));
    }

    public void confirmBoardDeletion() {
    }

    public int getBoardsCount() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='icon-lg icon-member']/../../..//li")));
        return driver.findElements(By.xpath("//*[@class='icon-lg icon-member']/../../..//li")).size();
    }

    public void clickOnMenu() {
        WebElement menuButton = driver.findElement(By.cssSelector(".board-header-btn.mod-show-menu"));
        if (menuButton.getCssValue("visibility").equals("visible")) {
            click(By.cssSelector(".mod-show-menu"));

        }
        return;

    }

    public boolean isTherePersonalBoardsPresent() {
        return isElementPresent(By.xpath("//*[@class='icon-lg icon-member']/../../.."));
    }

    public String getBoardNameFromBoardPage() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".js-board-editing-target.board-header-btn-text")));
        return driver.findElement(By.cssSelector(".js-board-editing-target.board-header-btn-text")).getText();
    }

    public void cleanBoards() {
        //refresh();
        int counts = getBoardsCount();
        while (counts > 6) {
            refresh();
            openThirstBoard();
            clickOnMenu();
            expandMenu();
            choseCloseBoard();
            clickByClosePopUpButton();
            confirmBoardDeletion();
            returnToHomePage();
            counts = getBoardsCount();
        }
    }

    public void typeNewName(String name) {
        driver.findElement(By.xpath("//input[@class='board-name-input js-board-name-input']")).sendKeys(name);
    }

    public void confirmChangingBoard() {
        driver.findElement(By.xpath("//input[@class='board-name-input js-board-name-input']")).sendKeys(Keys.ENTER);

    }

    public void initChangeBoardName() {
        click(By.xpath("//span[@class='js-board-editing-target board-header-btn-text']"));
    }

    public boolean isAnyBoardPresent() {
        return getBoardsCount() > 0;
    }

    public void createBoard() {
        clickOnPlusButtonOnHeader();
        selectCreateBoardFromDropDown();
        String boardName = "QA21" + System.currentTimeMillis();
        fillBoardCreationForm(new BoardNames().setBoardName(boardName));
        confirmBoardCreation();
    }
    public void clickOnPlusButton(){
        waitForElementAndClick(By.id("add_fab"),15);
    }
}
