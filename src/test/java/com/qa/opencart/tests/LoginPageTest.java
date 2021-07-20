package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.Utils.Constants;

public class LoginPageTest extends BaseTest {

	@Test
	public void loginPageTitleTest() {

		String loginTitle = loginPage.getLoginPageTile();
		System.out.println("Login page Title is: " + loginTitle);
		Assert.assertEquals(loginTitle, Constants.LOGIN_PAGE_TITLE);

	}
	
	@Test 
	public void forgotPwdLinkTest()
	{
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}

	@Test
	public void loginTest() {
		accountsPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertEquals(accountsPage.accountPageTitleTest(),Constants.ACCOUNT_PAGE_TITLE);
		
		
	}
}
