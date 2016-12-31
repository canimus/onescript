import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Keys;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import java.io.File;
import org.apache.commons.io.FileUtils;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.List;

// java -Dwebdriver.gecko.driver=/sw/apps/selenium3example/geckodriver
// -classpath selenium-server-standalone-3.0.1.jar:testng-6.9.14-20161109.042809-33.jar:.
// org.testng.TestNG -testclass NSHomePageWithNG

public class NSHomePageWithNG {
  //String driverPath = "/sw/apps/selenium3example/geckodriver";
  public WebDriver driver;

  @BeforeTest
  public void launchBrowser() {
    System.out.println("Launching browser");

    //String hubURL = "http://localhost:4444/wd/hub";
    String hubURL = "http://192.168.33.10:4444/wd/hub";
    DesiredCapabilities capability = DesiredCapabilities.chrome();
    try {
      driver = new RemoteWebDriver(new URL(hubURL), capability);
    } catch(MalformedURLException e) {
      System.out.println("Unable to build a proper URL with: " + e.toString() );
    }
  }

  @Test
  public void openApp() throws Exception {
    driver.get("http://www.ns.nl");
    driver.manage().window().maximize();
    driver.switchTo().frame(0);

    // Handling the cookie modal dialog window
    List<WebElement> links = driver.findElements(By.xpath("//a[contains(text(), 'Accepteer')]"));
    if ( !links.isEmpty() ) {
      links.iterator().next().click();
    }

    // Return to main window
    driver.switchTo().defaultContent();

    // Entering origin and destination
    WebElement departure = driver.findElement(By.name("departure"));
    departure.sendKeys("Utrecht");
    Thread.sleep(1000);
    //departure.sendKeys(Keys.ARROW_DOWN);
    departure.sendKeys(Keys.ENTER);
    Thread.sleep(1000);

    WebElement arrival = driver.findElement(By.name("arrival"));
    arrival.sendKeys("Schiphol");
    Thread.sleep(1000);
    //arrival.sendKeys(Keys.ARROW_DOWN);
    arrival.sendKeys(Keys.ENTER);

    Thread.sleep(1000);
    // Submission of enquiry
    driver.findElement(By.xpath("//button[(@type='submit') and (span[contains(text(),'Plannen')])]")).click();

    Thread.sleep(10000);
    // Screenshoot
    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    // Now you can do whatever you need to do with it, for example copy somewhere
    FileUtils.copyFile(scrFile, new File("/tmp/ns2.png"));

    Thread.sleep(15000);
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
