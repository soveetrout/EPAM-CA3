package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ResourceBundle;

public class GmailTest {
    WebDriver driver;
    @BeforeClass
    @Parameters({"browser","url"})
    void setup(String browser,String link)
    {
        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver=new ChromeDriver();
        }
        else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver=new EdgeDriver();
        }

        driver.get(link);
        driver.manage().window().maximize();

    }
    @Test
    public void login() throws InterruptedException {
        ResourceBundle r=ResourceBundle.getBundle("config");
        String username=r.getString("username");
        driver.findElement(By.xpath("//input[@id='identifierId']")).sendKeys(username);
        driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
        Thread.sleep(5000);
        Assert.assertEquals(driver.getTitle(), "Gmail");
    }
    @AfterClass
    void close(){

        driver.close();
    }
}
