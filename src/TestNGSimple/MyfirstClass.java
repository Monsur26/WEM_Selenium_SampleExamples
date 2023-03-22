package TestNGSimple;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class MyfirstClass {
    private WebDriver driver;
    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver","Driver/ChromeDriver/chromedriver.exe");
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver=new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.amazon.com/");
    }

    @Test(priority = 2, groups = {"smoke"})
    public void demoTest(){
        // getting title of the page
        String actualTitle= driver.getTitle();
        System.out.println("************************* "+ actualTitle + " *******************************");

        String expectedTitle="Amazon";
        // Manual assertion
        if (expectedTitle.equalsIgnoreCase(actualTitle)) {
            System.out.println("Successful assertion");
        } else {
            System.out.println("Assertion failed! Please cheack the title");
        }
    }

    @Test(priority = 1, groups = {"regression"},description = "THis is related to searching for a product")
    public void searchingItem(){
        WebElement element=driver.findElement(By.xpath("//*[@id=\"twotabsearchtextbox\"]"));
        element.sendKeys("Ipad");
        element.submit();

    }
    @Test(priority = 3)
    public void languageSelect(){

        // Mouse over the language select
        WebElement firstResultEN = driver.findElement(By.xpath("//div[text()='EN']"));

        Actions actions = new Actions(driver);
        actions.moveToElement(firstResultEN).build().perform();

        //select radio buttons

        WebElement ESradioButton=driver.findElement(By.xpath("//*[@id=\"nav-flyout-icp\"]/div[2]/a[1]/span/i"));

        ESradioButton.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement firstResultES = driver.findElement(By.xpath("//div[text()='ES']"));
        actions.moveToElement(firstResultES).build().perform();

        WebElement ENradioButton=driver.findElement(By.xpath("//*[@id=\"nav-flyout-icp\"]/div[2]/a[1]/span/i"));
        ENradioButton.click();

        //Assertion with TestNG
        String actualText=firstResultEN.getText();
        String expectedText="EN";

        Assert.assertEquals(actualText,expectedText,"Failed assertion");
    }

    @Test(priority = 4)
    public void selectDropDown(){
        WebElement selectDropdown=driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));

        Select sortBy = new Select(selectDropdown);
        sortBy.selectByValue("Books");
    }

    @AfterMethod
    public void cleanUp(){
        driver.quit();
    }

}
