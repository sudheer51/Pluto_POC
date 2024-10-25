package org.iit.mmp.patient.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

	protected WebDriver driver;
	public HomePage(WebDriver driver){
	    this.driver = driver;
	     if (!driver.getTitle().equals("home")) {
	      throw new IllegalStateException("This is Home Page," +
	            " current page is: " + driver.getCurrentUrl());
	    }
	  }
		public void navigateToAModule(String moduleName)
		{
			driver.findElement(By.xpath("//span[contains(text(),'"+moduleName+"')]")).click();

		}
}
