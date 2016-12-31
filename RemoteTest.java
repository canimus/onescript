import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.net.URL;

// java -classpath .:junit-4.12.jar:hamcrest-core-1.3.jar:selenium-server-standalone-3.0.1.jar -Dwebdriver.gecko.driver=/sw/apps/selenium3example/geckodriver RemoteTest
public class RemoteTest {

  public static void main(String args[]) {
    WebDriver driver = null;

    try {
      // java -jar selenium-server-standalone-3.0.1.jar -role hub
      // java -jar selenium-server-standalone-3.0.1.jar -role node -hub http://localhost:4444/wd/register


      //String hubURL = "http://localhost:4444/wd/hub";
      //http://192.168.33.10/

      // Running on Vagrant - Iovio Automation Federation
      String hubURL = "http://192.168.33.10:4444/wd/hub";
      DesiredCapabilities capability = DesiredCapabilities.firefox();
      driver = new RemoteWebDriver(new URL(hubURL), capability);

      driver.get("http://www.google.com");
      WebElement element = driver.findElement(By.name("q"));
      element.sendKeys("Cheese!");
      element.submit();
      Thread.sleep(10000);
    } catch(Exception e) {
      System.out.println("Error!");
    } finally {
      driver.quit();
    }
  }
}
