package pl.tomaszbuga.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pl.tomaszbuga.framework.PageObject;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;

import static org.testng.AssertJUnit.assertEquals;

public class SeleniumTrainingPage extends PageObject {
    private String baseUrl = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";

    @FindBy(css = "#my-text-id")
    private WebElement textInput;
//  hasło
    @FindBy(css = "[name='my-password']")
    private WebElement inputPassword;

    @FindBy(css = "[name='my-textarea']")
    private WebElement textareaInput;

    @FindBy(css = "[name='my-select']")
    private WebElement dropdownSelect;

    @FindBy(css = "[name='my-date']")
    private WebElement inputDate;
    @FindBy(css = "input[name='my-date']")
    private WebElement inputDatePicker;

    public SeleniumTrainingPage(WebElement textInput, WebElement textareaInput,WebElement inputPassword,WebElement inputDate, WebDriver driver) {
        this.textInput = textInput;
        this.textareaInput = textareaInput;
        this.inputPassword = inputPassword;
        this.inputDate = inputDate;
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SeleniumTrainingPage openPage() {
        driver.get(baseUrl);
        return this;
    }

    public SeleniumTrainingPage enterTextToTextInput(String text) {
        enterTextToInput(text, textInput);
        return this;
    }
//hasło
    public SeleniumTrainingPage enterTextToPasswordInput(String text) {
        enterTextToInput(text, inputPassword);
        return this;
    }

    public SeleniumTrainingPage enterTextToTextareaInput(String text) {
        enterTextToInput(text, textareaInput);
        return this;
    }

    public String getTextFromTextInput() {
        return getTextFromInput(textInput);
    }

    public String getTextFromPasswordInput() {
        return getTextFromInput(inputPassword);
    }
    public String getTextFromTextareaInput() {
        LOGGER.info("Getting text from Textarea input");
        return getTextFromInput(textareaInput);
    }

    public SeleniumTrainingPage clearTextInput() {
        LOGGER.info("Clearing text input");
        textInput.clear();
        return this;
    }

    public SeleniumTrainingPage selectValueFromDropdown(String valueToSelect) {
        Select select = new Select(dropdownSelect);

        LOGGER.info("Searching for '" + valueToSelect + "' in dropdown");
        try {
            select.selectByVisibleText(valueToSelect);
        } catch (NoSuchElementException ex) {
            LOGGER.warn("There is no value '" + valueToSelect + "' in dropdown");
        }

        return this;
    }

    public String getSelectedValueFromDropdown() {
        Select select = new Select(dropdownSelect);
        return select.getFirstSelectedOption().getText();
    }



    public SeleniumTrainingPage addDateFromCalendar() {
        inputDate.click();

        return this;
    }
    public void clickDatePicker() {
        inputDatePicker.click();
    }

    public void chooseToday() {
        String timeStamp = dateNow();
        inputDatePicker.sendKeys(timeStamp);
    }

    public static String dateNow() {
        return new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
    }
}
