package lt.insoft.uztis.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

import java.time.Duration;

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

    @FindBy(xpath = "//common-text-input//input[@type='text']")
    WebElement inputAccount;

    @FindBy(xpath = "(//input[@id='phone'])[2]")
    WebElement inputPhone;

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

    public WebElement getInputAccount() {
        return inputAccount;
    }

    public WebElement getInputPhone() {
        return inputPhone;
    }

    public void clickContractVUI_Document() {
        contractVUI_Document.click();
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



}
