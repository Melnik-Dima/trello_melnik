package com.telran.selenium.tests;

import com.telran.selenium.model.TeamData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TeamCreationTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> validTeams() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"name", "description"});
        list.add(new Object[]{"Name", "descr1"});
        list.add(new Object[]{"12325", "Descr"});
        list.add(new Object[]{"$%^&", "12345"});
        list.add(new Object[]{"name", ""});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> validTeamsFromcsv() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/TeamSheet4.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(",");
            list.add(new Object[]{new TeamData().withTeamName(split[0]).withDescription(split[1])});
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
            app.getTeamHelper().returnToHomePage();

        }
    }

    @Test
    public void testTeamCreationFromPlusButtonOnHeader() throws InterruptedException {
        //Thread.sleep(10000);
        int before = app.getTeamHelper().getTeamsCount();
        app.getTeamHelper().clickOnPlusButtonOnHeader();
        app.getTeamHelper().selectCreateTeamFromDropDown();
        String teamName = "QA21" + System.currentTimeMillis();
        app.getTeamHelper().fillTeamCreationForm(new TeamData().withTeamName(teamName).withDescription("descriptionQA21"));
        app.getTeamHelper().clickContinueButton();
        //Thread.sleep(4000);
        String createdTeamName = app.getTeamHelper().getTeamNameFromTeamPage();
        app.getTeamHelper().returnToHomePage();
        //Thread.sleep(5000);
        int after = app.getTeamHelper().getTeamsCount();
        Assert.assertEquals(createdTeamName.toLowerCase(), teamName.toLowerCase());
        Assert.assertEquals(after, before + 1);
    }

    @Test(dataProvider = "validTeams")
    public void testTeamCreationWithDataProvider(String teamName, String description) {
        //Thread.sleep(10000);
        TeamData team = new TeamData().withTeamName(teamName).withDescription(description);
        int before = app.getTeamHelper().getTeamsCount();
        app.getTeamHelper().clickOnPlusButtonOnHeader();
        app.getTeamHelper().selectCreateTeamFromDropDown();
        app.getTeamHelper().fillTeamCreationForm(team);
        app.getTeamHelper().clickContinueButton();
        //Thread.sleep(4000);
        String createdTeamName = app.getTeamHelper().getTeamNameFromTeamPage();
        app.getTeamHelper().returnToHomePage();
        //Thread.sleep(5000);
        int after = app.getTeamHelper().getTeamsCount();
        Assert.assertEquals(createdTeamName.toLowerCase(), teamName.toLowerCase());
        Assert.assertEquals(after, before + 1);
    }

    @Test(dataProvider = "validTeamsFromcsv")
    public void testTeamCreationWithDataProviderFromCsv(TeamData team) {
        //Thread.sleep(10000);
        int before = app.getTeamHelper().getTeamsCount();
        app.getTeamHelper().clickOnPlusButtonOnHeader();
        app.getTeamHelper().selectCreateTeamFromDropDown();
        app.getTeamHelper().fillTeamCreationForm(team);
        app.getTeamHelper().clickContinueButton();
        //Thread.sleep(4000);
        String createdTeamName = app.getTeamHelper().getTeamNameFromTeamPage();
        app.getTeamHelper().returnToHomePage();
        //Thread.sleep(5000);
        int after = app.getTeamHelper().getTeamsCount();
        Assert.assertEquals(createdTeamName.toLowerCase(), team.getTeamName().toLowerCase());
        Assert.assertEquals(after, before + 1);
    }

    @Test //(enabled = false)
    public void testTeamCreationCansellationFromPlusButtonOnHeader() throws InterruptedException {
        //Thread.sleep(5000);
        int before = app.getTeamHelper().getTeamsCount();
        app.getTeamHelper().clickOnPlusButtonOnHeader();
        app.getTeamHelper().selectCreateTeamFromDropDown();
        app.getTeamHelper().fillTeamCreationForm(new TeamData().withTeamName("QA21").withDescription("descriptionQA21"));
        app.getTeamHelper().clickXButton();
        app.getTeamHelper().returnToHomePage();
        Thread.sleep(3000);
        int after = app.getTeamHelper().getTeamsCount();
        Assert.assertEquals(after, before);

    }

    @Test //(enabled = false)
    public void testTeamCreationFromPlusButtonOnLeftNavMenu() throws InterruptedException {
        //Thread.sleep(5000);
        int before = app.getTeamHelper().getTeamsCount();
        app.getTeamHelper().clickOnPlusButtonFromLeftNavMenu();
        String teamName = "QA21" + System.currentTimeMillis();
        app.getTeamHelper().fillTeamCreationForm(new TeamData().withTeamName(teamName).withDescription("descriptionQA21"));
        app.getTeamHelper().clickContinueButton();
        String createdTeamName = app.getTeamHelper().getTeamNameFromTeamPage();
        app.getTeamHelper().returnToHomePage();
        Thread.sleep(3000);
        int after = app.getTeamHelper().getTeamsCount();
        Assert.assertEquals(after, before + 1);
        Assert.assertEquals(createdTeamName.toLowerCase(), teamName.toLowerCase());
    }

    @AfterClass //(enabled=false)
    public void afterActions() {
        app.getTeamHelper().cleanTeams();
    }


}

