package web_test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestLogin_Security {
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
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void loginWithWrongCredentials() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Owen\\workspaceJEE\\webDriver_lib\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(address + "login.html");

		try {
			// go to login page, fill in admin, send the inputs, send query on
			// the client.html page, and close
			driver.findElement(By.id("username")).sendKeys("user");
			driver.findElement(By.id("password")).sendKeys("user");
			driver.findElement(By.id("submit")).click();
			Thread.sleep(1000);

			bool = driver.getCurrentUrl().equals(address + "login.html");

			driver.quit();

		} catch (Exception e) {
			e.printStackTrace();
			driver.quit();
			fail(e.getMessage());
		}

		assertEquals(true, bool);
	}

	@Test
	public void loginAdmin_WithAdminLoggedIn() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Owen\\workspaceJEE\\webDriver_lib\\chromedriver.exe");
		WebDriver driver1 = new ChromeDriver();
		driver1.get(address + "login.html");

		try {
			// go to login page, fill in admin, send the inputs, send query on
			// the client.html page, and close
			driver1.findElement(By.id("username")).sendKeys("admin");
			driver1.findElement(By.id("password")).sendKeys("admin");
			driver1.findElement(By.id("submit")).click();
			Thread.sleep(1000);

			WebDriver driver2 = new ChromeDriver();
			driver2.get(address + "login.html");
			driver2.findElement(By.id("username")).sendKeys("admin");
			driver2.findElement(By.id("password")).sendKeys("admin");
			driver2.findElement(By.id("submit")).click();
			Thread.sleep(1000);

			bool = driver2.getCurrentUrl().equals(address + "login");

			driver1.findElement(By.id("logout")).click();
			driver2.quit();
			driver1.quit();
		} catch (Exception e) {
			e.printStackTrace();
			driver1.quit();
			fail(e.getMessage());
		}

		assertEquals(true, bool);
	}

	@Test
	public void login_unAuth_user_WithAdminLoggedIn() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Owen\\workspaceJEE\\webDriver_lib\\chromedriver.exe");
		WebDriver driver1 = new ChromeDriver();
		driver1.get(address + "login.html");

		try {
			// go to login page, fill in admin, send the inputs, send query on
			// the client.html page, and close
			driver1.findElement(By.id("username")).sendKeys("admin");
			driver1.findElement(By.id("password")).sendKeys("admin");
			driver1.findElement(By.id("submit")).click();
			Thread.sleep(1000);

			WebDriver driver2 = new ChromeDriver();
			driver2.get(address + "login.html");
			driver2.findElement(By.id("username")).sendKeys("user");
			driver2.findElement(By.id("password")).sendKeys("user");
			driver2.findElement(By.id("submit")).click();
			Thread.sleep(1000);

			bool = driver2.getCurrentUrl().equals(address + "login.html");

			driver1.findElement(By.id("logout")).click();
			driver2.quit();
			driver1.quit();
		} catch (Exception e) {
			e.printStackTrace();
			driver1.quit();
			fail(e.getMessage());
		}

		assertEquals(true, bool);
	}

	@Test
	public void login_Logout() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Owen\\workspaceJEE\\webDriver_lib\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(address + "login.html");

		try {
			// go to login page, fill in admin, send the inputs, send query on
			// the client.html page, and close
			driver.findElement(By.id("username")).sendKeys("admin");
			driver.findElement(By.id("password")).sendKeys("admin");
			driver.findElement(By.id("submit")).click();
			Thread.sleep(1000);

			driver.findElement(By.id("logout")).click();

			bool = driver.getCurrentUrl().equals(address + "login.html");

			driver.quit();

		} catch (Exception e) {
			e.printStackTrace();
			driver.quit();
			fail(e.getMessage());
		}

		assertEquals(true, bool);
	}
}
