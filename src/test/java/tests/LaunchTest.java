package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.XLUtility;

import java.io.IOException;


public class LaunchTest extends BaseClass {
	
	@Test(testName = "launch", dataProvider = "loginData",groups = {"smokeTest"})
	public void launch(String un, String pwd) {
		Reporter.log("user name = "+un+" password = "+pwd,true);
		Reporter.log("inside test3 "+Thread.currentThread().getId(), true);
	}
	
	@Test(testName = "closeBrowser")
	public void closeBrowser() {
		Reporter.log("close the browser ",true);
		Reporter.log("inside test3 "+Thread.currentThread().getId(), true);
	}
	
	@Test(testName = "test3")
	public void test3(){
		Reporter.log("inside test3 "+Thread.currentThread().getId(), true);	
		}
	
	@Test(testName = "test4")
	public void test4(){
		Reporter.log("inside test4 "+Thread.currentThread().getId(), true);	
		}
	
	@DataProvider(name = "loginData")
	public Object[][] getData() throws IOException{
		String path = "./src/test/java/testData/TestData.xlsx";
		String sheetName = "loginTest";
		int rowCount = XLUtility.getRowCount(path, sheetName);
		int cellCount = XLUtility.getCellCount(path, sheetName, 1);
		Object [][] data = new Object[rowCount][cellCount] ;
		for(int i=1; i<rowCount; i++) {
			for(int j= 0; j< cellCount; j++) {
				data[i-1][j] = XLUtility.getCellData(path, sheetName, i, j);
			}
		}
		return  data;
	}
}
