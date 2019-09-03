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
    click(By.cssSelector(".board-tile.mod-add"));
    click(By.cssSelector(".subtle-input"));
    type(By.cssSelector(".subtle-input"),name);
    click(By.xpath("//*[@type='submit']"));
    return true;


}

}
