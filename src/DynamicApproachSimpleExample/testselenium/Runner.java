package DynamicApproachSimpleExample.testselenium;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Runner {
    MyFirstTest firstTest=new MyFirstTest();


    @BeforeClass
    public void setup(){
        firstTest.setup();
    }
    @BeforeMethod
    public void baseURL(){
        firstTest.getUrL("https://www.ebay.com/");
        firstTest.maximizeBrowser();
    }

    @Test(priority = 1, groups = {"Smoke Test"}, description = "this is a dummy test")
    public void TestLogo(){

        firstTest.HomePageValidation();
    }

 /*   @Test(priority = 2)
    public void testSearch(){

        firstTest.serachItemHomePage("Ipad");


    }
*/

    @AfterClass
    public void tearDown(){
        firstTest.tearDown();
    }
}
