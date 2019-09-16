package com.telran.selenium;

import net.bytebuddy.utility.JavaModule;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TeamDeletionTests extends TestBase {
    @BeforeClass (enabled=false)
    public void ensurePreconditions() {
        if (!app.isUserLoggedIn()) {
            app.login("familymelniks@gmail.com", "gnomikim");
        }

    }

    @BeforeMethod
    public void isOnHomePage()  {
        if (!app.isTherePersonalBoardsPresent()) {
            app.returnToHomePage();

        }
    }

    @Test
    public void testTeamDeletionFromLeftNavMenu() throws InterruptedException {
        int before = app.getTeamsCount();
        app.clickByFirstTeam();
        app.clickByTeamSettings();
        app.clickByDeleteTeam();
        app.confirmTeamDeletion();
        Thread.sleep(3000);
        int after = app.getTeamsCount();
        Assert.assertEquals(after, before - 1);

    }

}
