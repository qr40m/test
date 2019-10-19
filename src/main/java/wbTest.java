import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.web.client.RestTemplate;


public class wbTest {

    @Test
    public void wbTest() {
        String i = "";
        Configuration.browser = "firefox";
        System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile testprofile = profile.getProfile("default");
        testprofile.setPreference("dom.webnotifications.enabled", false);
        testprofile.setPreference("dom.push.enabled", false);
        DesiredCapabilities dc = DesiredCapabilities.firefox();
        dc.setCapability(FirefoxDriver.PROFILE, testprofile);
        FirefoxOptions opt = new FirefoxOptions();
        opt.merge(dc);

        WebDriver driver = new FirefoxDriver(opt);
        driver.manage().window().fullscreen();
        driver.get("https://www.wildberries.ru/catalog/6094105/detail.aspx?targetUrl=GP");
        driver.findElement(byXpath("//label[@class='j-size']")).click();
        driver.findElement(byText("В корзину")).click();
        driver.findElement(byText("Корзина")).click();
        driver.findElement(byXpath("//button[@type='button'][@class='plus']")).click();
        i = driver.findElement(byXpath("//input[@type='text'][@data-link='quantity']")).getText();
        assertEquals(i, "2");
        driver.close();
    }

    }
