package com.telran.selenium.tests;

import org.testng.annotations.Test;

public class UserModTests extends TestBase {
    @Test
    public void changeAvatarTest() throws InterruptedException {
        app.getUserHelper().clickOnAvatar();
        app.getUserHelper().openProfileFromDropdown();
        app.getUserHelper().initAvatarChanging();
        app.getUserHelper().addPicture();
        Thread.sleep(3000);

    }
}
