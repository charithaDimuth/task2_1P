package sit707_week2;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SeleniumOperations {

    public static void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void officeworks_registration_page(String url) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Registration values
        String firstName = "Charitha";
        String lastName = "Dimuth";
        String email = "CharithaDimuth@gmail.com";
        String password = "SecurePass@123";
        String confirmPassword = "SecurePass@123";
        String phoneNumber = "0123456789";

        try {
            driver.get(url);
            sleep(2);

            driver.findElement(By.id("firstname")).sendKeys(firstName);
            driver.findElement(By.id("lastname")).sendKeys(lastName);
            driver.findElement(By.id("email")).sendKeys(email);
            driver.findElement(By.id("password")).sendKeys(password);
            driver.findElement(By.id("confirmPassword")).sendKeys(confirmPassword);
            driver.findElement(By.id("phoneNumber")).sendKeys(phoneNumber);

            WebElement createAccountButton = driver.findElement(By.xpath("//button[contains(text(),'Create Account')]"));
            createAccountButton.click();

            takeScreenshot(driver, "officeworks_registration.png");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void alternative_registration_page(String url) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Amazon registration values
        String fullName = "Charitha Dimuth";
        String email = "charitha.dimuth@amazon.com";
        String password = "AmazonSecure@123";

        try {
            driver.get(url);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Fill Amazon registration form
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_customer_name"))).sendKeys(fullName);
            driver.findElement(By.id("ap_email")).sendKeys(email);
            driver.findElement(By.id("ap_password")).sendKeys(password);
            driver.findElement(By.id("ap_password_check")).sendKeys(password);

            WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("continue")));
            continueButton.click();

           
            sleep(2);
            takeScreenshot(driver, "amazon_registration.png");

        } catch (Exception e) {
            System.out.println("Error during Amazon registration: " + e.getMessage());
        }
    }

    public static void takeScreenshot(WebDriver driver, String filename) {
        try {
            String screenshotPath = "C:/Users/YourUsername/Documents/SeleniumScreenshots"; 

            File screenshotsDir = new File(screenshotPath);
            if (!screenshotsDir.exists()) {
                screenshotsDir.mkdirs();
            }

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(screenshotsDir, filename);
            FileUtils.copyFile(srcFile, destFile);

            System.out.println("Screenshot saved: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error capturing screenshot: " + e.getMessage());
        }
    }
}
