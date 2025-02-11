package lt.insoft.uztis.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
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

public class SubmittedApplicationsPage extends UztisPage {

    private static final Logger log = getLogger(lookup().lookupClass());
    private static org.openqa.selenium.By By;

    public SubmittedApplicationsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@href='/application/submitted']")
    WebElement buttonMenuSubmittedApplications;

    @FindBy(xpath = "//h2[.='Kvietimui pateiktos paraiškos']")
    WebElement labelSubmittedApplications;

    @FindBy(xpath = "//td[@class='mat-mdc-cell mdc-data-table__cell cdk-cell cdk-column-CODE mat-column-CODE ng-star-inserted' and @role='cell']")
    WebElement lastInvitationCode;

    @FindBy(xpath = "//mat-select[contains(@class, 'mat-mdc-select') and @aria-required='true']")
    WebElement dropdownButtonInvitation;

    @FindBy(css = "mat-option")
    List<WebElement> dropdownText;

    @FindBy(xpath = "//button[contains(@class, 'btn--primary-light-menu') and contains(text(), 'Veiksmai')]")
    WebElement buttonActionApplication;

    @FindBy(xpath = "//button[contains(@class, 'mat-mdc-menu-item') and .//span[contains(text(), 'Pateikti naują')]]")
    WebElement buttonNewApplication;

    @FindBy(xpath = "//button[contains(@class, 'mat-mdc-menu-item') and .//span[contains(text(), 'Priskirti vertintojus')]]")
    WebElement buttonAddEvaluators;

    @FindBy(xpath = "//button[contains(@class, 'i-forms-stepper-button-next') and normalize-space(text())='Toliau']")
    WebElement buttonNext;

    @FindBy(xpath = "//input[@id='phone']")
    WebElement phoneInput;

    //application form step 1
    //------------------------------

    @FindBy(xpath = "//input[@name='mat-radio-group-0' and @value='FA']")
    WebElement radioButtonFA;

    @FindBy(xpath = "//mat-form-field[contains(.//label, 'El. pašto adresas')]//input")
    WebElement inputEMail;

    @FindBy(xpath = "//mat-label[contains(text(), 'Projekto teikėjo planuojama vykdyti veikla, kuriai prašoma subsidija, kodas pagal EVRK')]")
    WebElement dropdownButtonEVRK;

    //DVP
    @FindBy(xpath = "//mat-label[contains(text(), 'Paraiškos teikėjo vykdoma veikla, kodas pagal EVRK')]")
    WebElement dropdownButtonEVRK_DVP;
    //DVP

    @FindBy(xpath = "//mat-form-field[.//label[contains(., 'Planuojamos (-ų) steigti darbo vietos (-ų) adresas')]]//input")
    WebElement inputAddress;

    //DVP
    @FindBy(xpath = "//mat-form-field[.//label[contains(., 'Planuojamos (-ų) pritaikyti darbo vietos (-ų) adresas')]]//input")
    WebElement inputAddress_DVP;
    //DVP

    @FindBy(xpath = "//input[contains(@class, 'mat-datepicker-input')]")
    WebElement inputDate;

    @FindBy(xpath = "//mat-form-field[contains(.//label, 'Planuojamų steigti darbo vietų skaičius')]//input")
    WebElement inputJobCount;

    //application form step 2
    //------------------------------

    @FindBy(xpath = "//mat-label[contains(text(), 'Darbo vietos pavadinimas')]")
    WebElement dropdownButtonJobName;

    //DVP


    @FindBy(xpath = "//mat-label[contains(text(), 'Pritaikomos darbo vietos pavadinimas, kodas pagal profesijų klasifikatorių')]")
    WebElement dropdownButtonJobNameAdaptable;

    @FindBy(xpath = "//mat-label[contains(text(), 'Išlaidų tipas')]")
    WebElement dropdownButtonExpensesTyp;

    //DVP

    @FindBy(xpath = "//div[contains(@class, 'mdc-notched-outline')]//following::textarea[1]")
    WebElement inputJobFunction;

    @FindBy(xpath = "(//input[@type='radio' and @value='true'])[1]")
    WebElement radioButtonWithDisabilities;

    //DVP
    @FindBy(xpath = "(//input[@type='radio' and @value='false'])[1]")
    WebElement radioButtonForAlreadyWorking;
    //DVP

    @FindBy(xpath = "//mat-label[contains(text(), 'Papildomai remiamo asmens tipas')]")
    WebElement dropdownButtonDisabilitiesType;

    //DVP
    @FindBy(xpath = "//mat-label[contains(text(), 'Asmens su negalia dalyvumo lygis')]")
    WebElement dropdownButtonDisabilitiesLevel;
    //DVP



    @FindBy(xpath = "//div[contains(@class, 'mdc-notched-outline')]//following::textarea[2]")
    WebElement inputDisabilities;

    @FindBy(xpath = "//mat-label[contains(text(), 'Darbo laiko norma ir darbo laiko režimas')]")
    WebElement dropdownButtonTimeMode;

    @FindBy(xpath = "//mat-form-field[contains(.//label, 'Darbo laiko norma ir darbo laiko režimas')]//input")
    WebElement inputTimeModeOthers;

    @FindBy(xpath = "//div[contains(@class, 'mdc-notched-outline')]//following::textarea[3]")
    WebElement inputQualification;

    @FindBy(xpath = "(//input[contains(@class, 'mat-datepicker-input')])[2]")
    WebElement inputJopDate;

    //dvp
    @FindBy(xpath = "(//input[contains(@class, 'mat-datepicker-input')])[1]")
    WebElement inputJopDateDVP;
    //dvp

    @FindBy(xpath = "//mat-form-field[contains(.//label, 'Planuojamas mokėti bruto darbo užmokestis, Eur')]//input")
    WebElement inputSalary;

    @FindBy(xpath = "(//input[@type='radio' and @value='false'])[2]")
    WebElement radioButtonTemporaryJob;

    //DVP
    @FindBy(xpath = "(//input[@type='radio' and @value='true'])[2]")
    WebElement radioButtonPersonCountTwo;
    //DVP


    @FindBy(xpath = "(//input[@type='radio' and @value='false'])[3]")
    WebElement radioButtonSeasonJob;

    @FindBy(xpath = "//div[contains(@class, 'mdc-notched-outline')]//following::textarea[4]")
    WebElement inputJobDescription;

    @FindBy(xpath = "//div[contains(@class, 'mdc-notched-outline')]//following::textarea[5]")
    WebElement inputProsesDescription;

    @FindBy(xpath = "(//input[@type='radio' and @value='true'])[4]")
    WebElement radioButtonEnergy;

    @FindBy(xpath = "//div[contains(@class, 'mdc-notched-outline')]//following::textarea[6]")
    WebElement inputEnergyInformation;

    @FindBy(xpath = "(//input[@type='radio' and @value='true'])[5]")
    WebElement radioButtonRepair;

    @FindBy(xpath = "//div[contains(@class, 'mdc-notched-outline')]//following::textarea[7]")
    WebElement inputRepairInformation;

    @FindBy(xpath = "(//input[@type='radio' and @value='false'])[6]")
    WebElement radioButtonPVM;

    @FindBy(xpath = "(//mat-label[contains(text(), 'Darbo vietai įsteigti reikalinga')])[1]")
    WebElement dropdownButtonNecessaryForJobOne;

    @FindBy(xpath = "(//mat-label[contains(text(), 'Darbo vietai įsteigti reikalinga')])[2]")
    WebElement dropdownButtonNecessaryForJobTwo;

    //DVP
//    @FindBy(xpath = "//label//mat-label[contains(text(), 'Reikalinga')]")
    @FindBy(xpath = "//label[contains(@class, 'mdc-floating-label') and .//mat-label[contains(., 'Reikalinga')]]")
    WebElement dropdownButtonNecessaryForJobDVP;

    @FindBy(xpath = "//mat-form-field[contains(.//label, 'Darbo priemonės pavadinimas')]//input")
    WebElement inputTool;

    //DVP

//    @FindBy(xpath = "//mat-form-field[contains(.//label, 'Darbo priemonės pavadinimas')]//input")
//    WebElement inputTool;

        @FindBy(xpath = "(//mat-form-field[.//label[contains(normalize-space(), 'Darbo priemonės techninis parametras')]]//input)[1]")
    WebElement inputToolParameterOne;
    @FindBy(xpath = "(//mat-form-field[.//label[contains(normalize-space(), 'Darbo priemonės techninis parametras')]]//input)[2]")
    WebElement inputToolParameterTwo;
    @FindBy(xpath = "(//mat-form-field[.//label[contains(normalize-space(), 'Darbo priemonės techninis parametras')]]//input)[3]")
    WebElement inputToolParameterThree;

    @FindBy(xpath = "//mat-form-field[contains(.//label, 'Darbo priemonės kiekis, vnt.')]//input")
    WebElement inputToolsCount;

    @FindBy(xpath = "(//mat-form-field[contains(.//label, 'Kaina, Eur')]//input)[1]")
    WebElement inputPriceOne;

    @FindBy(xpath = "(//mat-form-field[contains(.//label, 'Kaina, Eur')]//input)[2]")
    WebElement inputPriceTwo;

    @FindBy(xpath = "(//mat-form-field[contains(.//label, 'Nuosavos lėšos, Eur')]//input)[1]")
    WebElement inputOwnFundsOne;

    @FindBy(xpath = "(//mat-form-field[contains(.//label, 'Nuosavos lėšos, Eur')]//input)[2]")
    WebElement inputOwnFundsTwo;

//    @FindBy(xpath = "//common-button//button[contains(@class, 'i-forms-repeater-button-add')]//mat-icon[text()='add']")
    @FindBy(xpath = "(//button[contains(@class, 'i-forms-repeater-button-add')])[4]")
    WebElement buttonAdd;

    @FindBy(xpath = "//mat-form-field[contains(.//label, 'Remonto darbų pavadinimas')]//input")
    WebElement inputRepairName;

    @FindBy(xpath = "//div[contains(@class, 'mdc-notched-outline')]//following::textarea[8]")
    WebElement inputRepairDescription;

    @FindBy(xpath = "//mat-form-field[contains(.//label, 'Valstybės institucijos, įstaigos (proc.)')]//input")
    WebElement inputCountryPerc;
    @FindBy(xpath = "//mat-form-field[contains(.//label, 'Savivaldybės institucijos, įstaigos (proc.)')]//input")
    WebElement inputInstitutionPerc;
    @FindBy(xpath = "//mat-form-field[contains(.//label, 'Valstybės ar savivaldybių įmonės (proc.)')]//input")
    WebElement inputMunicipalityPerc;

    @FindBy(xpath = "(//input[@type='radio' and @value='true'])[7]")
    WebElement radioButtonDeMinimis;

    @FindBy(xpath = "//mat-form-field[contains(.//label, 'Projekto ir finansuojančios programos pavadinimas')]//input")
    WebElement inputProgramName;

    @FindBy(xpath = "(//input[contains(@class, 'mat-datepicker-input')])[3]")
    WebElement inputProjectDateFrom;

    @FindBy(xpath = "(//input[contains(@class, 'mat-datepicker-input')])[4]")
    WebElement inputProjectDateUntil;

    @FindBy(xpath = "//mat-form-field[contains(.//label, 'Gautos paramos suma, Eur')]//input")
    WebElement inputSupportAmount;

//application form step 3 (Checkboxes)
//-----------
    @FindBy(xpath = "(//div[contains(@class, 'mdc-checkbox')]//input[@type='checkbox'])[1]")
    public WebElement checkboxConfirmationOne;

    @FindBy(xpath = "(//div[contains(@class, 'mdc-checkbox')]//input[@type='checkbox'])[2]")
    public WebElement checkboxConfirmationTwo;

    @FindBy(xpath = "(//div[contains(@class, 'mdc-checkbox')]//input[@type='checkbox'])[3]")
    public WebElement checkboxConfirmationThree;

    //application form step 4 (Checkboxes)
//-----------
    @FindBy(xpath = "(//div[contains(@class, 'mdc-checkbox')]//input[@type='checkbox'])[4]")
    public WebElement checkboxConfirmationStepFour;

    //application form step 5 (Checkboxes)
//-----------
    @FindBy(xpath = "(//mat-form-field[contains(.//label, 'Dokumento pavadinimas')]//input)[1]")
    WebElement inputDocumentNameOne;

    @FindBy(xpath = "(//mat-form-field[contains(.//label, 'Dokumento pavadinimas')]//input)[2]")
    WebElement inputDocumentNameTwo;

    @FindBy(xpath = "(//mat-form-field[contains(.//label, 'Dokumento pavadinimas')]//input)[3]")
    WebElement inputDocumentNameThree;

    @FindBy(xpath = "(//mat-form-field[contains(.//label, 'Dokumento pavadinimas')]//input)[4]")
    WebElement inputDocumentNameFour;

    @FindBy(xpath = "(//mat-form-field[contains(.//label, 'Dokumento pavadinimas')]//input)[5]")
    WebElement inputDocumentNameFive;

    @FindBy(xpath = "(//mat-form-field[contains(.//label, 'Dokumento pavadinimas')]//input)[6]")
    WebElement inputDocumentNameSix;

    @FindBy(xpath = "(//mat-form-field[contains(.//label, 'Dokumento pavadinimas')]//input)[7]")
    WebElement inputDocumentNameSeven;

    @FindBy(xpath = "(//mat-form-field[contains(.//label, 'Dokumento pavadinimas')]//input)[8]")
    WebElement inputDocumentNameEight;

    @FindBy(xpath = "(//mat-form-field[contains(.//label, 'Dokumento pavadinimas')]//input)[9]")
    WebElement inputDocumentNameNine;

    @FindBy(xpath = "(//mat-form-field[contains(.//label, 'Dokumento pavadinimas')]//input)[10]")
    WebElement inputDocumentNameTen;

    @FindBy(xpath = "(//div[contains(@class, 'mdc-checkbox')]//input[@type='checkbox'])[5]")
    public WebElement checkboxConfirmationDocument;

    @FindBy(xpath = "(//div[contains(@class, 'mdc-checkbox')]//input[@type='checkbox'])[6]")
    public WebElement checkboxConfirmationApplication;

    @FindBy(xpath = "//common-button/button[contains(text(), 'Peržiūrėti')]")
    WebElement buttonReview;

    @FindBy(xpath = "//common-button/button[contains(text(), 'Pateikti')]")
    WebElement buttonSubmit;

    @FindBy(xpath = "//common-button//button[@type='button' and contains(text(), 'Pateikti vertinimui')]")
    WebElement buttonSubmitConfirmation;

    @FindBy(xpath = "//common-button/button[contains(text(), 'Saugoti ruošinį')]")
    WebElement buttonSaveDraft;

    //DVP
    //--------------

    @FindBy(xpath = "(//mat-form-field[contains(.//label, 'Asmenų skaičius')]//input)[1]")
    WebElement inputPersonCountOne;

    @FindBy(xpath = "(//mat-form-field[contains(.//label, 'Asmenų skaičius')]//input)[2]")
    WebElement inputPersonCountTwo;

    //Add Evaluators
    //---------------

    @FindBy(xpath = "//div[contains(@class, 'mat-mdc-form-field-infix')]//input[@type='text' and contains(@class, 'mat-datepicker-input')]")
    WebElement firstEvaluationEndDate;
    @FindBy(xpath = "(//div[contains(@class, 'mat-mdc-form-field-infix')]//input[@type='text' and contains(@class, 'mat-datepicker-input')])[2]")
    WebElement secondEvaluationEndDate;
    @FindBy(xpath = "(//div[contains(@class, 'mat-mdc-form-field-infix')]//input[@type='text' and contains(@class, 'mat-datepicker-input')])[3]")
    WebElement thirdEvaluationEndDate;

    @FindBy(xpath = "//common-button[@btnclass='btn btn--primary']//button[text()='Priskirti vertintojus']")
    WebElement buttonAddEvaluatorsConfirmation;

    //---------------



    public void enterFirstEvaluationEndDate() {
        LocalDate today = LocalDate.now();
        LocalDate futureDate = today.plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedFutureDate = futureDate.format(formatter);

        if (firstEvaluationEndDate.isDisplayed() && firstEvaluationEndDate.isEnabled()) {
            firstEvaluationEndDate.clear();
            firstEvaluationEndDate.sendKeys(formattedFutureDate);
        } else {
            throw new RuntimeException("First date input is not interactable.");
        }
    }

    public void enterSecondEvaluationEndDate() {
        LocalDate today = LocalDate.now();
        LocalDate futureDate = today.plusDays(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedFutureDate = futureDate.format(formatter);

        if (secondEvaluationEndDate.isDisplayed() && secondEvaluationEndDate.isEnabled()) {
            secondEvaluationEndDate.clear();
            secondEvaluationEndDate.sendKeys(formattedFutureDate);
        } else {
            throw new RuntimeException("Second date input is not interactable.");
        }
    }

    public void enterThirdEvaluationEndDate() {
        LocalDate today = LocalDate.now();
        LocalDate futureDate = today.plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedFutureDate = futureDate.format(formatter);

        if (thirdEvaluationEndDate.isDisplayed() && thirdEvaluationEndDate.isEnabled()) {
            thirdEvaluationEndDate.clear();
            thirdEvaluationEndDate.sendKeys(formattedFutureDate);
        } else {
            throw new RuntimeException("Third date input is not interactable.");
        }
    }


    public void clickMenuSubmittedApplications() {
        buttonMenuSubmittedApplications.click();
    }

    public String getSubmittedApplicationsLabelText() {
        return labelSubmittedApplications.getText();
    }

    public void selectValueFromInvitation(String codeText) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonInvitation)).click();
            List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElements(dropdownText));

            options.stream()
                    .filter(option -> option.getText().contains(codeText))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Option with text '" + codeText + "' not found in dropdown!"))
                    .click();
        } catch (Exception e) {
            log.error("Failed to select value in dropdown: {}", e.getMessage());
            throw e;
        }
    }

    public void clickButtonActionApplication() {
        buttonActionApplication.click();
    }

    public void clickButtonNewApplication() {
        buttonNewApplication.click();
    }

    public void clickButtonAddEvaluators() {
        buttonAddEvaluators.click();
    }

    public void clickButtonAddEvaluatorsConfirmation() {
        buttonAddEvaluatorsConfirmation.click();
    }

    public boolean isButtonNextDisplayed() {
        try {
            return buttonNext.isDisplayed();
        } catch (NoSuchElementException e) {
            ApplicationFormsPage.log.error("Edit button is not displayed. Exception: {}", e.getMessage());
            return false;
        }
    }

    //----------------------------
    public void clickRadioButtonFA() {
        if (!radioButtonFA.isSelected()) {
            radioButtonFA.click();
        }
    }

    public void enterPhoneNumber(String phoneNumber) {
        try {
            phoneInput.clear();
            phoneInput.sendKeys(phoneNumber);
            System.out.println("Phone number entered successfully: " + phoneNumber);
        } catch (Exception e) {
            System.err.println("Failed to enter phone number: " + e.getMessage());
        }
    }

    public void enterEmailAddress(String emailAddress) {
        inputEMail.clear();
        inputEMail.sendKeys(emailAddress);
    }

    public void selectValueByListEVRK(String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            // Paspaudžiame ant pasirinkimo lauko
            log.info("Located the dropdown button.");
            wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonEVRK)).click();

            // Laukiame, kol pasirodys pasirinkimai
            List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option//span")));

            // Pridedame logą, kad pamatytume, kiek elementų surasta
            log.info("Found {} options in the dropdown.", options.size());

            // Surandame ir pasirinkame reikšmę
            boolean optionSelected = false;
            for (WebElement option : options) {
                // Patikriname, ar tekstas tinka
                if (option.getText().trim().equalsIgnoreCase(valueToSelect)) {
                    // Pasirenkame reikšmę
                    option.click();
                    optionSelected = true;
                    log.info("Successfully selected the value: '{}'", valueToSelect);
                    break; // Pasirenkame tik pirmą reikšmę
                }
            }

            // Jei reikšmė nerasta
            if (!optionSelected) {
                log.warn("The value '{}' was not found among the options.", valueToSelect);
                throw new RuntimeException("The value '" + valueToSelect + "' was not found in the dropdown.");
            }

            // Laukiame, kol pasirinkimo sąrašas užsidarys
            try {
                // Patikriname, ar 'aria-expanded' atributas pasikeitė į 'false'
                wait.until(ExpectedConditions.attributeToBe(dropdownButtonEVRK, "aria-expanded", "false"));
                log.info("Dropdown closed successfully.");
            } catch (TimeoutException e) {
                log.error("Failed to close the dropdown with aria-expanded = 'false'. Attempting manual close.");

                // Jeigu 'aria-expanded' nepasikeitė, bandykite uždaryti dropdown paspausdami už jo ribų
                try {
                    WebElement body = driver.findElement(By.className("cdk-overlay-backdrop"));
                    body.click(); // Paspaudžiame už dropdown ribų, kad uždarytumėte meniu
                    log.info("Manual close attempt for the dropdown using body click.");
                } catch (Exception ex) {
                    log.error("Failed to manually close the dropdown: {}", ex.getMessage());
                }
            }

        } catch (Exception e) {
            log.error("Error selecting value from the list: {}", e.getMessage());
            throw new RuntimeException("Failed to select from the list", e);
        }
    }

    public void selectValueByListEVRK_DVP(String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            log.info("Located the dropdown button.");
            wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonEVRK_DVP)).click();

            List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option//span")));

            log.info("Found {} options in the dropdown.", options.size());

            boolean optionSelected = false;
            for (WebElement option : options) {
                if (option.getText().trim().equalsIgnoreCase(valueToSelect)) {
                    option.click();
                    optionSelected = true;
                    log.info("Successfully selected the value: '{}'", valueToSelect);
                    break;
                }
            }

            if (!optionSelected) {
                log.warn("The value '{}' was not found among the options.", valueToSelect);
                throw new RuntimeException("The value '" + valueToSelect + "' was not found in the dropdown.");
            }

            try {
                wait.until(ExpectedConditions.attributeToBe(dropdownButtonEVRK_DVP, "aria-expanded", "false"));
                log.info("Dropdown closed successfully.");
            } catch (TimeoutException e) {
                log.error("Failed to close the dropdown with aria-expanded = 'false'. Attempting manual close.");

                try {
                    WebElement body = driver.findElement(By.className("cdk-overlay-backdrop"));
                    body.click(); // Paspaudžiame už dropdown ribų, kad uždarytumėte meniu
                    log.info("Manual close attempt for the dropdown using body click.");
                } catch (Exception ex) {
                    log.error("Failed to manually close the dropdown: {}", ex.getMessage());
                }
            }

        } catch (Exception e) {
            log.error("Error selecting value from the list: {}", e.getMessage());
            throw new RuntimeException("Failed to select from the list", e);
        }
    }


    public void enterAddress(String address) {
        inputAddress.clear();
        inputAddress.sendKeys(address);
    }

    //DVP
    public void enterAddress_DVP(String address) {
        inputAddress_DVP.clear();
        inputAddress_DVP.sendKeys(address);
    }
    //DVP

    public void enterDate() {
        LocalDate today = LocalDate.now();
        LocalDate pastDate = today.minusYears(1).minusDays(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedPastDate = pastDate.format(formatter);

        if (inputDate.isDisplayed() && inputDate.isEnabled()) {
            inputDate.clear();
            inputDate.sendKeys(formattedPastDate);
        } else {
            throw new RuntimeException("End date input is not interactable.");
        }
    }

    public void enterJobCount(String count) {
        inputJobCount.clear();
        inputJobCount.sendKeys(count);
    }

    public void clickButtonNext() {
        if (!buttonNext.isSelected()) {
            buttonNext.click();
        }
    }

    public void clickButtonReview() {
        if (!buttonReview.isSelected()) {
            buttonReview.click();
        }
    }

    public void clickButtonSubmit() {
        if (!buttonSubmit.isSelected()) {
            buttonSubmit.click();
        }
    }

    public void clickButtonSubmitConfirmation() {
        if (!buttonSubmitConfirmation.isSelected()) {
            buttonSubmitConfirmation.click();
        }
    }

    public void clickButtonSaveDraft() {
        if (!buttonSaveDraft.isSelected()) {
            buttonSaveDraft.click();
        }
    }

    //----------------------------

    public void selectDropdownJobName(String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Ilgesnis laukimo laikas

        log.info("Located the dropdown button.");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonJobName));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", dropdownButtonJobName); // Užtikrinsime, kad elementas būtų matomas
            dropdownButtonJobName.click();
        } catch (Exception e) {
            log.error("Dropdown button click failed: {}", e.getMessage());
        }

        try {
            List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option/span")));

            boolean optionSelected = false;
            for (WebElement option : options) {
                if (option.getText().trim().equalsIgnoreCase(valueToSelect)) {
                    wait.until(ExpectedConditions.elementToBeClickable(option)).click();
                    optionSelected = true;
                    log.info("Successfully selected the value: '{}'", valueToSelect);
                    break;
                }
            }

            if (!optionSelected) {
                log.warn("Dropdown value '{}' not found.", valueToSelect);
            }

        } catch (Exception e) {
            log.error("Failed to select dropdown option: {}", e.getMessage());
        }

        try {
            wait.until(ExpectedConditions.attributeToBe(dropdownButtonJobName, "aria-expanded", "false"));
        } catch (TimeoutException e) {
            log.error("Timeout while waiting for dropdown to close with aria-expanded = 'false'.");
        }
    }

    //DVP
public void selectDropdownJobNameAdaptable(String valueToSelect) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Ilgesnis laukimo laikas

    log.info("Located the dropdown button.");
    try {
        wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonJobNameAdaptable));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", dropdownButtonJobNameAdaptable); // Užtikrinsime, kad elementas būtų matomas
        dropdownButtonJobNameAdaptable.click();
    } catch (Exception e) {
        log.error("Dropdown button click failed: {}", e.getMessage());
    }

    try {
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option/span")));

        boolean optionSelected = false;
        for (WebElement option : options) {
            if (option.getText().trim().equalsIgnoreCase(valueToSelect)) {
                wait.until(ExpectedConditions.elementToBeClickable(option)).click();
                optionSelected = true;
                log.info("Successfully selected the value: '{}'", valueToSelect);
                break;
            }
        }

        if (!optionSelected) {
            log.warn("Dropdown value '{}' not found.", valueToSelect);
        }

    } catch (Exception e) {
        log.error("Failed to select dropdown option: {}", e.getMessage());
    }

    try {
        wait.until(ExpectedConditions.attributeToBe(dropdownButtonJobNameAdaptable, "aria-expanded", "false"));
    } catch (TimeoutException e) {
        log.error("Timeout while waiting for dropdown to close with aria-expanded = 'false'.");
    }
}

    public void selectDropdownExpensesTyp(String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Ilgesnis laukimo laikas

        log.info("Located the dropdown button.");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonExpensesTyp));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", dropdownButtonExpensesTyp); // Užtikrinsime, kad elementas būtų matomas
            dropdownButtonExpensesTyp.click();
        } catch (Exception e) {
            log.error("Dropdown button click failed: {}", e.getMessage());
        }

        try {
            List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option/span")));

            boolean optionSelected = false;
            for (WebElement option : options) {
                if (option.getText().trim().equalsIgnoreCase(valueToSelect)) {
                    wait.until(ExpectedConditions.elementToBeClickable(option)).click();
                    optionSelected = true;
                    log.info("Successfully selected the value: '{}'", valueToSelect);
                    break;
                }
            }

            if (!optionSelected) {
                log.warn("Dropdown value '{}' not found.", valueToSelect);
            }

        } catch (Exception e) {
            log.error("Failed to select dropdown option: {}", e.getMessage());
        }

        try {
            wait.until(ExpectedConditions.attributeToBe(dropdownButtonExpensesTyp, "aria-expanded", "false"));
        } catch (TimeoutException e) {
            log.error("Timeout while waiting for dropdown to close with aria-expanded = 'false'.");
        }
    }
    //DVP

        public void enterInputJobFunction(String text) {
        inputJobFunction.clear();
        inputJobFunction.sendKeys(text);
    }


    public void clickRadioButtonWithDisabilities() {
        if (!radioButtonWithDisabilities.isSelected()) {
            radioButtonWithDisabilities.click();
        }
    }

    //DVP
    public void clickRadioButtonForAlreadyWorking() {
        if (!radioButtonForAlreadyWorking.isSelected()) {
            radioButtonForAlreadyWorking.click();
        }
    }
    //DVP

    public void selectValueByListDisabilitiesType(String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            log.info("Clicking on the dropdown button.");
            wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonDisabilitiesType)).click();

            // Laukiame, kol išskleidžiamas meniu bus matomas
            List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option//span")));
            log.info("Found {} options in the dropdown.", options.size());

            boolean optionSelected = false;

            // Tikriname kiekvieną meniu parinktį
            for (WebElement option : options) {
                String optionText = option.getText().trim();
                log.debug("Checking option: '{}'", optionText);

                if (optionText.equalsIgnoreCase(valueToSelect)) {
                    option.click();  // Tiesioginis paspaudimas ant pasirinkimo
                    optionSelected = true;
                    log.info("Successfully selected the value: '{}'", valueToSelect);
                    break;
                }
            }

            if (!optionSelected) {
                log.warn("The value '{}' was not found among the options.", valueToSelect);
                throw new RuntimeException("The value '" + valueToSelect + "' was not found in the dropdown.");
            }

            log.info("Attempting to close the dropdown.");

            // Palaukite, kol pasirinkimas bus atliktas ir dropdown užsidarys
            // Paliekame laiko įsitikinti, kad pasirinkimas buvo atliktas (rekomenduojama ne mažiau kaip 500ms)
            Thread.sleep(500);

            // Bandome uždaryti meniu paspausdami ESC (kartais tai padeda, jei meniu nepatikimai užsidaro)
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.ESCAPE).perform();

            // Jei ESC nepadėjo, bandome paspausti į tuščią vietą ekrane
            WebElement body = driver.findElement(By.tagName("body")); // Paspauskime į visą puslapio kūną, kuris yra tiksliai matomas
            actions.moveToElement(body).click().perform();

            // Patikriname, ar meniu užsidarė
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//mat-option//span")));
            log.info("Dropdown should be closed now.");

        } catch (Exception e) {
            log.error("Error occurred while selecting value: {}", e.getMessage());
            throw new RuntimeException("Failed to select value from dropdown.", e);
        }
    }

    //DVP
    public void selectValueByListDisabilitiesLevel(String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            log.info("Clicking on the dropdown button.");
            wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonDisabilitiesLevel)).click();

            List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option//span")));
            log.info("Found {} options in the dropdown.", options.size());

            boolean optionSelected = false;

            for (WebElement option : options) {
                String optionText = option.getText().trim();
                log.debug("Checking option: '{}'", optionText);

                if (optionText.equalsIgnoreCase(valueToSelect)) {
                    option.click();  // Tiesioginis paspaudimas ant pasirinkimo
                    optionSelected = true;
                    log.info("Successfully selected the value: '{}'", valueToSelect);
                    break;
                }
            }

            if (!optionSelected) {
                log.warn("The value '{}' was not found among the options.", valueToSelect);
                throw new RuntimeException("The value '" + valueToSelect + "' was not found in the dropdown.");
            }

            log.info("Attempting to close the dropdown.");

            Thread.sleep(500);

            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.ESCAPE).perform();

            WebElement body = driver.findElement(By.tagName("body")); // Paspauskime į visą puslapio kūną, kuris yra tiksliai matomas
            actions.moveToElement(body).click().perform();

            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//mat-option//span")));
            log.info("Dropdown should be closed now.");

        } catch (Exception e) {
            log.error("Error occurred while selecting value: {}", e.getMessage());
            throw new RuntimeException("Failed to select value from dropdown.", e);
        }
    }
    //DVP



    public void enterDisabilities(String text) {
        inputDisabilities.clear();
        inputDisabilities.sendKeys(text);
    }

    public void selectDropdownTimeMode(String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        log.info("Located the dropdown button.");
        wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonTimeMode)).click();

        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option/span")));

        boolean optionSelected = false;
        for (WebElement option : options) {
            if (option.getText().trim().equalsIgnoreCase(valueToSelect)) {
                wait.until(ExpectedConditions.elementToBeClickable(option)).click();
                optionSelected = true;
                log.info("Successfully selected the value: '{}'", valueToSelect);
                break;
            }
        }
        if (!optionSelected) {
            log.warn("Dropdown value '{}' not found.", valueToSelect);
        }

        try {
            wait.until(ExpectedConditions.attributeToBe(dropdownButtonTimeMode, "aria-expanded", "false"));
        } catch (TimeoutException e) {
            log.error("Timeout while waiting for dropdown to close with aria-expanded = 'false'.");
        }
    }


    public void enterInputTimeModeOthers(String text) {
        inputTimeModeOthers.clear();
        inputTimeModeOthers.sendKeys(text);
    }

    public void enterQualification(String text) {
        inputQualification.clear();
        inputQualification.sendKeys(text);
    }

    public void enterJobDate() {
        LocalDate today = LocalDate.now();
        LocalDate pastDate = today.plusYears(1).plusDays(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedPastDate = pastDate.format(formatter);

        if (inputJopDate.isDisplayed() && inputJopDate.isEnabled()) {
            inputJopDate.clear();
            inputJopDate.sendKeys(formattedPastDate);
        } else {
            throw new RuntimeException("End date input is not interactable.");
        }
    }

    //DVP
    public void enterJobDateDVP() {
        LocalDate today = LocalDate.now();
        LocalDate pastDate = today.plusYears(1).plusDays(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedPastDate = pastDate.format(formatter);

        if (inputJopDateDVP.isDisplayed() && inputJopDateDVP.isEnabled()) {
            inputJopDateDVP.clear();
            inputJopDateDVP.sendKeys(formattedPastDate);
        } else {
            throw new RuntimeException("End date input is not interactable.");
        }
    }
//DVP

    public void enterSalary(String amount) {
        inputSalary.clear();
        inputSalary.sendKeys(amount);
    }

    public void clickRadioButtonTemporaryJob() {
        if (!radioButtonTemporaryJob.isSelected()) {
            radioButtonTemporaryJob.click();
        }
    }

    //DVP
    public void clickRadioButtonPersonCountTwo() {
        if (!radioButtonPersonCountTwo.isSelected()) {
            radioButtonPersonCountTwo.click();
        }
    }
    //DVP

    public void clickRadioButtonSeasonJob() {
        if (!radioButtonSeasonJob.isSelected()) {
            radioButtonSeasonJob.click();
        }
    }

    public void enterJobDescription(String text) {
        inputJobDescription.clear();
        inputJobDescription.sendKeys(text);
    }

    public void enterProsesDescription(String text) {
        inputProsesDescription.clear();
        inputProsesDescription.sendKeys(text);
    }

    public void clickRadioButtonEnergy() {
        if (!radioButtonEnergy.isSelected()) {
            radioButtonEnergy.click();
        }
    }

    public void enterEnergyInformation(String text) {
        inputEnergyInformation.clear();
        inputEnergyInformation.sendKeys(text);
    }

    public void clickRadioButtonRepair() {
        if (!radioButtonRepair.isSelected()) {
            radioButtonRepair.click();
        }
    }

    public void enterRepairInformation(String text) {
        inputRepairInformation.clear();
        inputRepairInformation.sendKeys(text);
    }

    public void clickRadioButtonPVM() {
        if (!radioButtonPVM.isSelected()) {
            radioButtonPVM.click();
        }
    }

    public void selectDropdownNecessaryForJobOne(String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        log.info("Located the dropdown button.");
        wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonNecessaryForJobOne)).click();

        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option/span")));

        boolean optionSelected = false;
        for (WebElement option : options) {
            if (option.getText().trim().equalsIgnoreCase(valueToSelect)) {
                wait.until(ExpectedConditions.elementToBeClickable(option)).click();
                optionSelected = true;
                log.info("Successfully selected the value: '{}'", valueToSelect);
                break;
            }
        }

        if (!optionSelected) {
            log.warn("Dropdown value '{}' not found.", valueToSelect);
        }

        try {
            wait.until(ExpectedConditions.attributeToBe(dropdownButtonNecessaryForJobOne, "aria-expanded", "false"));
        } catch (TimeoutException e) {
            log.error("Timeout while waiting for dropdown to close with aria-expanded = 'false'.");
        }
    }

    public void selectDropdownNecessaryForJobTwo(String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        log.info("Located the dropdown button.");
        wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonNecessaryForJobTwo)).click();

        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option/span")));

        boolean optionSelected = false;
        for (WebElement option : options) {
            if (option.getText().trim().equalsIgnoreCase(valueToSelect)) {
                wait.until(ExpectedConditions.elementToBeClickable(option)).click();
                optionSelected = true;
                log.info("Successfully selected the value: '{}'", valueToSelect);
                break;
            }
        }

        if (!optionSelected) {
            log.warn("Dropdown value '{}' not found.", valueToSelect);
        }

        try {
            wait.until(ExpectedConditions.attributeToBe(dropdownButtonNecessaryForJobTwo, "aria-expanded", "false"));
        } catch (TimeoutException e) {
            log.error("Timeout while waiting for dropdown to close with aria-expanded = 'false'.");
        }
    }

    //DVP
    public void selectDropdownNecessaryForJobDVP(String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

        log.info("Located the dropdown button.");
        wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonNecessaryForJobDVP)).click();

        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option/span")));

        boolean optionSelected = false;
        for (WebElement option : options) {
            if (option.getText().trim().equalsIgnoreCase(valueToSelect)) {
                wait.until(ExpectedConditions.elementToBeClickable(option)).click();
                optionSelected = true;
                log.info("Successfully selected the value: '{}'", valueToSelect);
                break;
            }
        }

        if (!optionSelected) {
            log.warn("Dropdown value '{}' not found.", valueToSelect);
        }

        try {
            wait.until(ExpectedConditions.attributeToBe(dropdownButtonNecessaryForJobDVP, "aria-expanded", "false"));
        } catch (TimeoutException e) {
            log.error("Timeout while waiting for dropdown to close with aria-expanded = 'false'.");
        }
    }
//DVP


    public void enterTool(String tool) {
        inputTool.clear();
        inputTool.sendKeys(tool);
    }

    public void enterToolParameterOne(String parameter) {
        inputToolParameterOne.clear();
        inputToolParameterOne.sendKeys(parameter);
    }

    public void enterToolParameterTwo(String parameter) {
        inputToolParameterTwo.clear();
        inputToolParameterTwo.sendKeys(parameter);
    }

    public void enterToolParameterThree(String parameter) {
        inputToolParameterThree.clear();
        inputToolParameterThree.sendKeys(parameter);
    }

    public void enterToolsCount(String amount) {
        inputToolsCount.clear();
        inputToolsCount.sendKeys(amount);
    }

    public void enterPriceOne(String amount) {
        inputPriceOne.clear();
        inputPriceOne.sendKeys(amount);
    }

    public void enterPriceTwo(String amount) {
        inputPriceTwo.clear();
        inputPriceTwo.sendKeys(amount);
    }

    public void enterOwnFundsOne(String amount) {
        inputOwnFundsOne.clear();
        inputOwnFundsOne.sendKeys(amount);
    }

    public void enterOwnFundsTwo(String amount) {
        inputOwnFundsTwo.clear();
        inputOwnFundsTwo.sendKeys(amount);
    }

    public void clickButtonAdd() {
        if (!buttonAdd.isSelected()) {
            buttonAdd.click();
        }
    }

    public void enterRepairName(String amount) {
        inputRepairName.clear();
        inputRepairName.sendKeys(amount);
    }

    public void enterRepairDescription(String text) {
        inputRepairDescription.clear();
        inputRepairDescription.sendKeys(text);
    }

    public void enterCountryPerc(String amount) {
        inputCountryPerc.clear();
        inputCountryPerc.sendKeys(amount);
    }

    public void enterInstitutionPerc(String amount) {
        inputInstitutionPerc.clear();
        inputInstitutionPerc.sendKeys(amount);
    }

    public void enterMunicipalityPerc(String amount) {
        inputMunicipalityPerc.clear();
        inputMunicipalityPerc.sendKeys(amount);
    }

    public void clickRadioButtonDeMinimis() {
        if (!radioButtonDeMinimis.isSelected()) {
            radioButtonDeMinimis.click();
        }
    }

    public void enterProgramName(String amount) {
        inputProgramName.clear();
        inputProgramName.sendKeys(amount);
    }

    public void enterProjectDateFrom() {
        LocalDate today = LocalDate.now();
        LocalDate pastDate = today.minusDays(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedPastDate = pastDate.format(formatter);

        if (inputProjectDateFrom.isDisplayed() && inputProjectDateFrom.isEnabled()) {
            inputProjectDateFrom.clear();
            inputProjectDateFrom.sendKeys(formattedPastDate);
        } else {
            throw new RuntimeException("End date input is not interactable.");
        }
    }

    public void enterProjectDateUntil() {
        LocalDate today = LocalDate.now();
        LocalDate pastDate = today.plusYears(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedPastDate = pastDate.format(formatter);

        if (inputProjectDateUntil.isDisplayed() && inputProjectDateUntil.isEnabled()) {
            inputProjectDateUntil.clear();
            inputProjectDateUntil.sendKeys(formattedPastDate);
        } else {
            throw new RuntimeException("End date input is not interactable.");
        }
    }

    public void enterSupportAmount(String amount) {
        inputSupportAmount.clear();
        inputSupportAmount.sendKeys(amount);
    }

//--------------------

    public class CheckboxHelper {

        public static void setConfirmationCheckbox(WebDriver driver, WebElement checkbox, boolean shouldBeChecked) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            try {
                // Palaukiame, kol checkbox taps paspaudžiamas
                wait.until(ExpectedConditions.elementToBeClickable(checkbox));

                // Jei reikia keisti būseną, spaudžiame checkbox
                if ((checkbox.isSelected() && !shouldBeChecked) || (!checkbox.isSelected() && shouldBeChecked)) {
                    try {
                        checkbox.click(); // Pirmiausia bandom normalų paspaudimą
                    } catch (ElementClickInterceptedException e) {
                        System.out.println("The element is blocked by another element. We are trying scrollIntoView...");
                        scrollToElement(driver, checkbox);
                        checkbox.click();
                    }
                }

            } catch (Exception e) {
                System.out.println("Failed to click the checkbox: " + e.getMessage());
                System.out.println("We are trying to click using JavaScript...");

                // Paskutinė išeitis – paspausti per JavaScript
                clickWithJavaScript(driver, checkbox);
            }
        }

        private static void scrollToElement(WebDriver driver, WebElement element) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
        }

        private static void clickWithJavaScript(WebDriver driver, WebElement element) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }

//--------------------

    public class RadioButtonHelper {

        // Konstantos, kurios saugo lokatorius pagal XPath
        public static final By RADIO_NO_1 = By.xpath("(//input[@type='radio' and @value='false'])[8]");
        public static final By RADIO_NO_2 = By.xpath("(//input[@type='radio' and @value='false'])[9]");
        public static final By RADIO_NO_3 = By.xpath("(//input[@type='radio' and @value='false'])[10]");
        public static final By RADIO_NO_4 = By.xpath("(//input[@type='radio' and @value='false'])[11]");
        public static final By RADIO_NO_5 = By.xpath("(//input[@type='radio' and @value='false'])[12]");
        public static final By RADIO_NO_6 = By.xpath("(//input[@type='radio' and @value='false'])[13]");

        public static final By RADIO_YES_7 = By.xpath("(//input[@type='radio' and @value='true'])[14]");
        public static final By RADIO_YES_8 = By.xpath("(//input[@type='radio' and @value='true'])[15]");
        public static final By RADIO_YES_9 = By.xpath("(//input[@type='radio' and @value='true'])[16]");
        public static final By RADIO_YES_10 = By.xpath("(//input[@type='radio' and @value='true'])[17]");
        public static final By RADIO_YES_11 = By.xpath("(//input[@type='radio' and @value='true'])[18]");
        public static final By RADIO_YES_12 = By.xpath("(//input[@type='radio' and @value='true'])[19]");



//        // Metodas pasirinks reikiamą radio mygtuką pagal tekstą
//        public static void selectRadioButton(WebDriver driver, By locator, boolean shouldBeSelected) {
//            WebElement radioButton = driver.findElement(locator); // Radome elementą pagal lokatorių
//
//            // Patikriname, ar radio mygtukas turi būti pažymėtas
//            if (radioButton.isSelected() != shouldBeSelected) {
//                radioButton.click(); // Jei būsenos nesutampa, paspaudžiame
//            }
//        }

        public static void selectRadioButton(WebDriver driver, By locator, boolean shouldBeSelected) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            try {
                // Laukiame, kol elementas taps paspaudžiamas
                WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(locator));

                // Patikriname, ar radio mygtukas turi būti pažymėtas
                if (radioButton.isSelected() != shouldBeSelected) {
                    radioButton.click(); // Jei būsenos nesutampa, paspaudžiame
                }
            } catch (Exception e) {
                log.error("Error selecting radio button: {}", e.getMessage());
                // Jei elementas uždengtas arba negalime jo paspausti, bandome su JavaScript
                JavascriptExecutor js = (JavascriptExecutor) driver;
                WebElement radioButton = driver.findElement(locator);
                js.executeScript("arguments[0].click();", radioButton); // Paspaudžiame per JavaScript
            }
        }
    }


    public void uploadFileStepFive() {
        String filePath = "C:\\Users\\ingrida.zadorozniene\\TXT.txt";  // Failo kelias

        // Rasti visus failų įkėlimo elementus puslapyje
        List<WebElement> fileInputs = driver.findElements(By.xpath("//input[@type='file']"));

        // Patikrinti, ar radome failų įkėlimo elementus
        if (fileInputs.isEmpty()) {
            log.warn("No file input elements found on the page.");
        } else {
            // Įkelti failą į kiekvieną elementą
            for (WebElement fileInput : fileInputs) {
                try {
                    fileInput.sendKeys(filePath);  // Įkelti failą į elementą
                    log.info("File uploaded successfully to an input element.");
                } catch (Exception e) {
                    log.error("Failed to upload file to the input element: " + e.getMessage());
                }
            }
        }
    }

    public void enterDocumentNameOne(String count) {
        inputDocumentNameOne.clear();
        inputDocumentNameOne.sendKeys(count);
    }

    public void enterDocumentNameTwo(String count) {
        inputDocumentNameTwo.clear();
        inputDocumentNameTwo.sendKeys(count);
    }

    public void enterDocumentNameThree(String count) {
        inputDocumentNameThree.clear();
        inputDocumentNameThree.sendKeys(count);
    }

    public void enterDocumentNameFour(String count) {
        inputDocumentNameFour.clear();
        inputDocumentNameFour.sendKeys(count);
    }

    public void enterDocumentNameFive(String count) {
        inputDocumentNameFive.clear();
        inputDocumentNameFive.sendKeys(count);
    }

    public void enterDocumentNameSix(String count) {
        inputDocumentNameSix.clear();
        inputDocumentNameSix.sendKeys(count);
    }

    public void enterDocumentNameSeven(String count) {
        inputDocumentNameSeven.clear();
        inputDocumentNameSeven.sendKeys(count);
    }

    public void enterDocumentNameEight(String count) {
        inputDocumentNameEight.clear();
        inputDocumentNameEight.sendKeys(count);
    }

    public void enterDocumentNameNine(String count) {
        inputDocumentNameNine.clear();
        inputDocumentNameNine.sendKeys(count);
    }

    public void enterDocumentNameTen(String count) {
        inputDocumentNameTen.clear();
        inputDocumentNameTen.sendKeys(count);
    }


    //DVP
    public void enterPersonCountOne(String count) {
        inputPersonCountOne.clear();
        inputPersonCountOne.sendKeys(count);
    }

    public void enterPersonCountTwo(String count) {
        inputPersonCountTwo.clear();
        inputPersonCountTwo.sendKeys(count);
    }

//---------------------------





}