import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import java.io.File;
import org.apache.commons.io.FileUtils;

// java -Dwebdriver.gecko.driver=/sw/apps/selenium3example/geckodriver
// -classpath selenium-server-standalone-3.0.1.jar:testng-6.9.14-20161109.042809-33.jar:.
// org.testng.TestNG -testclass FirefoxTestWithNG
public class FirefoxTestWithNG {
  //String driverPath = "/sw/apps/selenium3example/geckodriver";
  public WebDriver driver;

  @BeforeTest
  public void launchBrowser() {
    System.out.println("Launching browser");
    //System.setProperty("webdriver.gecko.driver", driverPath);
    driver = new FirefoxDriver();
  }

  @Test
  public void openApp() throws Exception {
    System.out.println("Going to iovio");
    driver.navigate().to("http://www.iovio.com");

    // Screenshoot
    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    // Now you can do whatever you need to do with it, for example copy somewhere
    FileUtils.copyFile(scrFile, new File("/tmp/screenshot.png"));

    Thread.sleep(5000);
  }

  @AfterTest
  public void closeDriver() {
    System.out.println("Closing browser");
    if ( driver != null ) {
      System.out.println("Yep is there");
      driver.quit();
    } else {
      System.out.println("Hold on?");
    }
  }
}
