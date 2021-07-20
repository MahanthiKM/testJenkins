package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.Utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil elementUtil;

	private By productHeader = By.cssSelector("div#content h1");
	private By productThumbnails = By.cssSelector("ul.thumbnails img");
	private By productMetadata = By.cssSelector("div#content  ul.list-unstyled:nth-of-type(1) li");
	private By productPricedata = By.cssSelector("div#content  ul.list-unstyled:nth-of-type(2) li");
	private By quantity = By.id("input-quanity");
	private By addToCart = By.id("button-cart");
	private By successMessage = By.cssSelector("div.alert.alert-success.alert-dismissible");
	private Map<String, String> productInfoMap;

	public ProductInfoPage(WebDriver driver) {

		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public String getProductHeaderText() {
		return elementUtil.doGetText(productHeader);
	}

	public int getProductImagesCount() {

		return elementUtil.waitForElementsToBeVisible(productThumbnails, 5).size();

	}

	public Map<String, String> getProductInfo() {
		productInfoMap = new HashMap<String, String>();
		productInfoMap.put("name", getProductHeaderText());
		getProductMetaData();
		getProductPriceData();
		return productInfoMap;
		
	}
	private void getProductMetaData() {
	List<WebElement> metaDataList = elementUtil.doGetElements(productMetadata);
		for (WebElement e : metaDataList) {
			String text = e.getText();
			String meta[] = text.split(":");
			String metaKey = meta[0].trim();
			String metametaValue = meta[1].trim();
			productInfoMap.put(metaKey, metametaValue);
		}
		}
		
	private void getProductPriceData() {	
		List <WebElement> metaPriceList = elementUtil.doGetElements(productPricedata);
		String price = metaPriceList.get(0).getText().trim();
		String extaxprice = metaPriceList.get(1).getText().trim();
		productInfoMap.put("price", price);
		productInfoMap.put("exTaxPrice", extaxprice);
		}
		
		
		
		
		
		
		
		
		

	}

