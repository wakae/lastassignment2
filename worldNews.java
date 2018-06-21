import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AutomateNewYorkTimes {
    private WebDriver driver;
    private String baseUrl, blogUrl;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver","/usr/local/share/chromedriver");
        driver = new ChromeDriver();
        baseUrl = "https://www.ethiopianreporter.com/zenahttps://www.nytimes.com/section/technology";
        blogUrl = "http://localhost/newsBlog/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testUntitledTestCase() throws Exception {
        driver.get(baseUrl);

        List<WebElement> divNewsContener = driver.findElements(
                By.xpath("//*[@id=\"latest-panel\"]/div[1]/ol[1]/li"));

        List<WebElement> newsTitles = driver.findElements(
                By.xpath("//*[@id=\"latest-panel\"]/div[1]/ol[1]/li//article/div/a/div[1]/h2"));
        List<WebElement> newsContents = driver.findElements(
                By.xpath("//*[@id=\"latest-panel\"]/div[1]/ol[1]/li/article[1]/div/a/div[1]/p[1]"));

        String[][] stringNews = new String[divNewsContener.size()][2];
        int i;

        for(i = 0; i < 10; i++){
            stringNews[i][0] = newsTitles.get(i).getText();
            stringNews[i][1] = newsContents.get(i).getText();
        }


        driver.get(blogUrl);
        for(i = 0; i < (divNewsContener.size() - 1); i++){
            driver.findElement(By.xpath("//input[@type='text']")).sendKeys(stringNews[i][0]);
            driver.findElement(By.xpath("//textarea[@name='content']")).sendKeys(stringNews[i][1]);
            driver.findElement(By.xpath("//input[@type='submit']")).click();
        }
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
