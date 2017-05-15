import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.assertj.core.api.Fail.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
 public class TestClassFireFox {
    @Test
    public void testWrongAddress() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\toon__000\\Downloads\\SE234-project1-master\\SE234-project1-master\\src\\main\\resources\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        String baseUrl = "http://localhost:4200/";


        driver.get("http://localhost:4200/login");
        driver.get("http://localhost:4200/bunbun");
        assertThat(driver.findElement(By.cssSelector("h2")).getText(), is("The resource you have asked is not in the server"));

    }

    @Test
    public void testUnauthorize() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\toon__000\\Downloads\\SE234-project1-master\\SE234-project1-master\\src\\main\\resources\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        String baseUrl = "http://localhost:4200/";


        driver.get("http://localhost:4200/login");
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("sss");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
// ERROR: Caught exception [unknown command [assertThat]]
        assertEquals("Unauthorized", driver.findElement(By.cssSelector("div.alert.alert-error")).getText());

    }


    @Test
    public void testnonPassword() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\toon__000\\Downloads\\SE234-project1-master\\SE234-project1-master\\src\\main\\resources\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        String baseUrl = "http://localhost:4200/";

        driver.get("http://localhost:4200/login");
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        assertEquals("Password is required", driver.findElement(By.cssSelector("span.help-block")).getText());
    }
    @Test
    public void testAddress() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\toon__000\\Downloads\\SE234-project1-master\\SE234-project1-master\\src\\main\\resources\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        String baseUrl = "http://localhost:4200/";

        driver.get("http://localhost:4200/login");
        assertEquals("Hello SE331", driver.findElement(By.cssSelector("h1.col-sm-offset-1")).getText());
    }
    @Test
    public void testLoginUser() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\toon__000\\Downloads\\SE234-project1-master\\SE234-project1-master\\src\\main\\resources\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        String baseUrl = "http://localhost:4200/";

        driver.get(baseUrl + "/login");
        driver.findElement(By.id("username")).sendKeys("user");
        driver.findElement(By.id("password")).sendKeys("user");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        assertEquals("Course list", driver.findElement(By.linkText("Course list")).getText());
    }
    @Test
    public void testLoginAdmin() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\toon__000\\Downloads\\SE234-project1-master\\SE234-project1-master\\src\\main\\resources\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        String baseUrl = "http://localhost:4200/";

        driver.get(baseUrl + "/login");
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        assertEquals("Add Course", driver.findElement(By.linkText("Add Course")).getText());

    }
    @Test
    public void testCheckCourseList() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\toon__000\\Downloads\\SE234-project1-master\\SE234-project1-master\\src\\main\\resources\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        String baseUrl = "http://localhost:4200/";

        driver.get(baseUrl + "/login");
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        assertThat(driver.findElement(By.linkText("Course list")).getText(), is("Course list"));

    }


}
