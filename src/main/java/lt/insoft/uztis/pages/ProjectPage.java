package lt.insoft.uztis.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class ProjectPage extends UztisPage {

    private static final Logger log = getLogger(lookup().lookupClass());

    public ProjectPage(WebDriver driver) {
        super(driver);

    }

    @FindBy(xpath = "//a[@href=\"/application/project\"]")
    WebElement buttonMenuProjects;

    @FindBy(xpath = "//h2[.='Projektai']")
    WebElement labelProjects;

    @FindBy(xpath = "(//tr[contains(@class, 'mdc-data-table__row')])[1]")
    WebElement lastProject;

    @FindBy(xpath = "//button[normalize-space(.)='Redaguoti']")
    WebElement buttonEditProject;

    @FindBy(xpath = "(//mat-select[@role='combobox'])[2]")
    WebElement dropdownButtonOrderStatus;

    @FindBy(xpath = "(//mat-select[@role='combobox'])[5]")
    WebElement dropdownButtonAccountStatus;

        @FindBy(xpath = "(//mat-select[@role='combobox'])[8]")
    WebElement dropdownButtonKOT_Status;

    @FindBy(xpath = "(//mat-select[@role='combobox'])[10]")
    WebElement dropdownButtonContractStatus;

    @FindBy(xpath = "(//mat-select[@role='combobox'])[13]")
    WebElement dropdownButtonAdvancePaymentStatus;

    @FindBy(xpath = "(//mat-select[@role='combobox'])[16]")
    WebElement dropdownButtonVFA_Status;

    @FindBy(xpath = "(//mat-select[@role='combobox'])[19]")
    WebElement dropdownButtonNotificationStatus;

    @FindBy(xpath = "(//mat-select[@role='combobox'])[22]")
    WebElement dropdownButtonActStatus;

    @FindBy(xpath = "(//mat-select[@role='combobox'])[25]")
    WebElement dropdownButtonFinalPaymentStatus;

    @FindBy(xpath = "(//mat-select[@role='combobox'])[28]")
    WebElement dropdownButtonCommitmentStatus;

    @FindBy(xpath = "(//mat-select[@role='combobox'])[30]")
    WebElement dropdownButtonAnnualReportStatus;

    @FindBy(xpath = "(//button[span[text()='Pridėti']])[10]")
    WebElement buttonAdd;

    @FindBy(xpath = "(//mat-label[contains(text(), 'Susijęs etapo dokumentas')])[6]")
    WebElement dropdownButtonDocumentInsurance;

    @FindBy(xpath = "(//mat-label[contains(text(),'Darbo vieta')]/ancestor::div[contains(@class,'mat-mdc-form-field')])[1]//mat-select")
    WebElement dropdownButtonWorkPlace;

    @FindBy(xpath = "(//mat-label[contains(text(),'Draudimo tipas')]/ancestor::div[contains(@class,'mat-mdc-form-field')])[1]//mat-select")
    WebElement dropdownButtonInsuranceType;

    @FindBy(xpath = "(//mat-label[contains(text(),'Įmokų laikotarpis')]/ancestor::div[contains(@class,'mat-mdc-form-field')])[1]//mat-select")
    WebElement dropdownButtonPaymentPeriod;

    @FindBy(xpath = "(//mat-label[contains(text(),'Darbo vietos (-ų) tipas')]/ancestor::div[contains(@class,'mat-mdc-form-field')])[1]//mat-select")
    WebElement dropdownButtonWorkPlaceType;

    @FindBy(xpath = "//button[normalize-space(.)='Patvirtinti']")
    WebElement buttonConfirmProject;

    @FindBy(xpath = "//button[normalize-space(.)='Saugoti ruošinį']")
    WebElement buttonSaveDocumentDraft;

    @FindBy(xpath = "//td[normalize-space(.)='Įsakymas skirti paramą ir sudaryti sutartį']")
    WebElement orderDocument;

    @FindBy(xpath = "//td[normalize-space(.)='Sutarties užtikrinimo garantas']")
    WebElement accountDocument;

    @FindBy(xpath = "//td[normalize-space(.)='Vietinių užimtumo iniciatyvų projekto įgyvendinimo ir finansavimo sutartis']")
    WebElement contractVUI_Document;

    @FindBy(xpath = "//td[normalize-space(.)='Veiklos finansinė ataskaita']")
    WebElement contractVFA_Document;

    @FindBy(xpath = "//td[normalize-space(.)='Draudimo įrodymas arba atsisakymas']")
    WebElement insuranceDocument;

    @FindBy(xpath = "//td[normalize-space(.)='Pranešimas apie sudarytas materialines ir teisines sąlygas naujoms darbo vietoms sukurti']")
    WebElement notificationDocument;

    @FindBy(xpath = "(//input[contains(@class, 'mat-datepicker-input')])[2]")
    WebElement inputRandomDate;

    @FindBy(xpath = "//common-text-input//input[@type='text']")
    WebElement inputAccount;

    @FindBy(xpath = "(//input[@id='phone'])[2]")
    WebElement inputPhone;

    @FindBy(xpath = "(//common-decimal-input//input[@type='text'])[2]")
    WebElement inputVFA_Amount;

    @FindBy(xpath = "(//div[contains(@class,'mat-mdc-form-field-infix')]/common-text-input/input)[2]")
    WebElement inputUserFirstName;

    @FindBy(xpath = "(//div[contains(@class,'mat-mdc-form-field-infix')]/common-text-input/input)[3]")
    WebElement inputUserLastName;

    @FindBy(xpath = "//div[contains(@class,'mat-mdc-form-field-infix')]/common-integer-input/input")
    WebElement inputUserPersonCode;

    @FindBy(xpath = "//button[normalize-space(.)='Teikti pasirašymui']")
    WebElement buttonSendForSignature;

    @FindBy(xpath = "//button[normalize-space(.)='Pasirašyti']")
    WebElement buttonSign;

    @FindBy(xpath = "//button[normalize-space(.)='Teikti peržiūrai']")
    WebElement buttonReview;

    @FindBy(xpath = "//button[normalize-space(text())='Teikti']")
    WebElement buttonConfirmReview;

    public void clickMenuProjects() {
        buttonMenuProjects.click();
    }

    public void clickProjectRow() {
        lastProject.click();
    }

    public String getProjectLabelText() {
        return labelProjects.getText();
    }

    public void clickButtonEditProject_Document() {
        buttonEditProject.click();
    }

    public WebElement getDropdownButtonOrderStatus() {
        return dropdownButtonOrderStatus;
    }

    public WebElement getDropdownButtonAccountStatus() {
        return dropdownButtonAccountStatus;
    }

    public WebElement getDropdownButtonKOT_Status() {
        return dropdownButtonKOT_Status;
    }

    public WebElement getDropdownButtonContractStatus() {
        return dropdownButtonContractStatus;
    }

    public WebElement getDropdownButtonAdvancePaymentStatus() {
        return dropdownButtonAdvancePaymentStatus;
    }

    public WebElement getDropdownButtonVFA_Status() {
        return dropdownButtonVFA_Status;
    }

    public WebElement getDropdownButtonNotificationStatus() {
        return dropdownButtonNotificationStatus;
    }

    public WebElement getDropdownButtonActStatus() {
        return dropdownButtonActStatus;
    }

    public WebElement getDropdownButtonFinalPaymentStatus() {
        return dropdownButtonFinalPaymentStatus;
    }

    public WebElement getDropdownButtonCommitmentStatus() {
        return dropdownButtonCommitmentStatus;
    }

    public WebElement getDropdownButtonAnnualReportStatus() {
        return dropdownButtonAnnualReportStatus;
    }

    public void clickButtonAdd() {
        buttonAdd.click();
    }

    public WebElement getDropdownButtonDocumentInsurance() {
        return dropdownButtonDocumentInsurance;
    }

    public WebElement getDropdownButtonWorkPlace() {
        return dropdownButtonWorkPlace;
    }

    public WebElement getDropdownButtonInsuranceType() {
        return dropdownButtonInsuranceType;
    }

    public WebElement getDropdownButtonPaymentPeriod() {
        return dropdownButtonPaymentPeriod;
    }

    public WebElement getDropdownButtonWorkPlaceType() {
        return dropdownButtonWorkPlaceType;
    }

    public void clickButtonEditConfirmProjectDocument() {
        buttonConfirmProject.click();
    }

    public void clickButtonSaveDocumentDraft() {
        buttonSaveDocumentDraft.click();
    }

    public void clickOrderDocument() {
        orderDocument.click();
    }

    public void clickAccountDocument() {
        accountDocument.click();
    }

    public void enterValueIntoInput(WebElement element, String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));

        element.clear();
        element.sendKeys(value);
    }

    public void enterAccountValue(String value) {
        enterValueIntoInput(inputAccount, value);
    }

    public void enterPhoneValue(String value) {
        enterValueIntoInput(inputPhone, value);
    }

    public void enterAmountValue(String value) {
        enterValueIntoInput(inputVFA_Amount, value);
    }

    public void enterUserFirstName(String value) {
        enterValueIntoInput(inputUserFirstName, value);
    }

    public void enterUserLastName(String value) {
        enterValueIntoInput(inputUserLastName, value);
    }

    public void enterUserPersonCode(String value) {
        enterValueIntoInput(inputUserPersonCode, value);
    }

    public WebElement getInputAccount() {
        return inputAccount;
    }

    public void clickContractVUI_Document() {
        contractVUI_Document.click();
    }

    public void clickContractVFA_Document() {
        contractVFA_Document.click();
    }

    public void clickInsuranceDocument() {
        insuranceDocument.click();
    }

    public void clickNotificationDocument() {
        notificationDocument.click();
    }

    public void checkCheckboxByLabel(String labelText) {
        WebElement label = driver.findElement(
                By.xpath("//label[text()='" + labelText + "']"));
        label.click();
    }

    public void clickButtonSendForSignature() {
        buttonSendForSignature.click();
    }

    public void clickButtonSign() {
        buttonSign.click();
    }

    public void clickButtonReview() {
        buttonReview.click();
    }

    public void clickButtonConfirmReview() {
        buttonConfirmReview.click();
    }

    public void enterRandomDate() {
        LocalDate today = LocalDate.now();
        LocalDate futureDate = today.plusDays(180);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedFutureDate = futureDate.format(formatter);

        if (inputRandomDate.isDisplayed() && inputRandomDate.isEnabled()) {
            inputRandomDate.clear();
            inputRandomDate.sendKeys(formattedFutureDate);
        } else {
            throw new RuntimeException("First date input is not interactable.");
        }
    }

}
