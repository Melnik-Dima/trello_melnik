package com.telran.selenium;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BoardCreationTest extends TestBase {
    @Test
    public void testBoardCreation () {
        Assert.assertTrue(createBoard("BoardTest1"));
    }
public boolean createBoard(String name){
    ckickByCreateBoardButton();
    typeNewBoardsName(name);
    submitBoardCreation();
    return true;


}

    public void submitBoardCreation() {
        click(By.xpath("//*[@type='submit']"));
    }

    public void typeNewBoardsName(String name) {
        type(By.cssSelector(".subtle-input"),name);
    }

    public void ckickByCreateBoardButton() {
        click(By.cssSelector(".board-tile.mod-add"));
    }

}
