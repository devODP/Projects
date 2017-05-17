package web_test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestClient_Security {
	static Properties prop = new Properties();
	static boolean bool;
	static String address;

	@BeforeClass
	public static void prep() {
		try {
			FileInputStream config = new FileInputStream(
					"C:\\Users\\Owen\\workspaceJEE\\DB_Ser_Cli\\src\\test\\java\\web_test\\global_variables.properties");
			prop.load(config);
			address = prop.getProperty("domain");
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Owen\\workspaceJEE\\webDriver_lib\\chromedriver.exe");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void notAuthenticated() throws InterruptedException {
		WebDriver driver = new ChromeDriver();

		driver.get(address + "client.html");
		bool = driver.getCurrentUrl().equals(address + "login.html");
		Thread.sleep(2000);
		driver.quit();

		assertEquals(true, bool);
	}

	@Test
	public void IsolatedAccess() {
		WebDriver driver1 = new ChromeDriver();

		try {
			driver1.get(address + "login.html");
			driver1.findElement(By.id("username")).sendKeys("admin");
			driver1.findElement(By.id("password")).sendKeys("admin");
			driver1.findElement(By.id("submit")).click();
			
			WebDriver driver2 = new ChromeDriver();
			Thread.sleep(2000);
			driver2.get(address + "client.html");
			bool = driver2.getCurrentUrl().equals(address + "login.html");
			
			driver1.findElement(By.id("logout")).click();
			driver2.quit();
			driver1.quit();
		} catch (InterruptedException e) {
			e.printStackTrace();
			fail(e.getMessage());
			driver1.quit();
		}

		assertEquals(true, bool);
	}
}
