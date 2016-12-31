import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class SimpleTest {

  public static void main(String args[]) {
    WebDriver driver = null;
    try {
      driver = new FirefoxDriver();
      driver.navigate().to("http://www.iovio.com");
      Thread.sleep(5000);
    } catch(Exception e) {
      System.out.println("Error!");
    } finally {
      driver.quit();
    }
  }
}
