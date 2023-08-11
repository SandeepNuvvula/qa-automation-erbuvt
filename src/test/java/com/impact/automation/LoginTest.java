package com.impact.automation;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginTest {
	
	static WebDriver driver;
	
	@BeforeAll
	public static void initDriver() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\narasimha\\Documents\\New folder\\automation\\src\\main\\resources\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		
	}


	@Test
	public void testValidLogin() {
		driver.get("https://qa-challenge.codesubmit.io");
		WebElement usernameField = driver.findElement(By.xpath("//input[@id='user-name']"));
		WebElement passwordField = driver.findElement(By.xpath("//input[@id='password']"));
		usernameField.sendKeys("standard_user");
		passwordField.sendKeys("secret_sauce");
		WebElement loginButton = driver.findElement(By.xpath("//input[@id='login-button']"));
		
		loginButton.click();
		String actual = driver.getTitle();
		System.out.println(driver.getPageSource());
		System.out.println(driver.getWindowHandle());
		System.out.println(driver.getCurrentUrl());
		System.out.println("Actual msg "+actual);
		Assertions.assertEquals("Swag Labs", actual);
	}
	
	@Test
	public void testLockedUserLogin() {
		driver.get("https://qa-challenge.codesubmit.io");
		WebElement usernameField = driver.findElement(By.xpath("//input[@id='user-name']"));
		WebElement passwordField = driver.findElement(By.xpath("//input[@id='password']"));
		usernameField.sendKeys("locked_out_user");
		passwordField.sendKeys("secret_sauce");
		WebElement loginButton = driver.findElement(By.xpath("//input[@id='login-button']"));
		
		loginButton.click();
		
		WebElement errorMsg  = driver.findElement(By.xpath("//div[@class='error-message-container error']//h3[text()='Epic sadface: Sorry, this user has been locked out.']"));
		String eooer = errorMsg.getText();
		Assertions.assertEquals("Epic sadface: Sorry, this user has been locked out.", eooer);
	}
	
	@Test
	public void testProblemUserLogin() {
		driver.get("https://qa-challenge.codesubmit.io");
		WebElement usernameField = driver.findElement(By.xpath("//input[@id='user-name']"));
		WebElement passwordField = driver.findElement(By.xpath("//input[@id='password']"));
		usernameField.sendKeys("problem_user");
		passwordField.sendKeys("secret_sauce");
		WebElement loginButton = driver.findElement(By.xpath("//input[@id='login-button']"));	
		loginButton.click();
		String actual = driver.getTitle();
		Assertions.assertEquals("Swag Labs", actual);
	}
	
	@Test
	public void testPerformanceGlitchUserLogin() {
		driver.get("https://qa-challenge.codesubmit.io");
		WebElement usernameField = driver.findElement(By.xpath("//input[@id='user-name']"));
		WebElement passwordField = driver.findElement(By.xpath("//input[@id='password']"));
		usernameField.sendKeys("performance_glitch_user");
		passwordField.sendKeys("secret_sauce");
		WebElement loginButton = driver.findElement(By.xpath("//input[@id='login-button']"));	
		loginButton.click();
		String actual = driver.getTitle();
		Assertions.assertEquals("Swag Labs", actual);
	}
	
	@Test
	public void testInvalidUserLogin() {
		driver.get("https://qa-challenge.codesubmit.io");
		WebElement usernameField = driver.findElement(By.xpath("//input[@id='user-name']"));
		WebElement passwordField = driver.findElement(By.xpath("//input[@id='password']"));
		usernameField.sendKeys("invalid_user");
		passwordField.sendKeys("secret_sauce");
		WebElement loginButton = driver.findElement(By.xpath("//input[@id='login-button']"));
		
		loginButton.click();
		
		WebElement errorMsg  = driver.findElement(By.xpath("//div[@class='error-message-container error']//h3[text()='Epic sadface: Username and password do not match any user in this service']"));
		String eooer = errorMsg.getText();
		Assertions.assertEquals("Epic sadface: Username and password do not match any user in this service", eooer);
	}
	
	@AfterAll
	public static void tearDown() {
		driver.close();
	}

}
