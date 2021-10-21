package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class AppTest {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver2.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://www.trendyol.com/");
    }

    @Test
    public void actionsTest() throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveToElement(
                findElement(By.cssSelector("div[class='account-nav-item user-login-container']")))
                .click()
                .build()
                .perform();
        findElement(By.cssSelector("div[class='login-button']")).click();
        TimeUnit.SECONDS.sleep(5);
    }

    @Test
    public void login() throws InterruptedException {
        actionsTest();
        WebElement username = findElement(By.id("login-email"));
        username.sendKeys("ceren.ercan@testinium.com");

        WebElement password = findElement(By.id("login-password-input"));
        TimeUnit.SECONDS.sleep(5);
        password.sendKeys("Test191021" + Keys.ENTER);

        TimeUnit.SECONDS.sleep(5);

    }

    @Test
    public void search() throws InterruptedException {
        login();
        WebElement searchBox = findElement(By.className("search-box"));
        searchBox.sendKeys("Kazak" + Keys.ENTER);
        TimeUnit.SECONDS.sleep(10);
    }

    @Test
    public void chooseGender() throws InterruptedException {
        search();
        WebElement chooseGender = findElement(By.xpath("(//div[@class='fltr-item-wrppr'])[1]"));
        chooseGender.click();
    }

    @Test
    public void addFavorite() throws InterruptedException{
        search();
        List<WebElement> imageList = driver.findElements(By.className("fvrt-btn-wrppr"));
        System.out.println(imageList.size());
        WebElement item = imageList.get(9);
        item.click();
    }

    @Test
    public void tenthElement() throws InterruptedException {
        addFavorite();
        List<WebElement> imageList = driver.findElements(By.className("p-card-wrppr"));
        System.out.println(imageList.size());
        WebElement item = imageList.get(9);
        item.click();
        TimeUnit.SECONDS.sleep(10);
    }

    @Test
    public void addToCart() throws InterruptedException {
        tenthElement();


    }

    public WebElement findElement(By by) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }


    @After
    public void tearDown(){
        driver.quit();
    }
}
