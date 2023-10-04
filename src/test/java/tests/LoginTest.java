package tests;

import org.testng.Reporter;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import utilities.WebElementUtiliity;

public class LoginTest extends BaseClass {
    @Test(priority = 1, testName = "login")
    public void login() throws InterruptedException {
        LoginPage login = new LoginPage(driver);
        login.enterUserName(userName);
        login.enterPassword(password);
        login.clickLogInBTN();
        Thread.sleep(3000);
        WebElementUtiliity.scrollPageUsingPixel(0,100);
        Thread.sleep(300);
        WebElementUtiliity.scrollPageUsingPixel(0,-100);
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
