package com.lambdatest;

import io.appium.java_client.MobileBy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class android {
    String username = System.getenv("LT_USERNAME") == null ? "LT_USERNAME" //Enter the Username here
            : System.getenv("LT_USERNAME");
    String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "LT_ACCESS_KEY"  //Enter the Access key here
            : System.getenv("LT_ACCESS_KEY");
    public static RemoteWebDriver driver = null;
    public String gridURL = "@mobile-hub.lambdatest.com/wd/hub";
    public String status = "passed";
    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("build", "JUNIT Native App automation");
        capabilities.setCapability("name", "Java JUnit Android Pixel 6");
        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("deviceName", "Pixel 6"); //Enter the name of the device here
        capabilities.setCapability("isRealMobile", true);
        capabilities.setCapability("platformVersion","12");
        capabilities.setCapability("app","APP_URL"); //Enter the App ID here
        capabilities.setCapability("deviceOrientation", "PORTRAIT");
        capabilities.setCapability("console",true);
        capabilities.setCapability("network",false);
        capabilities.setCapability("visual",true);
        try
        {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + gridURL), capabilities);
        }
        catch (MalformedURLException e)
        {
            System.out.println("Invalid grid URL");
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testSimple() throws Exception
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id("readMoreButton"))).click();
            Thread.sleep(3000);

            wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id("backButtonReadMorePage"))).click();;
            Thread.sleep(3000);

            wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Location"))).click();
            Thread.sleep(3000);
            driver.navigate().back();

            wait.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath("//android.widget.FrameLayout[@content-desc=\"Browser\"]/android.widget.FrameLayout/android.widget.ImageView"))).click();;
            Thread.sleep(10000);

            wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id("url"))).sendKeys("https://www.lambdatest.com/");

            wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id("find"))).click();
            Thread.sleep(5000);

            wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Home"))).click();;
            Thread.sleep(3000);

            wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("drawer open"))).click();;
            Thread.sleep(3000);

            wait.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath("//android.widget.CheckedTextView[contains(@text,\"Push Notification\")]"))).click();;
            Thread.sleep(3000);

            wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("drawer Closed"))).click();;
            Thread.sleep(3000);

            status="passed"; 
        }
            catch (Exception e)
             {
                System.out.println(e.getMessage());
                status="failed";
             }
    }
    @After
    public void tearDown() throws Exception
    {
        if (driver != null)
        {
            driver.executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
}

