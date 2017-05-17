package web_test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestClient_fileUpload {
	static String dropTable = "DROP TABLE AGENT";
	static String selectStmt = "SELECT * FROM AGENT";
	
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
	public void client_uploadFile() {
		WebDriver driver = new ChromeDriver();
		driver.get(address + "login.html");

		try {
			// go to login page, fill in admin, send the inputs, send query on
			// the client.html page, and close
			driver.findElement(By.id("username")).sendKeys("admin");
			driver.findElement(By.id("password")).sendKeys("admin");
			driver.findElement(By.id("submit")).click();

			driver.findElement(By.id("file")).sendKeys("C:\\Users\\Owen\\Desktop\\Agent.csv");
			driver.findElement(By.id("ul")).click();
			Thread.sleep(2000);

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

	@Test
	public void client_uploadFile_Insert() {
		WebDriver driver = new ChromeDriver();
		driver.get(address + "login.html");

		try {
			// go to login page, fill in admin, send the inputs, send query on
			// the client.html page, and close
			driver.findElement(By.id("username")).sendKeys("admin");
			driver.findElement(By.id("password")).sendKeys("admin");
			driver.findElement(By.id("submit")).click();

			// upload
			driver.findElement(By.id("file")).sendKeys("C:\\Users\\Owen\\Desktop\\Agent.csv");
			driver.findElement(By.id("ul")).click();
			Thread.sleep(2000);

			//insert
			driver.findElement(By.id("ui")).click();
			Thread.sleep(2000);

			// send select values from agent
			driver.findElement(By.id("textMessage")).sendKeys(selectStmt);
			Thread.sleep(5000);
			driver.findElement(By.id("send")).click();
			Thread.sleep(5000);

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

	@AfterClass
	public static void dropTables() {
		WebDriver driver = new ChromeDriver();
		driver.get(address + "login.html");

		try {
			// go to login page, fill in admin, send the inputs, send query on
			// the client.html page, and close
			driver.findElement(By.id("username")).sendKeys("admin");
			driver.findElement(By.id("password")).sendKeys("admin");
			driver.findElement(By.id("submit")).click();

			driver.findElement(By.id("textMessage")).sendKeys(dropTable);
			driver.findElement(By.id("send")).click();
			
			driver.findElement(By.id("logout")).click();
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
			driver.quit();
		}
	}
}
