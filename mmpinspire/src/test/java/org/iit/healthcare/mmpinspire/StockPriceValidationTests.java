package org.iit.healthcare.mmpinspire;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StockPriceValidationTests {
	
 WebDriver driver;
	@Test
	public void validateStockPriceDetails()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://money.rediff.com/gainers");
		String stockNameArr[]= {"Advik Capital","ABC"};
		//List<WebElement> hyperlinkList = driver.findElements(By.tagName("a"));
		//Set<String> wSet = driver.getWindowHandles();
		
		HashMap<String,String> stockDetailsHMap = new HashMap<String,String>();
		
		for(int i=0;i<stockNameArr.length;i++)
		{
			
			String stockPrice = fetchStockPrice(stockNameArr[i]);
			if(stockPrice!=null)
			{
				stockDetailsHMap.put(stockNameArr[i], stockPrice);
				
			}
		}
		System.out.println("Printing the Stock Details:"+ stockDetailsHMap);
	}
	public String fetchStockPrice(String stockName)
	{
		String stockPrice=null;
		try 
		{
			stockPrice = driver.findElement(By.xpath("//a[normalize-space()='"+stockName+"']/parent::td/following-sibling::td[3]")).getText();
			System.out.println("Stock Name :: "+stockName);
			System.out.println("Stock Price Details :: " + stockPrice);
		}
		catch(Exception e)
		{
			System.out.println("No Matching Stock found!!!!!  " + stockName);
		}
		return stockPrice;
	}

}
