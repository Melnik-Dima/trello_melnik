package com.telran.selenium;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TeamCreationTests extends TestBase {
    @Test
    public void testTeamCreationFromPlusButtonOnHeader() throws InterruptedException {
        Thread.sleep(5000);
        int before=getTeamsCount();
        clickOnPlusButtonOnHeader();
        selectCreateTeamFromDropDown();
        String teamName = "QA21";
        fillTeamCreationForm(teamName, "descriptionQA21");
        clickContinueButton();
        String createdTeamName=getTeamNameFromTeamPage();
        returnToHomePage();
        Thread.sleep(5000);
        int after=getTeamsCount();
        Assert.assertEquals(createdTeamName.toLowerCase(),teamName.toLowerCase());
        Assert.assertEquals(after,before+1);
    }

    @Test //(enabled = false)
    public void testTeamCreationCansellationFromPlusButtonOnHeader() throws InterruptedException {
        Thread.sleep(5000);
        int before=getTeamsCount();
        clickOnPlusButtonOnHeader();
        selectCreateTeamFromDropDown();
        fillTeamCreationForm("QA21","descriptionQA21");
        clickXButton();
        returnToHomePage();
        Thread.sleep(3000);
        int after=getTeamsCount();
        Assert.assertEquals(after,before);

    }
    @Test //(enabled = false)
    public void testTeamCreationFromPlusButtonOnLeftNavMenu() throws InterruptedException {
        Thread.sleep(5000);
        int before=getTeamsCount();
        clickOnPlusButtonFromLeftNavMenu();
        String teamName = "QA21";
        fillTeamCreationForm(teamName, "descriptionQA21");
        clickContinueButton();
        String createdTeamName=getTeamNameFromTeamPage();
        returnToHomePage();
        Thread.sleep(3000);
        int after=getTeamsCount();
        Assert.assertEquals(after,before+1);
        Assert.assertEquals(createdTeamName.toLowerCase(),teamName.toLowerCase());
    }

}
