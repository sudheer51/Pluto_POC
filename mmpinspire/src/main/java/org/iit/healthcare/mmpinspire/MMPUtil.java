package org.iit.healthcare.mmpinspire;


import java.util.HashMap;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MMPUtil {
	WebDriver driver;
	
	public MMPUtil(WebDriver driver) 
	{
		
		this.driver= driver;
	}
	public void launchBrowser(String url )
	{
		driver.get(url);

	}
	 
	 

}
