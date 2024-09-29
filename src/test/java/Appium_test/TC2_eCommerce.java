package Appium_test;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class TC2_eCommerce extends TestBase{
	
	@Test
	public void FillForm_ErrorValidation() throws InterruptedException, IOException
	{
		//FP.setNameField("Srilatha M");
		FP.setGender("female");
		FP.setCountrySelection("Argentina");
		FP.submitForm();
		String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
		AssertJUnit.assertEquals(toastMessage,"Please enter your name");
		captureScreenshot(driver, "Enter Name");
	}
	
}
