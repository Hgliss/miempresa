package com.miempresa.gestion_usuarios.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

class LoginLocalSeleniumTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() { if (driver != null) driver.quit(); }

    @Test
    void login_ok_en_mi_portal() {
        driver.get("http://localhost:8080/login.html");
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("admin@demo.com");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("admin123");
        driver.findElement(By.id("btn")).click();

        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("msg")));
        Assertions.assertEquals("Login OK", msg.getText());
    }
}

