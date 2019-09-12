package com.telran.selenium;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardDeletionTests extends TestBase {
    @BeforeClass
    public void ensurePreconditions() {
        if (!isUserLoggedIn()) {
            login("familymelniks@gmail.com", "gnomikim");
        }

    }

    @BeforeMethod
    public void isOnHomePage() {
        if (!isTherePersonalBoardsPresent()) {
            returnToHomePage();

        }
    }
    @Test
    public void testBoardDeletion()  {
        //Thread.sleep(5000);
        int before = getBoardsCount();
        openThirdBoard();
        clickOnMenu();
        expandMenu();
        choseCloseBoard();
        clickByClosePopUpButton();
        confirmBoardDeletion();
        returnToHomePage();
        int after = getBoardsCount();
        Assert.assertEquals(after, before - 1);
    }
}
