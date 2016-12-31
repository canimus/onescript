import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.TimeoutException;

import java.util.List;

public class NSRidePlanPO {
  WebDriver driver;
  WebDriverWait wait;

  public NSRidePlanPO(WebDriver driver) {
    this.driver = driver;
    // Wait a maximum of 2 seconds in this page
    this.wait = new WebDriverWait(this.driver, 2);

    // This call sets the WebElement fields.
    PageFactory.initElements(driver, this);
  }

}
