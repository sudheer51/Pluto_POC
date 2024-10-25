package org.iit.healthcare.mmpinspire;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	protected WebDriver driver;
	String environment;
	String browser;
	protected Properties prop;
	
	@BeforeTest
	public void setUp() throws IOException 
	{
		
		prop = loadProperties("config/mmp_global.properties");
		environment = prop.getProperty("environment");
	}
	@BeforeClass
	public void instantiateDriver() throws IOException
	{
	 
		if(environment.equals("qa"))
		{
			prop = loadProperties("config/mmp_qa.properties");
		 
		}
		else if(environment.equals("dev"))
		{
			prop = loadProperties("config/mmp_dev.properties");

		}
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
	}
	public Properties loadProperties(String filePath) throws IOException
	{
		Properties prop = new Properties();
		File f = new File(filePath);
		FileInputStream fis = new FileInputStream(f);
		prop.load(fis);
		return prop;
	}


}
