package com.telran.selenium;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardCreationTests extends TestBase {
    @BeforeClass
    public void ensurePreconditions() {
        if (!app.isUserLoggedIn()) {
            app.login("familymelniks@gmail.com", "gnomikim");
        }

    }

    @BeforeMethod
    public void isOnHomePage() {
        if (!app.isTherePersonalBoardsPresent()) {
            app.returnToHomePage();

        }
    }

    @Test //(enabled = false)
    public void testBoardCreationFromPlusButtonOnHeader() throws InterruptedException {
        //Thread.sleep(5000);
        int before = app.getBoardsCount();
        app.clickOnPlusButtonOnHeader();
        app.selectCreateBoardFromDropDown();
        String boardName = "QA21" + System.currentTimeMillis();
        app.fillBoardCreationForm(boardName);
        app.confirmBoardCreation();
        String createdBoardName = app.getBoardNameFromBoardPage();
        app.returnToHomePage();
        int after = app.getBoardsCount();
        Assert.assertEquals(after, before + 1);
        Assert.assertEquals(createdBoardName.toLowerCase(), boardName.toLowerCase());
    }


    @Test//(enabled = false)
    public void testBoardCreationFromPersonalBoardsSection() throws InterruptedException {
        //Thread.sleep(5000);
        int before = app.getBoardsCount();
        app.clickByCreateBoardButtonOfBoardsSection();
        //Thread.sleep(4000);
        String boardName = "QA21" + System.currentTimeMillis();
        app.typeNewBoardsName(boardName);
        app.submitBoardCreation();
        String createdBoardName = app.getBoardNameFromBoardPage();
        app.returnToHomePage();
        int after = app.getBoardsCount();
        Assert.assertEquals(after, before + 1);
        Assert.assertEquals(createdBoardName.toLowerCase(), boardName.toLowerCase());
    }

    @AfterClass //(enabled=false)
    public void cleanBoards() throws InterruptedException {
        int i = 0;
        while (i < 2) {
            Thread.sleep(5000);
            app.openThirdBoard();
            app.clickOnMenu();
            app.expandMenu();
            app.choseCloseBoard();
            app.clickByClosePopUpButton();
            app.confirmBoardDeletion();
            app.returnToHomePage();
            i++;
        }
    }
}
