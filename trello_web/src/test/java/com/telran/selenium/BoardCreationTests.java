package com.telran.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BoardCreationTests<enabled> extends TestBase {
    @Test //(enabled = false)
    public void testBoardCreationFromPlusButtonOnHeader() throws InterruptedException {
        returnToHomePage();
        Thread.sleep(5000);
        int before=getBoardsCount();
        clickOnPlusButtonOnHeader();
        selectCreateBoardFromDropDown();
        fillBoardCreationForm("QA21");
        confirmBoardCreation();
        Thread.sleep(6000);
        returnToHomePage();

        Thread.sleep(10000);
        int after=getBoardsCount();
        Assert.assertEquals(after,before+1);
    }

    private int getBoardsCount(){
        return driver.findElements(By.xpath("//div[@class='all-boards']//div[2]//ul//li")).size();
    }


    @Test //(enabled = false)
    public void testBoardCreationFromPersonalBoardsSection() throws InterruptedException {
        returnToHomePage();
        Thread.sleep(10000);
        int before=getBoardsCount();
        ckickByCreateBoardButtonOfBoardsSection();
        Thread.sleep(4000);
        typeNewBoardsName("QA21");
        submitBoardCreation();
        Thread.sleep(6000);
        returnToHomePage();
        Thread.sleep(10000);
        int after=getBoardsCount();
        Assert.assertEquals(after,before+1);
    }
    @Test //(enabled=false)
            public void testBoardDeletion () throws InterruptedException{
        //Thread.sleep(5000);
        returnToHomePage();
        Thread.sleep(5000);
        int before=getBoardsCount();
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
        int after=getBoardsCount();
        Assert.assertEquals(after,before-1);

    }



}
