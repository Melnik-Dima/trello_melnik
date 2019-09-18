package com.telran.selenium;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardCreationTests extends TestBase {
    @BeforeClass
    public void ensurePreconditions() {
        if (!app.getSessionHelper().isUserLoggedIn()) {
            app.getSessionHelper().login("familymelniks@gmail.com", "gnomikim");
        }

    }

    @BeforeMethod
    public void isOnHomePage() {
        if (!app.getBoardHelper().isTherePersonalBoardsPresent()) {
            app.getBoardHelper().returnToHomePage();

        }
    }

    @Test //(enabled = false)
    public void testBoardCreationFromPlusButtonOnHeader() throws InterruptedException {
        Thread.sleep(5000);
        int before = app.getBoardHelper().getBoardsCount();
        app.getBoardHelper().clickOnPlusButtonOnHeader();
        app.getBoardHelper().selectCreateBoardFromDropDown();
        String boardName = "QA21" + System.currentTimeMillis();
        app.getBoardHelper().fillBoardCreationForm(boardName);
        app.getBoardHelper().confirmBoardCreation();
        String createdBoardName = app.getBoardHelper().getBoardNameFromBoardPage();
        app.getBoardHelper().returnToHomePage();
        int after = app.getBoardHelper().getBoardsCount();
        Assert.assertEquals(after, before + 1);
        Assert.assertEquals(createdBoardName.toLowerCase(), boardName.toLowerCase());
    }


    @Test//(enabled = false)
    public void testBoardCreationFromPersonalBoardsSection() throws InterruptedException {
        //Thread.sleep(5000);
        int before = app.getBoardHelper().getBoardsCount();
        app.getBoardHelper().clickByCreateBoardButtonOfBoardsSection();
        //Thread.sleep(4000);
        String boardName = "QA21" + System.currentTimeMillis();
        app.getBoardHelper().typeNewBoardsName(boardName);
        app.getBoardHelper().submitBoardCreation();
        String createdBoardName = app.getBoardHelper().getBoardNameFromBoardPage();
        app.getBoardHelper().returnToHomePage();
        int after = app.getBoardHelper().getBoardsCount();
        Assert.assertEquals(after, before + 1);
        Assert.assertEquals(createdBoardName.toLowerCase(), boardName.toLowerCase());
    }

    @AfterClass //(enabled=false)
    public void afterActions() throws InterruptedException {
        app.getBoardHelper().cleanBoards();
    }


}
