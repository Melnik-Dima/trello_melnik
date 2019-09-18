package com.telran.selenium;

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

    public void fillTeamCreationForm(String teamName, String description) {
        waitForElementAndType((By.cssSelector("[data-test-id='header-create-team-name-input']")), 20, teamName);
        type(By.cssSelector("textarea"), description);
    }

    public void selectCreateTeamFromDropDown() {
        click(By.cssSelector("[data-test-id='header-create-team-button']"));
    }

    public void clickXButton() {
        click(By.cssSelector("button.qb90FI2uVIybRy._2b_HpRl1Tyl1YK"));
    }

    public String getTeamNameFromTeamPage() {
        return driver.findElement(By.cssSelector("h1")).getText();
    }

    public int getTeamsCount() {
        new WebDriverWait(driver, 15).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='_mtkwfAlvk6O3f']/../../..//li")));
        return driver.findElements(By.xpath("//*[@class='_mtkwfAlvk6O3f']/../../..//li")).size();
    }

    protected void confirmTeamDeletion() {
        click(By.cssSelector(".pop-over.is-shown input[type='submit']"));
    }

    public void clickByDeleteTeam() {
        click(By.cssSelector(".quiet-button"));
    }

    public void clickByFirstTeam() {
        click(By.xpath("//*[@class='_mtkwfAlvk6O3f']/../../..//li[1]"));
    }

    public void clickByTeamSettings() {
        new WebDriverWait(driver, 15).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@class='_3AG-Gnm-Fqx-I3']//li[4]")));
        click(By.xpath("//li[@class='_3AG-Gnm-Fqx-I3']//li[4]"));
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

}
