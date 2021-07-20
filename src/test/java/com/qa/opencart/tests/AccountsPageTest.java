package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.Utils.Constants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void AccountsPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@Test
	public void accountsPageTitleTest() {
		String title = accountsPage.accountPageTitleTest();
		System.out.println(" Accounts page title is :"+title);
		Assert.assertEquals(title, Constants.ACCOUNT_PAGE_TITLE);

	}

	@Test
	public void accPageSectionsListTest() {

		List<String> actualSecList = accountsPage.getAccountSecList();
		System.out.println("Accounts Section List" + actualSecList);
		Assert.assertEquals(actualSecList, Constants.getExpectedAccSecList());
	}

	@Test
	public void LogoutlinkTest() {
		Assert.assertTrue(accountsPage.isLogoutLinkExist());
	}

	@Test
	public void searchExistTest() {

		Assert.assertTrue(accountsPage.isSearchExists());
	}

	@Test
	public void headerTest() {
		Assert.assertEquals(accountsPage.getAccountsPageHeader(), Constants.ACCOUNT_PAGE_HEADER);
	}


}
