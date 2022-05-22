import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import java.util.concurrent.TimeUnit;

public class LoginTest {
    ChromeDriver driver;
    // for creating own logs
    Logger mLogger = Logger.getLogger(LoginTest.class);

    @BeforeMethod
    public void setUp(){
        final int PAGE_LOAD_TIME_OUT = 30;
        final int IMPLICIT_WAIT = 10;
        System.setProperty("webdriver.chrome.driver", "E:\\Softwares\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        mLogger.info("Chrome is launched.");
        driver.manage().deleteAllCookies();
        mLogger.info("cookies are deleted");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIME_OUT, TimeUnit.SECONDS);
        mLogger.info("page load time out is set to " + PAGE_LOAD_TIME_OUT);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        mLogger.info("Implicit wait is set to " + IMPLICIT_WAIT);

        driver.get("https://www.freecrm.com/");
    }

    @Test(priority = 1)
    public void testTitle(){
        mLogger.info("*****************testTitle test case started*******************");
        String title = driver.getTitle();
        System.out.println(title);
        Assert.assertEquals(title, "Free CRM software for customer relationship management, sales, marketing campaigns and support.");
        mLogger.info("*****************testTitle test case end*******************");
    }

    @Test(priority = 2)
    public void testLogo(){
        mLogger.info("*****************testLogo test case started*******************");
        String logoXpath = "(//img[@class='img-responsive'])[1]";
        boolean isDisplayed = driver.findElement(By.xpath(logoXpath)).isDisplayed();
        Assert.assertTrue(isDisplayed);
        mLogger.info("*****************testLogo test case end*******************");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
