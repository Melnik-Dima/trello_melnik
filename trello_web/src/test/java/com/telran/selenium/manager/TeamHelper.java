package com.telran.selenium.manager;

import com.telran.selenium.model.TeamData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TeamHelper extends HelperBase {

    public TeamHelper(WebDriver driver) {
        super(driver);
    }

    public void clickContinueButton() {
        click(By.cssSelector("[type='submit']"));
    }

    public void fillTeamCreationForm(TeamData team) {
        type((By.cssSelector("[data-test-id='header-create-team-name-input']")),team.getTeamName());
        //waitForElementAndType((By.cssSelector("[data-test-id='header-create-team-name-input']")), 20, team.getTeamName());
        type(By.cssSelector("textarea"), team.getDescription());
    }

    public void selectCreateTeamFromDropDown() {
        waitForElementAndClick(By.cssSelector("[data-test-id='header-create-team-button']"),20);
        //click(By.cssSelector("[data-test-id='header-create-team-button']"));
    }

    public void clickXButton() {
        click(By.cssSelector("button.qb90FI2uVIybRy._2b_HpRl1Tyl1YK"));
    }

    public String getTeamNameFromTeamPage() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1")));
        return driver.findElement(By.cssSelector("h1")).getText();
    }

    public int getTeamsCount() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='_mtkwfAlvk6O3f']/../../..//li")));
        return driver.findElements(By.xpath("//*[@class='_mtkwfAlvk6O3f']/../../..//li")).size();
    }

    public void confirmTeamDeletion() {
        click(By.cssSelector(".pop-over.is-shown input[type='submit']"));
    }

    public void clickByDeleteTeam() {
        click(By.cssSelector(".quiet-button"));
    }

    public void clickByFirstTeam() {
        waitForElementAndClick(By.xpath("//*[@class='_mtkwfAlvk6O3f']/../../..//li[1]"),20);
        click(By.xpath("//*[@class='_mtkwfAlvk6O3f']/../../..//li[1]"));
    }

    public void clickByTeamSettings() {
        new WebDriverWait(driver, 15).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("ul .icon-gear.icon-sm")));
        click(By.cssSelector("ul .icon-gear.icon-sm"));
    }

    public void cleanTeams() {
        int count = getTeamsCount();
        while (count > 5) {
            clickByFirstTeam();
            clickByTeamSettings();
            clickByDeleteTeam();
            confirmTeamDeletion();
            count = getTeamsCount();

        }
    }

    public void initChangeTeamProfile() {
        click(By.cssSelector(".js-edit-profile"));
        // waitForElementAndClick(By.cssSelector(".js-edit-profile"),10);

    }

    public void changeTeamProfile(String name, String description) {
        type(By.name("displayName"), name);
        type(By.name("desc"), description);
    }

    public void confirmTeamEdition() {
        click(By.cssSelector(".js-submit-profile"));
    }

    public boolean isAnyTeamPresent() {
        return getTeamsCount() > 0;
    }

    public void createTeam() {
        clickOnPlusButtonOnHeader();
        selectCreateTeamFromDropDown();
        String teamName = "QA21" + System.currentTimeMillis();
        fillTeamCreationForm(new TeamData().withTeamName(teamName).withDescription("descriptionQA21"));
        clickContinueButton();
        returnToHomePage();

    }
}
