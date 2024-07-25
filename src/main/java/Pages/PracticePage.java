package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PracticePage {
    WebDriver driver;
    WebDriverWait wait;

    public PracticePage(WebDriver driver) {
        this.driver = driver;
        Duration duration = Duration.ofSeconds(10);

        this.wait = new WebDriverWait(driver, duration);
    }

    // Radio buttons
    private By radioButtonTwo = By.xpath("//input[@value='radio2']");
    private By radioButtonThree = By.xpath("//input[@value='radio3']");
    private By radioButtons = By.name("radioButton");

    public void clickRadioButton3() {
        driver.findElement(radioButtonThree).click();
    }

    public void clickRadioButton2() {
        driver.findElement(radioButtonTwo).click();
    }

    public boolean isRadioButtonSelected(By radioButton) {
        return driver.findElement(radioButton).isSelected();
    }

    public List<WebElement> getAllRadioButtons() {
        return driver.findElements(radioButtons);
    }

    //Suggestion
    private By suggestionField = By.id("autocomplete");
    private By suggestionOptions = By.xpath("//li[@class='ui-menu-item']/div");

    public void enterSuggestionText(String text) {
        driver.findElement(suggestionField).sendKeys(text);
    }

    public void selectSuggestion(String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(suggestionOptions));
        List<WebElement> options = driver.findElements(suggestionOptions);
        for (WebElement option : options) {
            if (option.getText().equals(text)) {
                option.click();
                break;
            }
        }
    }

    public String getSuggestionFieldText() {
        return driver.findElement(suggestionField).getAttribute("value");
    }

    public void clearSuggestionField() {
        driver.findElement(suggestionField).clear();
    }

    // Checkboxes
    private By checkboxes = By.cssSelector("input[type='checkbox']");

    public List<WebElement> getAllCheckboxes() {
        return driver.findElements(checkboxes);
    }

    public void clickCheckbox(WebElement checkbox) {
        checkbox.click();
    }

    // Show/Hide
    private By textBox = By.id("displayed-text");
    private By hideButton = By.id("hide-textbox");
    private By showButton = By.id("show-textbox");

    public void clickHideButton() {
        driver.findElement(hideButton).click();
    }

    public void clickShowButton() {
        driver.findElement(showButton).click();
    }

    public boolean isTextBoxDisplayed() {
        return driver.findElement(textBox).isDisplayed();
    }

    // Web Table Fixed Header
    private By tableRows = By.cssSelector("table[name='courses'] tbody tr");
    private By totalAmount = By.xpath("//div[@class='tableFixHead']//table//tfoot/tr/td[3]");

    public List<WebElement> getTableRows() {
        return driver.findElements(tableRows);
    }

    public WebElement getTotalAmountElement() {
        return driver.findElement(totalAmount);
    }

    // iFrame
    private By iframe = By.tagName("iframe");
    private By loginButtonInIframe = By.id("some-element-id");

    public WebElement getIframe() {

        return driver.findElement(iframe);
    }

    public void switchToIframe() {
        driver.switchTo().frame(getIframe());
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public void clickLoginButtonInIframe() {
        // Perform actions within the iframe
        WebElement elementInIframe =  driver.findElement(loginButtonInIframe);
        System.out.println("Element text: " + elementInIframe.getText());
    }

    public boolean isLoginFormDisplayedInIframe() {
        return driver.findElement(By.id("user_login")).isDisplayed();
    }
}
