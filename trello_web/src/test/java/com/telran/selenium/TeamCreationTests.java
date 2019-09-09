package com.telran.selenium;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TeamCreationTests extends TestBase {
    @BeforeClass
    public void ensurePreconditions() {
        if (!isUserLoggedIn()) {
            login("familymelniks@gmail.com", "gnomikim");
        }

    }

    @BeforeMethod
    public void isOnHomePage() throws InterruptedException {
        if (!isTherePersonalBoardsPresent()) {
            returnToHomePage();

        }
    }

    @Test
    public void testTeamCreationFromPlusButtonOnHeader() throws InterruptedException {
        Thread.sleep(10000);
        int before = getTeamsCount();
        clickOnPlusButtonOnHeader();
        selectCreateTeamFromDropDown();
        String teamName = "QA21";
        fillTeamCreationForm(teamName, "descriptionQA21");
        clickContinueButton();
        Thread.sleep(4000);
        String createdTeamName = getTeamNameFromTeamPage();
        returnToHomePage();
        Thread.sleep(5000);
        int after = getTeamsCount();
        Assert.assertEquals(createdTeamName.toLowerCase(), teamName.toLowerCase());
        Assert.assertEquals(after, before + 1);
    }

    @Test //(enabled = false)
    public void testTeamCreationCansellationFromPlusButtonOnHeader() throws InterruptedException {
        Thread.sleep(5000);
        int before = getTeamsCount();
        clickOnPlusButtonOnHeader();
        selectCreateTeamFromDropDown();
        fillTeamCreationForm("QA21", "descriptionQA21");
        clickXButton();
        returnToHomePage();
        Thread.sleep(3000);
        int after = getTeamsCount();
        Assert.assertEquals(after, before);

    }

    @Test //(enabled = false)
    public void testTeamCreationFromPlusButtonOnLeftNavMenu() throws InterruptedException {
        Thread.sleep(5000);
        int before = getTeamsCount();
        clickOnPlusButtonFromLeftNavMenu();
        String teamName = "QA21" + System.currentTimeMillis();
        fillTeamCreationForm(teamName, "descriptionQA21");
        clickContinueButton();
        String createdTeamName = getTeamNameFromTeamPage();
        returnToHomePage();
        Thread.sleep(3000);
        int after = getTeamsCount();
        Assert.assertEquals(after, before + 1);
        Assert.assertEquals(createdTeamName.toLowerCase(), teamName.toLowerCase());
    }

}
