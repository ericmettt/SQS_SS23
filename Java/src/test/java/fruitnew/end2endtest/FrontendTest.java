package fruitnew.end2endtest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;

import org.openqa.selenium.chrome.*;

import org.junit.Test;
public class FrontendTest {

        @Test
        public void testTextfield() throws InterruptedException {

            // Optional. If not specified, WebDriver searches the PATH for chromedriver.
            System.setProperty("webdriver.chrome.driver","C:\\Users\\Eric\\Downloads\\chromedriver_win32\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            WebDriver driver = new ChromeDriver(options);

            driver.get("http://localhost:3000/");

            Thread.sleep(2000);

            WebElement textbox = driver.findElement(By.id("outlined-basic"));
            // Enter the text "fruit" into the textbox
            textbox.sendKeys("Apple");
            // Find the submit button by its ID attribute
            WebElement submitButton = driver.findElement(By.id("Submitbutton"));
            // Click the submit button
            submitButton.click();
            // Close the browser
            driver.quit();

        }
}
