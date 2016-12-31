import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import java.net.URL;
import java.util.List;

// java -classpath .:junit-4.12.jar:hamcrest-core-1.3.jar:selenium-server-standalone-3.0.1.jar -Dwebdriver.gecko.driver=/sw/apps/selenium3example/geckodriver RemoteTest
public class NSHomePage {

  public static void main(String args[]) {
    WebDriver driver = null;

    try {
      // java -jar selenium-server-standalone-3.0.1.jar -role hub
      // java -jar selenium-server-standalone-3.0.1.jar -role node -hub http://localhost:4444/wd/register

      String hubURL = "http://localhost:4444/wd/hub";
      DesiredCapabilities capability = DesiredCapabilities.firefox();
      driver = new RemoteWebDriver(new URL(hubURL), capability);

      driver.get("http://www.ns.nl");
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

      Thread.sleep(5000);
    } catch(Exception e) {
      System.out.println("Error:" + e.toString());
    } finally {
      driver.quit();
    }
  }
}
