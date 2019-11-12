package com.telran.selenium.tests;

import com.telran.selenium.model.BoardNames;
import org.testng.annotations.Test;

public class BoardCreationTests extends TestBase {
    @Test
    public void testBoardCreation(){
        app.getBoardHelper().clickOnPlusButton();
        String boardName = "QA21" + System.currentTimeMillis();
        app.getBoardHelper().fillBoardCreationForm(new BoardNames().setBoardName(boardName));
        app.getBoardHelper().confirmBoardCreation();
    }
}
