package com.telran.selenium;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class BoardCreationTests extends TestBase {
    @Test
    public void testBoardCreationFromPlusButtonOnHeader() throws InterruptedException {
        returnToHomePage();
        Thread.sleep(5000);
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

    @Test//(enabled = false)
    public void testBoardCreationFromPersonalBoardsSection() throws InterruptedException {
        Thread.sleep(15000);
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

@AfterClass
    public void cleanBoards() throws InterruptedException {
        int i=0;
        while (i<2){
    Thread.sleep(5000);
    openThirdBoard();
    clickOnMenu();
    expandMenu();
    choseCloseBoard();
    Thread.sleep(5000);
    clickByClosePopUpButton();
    Thread.sleep(4000);
    confirmBoardDeletion();
    Thread.sleep(6000);
    returnToHomePage();
      i++;
        }
}
}
