package org.iit.healthcare.mmpinspire;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Unit test for simple App.
 */
public class XpathAxesEx {

	/**
	 * Rigorous Test :-)
	 */
	@Test
	public void verifyStockDetails() {

		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();
		driver.get("https://money.rediff.com/gainers");

		//Scenario1
		String stockPrice = driver.findElement(By.xpath("//a[normalize-space()='SEPC Ltd']/parent::td/following-sibling::td[3]")).getText();
		System.out.println("Stock Price Details :: " + stockPrice);


		//Scenario2
		String stockName="Piotex Industries";
		stockPrice = driver.findElement(By.xpath("//a[normalize-space()='"+stockName+"']/parent::td/following-sibling::td[3]")).getText();
		System.out.println("Stock Name :: "+stockName);
		System.out.println("Price Details:::: "+ stockPrice);

		//Scenario 3
		stockName="ABC";
		try {
			stockPrice = driver.findElement(By.xpath("//a[normalize-space()='"+stockName+"']/parent::td/following-sibling::td[3]")).getText();
			System.out.println("Stock Name :: "+stockName);
			System.out.println("Stock Price Details :: " + stockPrice);
		}
		catch(Exception e)
		{
			System.out.println("No Matching Stock found!!!!!  " + stockName);
		}


	}

}
