package lt.insoft.uztis.pages;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;


public class InvitationsPage extends UztisPage {

    private static final Logger log = getLogger(lookup().lookupClass());
    private WebDriverWait wait;

    public InvitationsPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }


//    @FindBy(id = "kc-login")
//    WebElement buttonLogin;

    @FindBy(xpath = "//a[@href='/application/invitation']")
    WebElement buttonMenuInvitation;
    @FindBy(xpath = "//h2[.='Kvietimai teikti paraiškas']")
    WebElement labelInvitations;
    @FindBy(xpath = "//a[@href='/application/invitation/create']")
    WebElement buttonCreateNewInvitation;

    @FindBy(xpath = "//mat-label[contains(text(), 'Paraiška')]")
    WebElement dropdownButtonApplication;
    @FindBy(css = "mat-option")
    List<WebElement> dropdownText;

    @FindBy(xpath = "//mat-label[contains(text(), 'Kvietimo atrankos numeris')]")
    WebElement dropdownButtonInvitationNumber;

    @FindBy(xpath = "//input[contains(@class, 'mat-datepicker-input')]") // šitas tinka
    WebElement inputStartDate;
    @FindBy(xpath = "(//input[contains(@class, 'mat-datepicker-input')])[2]")
    WebElement inputEndDate;
    @FindBy(xpath = "(//input[contains(@class, 'mat-datepicker-input')])[3]")
    WebElement inputPublicationEndDate;

    @FindBy(xpath = "//div[contains(@class, 'ngb-tp-input-container') and contains(@class, 'ngb-tp-hour')]//input[@type='text' and @maxlength='2' and @placeholder='HH']")
    WebElement inputStartTimeHours;
    @FindBy(xpath = "//input[@type='text' and @maxlength='2' and @placeholder='MM' and @aria-label='Minutes']")
    WebElement inputStartTimeMinutes;

    @FindBy(xpath = "(//input[@aria-label='Hours'])[2]")
    WebElement inputEndTimeHours;
    @FindBy(xpath = "(//input[@aria-label='Minutes'])[2]")
    WebElement inputEndTimeMinutes;

    @FindBy(xpath = "//common-button[@btnclass='btn btn--transparent secondary-color']//button")
    WebElement buttonAdd;

    @FindBy(xpath = "(//mat-label[contains(text(), 'Atvaizduojama informacija apie sumas')]//following::mat-select)[1]")
    WebElement dropdownButtonAmountsInformation1;
    @FindBy(xpath = "(//mat-label[contains(text(), 'Atvaizduojama informacija apie sumas')]//following::mat-select)[2]")
    WebElement dropdownButtonAmountsInformation2;
    @FindBy(xpath = "(//mat-label[contains(text(), 'Atvaizduojama informacija apie sumas')]//following::mat-select)[3]")
    WebElement dropdownButtonAmountsInformation3;

    @FindBy(xpath = "(//mat-form-field[.//mat-label[contains(text(), 'Reikšmė')]]//input)")
    WebElement amountInputOne;
    @FindBy(xpath = "(//mat-form-field[.//mat-label[contains(text(), 'Reikšmė')]]//input)[2]")
    WebElement amountInputTwo;
    @FindBy(xpath = "(//mat-form-field[.//mat-label[contains(text(), 'Reikšmė')]]//input)[3]")
    WebElement amountInputThree;


    @FindBy(xpath = "(//div[contains(@class, 'mdc-checkbox')]//input[@type='checkbox'])[1]")
    public WebElement checkboxConfirm;
    @FindBy(xpath = "(//div[contains(@class, 'mdc-checkbox')]//input[@type='checkbox'])[2]")
    public WebElement checkboxLegalEntities;
    @FindBy(xpath = "(//div[contains(@class, 'mdc-checkbox')]//input[@type='checkbox'])[3]")
    public WebElement checkboxPersonsRegisteredWithEmploymentService;
    @FindBy(xpath = "(//div[contains(@class, 'mdc-checkbox')]//input[@type='checkbox'])[4]")
    public WebElement checkboxPersonsRegisteredWithEmploymentServiceAsEmployers;

    @FindBy(xpath = "(//common-integer-input//input)[2]")
    WebElement inputSecondEvaluationGrade;
    @FindBy(xpath = "(//common-integer-input//input)[3]")
    WebElement inputThirdEvaluationGrade;

    @FindBy(xpath = "(//common-decimal-input//input)[4]")
    WebElement inputSecondEvaluationGradeWeight;
    @FindBy(xpath = "(//common-decimal-input//input)[5]")
    WebElement inputThirdEvaluationGradeWeight;

    @FindBy(xpath = "//mat-label[contains(text(), 'Biudžetas, Eur')]/ancestor::mat-form-field//common-decimal-input/input")
    WebElement inputBudget;

//    @FindBy(xpath = "//mat-select[@role='combobox']//div[contains(@class,'mat-mdc-select-trigger')]")
//    WebElement dropdownButtonArticle;

    @FindBy(xpath = "(//common-decimal-input//input)[14]")
    WebElement inputBudgetKaunas;
    @FindBy(xpath = "(//common-decimal-input//input)[15]")
    WebElement inputBudgetKlaipeda;
    @FindBy(xpath = "(//common-decimal-input//input)[16]")
    WebElement inputBudgetPanevezys;
    @FindBy(xpath = "(//common-decimal-input//input)[17]")
    WebElement inputBudgetSiauliai;
    @FindBy(xpath = "(//common-decimal-input//input)[18]")
    WebElement inputBudgetVilnius;

    @FindBy(xpath = "//div[contains(@class, 'ql-editor')]")
    WebElement inputDescription;
    @FindBy(xpath = "(//div[contains(@class, 'ql-editor')])[2]")
    WebElement inputApplicants;
    @FindBy(xpath = "(//div[contains(@class, 'ql-editor')])[3]")
    WebElement inputExpenditures;
    @FindBy(xpath = "(//div[contains(@class, 'ql-editor')])[4]")
    WebElement inputActs;
    @FindBy(xpath = "(//div[contains(@class, 'ql-editor')])[5]")
    WebElement inputEducation;

    @FindBy(css = "common-button:nth-of-type(2) > .btn.btn--primary")
    WebElement buttonSave;

    @FindBy(css = "button.btn--accent")
    WebElement buttonPublish;


    @FindBy(css = ".reverse-mobile button.btn.btn--primary")
    WebElement buttonPublicationConfirmation;

    //------------------------------------------
    @FindBy(css = "common-button:nth-of-type(2) > .btn.btn--primary")
    WebElement buttonSearch;

    @FindBy(xpath = "//tr[td/span[contains(text(), 'Ruošinys')]]")
    WebElement statusInvitationRowDraft;
    @FindBy(xpath = "//tr[td/span[contains(text(), 'Publikuojamas')]]")
    WebElement statusInvitationRowPublishing;
    @FindBy(xpath = "//tr[td/span[contains(text(), 'Sustabdytas publikavimas')]]")
    WebElement statusInvitationPublicationStopped;
    @FindBy(xpath = "//tr[td/span[contains(text(), 'Archyvuotas')]]")
    WebElement statusInvitationArchived;

    @FindBy(css = ".btn--primary-light-menu")
    WebElement buttonAction;

    @FindBy(xpath = "//button[mat-icon[text()='assignment_late']]")
    WebElement buttonStopPublication;
    @FindBy(css = "div#mat-menu-panel-1 > div > button:nth-of-type(1)")
    WebElement buttonArchive;
    @FindBy(xpath = "//button[mat-icon[text()='delete']]")
    WebElement buttonDelete;

    @FindBy(css = "button.btn.btn--primary")
    WebElement buttonEdit;

    @FindBy(xpath = "//textarea[contains(@class, 'mat-mdc-form-field-input-control')]")
    WebElement inputReason;

    @FindBy(css = ".btn-group-mobile.modal-footer.reverse-mobile > common-button:nth-of-type(2)")
    WebElement buttonStatusChangeConfirmation;

    @FindBy(css = ".status.status-warning")
    WebElement labelInvitationsStatus;

    @FindBy(css = ".status.status-gray")
    WebElement labelInvitationsStatusArchive;

    //-------------------------------------------------------------------

    //Statistic information:
    @FindBy(xpath = "//span[contains(@class, 'mdc-tab__text-label') and text()='Statistinė informacija balų skaičiavimui']")
    WebElement tabStatisticInfo;

    @FindBy(css = ".common-datepicker-body input")
    WebElement inputDataDate;

    @FindBy(xpath = "(//common-decimal-input[@required]//input[@type='text'])[1]")
    WebElement inputUnemployment;

    @FindBy(xpath = "(//common-decimal-input[@required]//input[@type='text'])[2]")
    WebElement inputSalary;

    //-------------------------------------------

    @FindBy(xpath = "//div[contains(@class, 'mat-mdc-form-field-infix')]/common-select//mat-select[contains(@class, 'mat-mdc-select')]")
    WebElement invitationCode;

    //-------------------------------------------

    public String getInvitationCode() {
        return invitationCode.getText();
    }

    //-------------------------------------------

    public void clickMenuInvitation() {
        buttonMenuInvitation.click();
    }

    public String getInvitationLabelText() {
        return labelInvitations.getText();
    }

    public void clickCreateNewApplicationForm() {
        buttonCreateNewInvitation.click();
    }

    public void selectValueFromApplication(String codeText) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonApplication)).click();
            List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElements(dropdownText));

            options.stream()
                    .filter(option -> option.getText().contains(codeText))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Option with text '" + codeText + "' not found in dropdown!"))
                    .click();
//            for (WebElement element : options) {
//                if (element.getText().contains(codeText)) {
//                    element.click();
//                    return;
//                }
//            }
//            throw new RuntimeException("Option with text '" + codeText + "' not found in dropdown!");

        } catch (Exception e) {
            log.error("Failed to select value in dropdown: {}", e.getMessage());
            throw e;
        }
    }

    public void selectValueByListInvitationNumber(String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        log.info("Located the dropdown button.");
        wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonInvitationNumber)).click();

        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option")));

        options.stream()
                .filter(option -> option.getText().trim().equalsIgnoreCase(valueToSelect))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Dropdown value '" + valueToSelect + "' not found."))
                .click();
//        boolean optionSelected = false;
//        for (WebElement option : options) {
//            if (option.getText().trim().equalsIgnoreCase(valueToSelect)) {
//                wait.until(ExpectedConditions.elementToBeClickable(option)).click();
//                optionSelected = true;
//                log.info("Successfully selected the value: '{}'", valueToSelect);
//                break;
//            }
//        }
//
//        if (!optionSelected) {
//            log.warn("Dropdown value '{}' not found.", valueToSelect);
//        }

        try {
            wait.until(ExpectedConditions.attributeToBe(dropdownButtonInvitationNumber, "aria-expanded", "false"));
        } catch (TimeoutException e) {
            log.error("Timeout while waiting for dropdown to close with aria-expanded = 'false'.");
        }
    }

    public void enterCurrentDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDate = today.format(formatter);

        if (inputStartDate.isDisplayed() && inputStartDate.isEnabled()) {
            inputStartDate.clear();
            inputStartDate.sendKeys(currentDate);
        } else {
            throw new RuntimeException("Start date input is not interactable.");
        }
    }

    public void enterEndDate() {
        LocalDate today = LocalDate.now();
        LocalDate futureDate = today.plusDays(30); // Pridedame 30 dienų
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedFutureDate = futureDate.format(formatter);

        if (inputEndDate.isDisplayed() && inputEndDate.isEnabled()) {
            inputEndDate.clear();
            inputEndDate.sendKeys(formattedFutureDate);
        } else {
            throw new RuntimeException("End date input is not interactable.");
        }
    }

    public void enterPublicationEndDate() {
        LocalDate today = LocalDate.now();
        LocalDate futureDate = today.plusDays(60); // Pridedame 60 dienu
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedFutureDate = futureDate.format(formatter);

        if (inputPublicationEndDate != null && inputPublicationEndDate.isDisplayed() && inputPublicationEndDate.isEnabled()) {
            inputPublicationEndDate.clear();
            inputPublicationEndDate.sendKeys(formattedFutureDate);
        } else {
            throw new RuntimeException("Publication end date input is not interactable.");
        }
    }

    private void enterTime(WebElement timeElement, String value, String fieldName) {
        if (timeElement.isEnabled() && timeElement.isDisplayed()) {
            timeElement.clear();
            timeElement.sendKeys(value);
        } else {
            throw new RuntimeException(fieldName + " input is not interactable");
        }
    }

    public void enterStartTime(String hours, String minutes) {
        enterTime(inputStartTimeHours, hours, "Start Hours");
        enterTime(inputStartTimeMinutes, minutes, "Start Minutes");
    }

    public void enterEndTime(String hours, String minutes) {
        enterTime(inputEndTimeHours, hours, "End Hours");
        enterTime(inputEndTimeMinutes, minutes, "End Minutes");
    }

    public void clickButtonAdd() {
        buttonAdd.click();
    }

    public void selectDropdownAmountInformation(WebElement dropdownButton, String valueToSelect) {
        log.info("Located the dropdown button.");
        wait.until(ExpectedConditions.elementToBeClickable(dropdownButton)).click();

        List<WebElement> options = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option"))
        );

        options.stream()
                .filter(option -> option.getText().trim().equalsIgnoreCase(valueToSelect))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Dropdown value '" + valueToSelect + "' not found."))
                .click();

        try {
            wait.until(ExpectedConditions.attributeToBe(dropdownButton, "aria-expanded", "false"));
        } catch (TimeoutException e) {
            log.error("Timeout while waiting for dropdown to close with aria-expanded = 'false'.");
        }
    }

    public void selectAmountInfo1(String value) {
        selectDropdownAmountInformation(dropdownButtonAmountsInformation1, value);
    }

    public void selectAmountInfo2(String value) {
        selectDropdownAmountInformation(dropdownButtonAmountsInformation2, value);
    }

    public void selectAmountInfo3(String value) {
        selectDropdownAmountInformation(dropdownButtonAmountsInformation3, value);
    }

    public void selectDropdownLabel(String labelText, String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            // Rasti dropdown trigger pagal mat-label tekstą
            By dropdownButtonLocator = By.xpath(
                    "//mat-label[contains(text(), '" + labelText + "')]//following::mat-select[1]//div[contains(@class,'mat-mdc-select-trigger')]"
            );

            WebElement dropdownButton = wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonLocator));

            // Scroll į view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdownButton);

            // Click su JS fallback
            try {
                dropdownButton.click();
            } catch (Exception e) {
                log.warn("Normal click failed, trying JS click...");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownButton);
            }

            // Rasti ir pasirinkti opciją overlay zonoje
            By optionLocator = By.xpath(
                    "//div[@class='cdk-overlay-container']//mat-option//span[normalize-space(text())='" + valueToSelect + "']"
            );

            WebElement optionToSelect = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .pollingEvery(Duration.ofMillis(200))
                    .until(ExpectedConditions.elementToBeClickable(optionLocator));

            try {
                optionToSelect.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", optionToSelect);
            }

            log.info("Selected '{}' from dropdown with label '{}'", valueToSelect, labelText);

        } catch (Exception e) {
            log.error("Dropdown selection failed for label '{}': {}", labelText, e.getMessage());
            throw new RuntimeException("Failed to select value from dropdown", e);
        }
    }

    public void enterInvitationAmountValue(WebElement inputField, String inputValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(inputField));
            inputField.clear();
            inputField.sendKeys(inputValue);
        } catch (TimeoutException e) {
            throw new RuntimeException("Input field is not interactable or timed out.", e);
        }
    }

    public void enterAmountValueOne(String value) {
        enterInvitationAmountValue(amountInputOne, value);
    }

    public void enterAmountValueTwo(String value) {
        enterInvitationAmountValue(amountInputTwo, value);
    }

    public void enterAmountValueThree(String value) {
        enterInvitationAmountValue(amountInputThree, value);
    }

    public void setCheckbox(WebElement checkbox, boolean shouldBeChecked) {
        if ((checkbox.isSelected() && !shouldBeChecked) || (!checkbox.isSelected() && shouldBeChecked)) {
            checkbox.click();
        }
    }

    public void enterEvaluationValue(String field, String value) {
        switch (field) {
            case "secondGrade":
                inputSecondEvaluationGrade.sendKeys(value);
                break;
            case "thirdGrade":
                inputThirdEvaluationGrade.sendKeys(value);
                break;
            case "secondWeight":
                inputSecondEvaluationGradeWeight.sendKeys(value);
                break;
            case "thirdWeight":
                inputThirdEvaluationGradeWeight.sendKeys(value);
                break;
            case "budget":
                inputBudget.sendKeys(value);
                break;
            default:
                throw new IllegalArgumentException("Unknown field: " + field);
        }
    }

    public enum Field {
        DESCRIPTION, APPLICANTS, EXPENDITURES, ACTS, EDUCATION
    }

    public void enterText(Field field, String text) {
        WebElement inputField;

        switch (field) {
            case DESCRIPTION -> inputField = inputDescription;
            case APPLICANTS -> inputField = inputApplicants;
            case EXPENDITURES -> inputField = inputExpenditures;
            case ACTS -> inputField = inputActs;
            case EDUCATION -> inputField = inputEducation;
            default -> throw new IllegalArgumentException("Nežinomas laukas: " + field);
        }

        log.info("Įvedame tekstą į lauką {}: '{}'", field, text);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(inputField));
            inputField.clear();
            inputField.sendKeys(text);
        } catch (TimeoutException e) {
            log.error("Laukelis {} negalima pasiekti arba timeout.", field);
            throw new RuntimeException("Laukelis " + field + " negalima pasiekti.", e);
        }
    }

    public enum City {
        KAUNAS, KLAIPEDA, PANEVEZYS, SIAULIAI, VILNIUS
    }

    public void enterBudget(City city, String amount) {
        WebElement inputField;
        switch (city) {
            case KAUNAS -> inputField = inputBudgetKaunas;
            case KLAIPEDA -> inputField = inputBudgetKlaipeda;
            case PANEVEZYS -> inputField = inputBudgetPanevezys;
            case SIAULIAI -> inputField = inputBudgetSiauliai;
            case VILNIUS -> inputField = inputBudgetVilnius;
            default -> throw new IllegalArgumentException("Nežinomas miestas: " + city);
        }

        log.info("Įvedame biudžetą miestui {}: '{}'", city, amount);

        wait.until(ExpectedConditions.elementToBeClickable(inputField));
        inputField.clear();
        inputField.sendKeys(amount);
    }

    //    arba C:\Users\ingrida.zadorozniene\Desktop\Asm\automatinis\IngridaZ_egzaminas\files\TXT.txt
    //         C:\Users\ingrida.zadorozniene\TXT.txt
    public void uploadFile() {
        String filePath = "C:\\Users\\ingrida.zadorozniene\\TXT.txt";
        WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
        fileInput.sendKeys(filePath);
    }

    public void clickButtonPublish() {
        buttonPublish.click();
    }

    public void clickButtonSave() {
        buttonSave.click();
    }

    public void clickButtonEdit() {
        buttonEdit.click();
    }

    public void clickButtonPublicationConfirmation() {
        buttonPublicationConfirmation.click();
    }

    public void clickButtonSearch() {
        buttonSearch.click();
    }

    public void clickInvitationRowDraft() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            wait.until(ExpectedConditions.elementToBeClickable(statusInvitationRowDraft)).click();
        } catch (StaleElementReferenceException e) {
            wait.until(ExpectedConditions.elementToBeClickable(statusInvitationRowDraft)).click();
        }
    }

    public void clickInvitationRowPublishing() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            wait.until(ExpectedConditions.elementToBeClickable(statusInvitationRowPublishing)).click();
        } catch (StaleElementReferenceException e) {
            wait.until(ExpectedConditions.elementToBeClickable(statusInvitationRowPublishing)).click();
        }
    }

    public void clickInvitationRowPublicationStopped() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            wait.until(ExpectedConditions.elementToBeClickable(statusInvitationPublicationStopped)).click();
        } catch (StaleElementReferenceException e) {
            wait.until(ExpectedConditions.elementToBeClickable(statusInvitationPublicationStopped)).click();
        }
    }

    public void clickInvitationRowArchived() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            wait.until(ExpectedConditions.elementToBeClickable(statusInvitationArchived)).click();
        } catch (StaleElementReferenceException e) {
            wait.until(ExpectedConditions.elementToBeClickable(statusInvitationArchived)).click();
        }
    }

//    public void clickButtonLogin() {
//        buttonLogin.click();
//    }

    public void clickButtonAction() {
        buttonAction.click();
    }

    public void clickButtonStopPublication() {
        buttonStopPublication.click();
    }

    public void clickButtonArchive() {
        buttonArchive.click();
    }

    public void clickButtonDelete() {
        buttonDelete.click();
    }

    public void enterReason(String text5) {
        this.inputReason.sendKeys(text5);
    }

    public void clickButtonStatusChangeConfirmation() {
        buttonStatusChangeConfirmation.click();
    }

    public String getInvitationStatusText() {
        return labelInvitationsStatus.getText();
    }

    public String getInvitationStatusTextArchive() {
        return labelInvitationsStatusArchive.getText();
    }

    //-------------------------
    public void clickTabStatisticInformation() {
        tabStatisticInfo.click();
    }

    public void enterCurrentDateStatistic() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDate = today.format(formatter);

        if (inputDataDate.isDisplayed() && inputDataDate.isEnabled()) {
            inputDataDate.clear();
            inputDataDate.sendKeys(currentDate);
        } else {
            throw new RuntimeException("Data date input is not interactable.");
        }
    }

    public void enterUnemploymentValue(String valueU) {
        this.inputUnemployment.sendKeys(valueU);
    }

    public void enterAverageSalary(String salaryA) {
        this.inputSalary.sendKeys(salaryA);
    }

    public void uploadFileStatistic() {
        String filePath = "C:\\Users\\ingrida.zadorozniene\\Desktop\\Asm\\UZTIS\\III_iteracija\\PRIEMIMO_TESTAVIMUI\\Šablonas (5).xlsx";
        WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
        fileInput.sendKeys(filePath);
    }
}














