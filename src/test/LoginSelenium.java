import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginSelenium {

    private ChromeDriver driver;

    @Before
    public void setUp() {
        String exePath = "chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", exePath);
        driver = new ChromeDriver();
    }


    @Test
    public void loginAutomate() throws InterruptedException {

        driver.get("http://localhost:8080/carlooksystem_war/#!login");
        Thread.sleep(2000);
        WebElement email = driver.findElementById("email");
        WebElement password = driver.findElementById("password");
        WebElement login = driver.findElementById("login");
        Thread.sleep(2000);
        email.sendKeys("toni@bmw.de");
        password.sendKeys("tonimayer");
        Thread.sleep(2000);
        login.click();
        Thread.sleep(2000);

    }

    @After
    public void tearDown() {
        driver.close();
    }
}
