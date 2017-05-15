import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Created by toon__000 on 5/14/2017.
 */
public class TestClassChrome {
    @Test
    public void testWrongAddress() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

//        System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
//        WebDriver driver = new FirefoxDriver();
        String baseUrl = "http://localhost:4200/";

        driver.get("http://localhost:4200/bunbun");
        assertThat(driver.findElement(By.cssSelector("h2")).getText(), is("The resource you have asked is not in the server"));

//        System.setProperty("webdriver.gecko.driver", "E:\\CMU\\Advance SW\\geckodriver.exe");
//        WebDriver driver =  new FirefoxDriver();
//        driver.manage().window().maximize();
//        driver.get("http://www.google.com");


    }
    @Test
    public void testUnautorize () {
        System.setProperty("webdriver.chrome.driver"
                , "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();


        String baseUrl = "http://localhost:4200/";

        driver.get("http://localhost:4200/login");
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("sss");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
// ERROR: Caught exception [unknown command [assertThat]]
        assertEquals("Unauthorized", driver.findElement(By.cssSelector("div.alert.alert-error")).getText());


    }
    @Test
    public void testnonUsername () {
        System.setProperty("webdriver.chrome.driver"
                , "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();


        String baseUrl = "http://localhost:4200/";

        driver.get("http://localhost:4200/login");
        driver.findElement(By.id("username")).sendKeys("");
        driver.findElement(By.id("password")).sendKeys("sss");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        assertEquals("Username is required", driver.findElement(By.cssSelector("span.help-block")).getText());
    }
    @Test
    public void testnonPassword () {
        System.setProperty("webdriver.chrome.driver"
                , "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();


        String baseUrl = "http://localhost:4200/";

        driver.get("http://localhost:4200/login");
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        assertEquals("Password is required", driver.findElement(By.cssSelector("span.help-block")).getText());
    }
    @Test
    public void testAddress () {
        System.setProperty("webdriver.chrome.driver"
                , "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();


        String baseUrl = "http://localhost:4200/";

        driver.get("http://localhost:4200/login");
        assertEquals("Hello SE331", driver.findElement(By.cssSelector("h1.col-sm-offset-1")).getText());
    }
    @Test
    public void testLoginUser () {
        System.setProperty("webdriver.chrome.driver"
                , "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();


        String baseUrl = "http://localhost:4200/";
        driver.get(baseUrl + "/login");
        driver.findElement(By.id("username")).sendKeys("user");
        driver.findElement(By.id("password")).sendKeys("user");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        assertEquals("Course list", driver.findElement(By.linkText("Course list")).getText());

    }
    @Test
    public void testLoginAdmin () {
        System.setProperty("webdriver.chrome.driver"
                , "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();


        String baseUrl = "http://localhost:4200/";
        driver.get(baseUrl + "/login");
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        assertEquals("Add Course", driver.findElement(By.linkText("Add Course")).getText());

    }
    @Test
    public void testCheckCourseList () {
        System.setProperty("webdriver.chrome.driver"
                , "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String baseUrl = "http://localhost:4200/";
        driver.get(baseUrl + "/login");
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        assertThat(driver.findElement(By.linkText("Course list")).getText(), is("Course list"));

    }
    }