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

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

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



    @FindBy(xpath = "//button[contains(@class, 'mat-mdc-menu-item') and .//span[contains(text(), 'Kurti projektus')]]")
    WebElement buttonCreateProjects;




    @FindBy(xpath = "//button[contains(@class, 'i-forms-stepper-button-next') and normalize-space(text())='Toliau']")
    WebElement buttonNext;

    @FindBy(xpath = "//button[contains(@class, 'i-forms-stepper-button-previous') and normalize-space(text())='Atgal']")
    WebElement buttonPrevious;

    @FindBy(xpath = "//input[@id='phone']")
    WebElement phoneInput;

    //application form step 1
    //------------------------------

//    @FindBy(xpath = "//input[@name='mat-radio-group-0' and @value='FA']")
//    @FindBy(xpath = "//input[@name=\"mat-radio-group-0\" and @value=\"PERSON\"]")
@FindBy(xpath = "//input[@type='radio' and @value='PERSON']")
    WebElement radioButtonFA;

    @FindBy(xpath = "//mat-label[contains(text(), 'Projekto teikėjo planuojama vykdyti veikla, kuriai prašoma subsidija, kodas pagal EVRK')]")
    WebElement dropdownButtonEVRK_VUI;

    @FindBy(xpath = "//mat-form-field[contains(.//label, 'El. pašto adresas')]//input")
    WebElement inputEMail;

    @FindBy(xpath = "//mat-select[contains(@class, \"mat-mdc-select\")]")
    WebElement dropdownButtonApplicant;

    //DVP
    @FindBy(xpath = "//mat-label[contains(text(), 'Paraiškos teikėjo vykdoma veikla, kodas pagal EVRK')]")
    WebElement dropdownButtonEVRK_DVP;
    //DVP

    //address
//----------------
    @FindBy(xpath = "(//mat-icon[contains(text(), 'edit')])[3]")
    WebElement addressComponent;

    @FindBy(xpath = "//mat-label[contains(text(), 'Valstybė')]/following::mat-select[1]")
    WebElement dropdownAddressCountry;

    @FindBy(xpath = "//mat-label[contains(text(), 'Miestas')]/ancestor::mat-form-field")
    WebElement dropdownAddressCity;
    @FindBy(xpath = "//input[@placeholder='Ieškoti...']")
    WebElement dropdownSearch;

    @FindBy(xpath = "//mat-label[contains(text(), 'Gatvė')]/following::mat-select[1]")
    WebElement dropdownAddressStreet;

    @FindBy(xpath = "//mat-label[contains(text(), 'Namo Nr.')]/following::mat-select[1]")
    WebElement dropdownAddressHouse;

    @FindBy(xpath = "//mat-label[contains(text(), 'Buto / patalpos Nr.')]/following::mat-select[1]")
    WebElement dropdownAddressApartment;

    @FindBy(xpath = "//common-button[@btntype='submit']//button")
    WebElement buttonConfirm;
//------------------

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
    WebElement dropdownButtonJobNameAdaptable_DVP;

    @FindBy(xpath = "//mat-label[contains(text(), 'Išlaidų tipas')]")
    WebElement dropdownButtonExpensesTyp_DVP;

    @FindBy(xpath = "(//mat-label[contains(text(), 'Išlaidų tipas')])[2]")
    WebElement dropdownButtonExpensesTyp_DVP_2;
    //DVP

    @FindBy(xpath = "//div[contains(@class, 'mdc-notched-outline')]//following::textarea[1]")
    WebElement inputJobFunction;

    @FindBy(xpath = "(//input[@type='radio' and @value='true'])[1]")
    WebElement radioButtonWithDisabilities_VUI;

    //DVP
    @FindBy(xpath = "(//input[@type='radio' and @value='false'])[1]")
    WebElement radioButtonForAlreadyWorking_DVP_step1;
    @FindBy(xpath = "(//input[@type='radio' and @value='true'])[2]")
    WebElement radioButtonForNewWorking_DVP_step1;

    @FindBy(xpath = "(//input[@type='radio' and @value='false'])[3]")
    WebElement radioButtonForAlreadyWorking_DVP_step2;

    @FindBy(xpath = "(//input[@type='radio' and @value='true'])[4]")
    WebElement radioButtonForNotAlreadyWorking_DVP_step2;
    //DVP

    @FindBy(xpath = "//mat-label[contains(text(), 'Papildomai remiamo asmens tipas')]")
    WebElement dropdownButtonDisabilitiesType_VUI;

    //DVP
    @FindBy(xpath = "//mat-label[contains(text(), 'Asmens su negalia dalyvumo lygis')]")
    WebElement dropdownButtonDisabilitiesLevel_DVP;
    //DVP

    @FindBy(xpath = "//div[contains(@class, 'mdc-notched-outline')]//following::textarea[2]")
    WebElement inputDisabilities;

    @FindBy(xpath = "//mat-label[contains(text(), 'Darbo laiko norma ir darbo laiko režimas')]")
    WebElement dropdownButtonTimeMode;

    @FindBy(xpath = "//mat-form-field[contains(.//label, 'Darbo laiko norma ir darbo laiko režimas')]//input")
    WebElement inputTimeModeOthers;

    @FindBy(xpath = "//div[contains(@class, 'mdc-notched-outline')]//following::textarea[3]")
    WebElement inputQualification;

    @FindBy(xpath = "//div[contains(@class, 'mdc-notched-outline')]//following::textarea[4]")
    WebElement inputSalaryDescription;

    @FindBy(xpath = "//div[contains(@class, 'mdc-notched-outline')]//following::textarea[7]")
    WebElement inputSalaryDescription_DVP;

    @FindBy(xpath = "(//input[contains(@class, 'mat-datepicker-input')])[2]")
    WebElement inputJopDate;

    //DVP
    @FindBy(xpath = "(//input[contains(@class, 'mat-datepicker-input')])[1]")
    WebElement inputJopDate_DVP;

    @FindBy(xpath = "(//input[@type='radio' and @value='false'])[4]")
    WebElement radioButtonTemporaryJob_DVP;

    @FindBy(xpath = "(//input[@type='radio' and @value='false'])[5]")
    WebElement radioButtonSeasonJob_DVP;
    //DVP

    @FindBy(xpath = "//mat-form-field[contains(.//label, 'Planuojamas mokėti bruto darbo užmokestis, Eur')]//input")
    WebElement inputSalary;

    @FindBy(xpath = "(//input[@type='radio' and @value='false'])[2]")
    WebElement radioButtonTemporaryJob;

    @FindBy(xpath = "(//input[@type='radio' and @value='false'])[3]")
    WebElement radioButtonSeasonJob;

    @FindBy(xpath = "//div[contains(@class, 'mdc-notched-outline')]//following::textarea[5]")
    WebElement inputJobDescription;

    @FindBy(xpath = "//div[contains(@class, 'mdc-notched-outline')]//following::textarea[6]")
    WebElement inputProsesDescriptionVUI_equipmentDescriptionDVP;

    @FindBy(xpath = "(//input[@type='radio' and @value='true'])[4]")
    WebElement radioButtonEnergy;

    @FindBy(xpath = "//div[contains(@class, 'mdc-notched-outline')]//following::textarea[7]")
    WebElement inputEnergyInformationVUI;

    @FindBy(xpath = "//div[contains(@class, 'mdc-notched-outline')]//following::textarea[4]")
    WebElement inputWorkInformationDVP;

    @FindBy(xpath = "//div[contains(@class, 'mdc-notched-outline')]//following::textarea[8]")
    WebElement inputRepairWorkDescription_DVP;

    @FindBy(xpath = "(//input[@type='radio' and @value='true'])[5]")
    WebElement radioButtonRepair;

    @FindBy(xpath = "//div[contains(@class, 'mdc-notched-outline')]//following::textarea[8]")
    WebElement inputRepairInformation;

    @FindBy(xpath = "(//input[@type='radio' and @value='false'])[6]")
    WebElement radioButtonPVM;

    @FindBy(xpath = "(//mat-label[contains(text(), 'Darbo vietai įsteigti reikalinga')])[1]")
    WebElement dropdownButtonNecessaryForJobOne;

    @FindBy(xpath = "(//mat-label[contains(text(), 'Darbo vietai įsteigti reikalinga')])[2]")
    WebElement dropdownButtonNecessaryForJobTwo;

    //DVP
    @FindBy(xpath = "(//mat-form-field[.//mat-label[contains(text(), 'Reikalinga')]]//mat-select)[1]")
    WebElement dropdownButtonNecessaryForJobOne_DVP;

    @FindBy(xpath = "(//mat-form-field[.//mat-label[contains(text(), 'Reikalinga')]]//mat-select)[2]")
    WebElement dropdownButtonNecessaryForJobOne_DVP_2;

    @FindBy(xpath = "//mat-form-field[contains(.//label, 'Darbo priemonės pavadinimas')]//input")
    WebElement inputTool;

    @FindBy(xpath = "//mat-form-field[contains(.//label, 'Išlaidų elemento pavadinimas')]//input")
    WebElement inputTool_DVP;

    @FindBy(xpath = "(//mat-form-field[contains(.//label, 'Išlaidų elemento pavadinimas')]//input)[2]")
    WebElement inputTool_DVP_2;
    //DVP

    @FindBy(xpath = "(//mat-form-field[.//label[contains(normalize-space(), 'Darbo priemonės techninis parametras')]]//input)[1]")
    WebElement inputToolParameterOne;
    @FindBy(xpath = "(//mat-form-field[.//label[contains(normalize-space(), 'Darbo priemonės techninis parametras')]]//input)[2]")
    WebElement inputToolParameterTwo;
    @FindBy(xpath = "(//mat-form-field[.//label[contains(normalize-space(), 'Darbo priemonės techninis parametras')]]//input)[3]")
    WebElement inputToolParameterThree;

    @FindBy(xpath = "(//mat-form-field[.//label[contains(normalize-space(), 'Išlaidų elemento techninis parametras')]]//input)[1]")
    WebElement inputToolParameterOne_DVP;
    @FindBy(xpath = "(//mat-form-field[.//label[contains(normalize-space(), 'Išlaidų elemento techninis parametras')]]//input)[2]")
    WebElement inputToolParameterTwo_DVP;
    @FindBy(xpath = "(//mat-form-field[.//label[contains(normalize-space(), 'Išlaidų elemento techninis parametras')]]//input)[3]")
    WebElement inputToolParameterThree_DVP;


    @FindBy(xpath = "//mat-form-field[contains(.//label, 'Darbo priemonės kiekis, vnt.')]//input")
    WebElement inputToolsCount;

    @FindBy(xpath = "//mat-form-field[contains(.//label, 'Išlaidų elemento kiekis, vnt.')]//input")
    WebElement inputToolsCount_DVP;

    @FindBy(xpath = "(//mat-form-field[contains(.//label, 'Išlaidų elemento kiekis, vnt.')]//input)[2]")
    WebElement inputToolsCount_DVP_2;

    @FindBy(xpath = "(//mat-form-field[contains(.//label, 'Kaina, Eur')]//input)[1]")
    WebElement inputPriceOne;

    @FindBy(xpath = "(//mat-form-field[contains(.//label, 'Kaina, Eur')]//input)[2]")
    WebElement inputPriceTwo;

    @FindBy(xpath = "(//mat-form-field[contains(.//label, 'Nuosavos lėšos, Eur')]//input)[1]")
    WebElement inputOwnFundsOne;

    @FindBy(xpath = "(//mat-form-field[contains(.//label, 'Nuosavos lėšos, Eur')]//input)[2]")
    WebElement inputOwnFundsTwo;

    @FindBy(xpath = "(//button[contains(@class, 'i-forms-repeater-button-add')])[4]")
    WebElement buttonAdd;

    @FindBy(xpath = "(//button[contains(@class, 'i-forms-repeater-button-add')])[5]")
    WebElement buttonAddRemoved;

    @FindBy(xpath = "(//button[contains(@class, 'i-forms-repeater-button-remove')])[3]")
    WebElement buttonRemove;

    @FindBy(css = "common-button:nth-of-type(2) > .btn.btn--primary")
    WebElement buttonRemoveConfirmation;

    @FindBy(xpath = "(//button[contains(@class, 'i-forms-repeater-button-add')])[5]")
    WebElement buttonAddJobPlace;

    @FindBy(xpath = "//mat-form-field[contains(.//label, 'Remonto darbų pavadinimas')]//input")
    WebElement inputRepairName;

    @FindBy(xpath = "//div[contains(@class, 'mdc-notched-outline')]//following::textarea[9]")
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

    @FindBy(xpath = "(//input[contains(@class, 'mat-datepicker-input')])[2]")
    WebElement inputProjectDateFrom_DVP;

    @FindBy(xpath = "(//input[contains(@class, 'mat-datepicker-input')])[4]")
    WebElement inputProjectDateUntil;

    @FindBy(xpath = "(//input[contains(@class, 'mat-datepicker-input')])[3]")
    WebElement inputProjectDateUntil_DVP;

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

    @FindBy(xpath = "(//div[contains(@class, 'mdc-checkbox')]//input[@type='checkbox'])[4]")
    public WebElement checkboxConfirmationFour;

    @FindBy(xpath = "(//div[contains(@class, 'mdc-checkbox')]//input[@type='checkbox'])[5]")
    public WebElement checkboxConfirmationFive;

    @FindBy(xpath = "(//div[contains(@class, 'mdc-checkbox')]//input[@type='checkbox'])[6]")
    public WebElement checkboxConfirmationSix;

    //application form step 4 (Checkboxes)
//-----------
    @FindBy(xpath = "(//div[contains(@class, 'mdc-checkbox')]//input[@type='checkbox'])[4]")
    public WebElement checkboxConfirmationStepFour;

    @FindBy(xpath = "(//div[contains(@class, 'mdc-checkbox')]//input[@type='checkbox'])[6]")
    public WebElement checkboxConfirmationStepFourDVP;

    //application form step 5 (Radiobuttons)
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

    @FindBy(xpath = "(//div[contains(@class, 'mdc-checkbox')]//input[@type='checkbox'])[7]")
    public WebElement checkboxConfirmationDocument_DVP;

    @FindBy(xpath = "(//div[contains(@class, 'mdc-checkbox')]//input[@type='checkbox'])[6]")
    public WebElement checkboxConfirmationApplication;

    @FindBy(xpath = "(//div[contains(@class, 'mdc-checkbox')]//input[@type='checkbox'])[8]")
    public WebElement checkboxConfirmationApplication_DVP;

    @FindBy(xpath = "//common-button/button[contains(text(), 'Peržiūrėti')]")
    WebElement buttonReview;

//    @FindBy(xpath = "//common-button/button[contains(text(), 'Pateikti')]")
    @FindBy(css = "common-button:nth-of-type(3) > .btn.btn--primary")
    WebElement buttonSubmit;

    @FindBy(xpath = "//common-button//button[@type='button' and contains(text(), 'Pateikti vertinimui')]")
    WebElement buttonSubmitConfirmation;

    @FindBy(xpath = "//common-button/button[contains(text(), 'Saugoti ruošinį')]")
    WebElement buttonSaveDraft;

    //DVP
    //--------------

    @FindBy(xpath = "(//mat-form-field[contains(.//label, 'Asmenų skaičius')]//input)[1]")
    WebElement inputPersonCountOne_DVP;

    @FindBy(xpath = "(//mat-form-field[contains(.//label, 'Asmenų skaičius')]//input)[2]")
    WebElement inputPersonCountTwo_DVP_2JobPlace;

    @FindBy(xpath = "(//mat-form-field[contains(.//label, 'Asmenų skaičius')]//input)[1]")
    WebElement inputPersonCountTwo_DVP;

    //evaluations
    //---------------

    @FindBy(xpath = "//mat-label[contains(text(), 'Vertintojas')]/following::mat-select[1]")
    WebElement dropdownButtonEvaluator;

    @FindBy(xpath = "//div[contains(@class, 'mat-mdc-form-field-infix')]//input[@type='text' and contains(@class, 'mat-datepicker-input')]")
    WebElement firstEvaluationEndDate;
    @FindBy(xpath = "(//div[contains(@class, 'mat-mdc-form-field-infix')]//input[@type='text' and contains(@class, 'mat-datepicker-input')])[2]")
    WebElement secondEvaluationEndDate;
    @FindBy(xpath = "(//div[contains(@class, 'mat-mdc-form-field-infix')]//input[@type='text' and contains(@class, 'mat-datepicker-input')])[3]")
    WebElement thirdEvaluationEndDate;

    @FindBy(xpath = "//common-button[@btnclass='btn btn--primary']//button[text()='Priskirti vertintojus']")
    WebElement buttonAddEvaluatorsConfirmation;

//    @FindBy(xpath = "//common-button[@btnclass='btn btn--primary']//button[text()='Kurti projektus']")
    @FindBy(xpath = "//button[contains(normalize-space(text()), 'Kurti projektus')]")
    WebElement buttonProjectsConfirmation;

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

    //buttons
    //-------------------
    public void clickButtonActionApplication() {
        buttonActionApplication.click();
    }

    public void clickButtonNewApplication() {
        buttonNewApplication.click();
    }

    public void clickButtonAddJobPlace() {
        if (!buttonAddJobPlace.isSelected()) {
            buttonAddJobPlace.click();
        }
    }


    public void clickButtonAddEvaluators() {
        buttonAddEvaluators.click();
    }

    public void clickButtonCreateProjects() {
        buttonCreateProjects.click();
    }

    public void clickButtonAddEvaluatorsConfirmation() {
        buttonAddEvaluatorsConfirmation.click();
    }

    public void clickButtonProjectsConfirmation() {
        buttonProjectsConfirmation.click();
    }

    public void clickButtonConfirm() {
        buttonConfirm.click();
    }

    public void clickButtonPrevious() {
        buttonPrevious.click();
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



    public void selectValueByListEVRK_VUI(String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            log.info("Clicking on the dropdown button.");
            wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonEVRK_VUI)).click();

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

//            // Patikriname, ar meniu užsidarė
//            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//mat-option//span")));
//            log.info("Dropdown should be closed now.");

        } catch (Exception e) {
            log.error("Error occurred while selecting value: {}", e.getMessage());
            throw new RuntimeException("Failed to select value from dropdown.", e);
        }
    }



    public void selectValueApplicant_FA(String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonApplicant)).click();
            log.info("Dropdown opened.");

            WebElement searchInput = wait.until(ExpectedConditions.visibilityOf(dropdownSearch));
            searchInput.clear();
            searchInput.sendKeys(valueToSelect);
            log.debug("Entered search: '{}'", valueToSelect);

            // Trumpas laukimas, kol dropdown persikraus
            Thread.sleep(500);

            // Tikslus xpath pagal realų DOM
            String xpath = String.format("//mat-option//span[contains(text(), '%s')]", valueToSelect);
            WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

            // Paspaudžiam pasirinkimą
            try {
                option.click();
            } catch (Exception e) {
                log.warn("Normalus click nepavyko, bandau JS click: {}", e.getMessage());
                js.executeScript("arguments[0].click();", option);
            }

            log.info("Pasirinkta reikšmė: '{}'", option.getText().trim());

            // Palaukiam, kol dropdown užsidarys
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//mat-option")));

        } catch (Exception e) {
            log.error("Nepavyko pasirinkti reikšmės '{}': {}", valueToSelect, e.getMessage(), e);
            throw new RuntimeException("Nepavyko pasirinkti reikšmės iš dropdown'o: " + valueToSelect, e);
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
                    body.click();
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


    //address
    //----------------------------------
    public void clickAddressComponent() {
        try {
            // Laukiame, kol elementas bus matomas ir paspaudžiamas
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(addressComponent));

            // Paspaudžiame ant laukelio
            inputField.click();
            System.out.println("Elementas paspaustas!");

        } catch (Exception e) {
            System.out.println("Klaida: Nepavyko rasti elemento.");
        }
    }

    public void selectDropdownAddressCountry(String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Ilgesnis laukimo laikas

        log.info("Located the dropdown button.");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(dropdownAddressCountry));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", dropdownAddressCountry); // Užtikrinsime, kad elementas būtų matomas
            dropdownAddressCountry.click();
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
            wait.until(ExpectedConditions.attributeToBe(dropdownAddressCountry, "aria-expanded", "false"));
        } catch (TimeoutException e) {
            log.error("Timeout while waiting for dropdown to close with aria-expanded = 'false'.");
        }
    }

    public void selectDropdownAddressCity(String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        log.info("Located the dropdown search field.");

        try {
            wait.until(ExpectedConditions.elementToBeClickable(dropdownAddressCity)).click();
            log.info("Dropdown city is opened.");
        } catch (Exception e) {
            log.error("Dropdown city click failed: {}", e.getMessage());
            return;
        }

        try {
            wait.until(ExpectedConditions.elementToBeClickable(dropdownSearch)).click();
            dropdownSearch.clear();
            dropdownSearch.sendKeys(valueToSelect);

            // Laukiame, kol atsiras bent vienas variantas
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-option")));

            log.info("Searched for value: '{}'", valueToSelect);
        } catch (Exception e) {
            log.error("Failed to enter search value: {}", e.getMessage());
            return;
        }

        try {
            String optionXPath = "//mat-option//span[contains(@class, 'area-center') and text()='" + valueToSelect + "']";
            WebElement correctOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(optionXPath)));
            correctOption.click();

            log.info("Successfully selected: '{}'", valueToSelect);
        } catch (Exception e) {
            log.error("Failed to select correct dropdown option: {}", e.getMessage());
        }
    }

    public void selectDropdownAddressStreet(String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Ilgesnis laukimo laikas

        log.info("Located the dropdown button.");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(dropdownAddressStreet));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", dropdownAddressStreet); // Užtikrinsime, kad elementas būtų matomas
            dropdownAddressStreet.click();
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
            wait.until(ExpectedConditions.attributeToBe(dropdownAddressStreet, "aria-expanded", "false"));
        } catch (TimeoutException e) {
            log.error("Timeout while waiting for dropdown to close with aria-expanded = 'false'.");
        }
    }

    public void selectDropdownAddressHouse(String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        log.info("Located the dropdown button.");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(dropdownAddressHouse));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", dropdownAddressHouse);
            dropdownAddressHouse.click();
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
            wait.until(ExpectedConditions.attributeToBe(dropdownAddressHouse, "aria-expanded", "false"));
        } catch (TimeoutException e) {
            log.error("Timeout while waiting for dropdown to close with aria-expanded = 'false'.");
        }
    }

    public void selectDropdownAddressApartment(String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Ilgesnis laukimo laikas

        log.info("Located the dropdown button.");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(dropdownAddressApartment));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", dropdownAddressApartment); // Užtikrinsime, kad elementas būtų matomas
            dropdownAddressApartment.click();
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
            wait.until(ExpectedConditions.attributeToBe(dropdownAddressApartment, "aria-expanded", "false"));
        } catch (TimeoutException e) {
            log.error("Timeout while waiting for dropdown to close with aria-expanded = 'false'.");
        }
    }
//-------------------------------------

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

    //DVP
    public void clickRadioButtonForAlreadyWorking_DVP_step1() {
        if (!radioButtonForAlreadyWorking_DVP_step1.isSelected()) {
            radioButtonForAlreadyWorking_DVP_step1.click();
        }
    }

    public void clickRadioButtonForNewWorking_DVP_step1() {
        if (!radioButtonForNewWorking_DVP_step1.isSelected()) {
            radioButtonForNewWorking_DVP_step1.click();
        }
    }
    //DVP

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

//        try {
//            wait.until(ExpectedConditions.attributeToBe(dropdownButtonJobName, "aria-expanded", "false"));
//        } catch (TimeoutException e) {
//            log.error("Timeout while waiting for dropdown to close with aria-expanded = 'false'.");
//        }
    }

    //DVP
public void selectDropdownJobNameAdaptable_DVP(String valueToSelect) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Ilgesnis laukimo laikas

    log.info("Located the dropdown button.");
    try {
        wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonJobNameAdaptable_DVP));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", dropdownButtonJobNameAdaptable_DVP); // Užtikrinsime, kad elementas būtų matomas
        dropdownButtonJobNameAdaptable_DVP.click();
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

//    try {
//        wait.until(ExpectedConditions.attributeToBe(dropdownButtonJobNameAdaptable_DVP, "aria-expanded", "false"));
//    } catch (TimeoutException e) {
//        log.error("Timeout while waiting for dropdown to close with aria-expanded = 'false'.");
//    }
}

    public void selectDropdownExpensesTyp_DVP(String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Ilgesnis laukimo laikas

        log.info("Located the dropdown button.");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonExpensesTyp_DVP));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", dropdownButtonExpensesTyp_DVP); // Užtikrinsime, kad elementas būtų matomas
            dropdownButtonExpensesTyp_DVP.click();
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
            wait.until(ExpectedConditions.attributeToBe(dropdownButtonExpensesTyp_DVP, "aria-expanded", "false"));
        } catch (TimeoutException e) {
            log.error("Timeout while waiting for dropdown to close with aria-expanded = 'false'.");
        }
    }

    public void selectDropdownExpensesTyp_DVP_2(String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Ilgesnis laukimo laikas

        log.info("Located the dropdown button.");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonExpensesTyp_DVP_2));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", dropdownButtonExpensesTyp_DVP_2); // Užtikrinsime, kad elementas būtų matomas
            dropdownButtonExpensesTyp_DVP_2.click();
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
            wait.until(ExpectedConditions.attributeToBe(dropdownButtonExpensesTyp_DVP_2, "aria-expanded", "false"));
        } catch (TimeoutException e) {
            log.error("Timeout while waiting for dropdown to close with aria-expanded = 'false'.");
        }
    }
    //DVP

        public void enterInputJobFunction(String text) {
        inputJobFunction.clear();
        inputJobFunction.sendKeys(text);
    }

    public void clickRadioButtonWithDisabilitiesVUI() {
        if (!radioButtonWithDisabilities_VUI.isSelected()) {
            radioButtonWithDisabilities_VUI.click();
        }
    }

    //DVP
    public void clickRadioButtonForAlreadyWorking_DVP_step2() {
        if (!radioButtonForAlreadyWorking_DVP_step2.isSelected()) {
            radioButtonForAlreadyWorking_DVP_step2.click();
        }
    }
    //DVP

    public void selectValueByListDisabilitiesType_VUI(String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            log.info("Clicking on the dropdown button.");
            wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonDisabilitiesType_VUI)).click();

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
//            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//mat-option//span")));
//            log.info("Dropdown should be closed now.");

        } catch (Exception e) {
            log.error("Error occurred while selecting value: {}", e.getMessage());
            throw new RuntimeException("Failed to select value from dropdown.", e);
        }
    }

    //DVP
    public void selectValueByListDisabilitiesLevel_DVP(String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            log.info("Clicking on the dropdown button.");
            wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonDisabilitiesLevel_DVP)).click();

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

            WebElement body = driver.findElement(By.tagName("body"));
            actions.moveToElement(body).click().perform();

//            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//mat-option//span")));
//            log.info("Dropdown should be closed now.");

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

//        try {
//            wait.until(ExpectedConditions.attributeToBe(dropdownButtonTimeMode, "aria-expanded", "false"));
//        } catch (TimeoutException e) {
//            log.error("Timeout while waiting for dropdown to close with aria-expanded = 'false'.");
//        }
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
        LocalDate pastDate = today.plusMonths(3).plusDays(15);

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
    public void enterJobDate_DVP() {
        LocalDate today = LocalDate.now();
        LocalDate pastDate = today.plusMonths(3).plusDays(15);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedPastDate = pastDate.format(formatter);

        if (inputJopDate_DVP.isDisplayed() && inputJopDate_DVP.isEnabled()) {
            inputJopDate_DVP.clear();
            inputJopDate_DVP.sendKeys(formattedPastDate);
        } else {
            throw new RuntimeException("End date input is not interactable.");
        }
    }
//DVP

    public void enterSalary(String amount) {
        inputSalary.clear();
        inputSalary.sendKeys(amount);
    }

    public void enterSalaryDescription(String text) {
        inputSalaryDescription.clear();
        inputSalaryDescription.sendKeys(text);
    }

    public void enterSalaryDescription_DVP(String text) {
        inputSalaryDescription_DVP.clear();
        inputSalaryDescription_DVP.sendKeys(text);
    }

    public void clickRadioButtonTemporaryJob() {
        if (!radioButtonTemporaryJob.isSelected()) {
            radioButtonTemporaryJob.click();
        }
    }

    public void clickRadioButtonTemporaryJob_DVP() {
        if (!radioButtonTemporaryJob_DVP.isSelected()) {
            radioButtonTemporaryJob_DVP.click();
        }
    }

    public void clickRadioButtonSeasonJob() {
        if (!radioButtonSeasonJob.isSelected()) {
            radioButtonSeasonJob.click();
        }
    }

    public void clickRadioButtonSeasonJob_DVP() {
        if (!radioButtonSeasonJob_DVP.isSelected()) {
            radioButtonSeasonJob_DVP.click();
        }
    }

    public void enterJobDescription(String text) {
        inputJobDescription.clear();
        inputJobDescription.sendKeys(text);
    }

    public void enterProsesDescriptionVUI_equipmentDescriptionDVP(String text) {
        inputProsesDescriptionVUI_equipmentDescriptionDVP.clear();
        inputProsesDescriptionVUI_equipmentDescriptionDVP.sendKeys(text);
    }

    public void clickRadioButtonEnergy() {
        if (!radioButtonEnergy.isSelected()) {
            radioButtonEnergy.click();
        }
    }

    public void enterEnergyInformationVUI(String text) {
        inputEnergyInformationVUI.clear();
        inputEnergyInformationVUI.sendKeys(text);
    }

    public void enterWorkInformationDVP(String text) {
        inputWorkInformationDVP.clear();
        inputWorkInformationDVP.sendKeys(text);
    }

    public void enterWorkDescriptionDVP(String text) {
        inputRepairWorkDescription_DVP.clear();
        inputRepairWorkDescription_DVP.sendKeys(text);
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

//        try {
//            wait.until(ExpectedConditions.attributeToBe(dropdownButtonNecessaryForJobOne, "aria-expanded", "false"));
//        } catch (TimeoutException e) {
//            log.error("Timeout while waiting for dropdown to close with aria-expanded = 'false'.");
//        }
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

//        try {
//            wait.until(ExpectedConditions.attributeToBe(dropdownButtonNecessaryForJobTwo, "aria-expanded", "false"));
//        } catch (TimeoutException e) {
//            log.error("Timeout while waiting for dropdown to close with aria-expanded = 'false'.");
//        }
    }

//    //DVP
public void selectDropdownNecessaryForJobOne_DVP(String valueToSelect) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    try {
        wait.until(ExpectedConditions.visibilityOf(dropdownButtonNecessaryForJobOne_DVP));
        wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonNecessaryForJobOne_DVP)).click();
        log.debug("Located and clicked the dropdown button 'Reikalinga'.");
    } catch (TimeoutException e) {
        log.error("Dropdown 'Reikalinga' is not visible or clickable.", e);
        return;
    }

    // Priverstinai parodyti dropdown, jei jis už ekrano ribų
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].scrollIntoView(true);", dropdownButtonNecessaryForJobOne_DVP);

    // Laukiame, kol atsiras pasirinkimai
    List<WebElement> options;
    try {
        options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option/span")));
        wait.until(ExpectedConditions.elementToBeClickable(options.get(0)));
    } catch (TimeoutException e) {
        log.error("Dropdown options did not appear or are not clickable in time.", e);
        return;
    }

    // Bandome pasirinkti norimą reikšmę
    boolean optionSelected = false;
    for (WebElement option : options) {
        if (option.getText().trim().equalsIgnoreCase(valueToSelect)) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(option)).click();
                optionSelected = true;
                log.info("Successfully selected the value: '{}'", valueToSelect);
                break;
            } catch (Exception e) {
                log.error("Failed to click on the dropdown option '{}'", valueToSelect, e);
            }
        }
    }

    if (!optionSelected) {
        log.warn("Dropdown value '{}' not found.", valueToSelect);
        return;
    }

    // Palaukiame, kol dropdown užsidarys
//    try {
//        wait.until(ExpectedConditions.attributeToBe(dropdownButtonNecessaryForJobOne_DVP, "aria-expanded", "false"));
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//mat-option")));
//    } catch (TimeoutException e) {
//        log.error("Timeout while waiting for dropdown to close with aria-expanded = 'false'.", e);
//    }

    // Jei dropdown vis tiek nepasirinktas, bandome patikrinti faktinę pasirinktą reikšmę
//    String selectedValue = dropdownButtonNecessaryForJobOne_DVP.getText().trim();
//    if (!selectedValue.equalsIgnoreCase(valueToSelect)) {
//        log.error("Dropdown value mismatch! Expected '{}', but found '{}'", valueToSelect, selectedValue);
//    }
}

    public void selectDropdownNecessaryForJobOne_DVP_2(String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Užtikriname, kad dropdown yra matomas ir paspaudžiamas
        try {
            wait.until(ExpectedConditions.visibilityOf(dropdownButtonNecessaryForJobOne_DVP_2));
            wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonNecessaryForJobOne_DVP_2)).click();
            log.debug("Located and clicked the dropdown button 'Reikalinga'.");
        } catch (TimeoutException e) {
            log.error("Dropdown 'Reikalinga' is not visible or clickable.", e);
            return;
        }

        // Priverstinai parodyti dropdown, jei jis už ekrano ribų
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", dropdownButtonNecessaryForJobOne_DVP_2);

        // Laukiame, kol atsiras pasirinkimai
        List<WebElement> options;
        try {
            options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option/span")));
            wait.until(ExpectedConditions.elementToBeClickable(options.get(0)));
        } catch (TimeoutException e) {
            log.error("Dropdown options did not appear or are not clickable in time.", e);
            return;
        }

        // Bandome pasirinkti norimą reikšmę
        boolean optionSelected = false;
        for (WebElement option : options) {
            if (option.getText().trim().equalsIgnoreCase(valueToSelect)) {
                try {
                    wait.until(ExpectedConditions.elementToBeClickable(option)).click();
                    optionSelected = true;
                    log.info("Successfully selected the value: '{}'", valueToSelect);
                    break;
                } catch (Exception e) {
                    log.error("Failed to click on the dropdown option '{}'", valueToSelect, e);
                }
            }
        }

        if (!optionSelected) {
            log.warn("Dropdown value '{}' not found.", valueToSelect);
            return;
        }

        // Palaukiame, kol dropdown užsidarys
        try {
            wait.until(ExpectedConditions.attributeToBe(dropdownButtonNecessaryForJobOne_DVP_2, "aria-expanded", "false"));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//mat-option")));
        } catch (TimeoutException e) {
            log.error("Timeout while waiting for dropdown to close with aria-expanded = 'false'.", e);
        }

        // Jei dropdown vis tiek nepasirinktas, bandome patikrinti faktinę pasirinktą reikšmę
        String selectedValue = dropdownButtonNecessaryForJobOne_DVP_2.getText().trim();
        if (!selectedValue.equalsIgnoreCase(valueToSelect)) {
            log.error("Dropdown value mismatch! Expected '{}', but found '{}'", valueToSelect, selectedValue);
        }
    }
//DVP

    public void enterTool(String tool) {
        inputTool.clear();
        inputTool.sendKeys(tool);
    }

    public void enterTool_DVP(String tool) {
        inputTool_DVP.clear();
        inputTool_DVP.sendKeys(tool);
    }

    public void enterTool_DVP_2(String tool) {
        inputTool_DVP_2.clear();
        inputTool_DVP_2.sendKeys(tool);
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

    public void enterToolParameterOne_DVP(String parameter) {
        inputToolParameterOne_DVP.clear();
        inputToolParameterOne_DVP.sendKeys(parameter);
    }

    public void enterToolParameterTwo_DVP(String parameter) {
        inputToolParameterTwo_DVP.clear();
        inputToolParameterTwo_DVP.sendKeys(parameter);
    }

    public void enterToolParameterThree_DVP(String parameter) {
        inputToolParameterThree_DVP.clear();
        inputToolParameterThree_DVP.sendKeys(parameter);
    }


    public void enterToolsCount(String amount) {
        inputToolsCount.clear();
        inputToolsCount.sendKeys(amount);
    }

    public void enterToolsCount_DVP(String amount) {
        inputToolsCount_DVP.clear();
        inputToolsCount_DVP.sendKeys(amount);
    }

    public void enterToolsCount_DVP_2(String amount) {
        inputToolsCount_DVP_2.clear();
        inputToolsCount_DVP_2.sendKeys(amount);
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

    public void clickButtonAddRemoved() {
        if (!buttonAddRemoved.isSelected()) {
            buttonAddRemoved.click();
        }
    }

    public void clickButtonRemove() {
        if (!buttonRemove.isSelected()) {
            buttonRemove.click();
        }
    }

    public void clickButtonRemoveConfirmation() {
        if (!buttonRemoveConfirmation.isSelected()) {
            buttonRemoveConfirmation.click();
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

    public void enterProjectDateFrom_DVP() {
        LocalDate today = LocalDate.now();
        LocalDate pastDate = today.minusDays(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedPastDate = pastDate.format(formatter);

        if (inputProjectDateFrom_DVP.isDisplayed() && inputProjectDateFrom_DVP.isEnabled()) {
            inputProjectDateFrom_DVP.clear();
            inputProjectDateFrom_DVP.sendKeys(formattedPastDate);
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

    public void enterProjectDateUntil_DVP() {
        LocalDate today = LocalDate.now();
        LocalDate pastDate = today.plusYears(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedPastDate = pastDate.format(formatter);

        if (inputProjectDateUntil_DVP.isDisplayed() && inputProjectDateUntil_DVP.isEnabled()) {
            inputProjectDateUntil_DVP.clear();
            inputProjectDateUntil_DVP.sendKeys(formattedPastDate);
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

        public static void selectRadioButton(WebDriver driver, By locator, boolean shouldBeSelected) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            try {

                WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(locator));

                if (radioButton.isSelected() != shouldBeSelected) {
                    radioButton.click();
                }
            } catch (Exception e) {
                log.error("Error selecting radio button: {}", e.getMessage());
                JavascriptExecutor js = (JavascriptExecutor) driver;
                WebElement radioButton = driver.findElement(locator);
                js.executeScript("arguments[0].click();", radioButton);
            }
        }
    }

    public void uploadFileStepFive() {
        String filePath = "C:\\Users\\ingrida.zadorozniene\\TXT.txt";
        List<WebElement> fileInputs = driver.findElements(By.xpath("//input[@type='file']"));

        if (fileInputs.isEmpty()) {
            log.warn("No file input elements found on the page.");
        } else {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            for (WebElement fileInput : fileInputs) {
                try {
                    fileInput.sendKeys(filePath);
                    log.info("File uploaded successfully to an input element.");

                    wait.until(ExpectedConditions.attributeToBeNotEmpty(fileInput, "value"));

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
    public void enterPersonCountOne_DVP(String count) {
        inputPersonCountOne_DVP.clear();
        inputPersonCountOne_DVP.sendKeys(count);
    }

    public void enterPersonCountTwo_DVP(String count) {
        inputPersonCountTwo_DVP.clear();
        inputPersonCountTwo_DVP.sendKeys(count);
    }

    //evaluations
//---------------------------

//    public void selectValueByListEvaluators(String valueToSelect) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//
//        log.info("Located the dropdown search field.");
//
//        try {
//            wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonEvaluator)).click();
//            log.info("Dropdown city is opened.");
//        } catch (Exception e) {
//            log.error("Dropdown city click failed: {}", e.getMessage());
//            return;
//        }
//
//        try {
//            wait.until(ExpectedConditions.elementToBeClickable(dropdownSearch)).click();
//            dropdownSearch.clear();
//            dropdownSearch.sendKeys(valueToSelect);
//
//            // Laukiame, kol atsiras bent vienas variantas
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-option")));
//
//            log.info("Searched for value: '{}'", valueToSelect);
//        } catch (Exception e) {
//            log.error("Failed to enter search value: {}", e.getMessage());
//            return;
//        }
//
//        try {
//            String optionXPath = "//mat-option//span[contains(@class, 'area-center') and text()='" + valueToSelect + "']";
//            WebElement correctOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(optionXPath)));
//            correctOption.click();
//
//            log.info("Successfully selected: '{}'", valueToSelect);
//        } catch (Exception e) {
//            log.error("Failed to select correct dropdown option: {}", e.getMessage());
//        }
//    }

//    public void selectValueByListEvaluators(String valueToSelect) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//
//        log.info("Trying to open evaluator dropdown.");
//
//        try {
//            wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonEvaluator)).click();
//            log.info("Dropdown opened.");
//        } catch (Exception e) {
//            log.error("Failed to open dropdown: {}", e.getMessage());
//            return;
//        }
//
//        try {
//            wait.until(ExpectedConditions.elementToBeClickable(dropdownSearch)).click();
//            dropdownSearch.clear();
//            dropdownSearch.sendKeys(valueToSelect);
//            log.info("Search value entered: '{}'", valueToSelect);
//
//            // Laukiame, kol pasirodys bent vienas pasirinkimas
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-option")));
//        } catch (Exception e) {
//            log.error("Failed to search for value: {}", e.getMessage());
//            return;
//        }
//
//        try {
//            // Tikslus tekstas su normalize-space
//            String optionXPath = "//mat-option//span[contains(@class, 'area-center') and normalize-space(text())='" + valueToSelect + "']";
//            WebElement correctOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(optionXPath)));
//            correctOption.click();
//            log.info("Successfully selected evaluator: '{}'", valueToSelect);
//
//            // Patikriname, ar pasirinktas tekstas atsispindi dropdown'e
//            wait.until(ExpectedConditions.textToBePresentInElementLocated(
//                    By.cssSelector("mat-select span.mat-select-value-text"),
//                    valueToSelect
//            ));
//        } catch (Exception e) {
//            log.error("Failed to select the correct dropdown option: {}", e.getMessage());
//        }
//    }


    public void clickElementByTextInDropdown(String visibleText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            String optionXPath = "//mat-option//span[normalize-space(text())='" + visibleText + "']";
            WebElement optionToClick = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(optionXPath)));
            optionToClick.click();
            log.info("Clicked on dropdown item with text: '{}'", visibleText);
        } catch (Exception e) {
            log.error("Could not find or click dropdown option '{}': {}", visibleText, e.getMessage());
            throw e;
        }
    }

//    public void selectValueByListEvaluators(String valueToSelect) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//
//        try {
//            wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonEvaluator)).click();
//            log.info("Evaluator dropdown opened.");
//
//            wait.until(ExpectedConditions.elementToBeClickable(dropdownSearch)).click();
//            dropdownSearch.clear();
//            dropdownSearch.sendKeys(valueToSelect);
//
//            // Laukiam, kol bent viena opcija atsiras
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-option")));
//
//            // Naudojam bendrą metodą
//            clickElementByTextInDropdown(valueToSelect);
//
//            // Patvirtinam, kad pasirinkta reikšmė atsidūrė dropdown'e
//            wait.until(ExpectedConditions.textToBePresentInElementLocated(
//                    By.cssSelector("mat-select span.mat-select-value-text"),
//                    valueToSelect
//            ));
//        } catch (Exception e) {
//            log.error("Failed in selectValueByListEvaluators: {}", e.getMessage());
//            throw e;
//        }
//    }

    public void clickOutsideDropdown() {
        WebElement header = driver.findElement(By.cssSelector("h3.my-3"));
        header.click();
    }

    private String normalize(String s) {
        if (s == null) return "";
        return s.replaceAll("\\s+", " ").trim().toLowerCase();
    }

    public void selectValueByListEvaluators(String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonEvaluator)).click();
            log.info("Evaluator dropdown opened.");

            wait.until(ExpectedConditions.elementToBeClickable(dropdownSearch)).click();
            dropdownSearch.clear();
            dropdownSearch.sendKeys(valueToSelect);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//mat-option")));

            clickElementByTextInDropdown(valueToSelect);

            clickOutsideDropdown();

            // Palaukiam, kol dropdown užsidarys (t.y. nebeliks mat-option elementų)
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//mat-option")));

            // Tikrinam ar pasirinktas tekstas matomas teisingai
            wait.until(driver -> {
                try {
                    String selectedText = driver.findElement(
                            By.cssSelector(".mat-mdc-select-value-text mat-select-trigger span span")
                    ).getText().trim();
                    log.debug("Dropdown selected evaluator raw text: '{}'", selectedText);
                    return normalize(selectedText).contains(normalize(valueToSelect));
                } catch (Exception e) {
                    log.warn("Nepavyko gauti pasirinktos reikšmės: {}", e.getMessage());
                    return false;
                }
            });

            log.info("Evaluator successfully selected: {}", valueToSelect);

        } catch (Exception e) {
            log.error("Failed in selectValueByListEvaluators: {}", e.getMessage(), e);
            throw e;
        }
    }

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
//----------------------------
}