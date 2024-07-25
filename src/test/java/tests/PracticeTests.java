package tests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Pages.PracticePage;

import java.util.List;

public class PracticeTests {
    private WebDriver driver;
    private PracticePage practicePage;
    private final Logger logger = LogManager.getLogger();

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        //Maximize the browser window
        driver.manage().window().maximize();
        practicePage = new PracticePage(driver);
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        logger.info("Open Practice Page");
    }

   @Test
    public void testRadioButtons() {
        logger.info("Start test for Radio Buttons.");

        practicePage.clickRadioButton3();
        Assert.assertTrue(practicePage.isRadioButtonSelected(By.xpath("//input[@value='radio3']")));
        logger.info("Radio Button 3 is selected.");

        for (WebElement radioButton : practicePage.getAllRadioButtons()) {
            if (!radioButton.equals(driver.findElement(By.xpath("//input[@value='radio3']")))) {
                Assert.assertFalse(radioButton.isSelected());
            }
        }
        logger.info("Only Radio Button 3 is selected.");

        practicePage.clickRadioButton2();
        Assert.assertTrue(practicePage.isRadioButtonSelected(By.xpath("//input[@value='radio2']")));
        logger.info("Radio Button 2 is selected.");

        for (WebElement radioButton : practicePage.getAllRadioButtons()) {
            if (!radioButton.equals(driver.findElement(By.xpath("//input[@value='radio2']")))) {
                Assert.assertFalse(radioButton.isSelected());
            }
        }
        logger.info("Only Radio Button 2 is selected.");
    }

   @Test
    public void testSuggestions() {
        logger.info("Start test for Suggestions");

        practicePage.enterSuggestionText("South");
        practicePage.selectSuggestion("South Africa");
        Assert.assertEquals(practicePage.getSuggestionFieldText(), "South Africa");
        logger.info("Selected 'South Africa' from suggestions.");

        practicePage.clearSuggestionField();
        practicePage.enterSuggestionText("Republic");
        practicePage.selectSuggestion("Central African Republic");
        Assert.assertTrue(practicePage.getSuggestionFieldText().contains("Republic"));
        logger.info("Selected first option for 'Republic'.");
    }

   @Test
    public void testCheckboxes() {
        logger.info("Start test for Checkboxes");

        for (WebElement checkbox : practicePage.getAllCheckboxes()) {
            if (!checkbox.isSelected()) {
                practicePage.clickCheckbox(checkbox);
            }
            Assert.assertTrue(checkbox.isSelected());
        }
        logger.info("All checkboxes are checked.");

        WebElement firstCheckbox = practicePage.getAllCheckboxes().get(0);
        practicePage.clickCheckbox(firstCheckbox);
        Assert.assertFalse(firstCheckbox.isSelected());
        logger.info("First checkbox is unchecked.");

        for (int i = 1; i < practicePage.getAllCheckboxes().size(); i++) {
            Assert.assertTrue(practicePage.getAllCheckboxes().get(i).isSelected());
        }
        logger.info("The other two checkboxes remain checked.");
    }
   @Test
    public void testShowHide() {
        logger.info("Start test for Show and Hide button.");

        practicePage.clickHideButton();
        Assert.assertFalse(practicePage.isTextBoxDisplayed());
        logger.info("Text box is hidden.");

        practicePage.clickShowButton();
        Assert.assertTrue(practicePage.isTextBoxDisplayed());
        logger.info("Text box is shown.");
    }

    @Test
    public void testWebTable() {
        logger.info("Start test for Web Table Fixed Header.");

        int amountForJoe = 0;
        for (WebElement row : practicePage.getTableRows()) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.get(0).getText().equals("Joe Postman") && cells.get(1).getText().equals("Chennai")) {
                amountForJoe = Integer.parseInt(cells.get(2).getText());
                break;
            }
        }
        Assert.assertEquals(amountForJoe, 46);
        logger.info("Joe Postman from Chennai has amount of 46");

        //Validate that the total amount collected is 296
        int totalAmount = Integer.parseInt(practicePage.getTotalAmountElement().getText());
        Assert.assertEquals(totalAmount, 296);
        logger.info("Total amount collected is 296.");

        //Validate that the sum of all the rows is correct
        int sum = 0;
        for (WebElement row : practicePage.getTableRows()) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            sum += Integer.parseInt(cells.get(2).getText());
        }
        Assert.assertEquals(sum, 296);
        logger.info("Sum of all rows is correct.");
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        logger.info("Closed the browser.");
    }
}



