package com.telran.selenium.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TeamCreationTests extends TestBase {
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
        app.getTeamHelper().fillTeamCreationForm(teamName, "descriptionQA21");
        app.getTeamHelper().clickContinueButton();
        //Thread.sleep(4000);
        String createdTeamName = app.getTeamHelper().getTeamNameFromTeamPage();
        app.getTeamHelper().returnToHomePage();
        //Thread.sleep(5000);
        int after = app.getTeamHelper().getTeamsCount();
        Assert.assertEquals(createdTeamName.toLowerCase(), teamName.toLowerCase());
        Assert.assertEquals(after, before + 1);
    }

    @Test //(enabled = false)
    public void testTeamCreationCansellationFromPlusButtonOnHeader() throws InterruptedException {
        //Thread.sleep(5000);
        int before = app.getTeamHelper().getTeamsCount();
        app.getTeamHelper().clickOnPlusButtonOnHeader();
        app.getTeamHelper().selectCreateTeamFromDropDown();
        app.getTeamHelper().fillTeamCreationForm("QA21", "descriptionQA21");
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
        app.getTeamHelper().fillTeamCreationForm(teamName, "descriptionQA21");
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

