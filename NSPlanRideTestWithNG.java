import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.openqa.selenium.support.PageFactory;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;


import java.net.URL;
import java.net.MalformedURLException;
import java.util.List;

// java -cp selenium-server-standalone-3.0.1.jar:testng-6.9.14-20161109.042809-33.jar:. org.testng.TestNG -testclass NSPlanRideTestWithNG
public class NSPlanRideTestWithNG {
  public WebDriver driver;

  @BeforeTest
  public void launchBrowser() {
    System.out.println("Launching browser");

    //String hubURL = "http://localhost:4444/wd/hub";
    String hubURL = "http://192.168.33.10:4444/wd/hub";
    DesiredCapabilities capability = DesiredCapabilities.firefox();
    try {
      //driver = new RemoteWebDriver(new URL(hubURL), capability);
      driver = new FirefoxDriver();
      driver.get("http://www.ns.nl");
      //driver.manage().window().maximize();
    } catch(Exception e) {
      System.out.println("Unable to build a proper URL with: " + e.toString() );
    }
  }

  @Test(
    description = "Obtain ride options from origin to destination city",
    parameters = { "origin", "destination" },
    enabled = true
  )
  public void getRides(String origin, String destination) {
    try {
      NSHomePO page = PageFactory.initElements(driver, NSHomePO.class);
      page.planRide(origin, destination);
      Thread.sleep(5000);
      driver.navigate().back();
    } catch(Exception e) {
      System.out.println("Error processing PO:" + e.toString());
    }

  }

  @Test(
    description = "Obtain ride options from origin to destination city",
    dataProvider = "CityDataSource",
    enabled = false
  )
  public void getRidesParallel(String origin, String destination) throws Exception {

    NSHomePO page = PageFactory.initElements(driver, NSHomePO.class);
    page.planRide(origin, destination);
    Thread.sleep(5000);
    driver.navigate().back();
    Thread.sleep(5000);
  }

  // @Factory(
  //   dataProvider = "CityDataSource"
  // )
  // public Object[] getRidesFactory(String origin, String destination) throws Exception {
  //   Object[] result = new Object[2];
  //   result[0] = new NSPlanRideTestWithNG();
  //   result[1] = new NSPlanRideTestWithNG();
  //   return result;
  // }


  @DataProvider(name="CityDataSource")
  public Object[][] getDataFromDataprovider(){
      return new Object[][] {
              { "Utrecht", "Schiphol" },
              { "Schiphol", "Rotterdam" }
          };
  }

  @AfterTest
  public void closeDriver() {
    System.out.println("Closing browser");
    if ( driver != null ) {
      System.out.println("Yep, found it.... Kapoom! Gone.");
      driver.quit();
    } else {
      System.out.println("Hold on.. Uh?");
    }
  }
}
