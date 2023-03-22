package DynamicApproachSimpleExample.WebAPI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

public class Repo {
    public WebDriver driver;

    public void click(WebElement element){
        element.click();
    }
    public void writeOnInputField(WebElement element, String charSequence){
        element.sendKeys(charSequence);
    }
    public boolean elementDisplayed(WebElement element){
        return element.isDisplayed();
    }
    public boolean elementSelected(WebElement element){
        return  element.isSelected();
    }

    public WebDriver setupDriver(String OS, String BrowserName){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        if (OS.equals("Windows") && BrowserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "Driver/ChromeDriver/chromedriver.exe");

             driver = new ChromeDriver(options);
        }
        else if (OS.equals("Windows") && BrowserName.equalsIgnoreCase("Edge")){
            System.setProperty("webdriver.edge.driver", "Driver/ChromeDriver/EdgeDriver.exe");
             driver=new EdgeDriver();
        }
            return driver;
    }
}
