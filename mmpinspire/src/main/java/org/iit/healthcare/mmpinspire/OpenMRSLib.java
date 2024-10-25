package org.iit.healthcare.mmpinspire;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OpenMRSLib {

	static WebDriver driver;
	public static void main(String[] args) 
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://demo.openmrs.org/openmrs/login.htm");
		driver.findElement(By.id("username")).sendKeys("Admin");
		driver.findElement(By.id("password")).sendKeys("Admin123");
		driver.findElement(By.id("Inpatient Ward")).click();
		driver.findElement(By.id("loginButton")).click();
		driver.findElement(By.id("appointmentschedulingui-homeAppLink-appointmentschedulingui-homeAppLink-extension")).click();
		driver.findElement(By.id("appointmentschedulingui-manageAppointmentTypes-app")).click();
		String serviceName="Oncology";
		boolean deleteResult = deleteServiceType(serviceName);
		boolean searchResult = searchServiceType(serviceName);

		if(deleteResult==true&& searchResult==false)
		{
			System.out.println("Delete Service Type is completed successfully" );
		}
		else
		{
			System.out.println("Delete Service Type is not successful in execution" );
		}
	}
	public static boolean deleteServiceType(String serviceType)
	{
		boolean result=false;
		try {
			List<WebElement> pageList = driver.findElements(By.xpath("//span/a[@tabIndex='0']"));
			outerloop:
				for(int pageIndex=1;pageIndex<=pageList.size();pageIndex++)
				{
					List<WebElement> serviceTypeList = driver.findElements(By.xpath("//table[@id='appointmentTypesTable']/tbody/tr/td[1]"));
					for(int i=0;i<serviceTypeList.size();i++)
					{

						if(serviceType.equals(serviceTypeList.get(i).getText()))
						{

							driver.findElement(By.xpath("//td[text()='"+serviceType+"']/following-sibling::td[3]/span/i[@title='Delete']")).click();
							driver.findElement(By.cssSelector("div.simplemodal-data>div>button.confirm.right")).click();
							System.out.println("Service Name Found and Deleted Successfully!!!!! " + serviceType);
							result=true;
							break outerloop;
						}

					}
					pageList = driver.findElements(By.xpath("//span/a[@tabIndex='0']"));
					pageList.get(pageIndex).click();
					serviceTypeList = driver.findElements(By.xpath("//table[@id='appointmentTypesTable']/tbody/tr/td[1]"));
				}
		}
		catch(Exception e)
		{
			//System.out.println("Exception caught::" + e.getMessage());
			System.out.println("Service Name Not Found and Delete unsuccessful " + serviceType);
		}

		return result;

	}
	public static boolean searchServiceType(String serviceType)
	{
		boolean result=false;

		try {
			List<WebElement> pageList = driver.findElements(By.xpath("//span/a[@tabIndex='0']"));
			for(int pageIndex=1;pageIndex<=pageList.size();pageIndex++)
			{
				List<WebElement> serviceTypeList = driver.findElements(By.xpath("//table[@id='appointmentTypesTable']/tbody/tr/td[1]"));
				for(int i=0;i<serviceTypeList.size();i++)
				{

					if(serviceType.equals(serviceTypeList.get(i).getText()))
					{

						System.out.println("Service Name Found!!!!! " + serviceType);
						result=true;
						break;
					}
					pageList = driver.findElements(By.xpath("//span/a[@tabIndex='0']"));
					pageList.get(pageIndex).click();
					serviceTypeList = driver.findElements(By.xpath("//table[@id='appointmentTypesTable']/tbody/tr/td[1]"));
				}

			}
		}
		catch(Exception e)
		{
		
			//System.out.println("Exception caught::" + e.getMessage());
			System.out.println("Service Name Not Found and Search unsuccessful " + serviceType);
		}
		return result;
	}
}
