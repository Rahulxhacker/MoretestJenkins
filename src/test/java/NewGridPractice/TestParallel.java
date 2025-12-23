package NewGridPractice;

import java.net.MalformedURLException;
import java.net.URI;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestParallel {
	protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();
	public Capabilities cap;
	
	public WebDriver getDriver() {
		return driver.get();
	}
	
	
	@Parameters({"browser"})
	@Test
	public void doSearch(String browser) throws MalformedURLException, InterruptedException {
		if(browser.equals("chrome")) {
			cap = new ChromeOptions(); //chrome browser will open
		}else if(browser.equals("edge")) {
			cap = new EdgeOptions(); //edge browser will open
		}
		
		driver.set(new RemoteWebDriver(URI.create("http://192.168.47.116:4444/wd/hub").toURL(),cap));
		getDriver().get("https://google.com");
		getDriver().findElement(By.name("q")).sendKeys("Hello Grid!!! " + browser);
		System.out.println(getDriver().getTitle() + "------on browser-----"+browser);
		Thread.sleep(1000);
		getDriver().quit();
	}
	
}
