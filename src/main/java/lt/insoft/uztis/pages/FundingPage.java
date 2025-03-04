package lt.insoft.uztis.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class FundingPage extends UztisPage{

    private static final Logger log = getLogger(lookup().lookupClass());
    private static org.openqa.selenium.By By;

    public FundingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@href='/application/funding-queue']")
    WebElement buttonMenuFundingQueue;

//    @FindBy(xpath = "//button[contains(@class, 'btn--primary')]")
    @FindBy(xpath = "//button[contains(@class, 'btn') and text()='Formuoti']")
    WebElement buttonForm;

    @FindBy(xpath = "//common-button[contains(@class, 'ng-star-inserted')]//button[contains(@class, 'btn--primary') and text()='Finansuoti']")
    WebElement buttonFinance;

    @FindBy(xpath = "//mat-label[text()='Įsakymo Nr.']/ancestor::mat-form-field//input")
    WebElement inputOrderNumber;

    @FindBy(xpath = "//input[contains(@class, 'mat-datepicker-input')]")
    WebElement inputOrderDate;

    @FindBy(xpath = "//button[contains(@class, 'btn--primary') and normalize-space(text())='Finansuoti paraiškas']")
    WebElement buttonFinanceConfirmation;


    public void clickMenuFundingQueue() {
        buttonMenuFundingQueue.click();
    }

    public void clickButtonForm() {
        buttonForm.click();
    }

    public void clickButtonFinance() {
        buttonFinance.click();
    }

    public void enterOrderNumber(String text) {
        inputOrderNumber.clear();
        inputOrderNumber.sendKeys(text);
    }

    public void enterOrderDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedTodayDate = today.format(formatter);

        if (inputOrderDate.isDisplayed() && inputOrderDate.isEnabled()) {
            // Patikriname, ar laukelis jau turi reikšmę
            String currentValue = inputOrderDate.getAttribute("value");

            if (currentValue != null && !currentValue.isEmpty()) {
                inputOrderDate.clear(); // Jei reikšmė yra, ją ištriname
            }

            inputOrderDate.sendKeys(formattedTodayDate); // Įrašome naują datą
        } else {
            throw new RuntimeException("Date input is not interactable.");
        }
    }

    public void clickButtonFinanceConfirmation() {
        buttonFinanceConfirmation.click();
    }


}
