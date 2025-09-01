package lt.insoft.uztis.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class SubmittedApplicationsPage extends UztisPage {

    private static final Logger log = getLogger(lookup().lookupClass());
    private final Map<String, By> textAreaLocators = new HashMap<>();
    private final Map<String, By> textInputLocators = new HashMap<>();
    private final Map<String, By> radioButtonLocatorsY_N = new HashMap<>();
    private final Map<String, By> dropdownButtonCheckBoxLocators_VUI_DVP_PVK = new HashMap<>();
    private final Map<String, By> dropdownButtonLocators_VUI_DVP_PVK = new HashMap<>();
    private final Map<String, By> documentInputLocators = new HashMap<>();
    private final Map<String, By> buttonApplicationLocators = new HashMap<>();

    public SubmittedApplicationsPage(WebDriver driver) {
        super(driver);

        buttonApplicationLocators.put("next", By.xpath("//button[contains(@class, 'i-forms-stepper-button-next') and normalize-space(text())='Toliau']"));
        buttonApplicationLocators.put("review", By.xpath("//common-button/button[contains(text(), 'Peržiūrėti')]"));
        buttonApplicationLocators.put("submit", By.cssSelector("common-button:nth-of-type(3) > .btn.btn--primary"));
        buttonApplicationLocators.put("addressConfirm", By.xpath("//common-button[@btntype='submit']//button"));
        buttonApplicationLocators.put("save_draft", By.xpath("//common-button/button[contains(text(), 'Saugoti ruošinį')]"));
        buttonApplicationLocators.put("submitConfirm", By.xpath("//common-button//button[@type='button' and contains(text(), 'Pateikti vertinimui')]"));
        buttonApplicationLocators.put("add", By.xpath("(//button[contains(@class, 'i-forms-repeater-button-add')])[4]"));


        radioButtonLocatorsY_N.put("job_for_yourself_pvk", By.xpath("//input[@type='radio' and @name='mat-radio-group-1' and @value='jobForYourself']"));
        radioButtonLocatorsY_N.put("selfEmploymentTerminated_PVK", By.xpath("//input[@type='radio' and @value='true']"));
        radioButtonLocatorsY_N.put("1_true", By.xpath("(//input[@type='radio' and @value='true'])[1]"));
        radioButtonLocatorsY_N.put("1_false", By.xpath("(//input[@type='radio' and @value='false'])[1]"));
        radioButtonLocatorsY_N.put("2_true", By.xpath("(//input[@type='radio' and @value='true'])[2]"));
        radioButtonLocatorsY_N.put("2_false", By.xpath("(//input[@type='radio' and @value='false'])[2]"));
        radioButtonLocatorsY_N.put("3_true", By.xpath("(//input[@type='radio' and @value='true'])[3]"));
        radioButtonLocatorsY_N.put("3_false", By.xpath("(//input[@type='radio' and @value='false'])[3]"));
        radioButtonLocatorsY_N.put("4_true", By.xpath("(//input[@type='radio' and @value='true'])[4]"));
        radioButtonLocatorsY_N.put("4_false", By.xpath("(//input[@type='radio' and @value='false'])[4]"));
        radioButtonLocatorsY_N.put("5_true", By.xpath("(//input[@type='radio' and @value='true'])[5]"));
        radioButtonLocatorsY_N.put("5_false", By.xpath("(//input[@type='radio' and @value='false'])[5]"));
        radioButtonLocatorsY_N.put("6_false", By.xpath("(//input[@type='radio' and @value='false'])[6]"));
        radioButtonLocatorsY_N.put("7_true", By.xpath("(//input[@type='radio' and @value='true'])[7]"));
        radioButtonLocatorsY_N.put("7_false", By.xpath("(//input[@type='radio' and @value='false'])[7]"));


        textInputLocators.put("phone", By.xpath("//input[@id='phone']"));
        textInputLocators.put("e_mail", By.xpath("//mat-form-field[contains(.//label, 'El. pašto adresas')]//input"));
        textInputLocators.put("salary_pvk", By.xpath("//common-decimal-input[contains(@class, 'ng-untouched')]//input[@type='text']"));
        textInputLocators.put("salary_vui_dvp", By.xpath("//mat-form-field[contains(.//label, 'Planuojamas mokėti bruto darbo užmokestis, Eur')]//input"));
        textInputLocators.put("job_count_vui", By.xpath("//mat-form-field[contains(.//label, 'Planuojamų steigti darbo vietų skaičius')]//input"));
        textInputLocators.put("country_perc", By.xpath("//mat-form-field[contains(.//label, 'Valstybės institucijos, įstaigos (proc.)')]//input"));
        textInputLocators.put("institution_perc", By.xpath("//mat-form-field[contains(.//label, 'Savivaldybės institucijos, įstaigos (proc.)')]//input"));
        textInputLocators.put("municipality_perc", By.xpath("//mat-form-field[contains(.//label, 'Valstybės ar savivaldybių įmonės (proc.)')]//input"));
        textInputLocators.put("program_name", By.xpath("//mat-form-field[contains(.//label, 'Projekto ir finansuojančios programos pavadinimas')]//input"));
        textInputLocators.put("support_amount", By.xpath("//mat-form-field[contains(.//label, 'Gautos paramos suma, Eur')]//input"));
        textInputLocators.put("tool", By.xpath("//mat-form-field[contains(.//label, 'Darbo priemonės pavadinimas')]//input"));
        textInputLocators.put("tool_parameter_1", By.xpath("(//mat-form-field[.//label[contains(normalize-space(), 'Darbo priemonės techninis parametras')]]//input)[1]"));
        textInputLocators.put("tool_parameter_2", By.xpath("(//mat-form-field[.//label[contains(normalize-space(), 'Darbo priemonės techninis parametras')]]//input)[2]"));
        textInputLocators.put("tool_parameter_3", By.xpath("(//mat-form-field[.//label[contains(normalize-space(), 'Darbo priemonės techninis parametras')]]//input)[3]"));
        textInputLocators.put("tool_count", By.xpath("//mat-form-field[contains(.//label, 'Darbo priemonės kiekis, vnt.')]//input"));
        textInputLocators.put("person_count_dvp", By.xpath("(//mat-form-field[contains(.//label, 'Asmenų skaičius')]//input)[1]"));
        textInputLocators.put("repair_name", By.xpath("//mat-form-field[contains(.//label, 'Remonto darbų pavadinimas')]//input"));
        textInputLocators.put("purchase_name_dvp", By.xpath("//mat-form-field[contains(.//label, 'Išlaidų elemento pavadinimas')]//input"));
        textInputLocators.put("purchase_parameter_dvp_1", By.xpath("(//mat-form-field[.//label[contains(normalize-space(), 'Išlaidų elemento techninis parametras')]]//input)[1]"));
        textInputLocators.put("purchase_parameter_dvp_2", By.xpath("(//mat-form-field[.//label[contains(normalize-space(), 'Išlaidų elemento techninis parametras')]]//input)[2]"));
        textInputLocators.put("purchase_parameter_dvp_3", By.xpath("(//mat-form-field[.//label[contains(normalize-space(), 'Išlaidų elemento techninis parametras')]]//input)[3]"));
        textInputLocators.put("tool_count_dvp_1", By.xpath("//mat-form-field[contains(.//label, 'Išlaidų elemento kiekis, vnt.')]//input"));
        textInputLocators.put("tool_count_dvp_2", By.xpath("(//mat-form-field[contains(.//label, 'Išlaidų elemento kiekis, vnt.')]//input)[2]"));
        textInputLocators.put("repair_name_dvp", By.xpath("(//mat-form-field[contains(.//label, 'Išlaidų elemento pavadinimas')]//input)[2]"));
        textInputLocators.put("price_amount_1", By.xpath("(//mat-form-field[contains(.//label, 'Kaina, Eur')]//input)[1]"));
        textInputLocators.put("price_amount_2", By.xpath("(//mat-form-field[contains(.//label, 'Kaina, Eur')]//input)[2]"));
        textInputLocators.put("funds_amount_1", By.xpath("(//mat-form-field[contains(.//label, 'Nuosavos lėšos, Eur')]//input)[1]"));
        textInputLocators.put("funds_amount_2", By.xpath("(//mat-form-field[contains(.//label, 'Nuosavos lėšos, Eur')]//input)[2]"));


        textAreaLocators.put("1", By.xpath("//div[contains(@class, 'mdc-notched-outline')]//following::textarea[1]"));
        textAreaLocators.put("2", By.xpath("//div[contains(@class, 'mdc-notched-outline')]//following::textarea[2]"));
        textAreaLocators.put("3", By.xpath("//div[contains(@class, 'mdc-notched-outline')]//following::textarea[3]"));
        textAreaLocators.put("4", By.xpath("//div[contains(@class, 'mdc-notched-outline')]//following::textarea[4]"));
        textAreaLocators.put("5", By.xpath("//div[contains(@class, 'mdc-notched-outline')]//following::textarea[5]"));
        textAreaLocators.put("6", By.xpath("//div[contains(@class, 'mdc-notched-outline')]//following::textarea[6]"));
        textAreaLocators.put("7", By.xpath("//div[contains(@class, 'mdc-notched-outline')]//following::textarea[7]"));
        textAreaLocators.put("8", By.xpath("//div[contains(@class, 'mdc-notched-outline')]//following::textarea[8]"));
        textAreaLocators.put("9", By.xpath("//div[contains(@class, 'mdc-notched-outline')]//following::textarea[9]"));


        dropdownButtonCheckBoxLocators_VUI_DVP_PVK.put("evrk_VUI", By.xpath("//mat-label[contains(text(), 'Projekto teikėjo planuojama vykdyti veikla, kuriai prašoma subsidija, kodas pagal EVRK')]"));
        dropdownButtonCheckBoxLocators_VUI_DVP_PVK.put("supported_VUI", By.xpath("//mat-label[contains(text(), 'Papildomai remiamo asmens tipas')]"));
        dropdownButtonCheckBoxLocators_VUI_DVP_PVK.put("evrk_DVP", By.xpath("//mat-label[contains(text(), 'Paraiškos teikėjo vykdoma veikla, kodas pagal EVRK')]"));
        dropdownButtonCheckBoxLocators_VUI_DVP_PVK.put("evrk_PVK", By.xpath("//mat-label[contains(text(), 'Planuojama vykdyti veikla, kodas pagal EVRK')]"));
        dropdownButtonCheckBoxLocators_VUI_DVP_PVK.put("evrk_true_PVK", By.xpath("//mat-label[contains(text(), 'Vykdyta veikla (-os), kuri buvo nutraukta, kodas pagal EVRK')]"));
        dropdownButtonCheckBoxLocators_VUI_DVP_PVK.put("supported_PVK", By.xpath("//mat-label[contains(text(), 'Papildomai remiamo asmens tipas')]"));

        dropdownButtonLocators_VUI_DVP_PVK.put("jobName_VUI", By.xpath("//mat-label[contains(text(), 'Darbo vietos pavadinimas')]"));
        dropdownButtonLocators_VUI_DVP_PVK.put("jobName_DVP", By.xpath("//mat-label[contains(text(), 'Pritaikomos darbo vietos pavadinimas, kodas pagal profesijų klasifikatorių')]"));
        dropdownButtonLocators_VUI_DVP_PVK.put("disability_DVP", By.xpath("//mat-label[contains(text(), 'Asmens su negalia dalyvumo lygis')]"));
        dropdownButtonLocators_VUI_DVP_PVK.put("businessStructure_PVK", By.xpath("//mat-label[contains(text(), 'Planuojama steigti veiklos forma')]"));
        dropdownButtonLocators_VUI_DVP_PVK.put("jobName_PVK", By.xpath("//mat-label[contains(text(), 'Steigiamos darbo vietos pavadinimas, kodas pagal profesijų klasifikatorių')]"));

        documentInputLocators.put("documentNameOne", By.xpath("(//mat-form-field[contains(.//label, 'Dokumento pavadinimas')]//input)[1]"));
        documentInputLocators.put("documentNameTwo", By.xpath("(//mat-form-field[contains(.//label, 'Dokumento pavadinimas')]//input)[2]"));
        documentInputLocators.put("documentNameThree", By.xpath("(//mat-form-field[contains(.//label, 'Dokumento pavadinimas')]//input)[3]"));
        documentInputLocators.put("documentNameFour", By.xpath("(//mat-form-field[contains(.//label, 'Dokumento pavadinimas')]//input)[4]"));
        documentInputLocators.put("documentNameFive", By.xpath("(//mat-form-field[contains(.//label, 'Dokumento pavadinimas')]//input)[5]"));
        documentInputLocators.put("documentNameSix", By.xpath("(//mat-form-field[contains(.//label, 'Dokumento pavadinimas')]//input)[6]"));
        documentInputLocators.put("documentNameSeven", By.xpath("(//mat-form-field[contains(.//label, 'Dokumento pavadinimas')]//input)[7]"));
        documentInputLocators.put("documentNameEight", By.xpath("(//mat-form-field[contains(.//label, 'Dokumento pavadinimas')]//input)[8]"));
        documentInputLocators.put("documentNameNine", By.xpath("(//mat-form-field[contains(.//label, 'Dokumento pavadinimas')]//input)[9]"));
        documentInputLocators.put("documentNameTen", By.xpath("(//mat-form-field[contains(.//label, 'Dokumento pavadinimas')]//input)[10]"));
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

//    @FindBy(xpath = "//button[contains(@class, 'i-forms-stepper-button-previous') and normalize-space(text())='Atgal']")
//    WebElement buttonPrevious;

    //application form step 1
    //------------------------------

    @FindBy(xpath = "//input[@type='radio' and @value='PERSON']")
    WebElement radioButtonFA;

    @FindBy(xpath = "//mat-select[contains(@class, \"mat-mdc-select\")]")
    WebElement dropdownButtonApplicant;

    //PVK
    @FindBy(xpath = "(//mat-form-field[.//mat-label[contains(text(), 'Darbo vietai įsteigti reikalinga')]]//mat-select)[1]")
    WebElement dropdownButtonNecessaryForJobOne_PVK;

    @FindBy(xpath = "(//mat-form-field[.//mat-label[contains(text(), 'Darbo vietai įsteigti reikalinga')]]//mat-select)[2]")
    WebElement dropdownButtonNecessaryForJobOne_PVK_2;
    //PVK

    //DVP
    @FindBy(xpath = "//mat-label[contains(text(), 'Išlaidų tipas')]")
    WebElement dropdownButtonExpensesTyp_DVP;

    @FindBy(xpath = "(//mat-label[contains(text(), 'Išlaidų tipas')])[2]")
    WebElement dropdownButtonExpensesTyp_DVP_2;
    //DVP

    //address
//----------------
    @FindBy(xpath = "(//mat-icon[contains(text(), 'edit')])[3]")
    WebElement addressComponent;

    //PVK
    @FindBy(xpath = "(//mat-icon[contains(text(), 'edit')])[2]")
    WebElement addressComponentPVK;
    //PVK

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

//    @FindBy(xpath = "//mat-label[contains(text(), 'Buto / patalpos Nr.')]/following::mat-select[1]")
//    WebElement dropdownAddressApartment;


    //application form step 2
    //------------------------------

    @FindBy(xpath = "//mat-label[contains(text(), 'Darbo laiko norma ir darbo laiko režimas')]")
    WebElement dropdownButtonTimeMode;

    @FindBy(xpath = "//mat-form-field[contains(.//label, 'Darbo laiko norma ir darbo laiko režimas')]//input")
    WebElement inputTimeModeOthers;

    //VUI
    @FindBy(xpath = "//input[contains(@class, 'mat-datepicker-input')]")
    WebElement inputDate_VUI;

    @FindBy(xpath = "(//input[contains(@class, 'mat-datepicker-input')])[2]")
    WebElement inputJopDate_VUI;

    @FindBy(xpath = "(//mat-label[contains(text(), 'Darbo vietai įsteigti reikalinga')])[1]")
    WebElement dropdownButtonNecessaryForJobOne;

    @FindBy(xpath = "(//mat-label[contains(text(), 'Darbo vietai įsteigti reikalinga')])[2]")
    WebElement dropdownButtonNecessaryForJobTwo;
    //VUI

    //DVP
    @FindBy(xpath = "(//input[contains(@class, 'mat-datepicker-input')])[1]")
    WebElement inputJopDate_DVP;

    @FindBy(xpath = "(//mat-form-field[.//mat-label[contains(text(), 'Reikalinga')]]//mat-select)[1]")
    WebElement dropdownButtonNecessaryForJobOne_DVP;

    @FindBy(xpath = "(//mat-form-field[.//mat-label[contains(text(), 'Reikalinga')]]//mat-select)[2]")
    WebElement dropdownButtonNecessaryForJobOne_DVP_2;
    //DVP

    @FindBy(xpath = "(//button[contains(@class, 'i-forms-repeater-button-add')])[5]")
    WebElement buttonAddRemoved;

    @FindBy(xpath = "(//button[contains(@class, 'i-forms-repeater-button-remove')])[3]")
    WebElement buttonRemove;

    @FindBy(css = "common-button:nth-of-type(2) > .btn.btn--primary")
    WebElement buttonRemoveConfirmation;

//    @FindBy(xpath = "(//button[contains(@class, 'i-forms-repeater-button-add')])[5]")
//    WebElement buttonAddJobPlace;

    @FindBy(xpath = "(//input[contains(@class, 'mat-datepicker-input')])[3]")
    WebElement inputProjectDateFrom;

    @FindBy(xpath = "(//input[contains(@class, 'mat-datepicker-input')])[2]")
    WebElement inputProjectDateFrom_DVP;

    @FindBy(xpath = "(//input[contains(@class, 'mat-datepicker-input')])[4]")
    WebElement inputProjectDateUntil;

    @FindBy(xpath = "(//input[contains(@class, 'mat-datepicker-input')])[3]")
    WebElement inputProjectDateUntil_DVP;

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

//    @FindBy(xpath = "(//div[contains(@class, 'mdc-checkbox')]//input[@type='checkbox'])[6]")
//    public WebElement checkboxConfirmationSix;

    //application form step 4 (Checkboxes)
//-----------
    @FindBy(xpath = "(//div[contains(@class, 'mdc-checkbox')]//input[@type='checkbox'])[4]")
    public WebElement checkboxConfirmationStepFour;

    @FindBy(xpath = "(//div[contains(@class, 'mdc-checkbox')]//input[@type='checkbox'])[6]")
    public WebElement checkboxConfirmationStepFourDVP;

    //application form step 5 (Checkboxes)
//-----------
    @FindBy(xpath = "(//div[contains(@class, 'mdc-checkbox')]//input[@type='checkbox'])[5]")
    public WebElement checkboxConfirmationDocument;

    @FindBy(xpath = "(//div[contains(@class, 'mdc-checkbox')]//input[@type='checkbox'])[7]")
    public WebElement checkboxConfirmationDocument_DVP;

    @FindBy(xpath = "(//div[contains(@class, 'mdc-checkbox')]//input[@type='checkbox'])[6]")
    public WebElement checkboxConfirmationApplication;

    @FindBy(xpath = "(//div[contains(@class, 'mdc-checkbox')]//input[@type='checkbox'])[8]")
    public WebElement checkboxConfirmationApplication_DVP;

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

    //application processing buttons

//    public void clickButtonAddJobPlace() {
//        if (!buttonAddJobPlace.isSelected()) {
//            buttonAddJobPlace.click();
//        }
//    }

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

    public void selectRadioButtonFA() {
        if (!radioButtonFA.isSelected()) {
            radioButtonFA.click();
        }
    }

    public void clickButtonActionApplication() {
        buttonActionApplication.click();
    }

    public void clickButtonNewApplication() {
        buttonNewApplication.click();
    }

    public void clickApplicationButton(String buttonKey) {
        By locator = buttonApplicationLocators.get(buttonKey);
        if (locator == null) {
            throw new IllegalArgumentException("No button found for key: " + buttonKey);
        }
        WebElement button = driver.findElement(locator);
        button.click();
    }

    public boolean isButtonNextDisplayed() {
        try {
            return buttonNext.isDisplayed();
        } catch (NoSuchElementException e) {
            ApplicationFormsPage.log.error("Edit button is not displayed. Exception: {}", e.getMessage());
            return false;
        }
    }

    //application elements

    public void selectDropdownButtonValue_VUI_DVP_PVK(String dropdownKey, String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        By dropdownLocator = dropdownButtonLocators_VUI_DVP_PVK.get(dropdownKey);

        if (dropdownLocator == null) {
            throw new IllegalArgumentException("No dropdown locator found for key: " + dropdownKey);
        }

        try {
            WebElement dropdownButton = wait.until(ExpectedConditions.elementToBeClickable(dropdownLocator));
            log.info("Located the dropdown button for key: {}", dropdownKey);

            // Scroll into view (jei reikia)
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdownButton);
            dropdownButton.click();
        } catch (Exception e) {
            log.error("Dropdown button click failed for '{}': {}", dropdownKey, e.getMessage());
            throw new RuntimeException("Failed to click dropdown: " + dropdownKey, e);
        }

        try {
            List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option/span")));

            boolean optionSelected = false;
            for (WebElement option : options) {
                if (option.getText().trim().equalsIgnoreCase(valueToSelect)) {
                    wait.until(ExpectedConditions.elementToBeClickable(option)).click();
                    optionSelected = true;
                    log.info("Successfully selected the value '{}' from '{}'", valueToSelect, dropdownKey);
                    break;
                }
            }

            if (!optionSelected) {
                log.warn("Value '{}' not found in dropdown '{}'", valueToSelect, dropdownKey);
                throw new RuntimeException("Value '" + valueToSelect + "' not found in dropdown '" + dropdownKey + "'");
            }

        } catch (Exception e) {
            log.error("Failed to select dropdown option for '{}': {}", dropdownKey, e.getMessage());
            throw new RuntimeException("Dropdown value selection failed for: " + dropdownKey, e);
        }
    }

    public void clickMatCheckboxByLabelText_PVK(String partialLabelText) {
        By checkboxLabel = By.xpath("//mat-checkbox[.//label[contains(normalize-space(), '" + partialLabelText + "')]]//label");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement label = wait.until(ExpectedConditions.elementToBeClickable(checkboxLabel));
        label.click();
        log.info("Clicked mat-checkbox label containing text: {}", partialLabelText);
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

    public void selectDropdownButtonCheckBoxValueEVRK_VUI_DVP_PVK(String dropdownKey, String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By dropdownLocator = dropdownButtonCheckBoxLocators_VUI_DVP_PVK.get(dropdownKey);

        if (dropdownLocator == null) {
            throw new IllegalArgumentException("No dropdown locator found for key: " + dropdownKey);
        }

        try {
            log.info("Clicking on the dropdown for key: {}", dropdownKey);
            wait.until(ExpectedConditions.elementToBeClickable(dropdownLocator)).click();

            List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option//span")));
            log.info("Found {} options in the dropdown.", options.size());

            boolean optionSelected = false;

            for (WebElement option : options) {
                String optionText = option.getText().trim();
                log.debug("Checking option: '{}'", optionText);

                if (optionText.equalsIgnoreCase(valueToSelect)) {
                    option.click();
                    optionSelected = true;
                    log.info("Successfully selected the value: '{}'", valueToSelect);
                    break;
                }
            }

            if (!optionSelected) {
                log.warn("The value '{}' was not found in the dropdown.", valueToSelect);
                throw new RuntimeException("Value '" + valueToSelect + "' not found in dropdown '" + dropdownKey + "'");
            }

            Thread.sleep(500);

            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.ESCAPE).perform();

            WebElement body = driver.findElement(By.tagName("body"));
            actions.moveToElement(body).click().perform();

        } catch (Exception e) {
            log.error("Error selecting value '{}' from dropdown '{}': {}", valueToSelect, dropdownKey, e.getMessage());
            throw new RuntimeException("Failed to select value from dropdown '" + dropdownKey + "'", e);
        }
    }

    public void selectRadioButtonY_N(String type) {
        By locator = radioButtonLocatorsY_N.get(type);
        if (locator == null) {
            throw new IllegalArgumentException("No locator found for type: " + type);
        }
        WebElement radioButton = driver.findElement(locator);
        if (!radioButton.isSelected()) {
            radioButton.click();
        }
    }

    public void enterTextArea(String type, String text) {
        By locator = textAreaLocators.get(type.toLowerCase());
        if (locator == null) {
            throw new IllegalArgumentException("No input found for type: " + type);
        }
        WebElement input = driver.findElement(locator);
        input.clear();
        input.sendKeys(text);
    }

    public void enterText(String type, String text) {
        By locator = textInputLocators.get(type.toLowerCase());
        if (locator == null) {
            throw new IllegalArgumentException("No input found for type: " + type);
        }
        WebElement input = driver.findElement(locator);
        input.clear();
        input.sendKeys(text);
    }

    //address elements
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

    public void clickAddressComponent_PVK() {
        try {
            // Laukiame, kol elementas bus matomas ir paspaudžiamas
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(addressComponentPVK));

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

    //jei kada reikes buto nr.
//    public void selectDropdownAddressApartment(String valueToSelect) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Ilgesnis laukimo laikas
//
//        log.info("Located the dropdown button.");
//        try {
//            wait.until(ExpectedConditions.elementToBeClickable(dropdownAddressApartment));
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            js.executeScript("arguments[0].scrollIntoView(true);", dropdownAddressApartment); // Užtikrinsime, kad elementas būtų matomas
//            dropdownAddressApartment.click();
//        } catch (Exception e) {
//            log.error("Dropdown button click failed: {}", e.getMessage());
//        }
//
//        try {
//            List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option/span")));
//
//            boolean optionSelected = false;
//            for (WebElement option : options) {
//                if (option.getText().trim().equalsIgnoreCase(valueToSelect)) {
//                    wait.until(ExpectedConditions.elementToBeClickable(option)).click();
//                    optionSelected = true;
//                    log.info("Successfully selected the value: '{}'", valueToSelect);
//                    break;
//                }
//            }
//
//            if (!optionSelected) {
//                log.warn("Dropdown value '{}' not found.", valueToSelect);
//            }
//
//        } catch (Exception e) {
//            log.error("Failed to select dropdown option: {}", e.getMessage());
//        }
//
//        try {
//            wait.until(ExpectedConditions.attributeToBe(dropdownAddressApartment, "aria-expanded", "false"));
//        } catch (TimeoutException e) {
//            log.error("Timeout while waiting for dropdown to close with aria-expanded = 'false'.");
//        }
//    }
//-------------------------------------

    public void enterDate_VUI() {
        LocalDate today = LocalDate.now();
        LocalDate pastDate = today.minusYears(1).minusDays(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedPastDate = pastDate.format(formatter);

        if (inputDate_VUI.isDisplayed() && inputDate_VUI.isEnabled()) {
            inputDate_VUI.clear();
            inputDate_VUI.sendKeys(formattedPastDate);
        } else {
            throw new RuntimeException("End date input is not interactable.");
        }
    }

    public void enterJobDate_VUI() {
        LocalDate today = LocalDate.now();
        LocalDate pastDate = today.plusMonths(3).plusDays(15);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedPastDate = pastDate.format(formatter);

        if (inputJopDate_VUI.isDisplayed() && inputJopDate_VUI.isEnabled()) {
            inputJopDate_VUI.clear();
            inputJopDate_VUI.sendKeys(formattedPastDate);
        } else {
            throw new RuntimeException("End date input is not interactable.");
        }
    }

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

    //----------------------------

    //DVP
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
        wait.until(ExpectedConditions.elementToBeClickable(options.getFirst()));
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

    public void selectDropdownNecessaryForJobOne_PVK(String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            wait.until(ExpectedConditions.visibilityOf(dropdownButtonNecessaryForJobOne_PVK));
            wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonNecessaryForJobOne_PVK)).click();
            log.debug("Located and clicked the dropdown button 'Reikalinga'.");
        } catch (TimeoutException e) {
            log.error("Dropdown 'Reikalinga' is not visible or clickable.", e);
            return;
        }

        // Priverstinai parodyti dropdown, jei jis už ekrano ribų
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", dropdownButtonNecessaryForJobOne_PVK);

        // Laukiame, kol atsiras pasirinkimai
        List<WebElement> options;
        try {
            options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option/span")));
            wait.until(ExpectedConditions.elementToBeClickable(options.getFirst()));
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
            wait.until(ExpectedConditions.elementToBeClickable(options.getFirst()));
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

    public void selectDropdownNecessaryForJobOne_PVK_2(String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Užtikriname, kad dropdown yra matomas ir paspaudžiamas
        try {
            wait.until(ExpectedConditions.visibilityOf(dropdownButtonNecessaryForJobOne_PVK_2));
            wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonNecessaryForJobOne_PVK_2)).click();
            log.debug("Located and clicked the dropdown button 'Reikalinga'.");
        } catch (TimeoutException e) {
            log.error("Dropdown 'Reikalinga' is not visible or clickable.", e);
            return;
        }

        // Priverstinai parodyti dropdown, jei jis už ekrano ribų
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", dropdownButtonNecessaryForJobOne_PVK_2);

        // Laukiame, kol atsiras pasirinkimai
        List<WebElement> options;
        try {
            options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option/span")));
            wait.until(ExpectedConditions.elementToBeClickable(options.getFirst()));
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
            wait.until(ExpectedConditions.attributeToBe(dropdownButtonNecessaryForJobOne_PVK_2, "aria-expanded", "false"));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//mat-option")));
        } catch (TimeoutException e) {
            log.error("Timeout while waiting for dropdown to close with aria-expanded = 'false'.", e);
        }

        // Jei dropdown vis tiek nepasirinktas, bandome patikrinti faktinę pasirinktą reikšmę
        String selectedValue = dropdownButtonNecessaryForJobOne_PVK_2.getText().trim();
        if (!selectedValue.equalsIgnoreCase(valueToSelect)) {
            log.error("Dropdown value mismatch! Expected '{}', but found '{}'", valueToSelect, selectedValue);
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

//--------------------

    public static class CheckboxHelper {

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

    public static class RadioButtonHelper {

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

    public void uploadFiles() {
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

    public void enterDocumentInputValue(String fieldKey, String value) {
        By locator = documentInputLocators.get(fieldKey);
        if (locator == null) {
            throw new IllegalArgumentException("No input field found for key: " + fieldKey);
        }

        WebElement inputField = driver.findElement(locator);
        inputField.clear();
        inputField.sendKeys(value);
        log.info("Entered value '{}' into document input '{}'", value, fieldKey);
    }

    public void fillDocumentFields(int count) {
        String[] numberWords = {
                "One", "Two", "Three", "Four", "Five",
                "Six", "Seven", "Eight", "Nine", "Ten"
        };

        if (count > numberWords.length) {
            throw new IllegalArgumentException("Max allowed is " + numberWords.length);
        }

        for (int i = 0; i < count; i++) {
            String fieldKey = "documentName" + numberWords[i];
            String docValue = "Dokumentas " + (i + 1) + " testas";
            enterDocumentInputValue(fieldKey, docValue);
            log.debug("Entered value: '{}' into field: '{}'.", docValue, fieldKey);
        }
    }

    //DVP
//    public void enterPersonCountOne_DVP(String count) {
//        inputPersonCountOne_DVP.clear();
//        inputPersonCountOne_DVP.sendKeys(count);
//    }

    //evaluations
//---------------------------

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