package tests;

import org.testng.Reporter;
import org.testng.annotations.Test;
import pageObjects.LoginPage;

public class LoginTest extends BaseClass {
    @Test(priority = 1, testName = "login")
    public void login(){
        LoginPage login = new LoginPage(driver);
        login.enterUserName(userName);
        login.enterPassword(password);
        login.clickLogInBTN();
    }
    @Test(priority = 2)
    public void checkMenu(){
        LoginPage login = new LoginPage(driver);
        login.clickMenuBTN();
        login.clickOnAboutLink();
        driver.navigate().back();
        driver.navigate().forward();
    }
}
