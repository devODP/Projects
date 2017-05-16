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

import Server_side.AuthenticationScheme;
import Server_side.User_Info;

public class TestLogin {
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
	public void loginWithCorrectCredentials() {
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
			
			bool = driver.getCurrentUrl().equals(address + "client.html");
			
			driver.findElement(By.id("logout")).click();
			driver.quit();
			
		} catch (Exception e) {
			e.printStackTrace();
			driver.quit();
			fail(e.getMessage());
		}
		
		assertEquals(true, bool);
	}
}
