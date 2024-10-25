package org.iit.mmp.patient.pageobjects;

import java.time.Duration;
import java.util.HashMap;

import org.iit.healthcare.mmpinspire.JavaUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScheduleAppointmentPage {
	WebDriver driver;
	public ScheduleAppointmentPage(WebDriver driver){
	    this.driver = driver;
	    if (!driver.getTitle().equals("Shedule Appointments")) {
		      throw new IllegalStateException("This is ScheduleAppointment Page," +
		            " current page is: " + driver.getCurrentUrl());
		    }
	     
	  }
	
	/**
	 * BookAppointment is useful to do a booking with doctorName
	 * @param doctorName
	 * @return 
	 */
	public HashMap<String, String> bookAppointment(String doctorName)
	{
		HashMap<String,String> expectedHMap = new HashMap<String,String>();
		expectedHMap.put("doctor", doctorName);
		
		driver.findElement(By.xpath("//*[@value='Create new appointment']")).click();


		driver.findElement(By.xpath("//h4[text()='Dr."+doctorName+"']/ancestor::ul/following-sibling::button")).click();


		driver.switchTo().frame("myframe");


		driver.findElement(By.id("datepicker")).click();

		//Logic to select the date 
		//Future Date :::October/5/2024
		int duration = 60;
		String expectedDate = JavaUtility.getFutureDate(duration,"MMMMM/d/YYYY");

		expectedHMap.put("date", JavaUtility.getFutureDate(duration,"MM/dd/YYYY"));

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
		return expectedHMap;

	}
	public HashMap<String, String> fetchPatientPortalData()
	{
		HashMap<String,String> actualHMap = new HashMap<String,String>();

		String actualDate = driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[1]")).getText();
		String actualTime=driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[2]")).getText();
		String actualAppointment=driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[3]")).getText();
		String actualDoctor=driver.findElement(By.xpath("//table[@class='table']/tbody/tr[1]/td[4]")).getText();

		actualHMap.put("date",actualDate );
		actualHMap.put("time",actualTime );
		actualHMap.put("sym",actualAppointment);
		actualHMap.put("doctor",actualDoctor);

		System.out.println("Actual HMap Values:::::::" +actualHMap );
		return actualHMap;
	}

}
