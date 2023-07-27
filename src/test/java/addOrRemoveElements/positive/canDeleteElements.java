package addOrRemoveElements.positive;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class canDeleteElements {
    public static WebDriver driver;
    /**
     * Starts browser maximized
     */
    @BeforeMethod
    public static void setupBrowser() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/");

    }
    /**
     * After test is complete quits the opened browser
     */
    @AfterMethod
    void tearDown() {
        driver.quit();
    }
    @Test
    public void addElement(){

        WebElement linkElement = driver.findElement(By.cssSelector("a[href='/add_remove_elements/']"));
        linkElement.click();
        driver.findElement(By.xpath("//*[.='Add Element'] ")).click();
        WebElement buttonElement = driver.findElement(By.cssSelector("button.added-manually[onclick='deleteElement()']"));
        buttonElement.click();
        try {
            driver.findElement(By.cssSelector("button.added-manually[onclick='deleteElement()']"));
            System.out.println("Element exists, but it shouldn't be present on this page.");
        } catch (NoSuchElementException e) {
            System.out.println("Element not found (Expected behavior).");
        }

    }
}

