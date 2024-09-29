package Appium_test;


import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Utils.ExcelUtility;

public class TC1_eCommerce extends TestBase{
	SoftAssert SA;

	@Test(dataProvider="getData", priority = 1)
	public void FillForm(String Name, String Gender, String Country) throws InterruptedException, IOException
	{	
		FP.setNameField(Name);
		Thread.sleep(1000);
		FP.setGender(Gender);
		FP.setCountrySelection(Country);
		FP.submitForm();
		AssertJUnit.assertTrue(driver.findElements(By.xpath("(//android.widget.Toast)[1]")).size()<1);
		captureScreenshot(driver, "Items Display");
		
	}
	
	@Test(priority=2, dependsOnMethods = "FillForm")
	public void ProductAddingToCart() throws InterruptedException {
		Thread.sleep(2000);
		PC.addItemToCartByIndex(0);
		PC.addItemToCartByIndex(0);
		PC.goToCartPage();
		Thread.sleep(2000);
	}

	
	@DataProvider
	public String[][] getData() throws IOException
	{
		String Xlpath = "C:\\Users\\srila\\eclipse-workspace\\Appium_Capstone\\src\\test\\java\\testData\\Data.xlsx";
		String Xsheetname = "Sheet1";
		int rowCount = ExcelUtility.getRowCount(Xlpath, Xsheetname);
		int cellCount = ExcelUtility.getCellCount(Xlpath, Xsheetname, rowCount);
		String[][] data = new String[rowCount][cellCount];
		for(int i=1;i<=rowCount;i++) {
			for(int j=0;j<cellCount;j++) {
			data[i-1][j]=ExcelUtility.getCellData(Xlpath, Xsheetname, i, j);
			}
		}
		return data;
	}
	
}
