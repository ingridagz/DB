package lt.insoft.uztis.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class ApplicationFormsPage extends UztisPage {

    static final Logger log = getLogger(lookup().lookupClass());

    public ApplicationFormsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "username")
    WebElement username;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(id = "kc-login")
    WebElement buttonLogin;

    //Menu
//    @FindBy(xpath = "//button/mat-icon[@role='img']")
//    WebElement MenuAppProcessing;
//    @FindBy(xpath = "//mat-expansion-panel[4]/mat-expansion-panel-header[@role='button']")
//    @FindBy(xpath = "//mat-expansion-panel-header[.//span[contains(text(), 'Paraiškų tvarkymas')]]")
//    @FindBy(xpath = "//mat-expansion-panel-header[.//span[contains(text(), 'Paraiškų tvarkymas')]]//mat-icon[text()='expand_more']")
//    @FindBy(xpath = "//span[@class='mat-content mat-content-hide-toggle']//span[contains(text(), 'Paraiškų tvarkymas')]")
    @FindBy(xpath = "//mat-expansion-panel-header[@id='mat-expansion-panel-header-4']")
    WebElement buttonMenuApplicationProcessing;

    @FindBy(xpath = "//a[@href='/application/description']")
    WebElement buttonMenuApplicationForms;
    @FindBy(xpath = "//h2[.='Paraiškų formos']")
    WebElement labelApplicationForms;
    @FindBy(xpath = "//a[@href='/application/description/create']")
    WebElement buttonCreateNewForm;

    //Application code
    @FindBy(xpath = "//common-text-input/input")
    WebElement inputDescriptionCode;

    //Submitted applications are assigned to
    @FindBy(xpath = "//input[@type='radio' and @value='REGIONAL']")
    WebElement radioButtonMunicipality;
    @FindBy(xpath = "//input[@type='radio' and @value='BRANCH']")
    WebElement radioButtonSection;

    //Budget
    @FindBy(xpath = "(//input[@type='radio' and @value='REGIONAL'])[2]")
    WebElement radioButtonBudgetByMunicipality;
    @FindBy(xpath = "(//input[@type='radio' and @value='GLOBAL'])")
    WebElement radioButtonBudgetByCountry;

    //Queue
    @FindBy(xpath = "(//input[@type='radio' and @value='true'])")
    WebElement radioButtonFinancedByQueue;
    @FindBy(xpath = "(//input[@type='radio' and @value='false'])")
    WebElement radioButtonFinancedWithoutQueue;

    //Add
    @FindBy(css = ".btn.btn--transparent.ms-2.primary-color > span")
    WebElement buttonAdd;

    //Dropdowns
    @FindBy(xpath = "//mat-label[contains(text(), 'Departamentas')]")
    WebElement dropdownButtonMunicipality;
    //    List<WebElement> dropdownButtonMunicipality;
    @FindBy(xpath = "//mat-label[contains(text(), 'Departamentas / skyrius')]")
    WebElement dropdownButtonSection;


    @FindBy(xpath = "//mat-label[contains(text(), 'I etapo vertinimo forma')]")
//    @FindBy(xpath = "//mat-option")
    WebElement dropdownButtonEvaluationOne;
    @FindBy(xpath = "//mat-label[contains(text(), 'II etapo vertinimo forma')]")
    WebElement dropdownButtonEvaluationTwo;
    @FindBy(xpath = "//mat-label[contains(text(), 'III etapo vertinimo forma')]")
    WebElement dropdownButtonEvaluationThree;

    @FindBy(xpath = "//mat-label[contains(text(), 'Nustatymas')]")
    List<WebElement> dropdownButtonSetting;
    @FindBy(xpath = "//mat-form-field[.//mat-label[contains(text(), 'Reikšmė')]]//input")
    List<WebElement> valueSettingValue;

    @FindBy(css = "mat-option")
//    List<WebElement> dropdownText;
    WebElement dropdownText;

    @FindBy(css = ".ms-2 > .btn.btn--transparent.secondary-color")
    WebElement buttonAddSetting;

    //    @FindBy(css = "common-button > .btn.btn--primary-light-dashed")
//    @FindBy(css = ".btn--primary-light-dashed")
    @FindBy(xpath = "//common-button[@btnclass='btn btn--primary-light-dashed']//button[@type='button']")
//    @FindBy(xpath = "//button[.//mat-icon[text()='add']]")
    WebElement buttonPlus;

    //Application name
    @FindBy(xpath = "//mat-form-field[.//mat-label[contains(text(), 'Paraiškos pavadinimas lietuvių kalba')]]//input")
    WebElement inputApplicationName;

    @FindBy(css = "common-button:nth-of-type(2) > .btn.btn--primary")
    WebElement buttonSave;

    @FindBy(id = "mat-button-toggle-0-button")
    WebElement buttonSaveForm;

     //-------------------------------------------------------------------

    @FindBy(css = "tbody > tr:nth-of-type(1)")
    WebElement lastDescription;

    @FindBy(xpath = "//td[@class='mat-mdc-cell mdc-data-table__cell cdk-cell cdk-column-CODE mat-column-CODE ng-star-inserted' and @role='cell']")
    WebElement lastDescriptionCode;

    @FindBy(xpath = "//input[@class='primary-control mat-mdc-form-field-input-control mat-mdc-form-field-input ng-untouched ng-pristine']")
    WebElement lastDescriptionCodeInput;

    //-------------------------------------------------------------------

    @FindBy(css = ".btn--primary")
    WebElement buttonEdit;

//    @FindBy(css = ".components .ng-star-inserted")
//    WebElement formDataArea;

    //-------------------------------------------------------------------

    @FindBy(xpath = "//span[@class='mdc-tab__text-label' and contains(text(), 'Projekto vykdymo eiga')]")
    WebElement tabProjectProgress;

    @FindBy(xpath = "(//mat-label[contains(text(), 'Dokumentas')])[3]")
    WebElement dropdownButtonDocument;

//--------------------
    @FindBy(css = "[class='w-100']")
    List<WebElement> successMessages;

    public List<String> getAllMessagesText() {
        return successMessages.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void verifySuccessMessage(String expectedMessage) {
        List<String> actualMessages = getAllMessagesText(); // Gauti visas žinutės tekstas

        boolean messageFound = false;
        for (String message : actualMessages) {
            if (message.equals(expectedMessage)) {
                messageFound = true; // Jei žinutė rasta, pažymime, kad ji yra
                break; // Baigiame paiešką, nes radome norimą žinutę
            }
        }

        if (messageFound) {
            log.debug("Verified success message: '{}'", expectedMessage); // Sėkmingas tikrinimas
        } else {
            log.error("Failed to verify the success message: '{}'. Available messages: {}", expectedMessage, actualMessages); // Klaidos pranešimas su visų žinučių sąrašu
        }
    }
//-------------------------

    public void clickTabProjectProgress() {
        tabProjectProgress.click();
    }

    public void selectValuesByListDocument(String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        log.info("Located the dropdown button.");
        wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonDocument)).click();

        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option"))); // Replace with actual locator

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
            log.warn("The value '{}' was not found among the options.", valueToSelect);
        }

        try {
            wait.until(ExpectedConditions.attributeToBe(dropdownButtonDocument, "aria-expanded", "false"));
        } catch (TimeoutException e) {
            log.error("Failed to close the dropdown with aria-expanded = 'false'.");
        }
    }


    public void login(String uname, String pword) {
        this.username.sendKeys(uname);
        this.password.sendKeys(pword);
        this.buttonLogin.click();
    }

        public void openMenuApplicationProcessing() {
        if (isMenuApplicationProcessingDisplayed()) {
            clickMenuApplicationProcessing();
        } else {
            throw new RuntimeException("MenuApplicationProcessing is not displayed!");
        }
    }

    public boolean isMenuApplicationProcessingDisplayed() {
        try {
            return buttonMenuApplicationProcessing.isDisplayed();
        } catch (Exception e) {
            log.error("MenuApplicationProcessing button is not displayed {}", e.getMessage());
            return false;
        }
    }

    public String getFormsLabelText() {
        return labelApplicationForms.getText();
    }

    //buttons
    public void clickMenuApplicationProcessing() {
        buttonMenuApplicationProcessing.click();
    }

    public void clickMenuApplicationForms() {
        buttonMenuApplicationForms.click();
    }

    public void clickCreateNewApplicationForm() {
        buttonCreateNewForm.click();
    }

    public void clickButtonAdd() {
        buttonAdd.click();
    }

    public void clickButtonPlus() {
        buttonPlus.click();
    }

    public void clickButtonAddSevenTimes() {
        IntStream.range(0, 7)
                .forEach(i -> {
                    buttonAddSetting.click();
                    // Optionally add a wait or any logic you want between clicks
                });
    }

    public void clickButtonSave() {
        buttonSave.click();
    }

    public void clickButtonEdit() {
        buttonEdit.click();
    }

    public boolean isButtonSaveFormDisplayed() {
        try {
            return this.buttonSaveForm.isDisplayed();
        } catch (Exception e) {
            log.error("Button SaveForm is not displayed", e);
            return false;
        }
    }

    public boolean isButtonEditDisplayed() {
        try {
            return buttonEdit.isDisplayed();
        } catch (NoSuchElementException e) {
            ApplicationFormsPage.log.error("Edit button is not displayed. Exception: {}", e.getMessage());
            return false;
        }
    }

    //enter text:
    public void enterApplicationCode(String code) {
        this.inputDescriptionCode.sendKeys(code);
    }

    public void enterApplicationName(String code) {
        this.inputApplicationName.sendKeys(code);
    }

    //radio buttons
    public void clickRadioButtonMunicipality() {
        if (!radioButtonMunicipality.isSelected()) {
            radioButtonMunicipality.click();
        }
    }

    public void clickRadioButtonSection() {
        if (!radioButtonSection.isSelected()) {
            radioButtonSection.click();
        }
    }

    public void clickRadioButtonBudgetByMunicipality() {
        if (!radioButtonBudgetByMunicipality.isSelected()) {
            radioButtonBudgetByMunicipality.click();
        }
    }

    public void clickRadioButtonBudgetByCountry() {
        if (!radioButtonBudgetByCountry.isSelected()) {
            radioButtonBudgetByCountry.click();  //
        }
    }

    public void clickRadioButtonFinancedByQueue() {
        if (!radioButtonFinancedByQueue.isSelected()) {
            radioButtonFinancedByQueue.click();
        }
    }

    public void clickRadioButtonFinancedWithoutQueue() {
        if (!radioButtonFinancedWithoutQueue.isSelected()) {
            radioButtonFinancedWithoutQueue.click();
        }
    }

    public void selectValueByListMunicipality(String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
        log.info("Located the dropdown button.");
        wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonMunicipality)).click();

        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option"))); // Replace with actual locator

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
            log.warn("The value '{}' was not found among the options.", valueToSelect);
        }

        try {
            wait.until(ExpectedConditions.attributeToBe(dropdownButtonMunicipality, "aria-expanded", "false"));
        } catch (TimeoutException e) {
            log.error("Failed to close the dropdown with aria-expanded = 'false'.");
        }
        } catch (Exception e) {
            log.error("Error selecting value from the list: {}", e.getMessage());
            throw new RuntimeException("Failed to select from the list", e);
        }
    }

    public void selectValueByListSection(String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        log.info("Located the dropdown button.");
        wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonSection)).click();

        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option"))); // Replace with actual locator

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
            log.warn("The value '{}' was not found among the options.", valueToSelect);
        }

        try {
            wait.until(ExpectedConditions.attributeToBe(dropdownButtonSection, "aria-expanded", "false"));
        } catch (TimeoutException e) {
            log.error("Failed to close the dropdown with aria-expanded = 'false'.");
        }
    }


    public void selectValuesByListEvaluationOne(String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        log.info("Located the dropdown button.");
        wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonEvaluationOne)).click();

        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option"))); // Replace with actual locator

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
            log.warn("The value '{}' was not found among the options.", valueToSelect);
        }

        try {
            wait.until(ExpectedConditions.attributeToBe(dropdownButtonEvaluationOne, "aria-expanded", "false"));
        } catch (TimeoutException e) {
            log.error("Failed to close the dropdown with aria-expanded = 'false'.");
        }
    }


    public void selectValuesByListEvaluationTwo(String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        log.info("Located the dropdown button.");
        wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonEvaluationTwo)).click();

        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option"))); // Replace with actual locator

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
            log.warn("The value '{}' was not found among the options.", valueToSelect);
        }

        try {
            wait.until(ExpectedConditions.attributeToBe(dropdownButtonEvaluationTwo, "aria-expanded", "false"));
        } catch (TimeoutException e) {
            log.error("Failed to close the dropdown with aria-expanded = 'false'.");
        }
    }

    public void selectValuesByListEvaluationThree(String valueToSelect) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        log.info("Located the dropdown button.");
        wait.until(ExpectedConditions.elementToBeClickable(dropdownButtonEvaluationThree)).click();

        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option"))); // Replace with actual locator

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
            log.warn("The value '{}' was not found among the options.", valueToSelect);
        }

        try {
            wait.until(ExpectedConditions.attributeToBe(dropdownButtonEvaluationThree, "aria-expanded", "false"));
        } catch (TimeoutException e) {
            log.error("Failed to close the dropdown with aria-expanded = 'false'.");
        }
    }

    public void selectValueAndEnterText() {
        // Bendras reikšmių sąrašas (dropdown pasirinkimai)
        List<String> valuesToSelect = List.of(
                "Maksimalus subsidijos dydis remontui nuo visos subsidijos, %",
                "Minimalaus atlyginimo dydis, Eur",
                "Minimalių mėnesinių atlyginimų skaičius, kuris turi neviršyti subsidijos",
                "Minimalių mėnesinių atlyginimų skaičius, kuris turi neviršyti subsidijos, aplinkos pritaikymui",
                "Nuosavos lėšos perkamai darbo priemonei, %",
                "Nuosavos lėšos perkamai darbo priemonei (kai steigiama sunkų neįgalumą turinčiam asmeniui), %",
                "Nuosavos lėšos perkamai darbo priemonei (kai steigiama vidutinį neįgalumą turinčiam asmeniui), %",
                "Pridedamas dienų skaičius prie paraiškų teikimo datos, tikrinant darbo vietos steigimo datą"
        );

        // Reikšmės, kurias reikia įrašyti į laukus
        List<String> inputValues = List.of("50", "924", "31", "4.7", "35", "20", "30", "60");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Užtikrinkite, kad dropdown mygtukų ir reikšmių įvedimo laukų sąrašai turi vienodą ilgį
        if (dropdownButtonSetting.size() != valuesToSelect.size() || valueSettingValue.size() != inputValues.size()) {
            throw new IllegalStateException("Dropdown buttons count and input values count do not match! " +
                    "Dropdown count: " + dropdownButtonSetting.size() +
                    ", Values count: " + valueSettingValue.size());
        }

        // Dropdown pasirinkimai
        for (int i = 0; i < dropdownButtonSetting.size(); i++) {
            log.info("Processing dropdown index: {}", i);

            // Paspauskite ant dropdown mygtuko
            WebElement dropdownButton = dropdownButtonSetting.get(i);
            wait.until(ExpectedConditions.elementToBeClickable(dropdownButton)).click();

            // Palaukite, kol parinktys atsiras
            List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("mat-option")));

            // Pasirinkite tinkamą reikšmę iš sąrašo
            boolean optionSelected = false;
            for (WebElement option : options) {
                if (option.getText().trim().equalsIgnoreCase(valuesToSelect.get(i))) {
                    wait.until(ExpectedConditions.elementToBeClickable(option)).click();
                    optionSelected = true;
                    break;
                }
            }

            if (!optionSelected) {
                log.warn("Dropdown value '{}' not found for index: {}", valuesToSelect.get(i), i);
            }

            // Palaukite, kol dropdown užsidarys, tikrinkite atributą "aria-expanded"
            try {
                wait.until(ExpectedConditions.attributeToBe(dropdownButton, "aria-expanded", "false"));
            } catch (TimeoutException e) {
                log.error("Timeout while waiting for dropdown to close at index: {} with aria-expanded = 'false'", i);
            }
        }

        // Įvedimo laukų užpildymas
        for (int i = 0; i < valueSettingValue.size(); i++) {
            log.info("Processing input field index: {}", i);

            WebElement inputField = valueSettingValue.get(i);

            // Palaukite, kol laukelis taps pasiekiamas
            try {
                wait.until(ExpectedConditions.visibilityOf(inputField));
                wait.until(ExpectedConditions.elementToBeClickable(inputField));
            } catch (TimeoutException e) {
                log.error("Timeout for input field at index: {} with value: {}", i, inputValues.get(i));
                continue;
            }

            // Įrašykite reikšmę į lauką
            inputField.clear(); // Išvalykite lauką
            inputField.sendKeys(inputValues.get(i)); // Įrašykite reikšmę
            log.info("Entered value '{}' into field index: {}", inputValues.get(i), i);
        }
    }

    //-------------------------------------------------------------------

//    public String getSuccessMessageText() {
//        return successMessage.getText();
//    }
public List<String> getSuccessMessagesText() {
    return successMessages.stream()
            .map(WebElement::getText) // Iš kiekvieno elemento paimame tekstą
            .collect(Collectors.toList());
}

    //-------------------------------------------------------------------

    public void clickLastDescription() {
        lastDescription.click();
    }

    public String getLastDescriptionCode() {
        return lastDescriptionCode.getText();
    }

    //-------------------------------------------------------------------

//    public void enterFormData(String json) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        // Palaukti, kol elementas bus matomas
//        WebElement element = wait.until(ExpectedConditions.visibilityOf(formDataArea));
//
//        // Skaityti failo turinį kaip tekstą
//        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data.csv")) {
//            if (inputStream == null) {
//                throw new IllegalArgumentException("Failas data.csv nerastas!");
//            }
//            String content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
//
//            // Įrašyti turinį į lauką
//            element.sendKeys(content);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Klaida skaitant failo turinį: " + e.getMessage());
//        }
//    }


}









