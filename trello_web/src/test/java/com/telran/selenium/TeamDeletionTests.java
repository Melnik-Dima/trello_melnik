package com.telran.selenium;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TeamDeletionTests extends TestBase {
    @BeforeClass
    public void ensurePreconditions() {
        if (!isUserLoggedIn()) {
            login("familymelniks@gmail.com", "gnomikim");
        }

    }

    @BeforeMethod
    public void isOnHomePage()  {
        if (!isTherePersonalBoardsPresent()) {
            returnToHomePage();

        }
    }

    @Test
    public void testTeamDeletionFromLeftNavMenu() throws InterruptedException {
        int before = getTeamsCount();
        clickByFirstTeam();
        clickByTeamSettings();
        clickByDeleteTeam();
        confirmTeamDeletion();
        Thread.sleep(3000);
        int after = getTeamsCount();
        Assert.assertEquals(after, before - 1);

    }

}
