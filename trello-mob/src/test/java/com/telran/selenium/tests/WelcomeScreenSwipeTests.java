package com.telran.selenium.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class WelcomeScreenSwipeTests extends TestBase {
    @Test
    public void swipeToLeftTest(){
        app.getSessionHelper().swipeLeft(By.xpath("//*[@resource-id='com.trello:id/view_pager']"));

    }
}
