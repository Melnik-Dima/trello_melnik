package com.telran.selenium;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardCreationTests extends TestBase {
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

    @Test //(enabled = false)
    public void testBoardCreationFromPlusButtonOnHeader() throws InterruptedException {
        //Thread.sleep(5000);
        int before = getBoardsCount();
        clickOnPlusButtonOnHeader();
        selectCreateBoardFromDropDown();
        String boardName = "QA21" + System.currentTimeMillis();
        fillBoardCreationForm(boardName);
        confirmBoardCreation();
        String createdBoardName = getBoardNameFromBoardPage();
        returnToHomePage();
        int after = getBoardsCount();
        Assert.assertEquals(after, before + 1);
        Assert.assertEquals(createdBoardName.toLowerCase(), boardName.toLowerCase());
    }


    @Test//(enabled = false)
    public void testBoardCreationFromPersonalBoardsSection() throws InterruptedException {
        //Thread.sleep(5000);
        int before = getBoardsCount();
        clickByCreateBoardButtonOfBoardsSection();
        //Thread.sleep(4000);
        String boardName = "QA21" + System.currentTimeMillis();
        typeNewBoardsName(boardName);
        submitBoardCreation();
        String createdBoardName = getBoardNameFromBoardPage();
        returnToHomePage();
        int after = getBoardsCount();
        Assert.assertEquals(after, before + 1);
        Assert.assertEquals(createdBoardName.toLowerCase(), boardName.toLowerCase());
    }

    @AfterClass //(enabled=false)
    public void cleanBoards() throws InterruptedException {
        int i = 0;
        while (i < 2) {
            Thread.sleep(5000);
            openThirdBoard();
            clickOnMenu();
            expandMenu();
            choseCloseBoard();
            clickByClosePopUpButton();
            confirmBoardDeletion();
            returnToHomePage();
            i++;
        }
    }
}
