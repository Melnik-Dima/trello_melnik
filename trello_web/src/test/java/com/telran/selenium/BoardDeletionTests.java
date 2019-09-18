package com.telran.selenium;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardDeletionTests extends TestBase {
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
    @Test
    public void testBoardDeletion()  {
        //Thread.sleep(5000);
        int before = app.getBoardHelper().getBoardsCount();
        app.getBoardHelper().openThirdBoard();
        app.getBoardHelper().clickOnMenu();
        app.getBoardHelper().expandMenu();
        app.getBoardHelper().choseCloseBoard();
        app.getBoardHelper().clickByClosePopUpButton();
        app.getBoardHelper().confirmBoardDeletion();
        app.getBoardHelper().returnToHomePage();
        int after = app.getBoardHelper().getBoardsCount();
        Assert.assertEquals(after, before - 1);
    }
}
