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

public class TestClient_sendSQL {
	static String createTable = "CREATE TABLE TEST_TABLE(id varchar (50) NOT NULL, "
			+ "name varchar (50) NOT NULL, job_title varchar (50) NOT NULL)";
	static String insertValue = "INSERT INTO TEST_TABLE VALUES('07897', 'Robin', 'Software Engineer')";
	static String selectStmt = "SELECT * FROM TEST_TABLE";
	static String dropTable = "DROP TABLE TEST_TABLE";

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
	public void client_send_sql() {
		WebDriver driver = new ChromeDriver();
		driver.get(address + "login.html");

		try {
			// go to login page, fill in admin, send the inputs, send query on
			// the client.html page, and close
			driver.findElement(By.id("username")).sendKeys("admin");
			driver.findElement(By.id("password")).sendKeys("admin");
			driver.findElement(By.id("submit")).click();
			// create table test_table
			driver.findElement(By.id("textMessage")).sendKeys(createTable);
			Thread.sleep(5000);
			driver.findElement(By.id("send")).click();
			Thread.sleep(1000);
			
			driver.findElement(By.id("textMessage")).clear();
			
			// send insert values into test_table
			driver.findElement(By.id("textMessage")).sendKeys(insertValue);
			Thread.sleep(5000);
			driver.findElement(By.id("send")).click();
			Thread.sleep(1000);
			
			driver.findElement(By.id("textMessage")).clear();
			
			// send select values from test_table
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
