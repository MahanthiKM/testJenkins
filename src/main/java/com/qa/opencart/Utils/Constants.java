package com.qa.opencart.Utils;

import java.util.Arrays;
import java.util.List;

public class Constants {

	
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	public static final String ACCOUNT_PAGE_HEADER = "Your Store";
	
	public static List<String> getExpectedAccSecList() {
		return Arrays.asList("My Account", "My Orders", "My Affiliate Account","Newsletter");
		
	}
}
