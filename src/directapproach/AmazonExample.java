package directapproach;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class AmazonExample {
    public static void main(String[] args) throws InterruptedException {
        //if you are using Chrome 111 version, use chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        // for headless browser options.addArguments("--headless");
        // for ssl certificate handling
        /*
            DesiredCapabilities handlSSLErr = DesiredCapabilities.chrome ()
            handlSSLErr.setCapability (CapabilityType.ACCEPT_SSL_CERTS, true)
            WebDriver driver = new ChromeDriver (handlSSLErr);*/




        // Set the path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver", "Driver/ChromeDriver/chromedriver.exe");

        // Create a new ChromeDriver instance
        WebDriver driver = new ChromeDriver(options);

        // Navigate to the Amazon homepage
        driver.get("https://www.amazon.com/");

        //Maximize Browser window
        driver.manage().window().maximize();

        // Click the "Sign in" link
/*        WebElement signInLink = driver.findElement(By.id("nav-link-accountList"));
        signInLink.click();

        // Enter email address and click "Continue"
        WebElement emailField = driver.findElement(By.id("ap_email"));
        emailField.sendKeys("your email");
        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();

        // Enter password and click "Sign in"
        WebElement passwordField = driver.findElement(By.id("ap_password"));
        passwordField.sendKeys("your Password");
        WebElement signInButton = driver.findElement(By.id("signInSubmit"));
        signInButton.click();*/

        /*Use your own user name and password with amazon to test*/

        //Implicit wait for page elements to load
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

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

        WebElement selectDropdown=driver.findElement(By.cssSelector("select#searchDropdownBox"));

        // Select an option from a dropdown menu
 /*       WebDriverWait waitselect = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitselect.until(ExpectedConditions.elementToBeSelected(By.cssSelector("select#searchDropdownBox")));*/
        try {


            Select sortBy = new Select(selectDropdown);
            sortBy.selectByValue("Books");
        } catch (Exception e){
            System.out.println("Could not select");
        }

        // Find the search bar and enter a search query
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("Java Programming Book");

        // Click the search button
        WebElement searchButton = driver.findElement(By.cssSelector("input.nav-input[value='Go']"));
        searchButton.click();

        // Find all search results and print their titles
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.s-result-item")));
        java.util.List<WebElement> searchResults = driver.findElements(By.cssSelector("div.s-result-item h2"));
        for (WebElement result : searchResults) {
            System.out.println(result.getText());
        }

        // Find and drag the price slider to a new position
        WebElement priceSlider = driver.findElement(By.cssSelector("div#priceRefinements"));
        actions.dragAndDropBy(priceSlider, 50, 0).build().perform();

        //select checkbox
        try {
            WebElement checkbox = driver.findElement(By.xpath("//*[text()='Head First']"));
            checkbox.isDisplayed();
            checkbox.click();
            checkbox.isSelected();
            checkbox.click();
        } catch (Exception e){
            System.out.println("Exception handling checkbox");
        }

        driver.navigate().to("https://demoqa.com/alerts");


        // Handle a popup


/*        String alertText=alert.getText();
        System.out.println("*********"+ alertText+ "**************");

        String expectedAlertText="Just Text";

        Assert.assertEquals(alertText,expectedAlertText,"DId not pass");*/

       // alert.dismiss();
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            WebElement element1 = driver.findElement(By.xpath("//*[@id=\"promtButton\"]"));

            element1.click();
            Alert alert=driver.switchTo().alert();

            alert.sendKeys("Hello World");
            alert.dismiss();
        } catch (Exception e) {
            System.out.println("Alert Exception");
        }

        driver.navigate().to("https://demoqa.com/browser-windows");

        try {

            //Storing parent window in a varible
            String parentWindow=driver.getWindowHandle();


            driver.findElement(By.xpath("//*[@id=\"tabButton\"]")).click();

            Thread.sleep(5000);

            Set<String> s=driver.getWindowHandles();

            Iterator<String> i=s.iterator();
            while(i.hasNext()){
                String childWindow=i.next();

                if (!parentWindow.equals(childWindow)){
                    driver.switchTo().window(childWindow);

                    driver.getTitle();
                    System.out.println(driver.getTitle());

                    driver.close();
                }
            }
            driver.switchTo().window(parentWindow);

        } catch (Exception e){
            System.out.println("Exception handling window");
        }


/*
        // Handle a new window for each loop
        String mainWindow = driver.getWindowHandle();
        java.util.Set<String> allWindows =
                driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(mainWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        // Do something in the new window
        // ...

        // Handle a frame
        driver.switchTo().frame("frameName");
        // Do something in the frame
        // ...

        // Switch back to the default content
        driver.switchTo().defaultContent();
*/

        // Quit the driver
         driver.quit();
    }
}

