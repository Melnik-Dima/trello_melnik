package com.telran.selenium;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BoardCreationTests extends TestBase {
    @Test //(enabled = false)
    public void testBoardCreationFromPlusButtonOnHeader() throws InterruptedException {
        int before = getBoardsCount();
        clickOnPlusButtonOnHeader();
        selectCreateBoardFromDropDown();
        fillBoardCreationForm("QA21");
        confirmBoardCreation();
        Thread.sleep(5000);
        returnToHomePage();
        Thread.sleep(5000);
        int after = getBoardsCount();
        Assert.assertEquals(after, before + 1);
    }

    @Test //(enabled = false)
    public void testBoardCreationFromPersonalBoardsSection() throws InterruptedException {
        Thread.sleep(5000);
        int before = getBoardsCount();
        ckickByCreateBoardButtonOfBoardsSection();
        Thread.sleep(4000);
        typeNewBoardsName("QA21");
        submitBoardCreation();
        Thread.sleep(6000);
        returnToHomePage();
        Thread.sleep(5000);
        int after = getBoardsCount();
        Assert.assertEquals(after, before + 1);
    }

    @Test //(enabled = false)
    public void testBoardDeletion() throws InterruptedException {
        //Thread.sleep(5000);
       // returnToHomePage();
        Thread.sleep(5000);
        int before = getBoardsCount();
        openThirdBoard();
        expandMenu();
        choseCloseBoard();
        Thread.sleep(5000);
        clickByClosePopUpButton();
        Thread.sleep(4000);
        confirmBoardDeletion();
        Thread.sleep(6000);
        returnToHomePage();
        Thread.sleep(5000);
        int after = getBoardsCount();
        Assert.assertEquals(after, before-1);
    }
}
