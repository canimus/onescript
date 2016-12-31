import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GooglePage {
  final WebDriver driver;
  final WebDriverWait wait;

  @FindBy(how = How.NAME, using = "q")
  @CacheLookup
  private WebElement queryText;

  public GooglePage(WebDriver driver) {
    this.driver = driver;
    // Maximum wait for this page is 5 seconds
    this.wait = new WebDriverWait(this.driver, 5);
  }


}
