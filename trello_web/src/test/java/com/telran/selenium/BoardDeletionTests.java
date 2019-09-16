package com.telran.selenium;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardDeletionTests extends TestBase {
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
    @Test
    public void testBoardDeletion()  {
        //Thread.sleep(5000);
        int before = app.getBoardsCount();
        app.openThirdBoard();
        app.clickOnMenu();
        app.expandMenu();
        app.choseCloseBoard();
        app.clickByClosePopUpButton();
        app.confirmBoardDeletion();
        app.returnToHomePage();
        int after = app.getBoardsCount();
        Assert.assertEquals(after, before - 1);
    }
}
