package com.telran.selenium.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TeamModificationTests extends TestBase {
    @BeforeClass
    public void ensurePreconditions() {
        if (!app.getTeamHelper().isAnyTeamPresent()) {
            app.getTeamHelper().createTeam();
        }

    }

    @BeforeMethod
    public void isOnHomePage() {
        if (!app.getBoardHelper().isTherePersonalBoardsPresent()) {
            app.getBoardHelper().returnToHomePage();

        }
    }
    @Test
    public  void testRenameTeam() throws InterruptedException {

        app.getTeamHelper().clickByFirstTeam();
        app.getTeamHelper().clickByTeamSettings();
        app.getTeamHelper().initChangeTeamProfile();
        app.getTeamHelper().changeTeamProfile("newTeam","Description");
        app.getTeamHelper().confirmTeamEdition();
        Thread.sleep(5000);
        String newName =app.getTeamHelper().getTeamNameFromTeamPage();
        Assert.assertEquals(newName,"newTeam");
    }
    @AfterClass //(enabled=false)
    public void afterActions() {
        if (!app.getBoardHelper().isTherePersonalBoardsPresent()) {
            app.getTeamHelper().returnToHomePage();
        }
    }
}
