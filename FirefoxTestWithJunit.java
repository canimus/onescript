import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

// java -classpath .:junit-4.12.jar:hamcrest-core-1.3.jar:selenium-server-standalone-3.0.1.jar -Dwebdriver.gecko.driver=/sw/apps/selenium3example/geckodriver org.junit.runner.JUnitCore FirefoxTestWithJunit
public class FirefoxTestWithJunit {
  //String driverPath = "/sw/apps/selenium3example/geckodriver";
  public WebDriver driver;

  @Before
  public void launchBrowser() {
    System.out.println("Launching browser");
    //System.setProperty("webdriver.gecko.driver", driverPath);
    driver = new FirefoxDriver();
  }

  @Test
  public void openApp() throws Exception {
    System.out.println("Going to iovio");
    driver.navigate().to("http://www.iovio.com");
    Thread.sleep(5000);
  }

  @After
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
