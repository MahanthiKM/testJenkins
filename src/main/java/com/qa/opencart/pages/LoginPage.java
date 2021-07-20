package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.Utils.Constants;
import com.qa.opencart.Utils.ElementUtil;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	//1. By Locator
	
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	
	//2. Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
				
	}
	
	//3. Page actions
	
	public String getLoginPageTile()
	{
		return elementUtil.waitForTitleIs(Constants.LOGIN_PAGE_TITLE, 5);
	}
	 
    public boolean isForgotPwdLinkExist() {
    	
    	return elementUtil.doIsDisplayed(forgotPwdLink);
    }
	
	public AccountsPage doLogin(String un, String pwd) {
		System.out.println("Login with:"+un+" : "+pwd);
		elementUtil.doSendKeys(emailId, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginBtn);
	    return new AccountsPage(driver);
		
	}
	

}
