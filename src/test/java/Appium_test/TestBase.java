package Appium_test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import pages.FormPages;
import pages.ProductCatalogue;

public class TestBase {
	
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public FormPages FP;
	public ProductCatalogue PC;
	
	@BeforeClass
	public void configureAppium() throws MalformedURLException {
		service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\srila\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
			service.start();
			
								
			UiAutomator2Options options = new UiAutomator2Options();
			options.setDeviceName("Srilatha phone"); //emulator
			
			
			options.setChromedriverExecutable("//Users//rahulshetty//documents//chromedriver 11");
			
			options.setApp("C:\\Users\\srila\\eclipse-workspace\\Appium_Capstone\\src\\test\\java\\resources\\General-Store.apk");	
			
			 driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
			 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			 FP = new FormPages(driver);
			 PC = new ProductCatalogue(driver);
		
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
        service.stop();
	}
	public void captureScreenshot(WebDriver driver,String tName) throws IOException{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File target=new File(System.getProperty("user.dir")+"/Screenshots/"+tName+".png");
		FileUtils.copyFile(source, target);
	}


}
