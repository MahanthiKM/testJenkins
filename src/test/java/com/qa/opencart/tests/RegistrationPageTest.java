package com.qa.opencart.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegistrationPageTest extends BaseTest{
	@DataProvider
	public Object[][] formData()
	{
		return new Object[][] {
			{"Mahanti","Kanakamedala","maxxxxx@gmail.com","1234567", "223311","223311"},
			{"Mahanti","","maxxxxx@gmail.com","1234567", "223311","223311"},
			{"Mahanti","Kanakamedala","","1234567", "223311","223311"},
			{"Mahanti","Kanakamedala","maxxxxx@gmail.com","", "223311","223311"},
			{"Mahanti","Kanakamedala","maxxxxx@gmail.com","1234567", "","223311"},
			{"Mahanti","Kanakamedala","maxxxxx@gmail.com","1234567", "223311",""}
			
				
		};
		
		
	}
	
	@Test(dataProvider="formData")
	public void fillFormTest(String fName, String lName, String emailid, String phone, String pwd, String pwdConfirm) throws InterruptedException
	{
	regPage.doClickRegister();
	regPage.doFillForm(fName, lName, emailid,  phone, pwd,  pwdConfirm);
	Thread.sleep(2000);
		
	}

}
