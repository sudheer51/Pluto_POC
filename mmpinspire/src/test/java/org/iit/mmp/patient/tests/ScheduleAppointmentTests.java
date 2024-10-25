package org.iit.mmp.patient.tests;

import java.io.IOException;
import java.util.HashMap;

import org.iit.healthcare.mmpinspire.BaseClass;
import org.iit.healthcare.mmpinspire.JavaUtility;
import org.iit.healthcare.mmpinspire.MMPUtil;
import org.iit.mmp.patient.pageobjects.HomePage;
import org.iit.mmp.patient.pageobjects.LoginPage;
import org.iit.mmp.patient.pageobjects.ScheduleAppointmentPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ScheduleAppointmentTests extends BaseClass{


	@Parameters({"modulename","doctor"})
	@Test
	public void bookAppointmentTests(String modulename,String doctor)
	{
		MMPUtil mmpUtil =new MMPUtil(driver);
		mmpUtil.launchBrowser(prop.getProperty("patient_url"));
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(prop.getProperty("patient_username"),prop.getProperty("patient_password"));
		HomePage homePage = new HomePage(driver);
		homePage.navigateToAModule(modulename);
		ScheduleAppointmentPage sPage = new ScheduleAppointmentPage(driver);
		HashMap<String,String> expectedHMap =sPage.bookAppointment(doctor);
		HashMap<String,String> actualHMap=sPage.fetchPatientPortalData();
		Assert.assertTrue(expectedHMap.equals(actualHMap));
	}

	 //https://testng.org/parameters.html
	@DataProvider(name = "loginvalid")
	public String[][] createData1() throws IOException
	{
	  String data[][]= JavaUtility.readXLSX("logindata.xlsx");
	  return data;
	}

	 
	@Test(dataProvider = "loginvalid")
	public void verifyData1(String username, String password) {
	 System.out.println(username + " " + password);
	}
 

}
