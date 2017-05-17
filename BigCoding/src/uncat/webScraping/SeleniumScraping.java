package uncat.webScraping;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SeleniumScraping {
	public static void main(String[] args) throws InterruptedException {
		prestoWebUIBootstrapScraping();
		someCapabilityNotes();
	}

	//Ref- https://www.youtube.com/watch?v=OmaD6T2-d_Q&index=22&list=PLUY1lsOTtPeLPAkMpl8EFIDqWgv1QTXST
	private static void someCapabilityNotes() throws InterruptedException {
		// for selenium 2- gecko driver is not necessary
		System.setProperty("webdriver.gecko.driver", "D:\\Libs\\geckodriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
		System.out.println(capabilities.getBrowserName() + capabilities.getVersion());

		// page info
		WebDriver driver = new FirefoxDriver();
		driver.get("http://google.com");
		driver.navigate().refresh();
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getPageSource());
		System.out.println(driver.getTitle());

		// Scroll
		driver.get("http://wikipedia.org");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scrollBy(0,2500)");

		// Changing dimensions
		driver.manage().window().maximize();
		System.out.println("Max size is " + driver.manage().window().getSize());
		Thread.sleep(8000);
		Dimension d = new Dimension(300, 150);
		driver.manage().window().setSize(d);
		Thread.sleep(4000);

		// filling inputs and clearing
		driver.get("http://google.com");
		driver.findElement(By.id("lst-ib")).sendKeys("Selenium Java");
		driver.findElement(By.id("lst-ib")).sendKeys(Keys.RETURN);
		try {
			Thread.sleep(6000);
			driver.findElement(By.id("lst-ib")).clear();
		} catch (Exception e) {
			System.out.println("Unable to clear textbox");
		}

		// click links
		driver.get("http://google.com");
		driver.findElement(By.linkText("Gmail")).click(); // partialLinkText,className,xpath-18,
		Thread.sleep(5000);

		// open new tab
		driver.findElement(By.cssSelector("Body")).sendKeys(Keys.CONTROL + "t");
		driver.get("http://facebook.com");
		driver.quit();

		// take screen shots- lecture 14 (to do)
	}

	private static void prestoWebUIBootstrapScraping() throws InterruptedException {
		// for selenium 2- gecko driver is not necessary
		System.setProperty("webdriver.gecko.driver", "APIs/geckodriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
		System.out.println(capabilities.getBrowserName() + capabilities.getVersion());

		WebDriver webDriver = new FirefoxDriver();
		webDriver.get("http://192.168.50.135:8081");
		Thread.sleep(2000); // to make sure page is fully loaded
		webDriver.findElement(By.xpath("//button[text()='Finished']")).click();
		WebElement webElement = webDriver.findElement(By.xpath("//button[text()[contains(.,'Show')]]"));
		System.out.println("Krstos " + webElement.getText());
		webElement.click();
		List<WebElement> webElementList = webDriver.findElements(By.xpath("//ul[@class='dropdown-menu']"));
		System.out.println("size is " + webElementList.size());
		System.out.println(webElementList.get(2).getText());
		webElement = webElementList.get(2);
		By by = By.linkText("All queries");
		webElement = webElement.findElement(by);
		webElement.click();
		// well.get(2).findElement(By.linkText("All queries")).click();
		// System.out.println(driver.getPageSource());
		// System.out.println("Text is " +
		// driver.findElement(By.className("tinystat")).getText());
		// List<WebElement> webElements =
		// (driver.findElements(By.xpath("//span[@class='tinystat']")));
		webElementList = (webDriver.findElements(By.xpath("//div[@class='query']")));
		for (WebElement webElementInLoop : webElementList) {
			// System.out.println(key.getText());
			System.out.println(webElementInLoop.findElement(By.className("sql")).getText());
			List<WebElement> webElementsNumbers = webElementInLoop.findElements(By.className("tinystat"));
			for (WebElement we : webElementsNumbers) {
				System.out.print(we.getText() + "\t");
			}
			System.out.print("\n");
		}
	}
}
// Ref-
// https://www.youtube.com/watch?v=OmaD6T2-d_Q&index=22&list=PLUY1lsOTtPeLPAkMpl8EFIDqWgv1QTXST
// jars- selenium-server-standalone-3.4.0.jar and mozilla firefox-
// client-combined-3.4.0-nodeps.jar (OS Specific)