package DynamicApproachSimpleExample.testselenium;

import DynamicApproachSimpleExample.WebAPI.Repo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.time.Duration;

public class MyFirstTest extends Repo {


    public void setup(){
        setupDriver("Windows","Chrome");
    }

    public void getUrL(String url){
        driver.get(url);
    }
    public void maximizeBrowser(){
        driver.manage().window().maximize();
    }

    public void HomePageValidation(){
        String elmentLogo=new WebElementsofPageEbay().elmentLogoXpath;
        WebElement logo;

        logo= driver.findElement(By.xpath(elmentLogo));
        boolean logoDisplayed=elementDisplayed(logo);
        Assert.assertTrue(logoDisplayed,"Logo is not diplayed");
   }

   public void serachItemHomePage(String ProductToSearch){
        String elementSearchBar=new WebElementsofPageEbay().elementSearchBarCSS;
        String elementSearchAfterPage=new WebElementsofPageEbay().elementSearchPageXPath;

        //Searching for a product in Homepage
        WebElement search= driver.findElement(By.cssSelector(elementSearchBar));
        search.sendKeys(ProductToSearch);
        search.sendKeys(Keys.ENTER);

        //Navigating to search result page
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
        WebElement serachResultVerify= driver.findElement(By.xpath(elementSearchAfterPage));

/*        String actual_text=search.getText();
        System.out.println(actual_text);
        String expected_text="Ipad";
        Assert.assertEquals(actual_text,expected_text,"Assertion failed");*/

   }



   public void tearDown(){
        driver.quit();
   }


}
