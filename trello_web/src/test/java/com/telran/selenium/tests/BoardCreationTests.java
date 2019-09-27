package com.telran.selenium.tests;

import com.telran.selenium.model.BoardNames;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BoardCreationTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> validBoards() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"name"});
        list.add(new Object[]{"Name"});
        list.add(new Object[]{"12325"});
        list.add(new Object[]{"$%^&"});
        list.add(new Object[]{"name"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> validBoardsFromcsv() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/BoardsName.csv")));
        String line = reader.readLine();
        while (line != null) {
            list.add(new Object[]{new BoardNames().setBoardName(line.trim())});
            line = reader.readLine();
        }
        return list.iterator();
    }
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

    @Test(dataProvider = "validBoards")
    public void testBoardCreationFromPlusButtonOnHeaderWithDataProvider(String name) throws InterruptedException {
        Thread.sleep(5000);
        BoardNames bName = new BoardNames().setBoardName(name);
        int before = app.getBoardHelper().getBoardsCount();
        app.getBoardHelper().clickOnPlusButtonOnHeader();
        app.getBoardHelper().selectCreateBoardFromDropDown();
        app.getBoardHelper().fillBoardCreationForm(bName);
        app.getBoardHelper().confirmBoardCreation();
        String createdBoardName = app.getBoardHelper().getBoardNameFromBoardPage();
        app.getBoardHelper().returnToHomePage();
        int after = app.getBoardHelper().getBoardsCount();
        Assert.assertEquals(after, before + 1);
        Assert.assertEquals(createdBoardName.toLowerCase(), name.toLowerCase());
    }
    @Test(dataProvider = "validBoardsFromcsv")
    public void testBoardCreationFromPlusButtonOnHeaderWithDataProviderFromCsv(BoardNames name) throws InterruptedException {
        Thread.sleep(5000);
        int before = app.getBoardHelper().getBoardsCount();
        app.getBoardHelper().clickOnPlusButtonOnHeader();
        app.getBoardHelper().selectCreateBoardFromDropDown();
        app.getBoardHelper().fillBoardCreationForm(name);
        app.getBoardHelper().confirmBoardCreation();
        String createdBoardName = app.getBoardHelper().getBoardNameFromBoardPage();
        app.getBoardHelper().returnToHomePage();
        int after = app.getBoardHelper().getBoardsCount();
        Assert.assertEquals(after, before + 1);
        Assert.assertEquals(createdBoardName.toLowerCase(),name.getBoardName().toLowerCase());
    }

    @Test //(enabled = false)
    public void testBoardCreationFromPlusButtonOnHeader() throws InterruptedException {
        Thread.sleep(5000);
        int before = app.getBoardHelper().getBoardsCount();
        app.getBoardHelper().clickOnPlusButtonOnHeader();
        app.getBoardHelper().selectCreateBoardFromDropDown();
        String boardName = "QA21" + System.currentTimeMillis();
        app.getBoardHelper().fillBoardCreationForm(new BoardNames().setBoardName(boardName));
        app.getBoardHelper().confirmBoardCreation();
        String createdBoardName = app.getBoardHelper().getBoardNameFromBoardPage();
        app.getBoardHelper().returnToHomePage();
        int after = app.getBoardHelper().getBoardsCount();
        Assert.assertEquals(after, before + 1);
        Assert.assertEquals(createdBoardName.toLowerCase(), boardName.toLowerCase());
    }


    @Test//(enabled = false)
    public void testBoardCreationFromPersonalBoardsSection() throws InterruptedException {
        //Thread.sleep(5000);
        int before = app.getBoardHelper().getBoardsCount();
        app.getBoardHelper().clickByCreateBoardButtonOfBoardsSection();
        //Thread.sleep(4000);
        String boardName = "QA21" + System.currentTimeMillis();
        app.getBoardHelper().typeNewBoardsName(new BoardNames().setBoardName(boardName));
        app.getBoardHelper().submitBoardCreation();
        String createdBoardName = app.getBoardHelper().getBoardNameFromBoardPage();
        app.getBoardHelper().returnToHomePage();
        int after = app.getBoardHelper().getBoardsCount();
        Assert.assertEquals(after, before + 1);
        Assert.assertEquals(createdBoardName.toLowerCase(), boardName.toLowerCase());
    }

    @AfterClass //(enabled=false)
    public void afterActions() throws InterruptedException {
        app.getBoardHelper().cleanBoards();
    }


}
