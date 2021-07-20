package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.Utils.Constants;
import com.qa.opencart.Utils.ElementUtil;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtil elementUtil;

	By header = By.cssSelector("div#logo h1");
	By acctSections = By.cssSelector("div#content h2");
	By searchBox = By.name("search");
	By searchButton = By.xpath("//*[@id='search']/span/button");
	By logOut = By.linkText("Logout");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public String accountPageTitleTest() {

		return elementUtil.waitForTitleIs(Constants.ACCOUNT_PAGE_TITLE, 5);
	}

	public String getAccountsPageHeader() {
		return elementUtil.doGetText(header);
	}

	public boolean isLogoutLinkExist() {
		return elementUtil.doIsDisplayed(logOut);
	}

	public void doLogout() {
		if (isLogoutLinkExist()) {
			elementUtil.doClick(logOut);
		}
	}

	public List<String> getAccountSecList() {
	 List <WebElement> acctSecList=	elementUtil.waitForElementsToBeVisible(acctSections, 5);
	 List<String> acctSecNameList = new ArrayList<String>();
	 
	 for (WebElement e : acctSecList)
	 {
		acctSecNameList.add(e.getText()); 
		 
	 }
	 return acctSecNameList;
	}
	
	public boolean isSearchExists() {
		return elementUtil.doIsDisplayed(searchBox);
	}
	
	public SearchResultsPage doSearch(String productName) {
		System.out.println("Searching for the Product: "+productName);
		elementUtil.doSendKeys(searchBox, productName);
		elementUtil.doClick(searchButton);
				return new SearchResultsPage(driver);
	}
}
