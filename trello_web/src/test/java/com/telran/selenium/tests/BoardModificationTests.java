package com.telran.selenium.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardModificationTests extends TestBase {
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
    public void renameBoardTest() {
        app.getBoardHelper().openThirstBoard();
        app.getBoardHelper().initChangeBoardName();
        String name="newBoardsName";
        app.getBoardHelper().typeNewName(name);
        app.getBoardHelper().confirmChangingBoard();
        String BoardName = app.getBoardHelper().getBoardNameFromBoardPage();
        Assert.assertEquals(BoardName, name);
    }

    @AfterClass //(enabled=false)
    public void afterActions() {
        if (!app.getBoardHelper().isTherePersonalBoardsPresent()) {
            app.getTeamHelper().returnToHomePage();
        }
    }
}