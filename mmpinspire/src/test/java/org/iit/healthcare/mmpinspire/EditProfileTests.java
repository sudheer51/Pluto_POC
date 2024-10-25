package org.iit.healthcare.mmpinspire;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EditProfileTests extends BaseClass{
	
	 
	 
	@Test
	public void bookAppointmentTests()
	{
		HashMap<String,String> expectedHMap = new HashMap<String,String>();
		HashMap<String,String> actualHMap = new HashMap<String,String>();
		/**
		 * doctor,time,sym,date
		 */
		
		 MMPUtil mmpUtil =new MMPUtil(driver);
//		 mmpUtil.launchBrowser();
//		 mmpUtil.login();
 		
		 	navigateToAModule("Schedule Appointment");
		driver.findElement(By.xpath("//*[@value='Create new appointment']")).click();
		
		String doctor="Charlie";
		expectedHMap.put("doctor", doctor);
		
		//click on book button
		bookAppointment(doctor);
		
		
		driver.switchTo().frame("myframe");
		
		 
		driver.findElement(By.id("datepicker")).click();
		
		//Logic to select the date 
		//Future Date :::October/5/2024
		int duration = 60;
		String expectedDate = getFutureDate(duration,"MMMMM/d/YYYY");
		
		expectedHMap.put("date", getFutureDate(duration,"MM/dd/YYYY"));
		
		String expectedDateArr[]=expectedDate.split("/");
		
		String expectedMonth=expectedDateArr[0];
		
		String expectedDay=expectedDateArr[1];
		
		String expectedYear=expectedDateArr[2];
		
		String actualYear = driver.findElement(By.className("ui-datepicker-year")).getText();
		
		while(!(expectedYear.equals(actualYear)))
		{
			driver.findElement(By.xpath("//span[text()='Next']")).click();	
			actualYear = driver.findElement(By.className("ui-datepicker-year")).getText();
		}
		String actualMonth = driver.findElement(By.className("ui-datepicker-month")).getText();
		//https://corejava25hours.com/2021/02/13/type-casting-boxinginboxing-and-outboxing/
		while(!(expectedMonth.equals(actualMonth)))
		{
			driver.findElement(By.xpath("//span[text()='Next']")).click();
			actualMonth = driver.findElement(By.className("ui-datepicker-month")).getText();
		}
		
		driver.findElement(By.linkText(expectedDay)).click();
	
		
		Select timeSelect = new Select(driver.findElement(By.id("time")));
		timeSelect.selectByVisibleText("11Am");
		
		
		expectedHMap.put("time", timeSelect.getFirstSelectedOption().getText());
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@id='status']"), "OK"));
		

		driver.findElement(By.id("ChangeHeatName")).click();
		
		driver.switchTo().defaultContent();
		//driver.switchTo().activeElement();
		
		driver.findElement(By.id("sym")).sendKeys("AUT Tester");
		
		expectedHMap.put("sym",  driver.findElement(By.id("sym")).getAttribute("value"));
 
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		
		System.out.println("Expected HMap Values:::::::" +expectedHMap );
		
		String actualDate = driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[1]")).getText();
		String actualTime=driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[2]")).getText();
		String actualAppointment=driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[3]")).getText();
		String actualDoctor=driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[4]")).getText();
		
		actualHMap.put("date",actualDate );
		actualHMap.put("time",actualTime );
		actualHMap.put("sym",actualAppointment);
		actualHMap.put("doctor",actualDoctor);
		
		System.out.println("Actual HMap Values:::::::" +actualHMap );
		Assert.assertTrue(expectedHMap.equals(actualHMap));
		
	}
	public static String getFutureDate(int n,String format)
	{		
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.getTime());
		 
		cal.add(Calendar.DATE, n);
		System.out.println(cal.getTime());
		//"MM/dd/YYYY"
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String futureDate = sdf.format(cal.getTime());
		System.out.println("Future Date :::" + futureDate);
		
		return futureDate;
	}
	/**
	 * BookAppointment is useful to do a booking with doctorName
	 * @param doctorName
	 */
	public void bookAppointment(String doctorName)
	{
			driver.findElement(By.xpath("//h4[text()='Dr."+doctorName+"']/ancestor::ul/following-sibling::button")).click();

	}
	/**
	 * 
	 * @param doctorName
	 * @param specialization
	 */
	public void bookAppointment(String doctorName,String specialization)
	{
			//driver.findElement(By.xpath("//h4[text()='Dr."+doctorName+"']/ancestor::ul/following-sibling::button")).click();

	}
	public void navigateToAModule(String moduleName)
	{
		driver.findElement(By.xpath("//span[contains(text(),'"+moduleName+"')]")).click();

	}

}
