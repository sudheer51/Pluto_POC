package org.iit.mmp.patient.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	/*
	 * The public methods represent the services that the page offers
		Try not to expose the internals of the page
		Generally don’t make assertions
		Methods return other PageObjects
		Need not represent an entire page
		Different results for the same action are modelled as different methods
	 */
	//default value
	WebDriver driver;//null
	int i ;//0
	String str;//null
	
	@FindBy(how = How.ID, using = "username")
	private WebElement usernameWE;
	
//	private By usernameLocator =By.id("username");
	private By passwordLocator= By.id("password");
	private By submitLocator = By.name("submit");
	
	public LoginPage(WebDriver driver){
	    this.driver = driver;
	     if (!driver.getTitle().equals("Login")) {
	      throw new IllegalStateException("This is Login Page," +
	            " current page is: " + driver.getCurrentUrl());
	    }
	    PageFactory.initElements(driver,this);
	  }
	
	public String login(String uname,String pword)
	{
		usernameWE.sendKeys(uname);
		driver.findElement(passwordLocator).sendKeys(pword);
		driver.findElement( submitLocator).click();
		String msg = driver.findElement(By.tagName("h3")).getText();
		return msg;
	}

}

















