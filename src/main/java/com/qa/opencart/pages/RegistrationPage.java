package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.Utils.ElementUtil;

public class RegistrationPage{
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	By registerLink = By.linkText("Register");
	By firstName = By.id("input-firstname");
	By lastName = By.id("input-lastname");
	By email= By.id("input-email");
	By telephone = By.id("input-telephone");
	By password= By.id("input-password");
	By passwordConfirm = By.id("input-confirm");
	By subscribeYes = By.xpath("");
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		
	}
	
	public void doClickRegister() {
		System.out.println("Clicking on register");
		elementUtil.doClick(registerLink);
	}
	
	public void doFillForm(String fName, String lName, String emailid, String phone, String pwd, String pwdConfirm) {
		
	elementUtil.doSendKeys(firstName, fName);
	elementUtil.doSendKeys(lastName, lName);
	elementUtil.doSendKeys(email, emailid);
	elementUtil.doSendKeys(telephone, phone);
	elementUtil.doSendKeys(password, pwd);
	elementUtil.doSendKeys(passwordConfirm, pwdConfirm);
	
	
		
		
		
		
	}
	
	

}
