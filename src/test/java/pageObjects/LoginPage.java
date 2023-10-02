package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import utilities.ReadConfig;
import utilities.WebElementUtiliity;

import java.util.List;

public class LoginPage {
    @FindBy(id="user-name")
    private WebElement un;

    @FindBy(id="password")
    private WebElement pwd;

    @FindBy(id="login-button")
    private WebElement loginBTN;

    @FindBy(xpath = "//button[text()='Open Menu']")
    private WebElement menuBTN;

    @FindBy(id="about_sidebar_link")
    private WebElement aboutLink;

    @FindBy(id="onetrust-accept-btn-handler")
    private WebElement cookie;

    @FindBy(xpath = "//div[@class='inventory_list']/div")
    private WebElement inventoryList;

    @FindBy(xpath = "//span[@class='fa-layers-counter shopping_cart_badge']")
    private WebElement cart;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void enterUserName(String userName){
        Reporter.log("Enter user name", true);
        WebElementUtiliity.enterTextInTB(un, userName);
    }

    public void enterPassword(String password){
        Reporter.log("Enter password", true);
        WebElementUtiliity.enterTextInTB(pwd, password);
    }

    public void clickLogInBTN(){
        Reporter.log("click login button", true);
        WebElementUtiliity.clickOnElement(loginBTN);
    }

    public void clickMenuBTN(){
        Reporter.log("click menu button", true);
        WebElementUtiliity.clickOnElement(menuBTN);
    }

    public void clickOnAboutLink(){
        Reporter.log("click about link", true);
        WebElementUtiliity.clickOnElement(aboutLink);
    }

    public void acceptCookie(){
        Reporter.log("click accept coocie", true);
        cookie.click();
    }
    public void selectItemWithHighestPrice(){
        float maxPrice = 0;
       List<WebElement> invList = inventoryList.findElements(By.xpath("//div[@class='inventory_list']/div"));
        for (WebElement ele : invList){
            String price = ele.findElement(By.xpath("//div[@class='pricebar']/div[1]")).getText();
            if (maxPrice < Integer.valueOf(price)){
                maxPrice = Integer.valueOf(price);
            }
        }
        WebElement highestPriceEle = invList.get(0).findElement(By
                .xpath("//div[@class='pricebar']/div[text()='"+ maxPrice + "']/parent::div/button"));
        WebElementUtiliity.clickOnElement(highestPriceEle);
    }

    public boolean validateCart() {
        String text = cart.getText();
        if (Integer.valueOf(text)>0){
            cart.click();
            return true;
        }
        return false;
    }
}
