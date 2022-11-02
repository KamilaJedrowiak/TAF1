package pl.tomaszbuga.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.tomaszbuga.framework.BaseTest;
import pl.tomaszbuga.pom.SeleniumTrainingPage;

import java.time.LocalDate;


import static org.testng.AssertJUnit.assertEquals;

public class FirstTest extends BaseTest {

    SeleniumTrainingPage seleniumTrainingPage;
    private WebElement textInput;
    private WebElement inputPassword;
    private WebElement inputDate;



    @BeforeMethod
    public void beforeSetup() {
        WebElement textareaInput = null;
        seleniumTrainingPage = new SeleniumTrainingPage(textInput, textareaInput, inputPassword, inputDate, getDriver());
        seleniumTrainingPage.openPage();

    }

    @Test()
    public void enterTextToTextInputTest() {
        // Assign
        String expectedText = "Jakiś inny tekst, który wpisaliśmy";
        // Act
        String textFromTextInput = enterTextAndGetInputValue(expectedText);
        // Assert
        Assert.assertEquals(textFromTextInput, expectedText);
    }

    @Test()
    public void clearTextInputTest() {
        String expectedText = "Jakiś inny tekst, który wpisaliśmy";

        String textFromTextInput = enterTextAndGetInputValue(expectedText);
        Assert.assertEquals(textFromTextInput, expectedText);

        String inputValueAfterClear = getTextAfterClear();
        Assert.assertEquals(inputValueAfterClear, "");
    }

    @Test()
    public void enterTextToTextareaInputTest() {
        // Assign
        String expectedText = "Jakiś inny tekst, który wpisaliśmy";
        // Act
        String textFromTextInput = fillTextareaAndGetInputValue(expectedText);
        // Assert
        Assert.assertEquals(textFromTextInput, expectedText);
    }

    @Test()
    public void selectValueFromDropdownTest() {
        String expectedValue = "Two";
        String selectedValueFromDropdown;

        seleniumTrainingPage.selectValueFromDropdown("Two");
        selectedValueFromDropdown = seleniumTrainingPage.getSelectedValueFromDropdown();

        Assert.assertEquals(selectedValueFromDropdown, expectedValue);
    }

    private String getTextAfterClear() {
        return seleniumTrainingPage
                .clearTextInput()
                .getTextFromTextInput();
    }

    private String enterTextAndGetInputValue(String expectedText) {
        return seleniumTrainingPage
                .enterTextToTextInput(expectedText)
                .getTextFromTextInput();
    }

    private String enterPasswordAndGetInputValue(String expectedText) {
        return seleniumTrainingPage
                .enterTextToPasswordInput(expectedText)
                .getTextFromPasswordInput();
    }

    private String fillTextareaAndGetInputValue(String expectedText) {
        return seleniumTrainingPage
                .enterTextToTextareaInput(expectedText)
                .getTextFromTextareaInput();
    }

    @Test()
    public void addPassword() {
//         Assign
        String expectedText = "Password ";
        // Act
        String myPassword = enterPasswordAndGetInputValue(expectedText);
        // Assert
        Assert.assertEquals(myPassword, expectedText);
//        inputPassword.getAttribute("type").equals("password");
    }

    @Test
    public void chooseDateFromCalendar() {
        seleniumTrainingPage.clickDatePicker();
        seleniumTrainingPage.chooseToday();
    }


}

