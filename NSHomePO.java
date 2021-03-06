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

public class NSHomePO {
  WebDriver driver;
  WebDriverWait wait;

  @FindBy(name = "departure")
  @CacheLookup
  private WebElement departureInput;

  @FindBy(name = "arrival")
  @CacheLookup
  private WebElement arrivalInput;

  @FindBy(xpath = "//button[(@type='submit') and (span[contains(text(),'Plannen')])]")
  @CacheLookup
  private WebElement planButton;

  By departureListItem = By.xpath("//input[@name='departure']/following-sibling::div/ul/li[1]");
  By arrivalListItem = By.xpath("//input[@name='arrival']/following-sibling::div/ul/li[1]");

  public NSHomePO(WebDriver driver) {
    this.driver = driver;
    // Wait a maximum of 2 seconds in this page
    this.wait = new WebDriverWait(this.driver, 2);

    // This call sets the WebElement fields.
    PageFactory.initElements(driver, this);
  }

  public NSHomePO acceptCookies() {
    try {
      this.wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
      // Handling the cookie modal dialog window
      List<WebElement> links = this.driver.findElements(By.xpath("//a[contains(text(), 'Accepteer')]"));
      if ( !links.isEmpty() ) {
        links.iterator().next().click();
      }
    } catch(TimeoutException e) { // No action if modal not present
    } finally {
      // Return to main window and continue
      driver.switchTo().defaultContent();
    }
    return this;
  }

  public NSHomePO enterDeparture(String city) throws Exception {
    departureInput.sendKeys(city);
    this.wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(departureListItem, 0));
    departureInput.sendKeys(Keys.ENTER);
    return this;
  }

  public NSHomePO enterArrival(String city) throws Exception {
    arrivalInput.sendKeys(city);
    this.wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(arrivalListItem, 0));
    arrivalInput.sendKeys(Keys.ENTER);
    return this;
  }

  public NSRidePlanPO getRideOptions() {
    planButton.click();
    return new NSRidePlanPO(driver);
  }

  public NSRidePlanPO planRide(String start, String end) throws Exception {
    return this.acceptCookies()
      .enterDeparture(start)
      .enterArrival(end)
      .getRideOptions();
  }
}
