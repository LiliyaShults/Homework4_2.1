import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;


public class TestWithKiosk {
    private WebDriver driver;
    private Logger logger = LogManager.getLogger(TestWithKiosk.class);

    public void Log(){
        logger.info("this is info");}

    @BeforeAll
    public static void driverSetup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void driverStart() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--kiosk");
        driver = new ChromeDriver(options);
        logger.info("Драйвер открыт");
    }

    @AfterEach
    public void driverStop() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void kiosk()  {

        driver.get("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_" +
                "ga=2.181802926.889871791.1632394818-2083132868.1632394818");
         WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.xpath("//div[@class= 'content-overlay']"))
             .click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("pp_content_container")));


     if  (driver.findElement(By.className("pp_content_container")) != null){
         logger.info("Элемент найден и открыт");

         System.out.println("Модальное окно было открыто. Картинка показалась после клика");
     }
        logger.info("Драйвер закрыт");

    }

    }
