package com.telran.selenium;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TeamCreationTests extends TestBase {
    @BeforeClass
    public void ensurePreconditions() {
        if (!app.isUserLoggedIn()) {
            app.login("familymelniks@gmail.com", "gnomikim");
        }

    }

    @BeforeMethod
    public void isOnHomePage() {
        if (!app.isTherePersonalBoardsPresent()) {
            app.returnToHomePage();

        }
    }

    @Test
    public void testTeamCreationFromPlusButtonOnHeader() throws InterruptedException {
        //Thread.sleep(10000);
        int before = app.getTeamsCount();
        app.clickOnPlusButtonOnHeader();
        app.selectCreateTeamFromDropDown();
        String teamName = "QA21" + System.currentTimeMillis();
        app.fillTeamCreationForm(teamName, "descriptionQA21");
        app.clickContinueButton();
        //Thread.sleep(4000);
        String createdTeamName = app.getTeamNameFromTeamPage();
        app.returnToHomePage();
        //Thread.sleep(5000);
        int after = app.getTeamsCount();
        Assert.assertEquals(createdTeamName.toLowerCase(), teamName.toLowerCase());
        Assert.assertEquals(after, before + 1);
    }

    @Test //(enabled = false)
    public void testTeamCreationCansellationFromPlusButtonOnHeader() throws InterruptedException {
        //Thread.sleep(5000);
        int before = app.getTeamsCount();
        app.clickOnPlusButtonOnHeader();
        app.selectCreateTeamFromDropDown();
        app.fillTeamCreationForm("QA21", "descriptionQA21");
        app.clickXButton();
        app.returnToHomePage();
        Thread.sleep(3000);
        int after = app.getTeamsCount();
        Assert.assertEquals(after, before);

    }

    @Test //(enabled = false)
    public void testTeamCreationFromPlusButtonOnLeftNavMenu() throws InterruptedException {
        //Thread.sleep(5000);
        int before = app.getTeamsCount();
        app.clickOnPlusButtonFromLeftNavMenu();
        String teamName = "QA21" + System.currentTimeMillis();
        app.fillTeamCreationForm(teamName, "descriptionQA21");
        app.clickContinueButton();
        String createdTeamName = app.getTeamNameFromTeamPage();
        app.returnToHomePage();
        Thread.sleep(3000);
        int after = app.getTeamsCount();
        Assert.assertEquals(after, before + 1);
        Assert.assertEquals(createdTeamName.toLowerCase(), teamName.toLowerCase());
    }

    @AfterClass //(enabled=false)
    public void cleanTeams() {
        int i = 0;
        while (i < 2) {
            app.clickByFirstTeam();
            app.clickByTeamSettings();
            app.clickByDeleteTeam();
            app.confirmTeamDeletion();
            i++;
        }

    }

}