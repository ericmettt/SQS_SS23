package fruitnew.end2endtest;


import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;

import org.openqa.selenium.chrome.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class FrontendTest {

    @Before
    public void setupWebdriver(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Eric\\Downloads\\chromedriver_win32\\chromedriver.exe");
    }
        @Test
        public void testvalidTextfield() throws SQLException {
            // Optional. If not specified, WebDriver searches the PATH for chromedriver.
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            WebDriver driver = new ChromeDriver(options);

            driver.get("http://localhost:3000/");
            WebElement textbox = driver.findElement(By.id("outlined-basic"));
            // Enter the text "fruit" into the textbox
            textbox.sendKeys("Apple");
            // Find the submit button by its ID attribute
            WebElement submitButton = driver.findElement(By.id("Submitbutton"));
            // Click the submit button
            submitButton.click();
            // Close the browser
            driver.quit();

            Properties properties = new Properties();
            try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application-end2end.properties")) {
                properties.load(inputStream);
            } catch (IOException e) {
                // Handle the exception
            }
            Connection connection = DriverManager.getConnection(properties.getProperty("spring.datasource.url"), properties.getProperty("spring.datasource.username"), properties.getProperty("spring.datasource.password"));
            try {
                // Execute an SQL query to retrieve the inserted data from the database
                String sql = "SELECT * FROM fruit_db WHERE name = 'Apple'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                // Assert that the query returned at least one result
                assertTrue(resultSet.next());

                // Assert the values of specific columns in the result set
                String fruitName = resultSet.getString("name");
                assertEquals("Apple", fruitName);

                // ... perform additional assertions on other columns if necessary ...

            } finally {
                // Close the database connection
                if (connection != null) {
                    connection.close();
                }
            }

        }

    @Test
    public void testinvalidTextfield() throws InterruptedException, SQLException {
        // Optional. If not specified, WebDriver searches the PATH for chromedriver.
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);

        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application-end2end.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            // Handle the exception
        }

        driver.get("http://localhost:3000/");
        WebElement textbox = driver.findElement(By.id("outlined-basic"));
        // Enter the text "fruit" into the textbox
        textbox.sendKeys("Bread");
        // Find the submit button by its ID attribute
        WebElement submitButton = driver.findElement(By.id("Submitbutton"));
        // Click the submit button
        submitButton.click();
        // Close the browser
        driver.quit();
        Connection connection = DriverManager.getConnection(properties.getProperty("spring.datasource.url"), properties.getProperty("spring.datasource.username"), properties.getProperty("spring.datasource.password"));
        try {
            // Execute an SQL query to retrieve the inserted data from the database
            String sql = "SELECT * FROM fruit_db WHERE name = 'Bread'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            // Assert that the query returned at least one result
            assertTrue(resultSet.next());

            // Assert the values of specific columns in the result set
            String fruitName = resultSet.getString("name");
            assertNotEquals("Bread", fruitName);

            // ... perform additional assertions on other columns if necessary ...

        } finally {
            // Close the database connection
            if (connection != null) {
                connection.close();
            }
        }

    }
}
