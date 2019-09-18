package com.telran.selenium;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TeamDeletionTests extends TestBase {
    @BeforeClass //(enabled=false)
    public void ensurePreconditions() {
        if (!app.getSessionHelper().isUserLoggedIn()) {
            app.getSessionHelper().login("familymelniks@gmail.com", "gnomikim");
        }

    }

    @BeforeMethod
    public void isOnHomePage()  {
        if (!app.getBoardHelper().isTherePersonalBoardsPresent()) {
            app.getBoardHelper().returnToHomePage();

        }
    }

    @Test
    public void testTeamDeletionFromLeftNavMenu() throws InterruptedException {
        int before = app.getTeamHelper().getTeamsCount();
        app.getTeamHelper().clickByFirstTeam();
        app.getTeamHelper().clickByTeamSettings();
        app.getTeamHelper().clickByDeleteTeam();
        app.getTeamHelper().confirmTeamDeletion();
        Thread.sleep(3000);
        int after = app.getTeamHelper().getTeamsCount();
        Assert.assertEquals(after, before - 1);

    }

}
