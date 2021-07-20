package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	/**
	 * This method is used to initialize the driver on the basis of the given
	 * browser name
	 * 
	 * @param prop
	 * @return
	 */
	public WebDriver init_driver(Properties prop) {
		String browserName = prop.getProperty("browser");
		System.out.println("Browser name is :" + browserName);
		optionsManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight");

		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("chrome");
			} else {
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("chrome");
			} else {
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			}

		} else {
			System.out.println("Please enter correct browser: " + browserName);
		}

		getDriver().get(prop.getProperty("url"));
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		return getDriver();

	}

	private void init_remoteDriver(String browser) {
		System.out.println("Running test on Grid server: " + browser);
		if (browser.equals("chrome")) {
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(ChromeOptions.CAPABILITY, optionsManager.getChromeOptions());
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (browser.equals("firefox")) {
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionsManager.getChromeOptions());
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static WebDriver getDriver() {
		return tlDriver.get();

	}

	public Properties init_prop() {
		prop = new Properties();
		FileInputStream ip = null;
		String envName = System.getProperty("env");

		if (envName == null) {
			System.out.println("Running on Environment PROD env");
			try {
				ip = new FileInputStream("./src/test/resources/config/config.properties");

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("Running on " + envName + " environment");
			try {
				switch (envName) {

				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				default:
					break;
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		try {
			prop.load(ip);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;

	}

	// take screenshot

	public String getScreenshot() {

		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;

	}

}
