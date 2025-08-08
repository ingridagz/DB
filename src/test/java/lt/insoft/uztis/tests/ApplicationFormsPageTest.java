package lt.insoft.uztis.tests;


import lt.insoft.uztis.pages.ApplicationFormsPage;
import lt.insoft.uztis.tests.utils.TestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class ApplicationFormsPageTest extends UztisPageTest {
    private static final Logger log = getLogger(lookup().lookupClass());

    private ApplicationFormsPage applicationFormsPage;

    public void navigateToApplicationFormsMenu() {
        applicationFormsPage.clickMenuExpand();
        applicationFormsPage.clickMenuMore();
        applicationFormsPage.openMenuApplicationProcessing();
        log.debug("Opened 'Application Processing' menu.");
        applicationFormsPage.clickMenuApplicationForms();
        log.debug("Clicked on 'Application Forms' menu.");
    }

    public static String getRandomVuiCode() {
        // Generuojame 2 atsitiktinius skaitmenis
        String randomDigits = RandomStringUtils.randomNumeric(2);
        // Pridedame "..." prie atsitiktinių skaitmenų
        return "TST-VUI-" + randomDigits;
    }

    public static String getRandomDvpCode() {
        String randomDigits = RandomStringUtils.randomNumeric(2);
        return "TST-DVP-" + randomDigits;
    }

    public static String getRandomPvkCode() {
        String randomDigits = RandomStringUtils.randomNumeric(2);
        return "TST-PVK-" + randomDigits;
    }

    @BeforeEach
    void setUpApplicationFormsPage() {
        applicationFormsPage = new ApplicationFormsPage(driver);
        log.info("Setup complete. ApplicationFormsPage initialized.");
    }

    @Test
    void testNavigateToAndReviewApplicationFormsPageTest() {
        log.info("Starting test:'testNavigateToAndReviewApplicationFormsPageTest'");

        try {

            TestUtils.loginAllTests(driver);
            log.debug("User logged in with test credentials");

            navigateToApplicationFormsMenu();

            String actualLabel = applicationFormsPage.getFormsLabelText();
            String expectedLabel = "Paraiškų formos";
            log.debug("Fetched label text: {}", actualLabel);

            Assertions.assertEquals(expectedLabel, actualLabel,
                    String.format("Expected label '%s' but found '%s'", expectedLabel, actualLabel));
            log.info("Verified that the label text matches the expected value.");

            applicationFormsPage.clickLastDescription();
            log.debug("Selected the last application description.");

            boolean isEditFormButtonVisible = applicationFormsPage.isButtonEditDisplayed();
            Assertions.assertTrue(isEditFormButtonVisible, "Edit button should be displayed.");
            log.info("Verified that the 'Edit' button is displayed.");

            log.info("Test 'testNavigateToAndReviewApplicationFormsPageTest' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testNavigateToAndReviewApplicationFormsPageTest' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }


    @Test
    void testCreateNewVuiApplication() throws IOException, AWTException {
        log.info("Starting test:'testCreateNewVuiApplication'");

        try {

            TestUtils.loginAllTests(driver);
            log.debug("User logged in with test credentials");

            navigateToApplicationFormsMenu();

            applicationFormsPage.clickCreateNewApplicationForm();
            log.debug("Clicked to create a new application form.");

            String randomText = getRandomVuiCode();
            applicationFormsPage.enterApplicationCode(randomText);
            log.debug("Entered random application code: {}", randomText);

            //Biudžetas:
            applicationFormsPage.clickRadioButtonBudgetByCountry();
            log.debug("Selected 'Budget By Country' radio button.");

            //pabandymui
//            applicationFormsPage.clickRadioButtonBudgetByMunicipality();
//            log.debug("Selected 'Budget by Municipality' option.");

            //Finansavimo eilės sudarymas:
            applicationFormsPage.clickRadioButtonFinancedByQueue();
            log.debug("Selected 'Financed By Queue' radio button.");
//            applicationFormsPage.clickRadioButtonFinancedWithoutQueue();
//            log.debug("Selected 'Financed Without Queue' option.");

            applicationFormsPage.clickButtonAdd();
            applicationFormsPage.clickButtonAdd();
            log.debug("Clicked 'Add' button.");

            applicationFormsPage.selectValueFromDropdown(applicationFormsPage.getDropdownButtonEvaluationOne(), "VUI atitiktis URP");
            applicationFormsPage.selectValueFromDropdown(applicationFormsPage.getDropdownButtonEvaluationTwo(), "VUI atitiktis kokybės kriterijams");
            applicationFormsPage.selectValueFromDropdown(applicationFormsPage.getDropdownButtonEvaluationThree(), "VUI gynimo vertinimo forma");

            //Pateiktos paraiškos priskiriamos:

            applicationFormsPage.clickRadioButtonMunicipality();
            log.debug("Selected 'Municipality' radio button.");
//
            applicationFormsPage.selectValueFromDropdown(applicationFormsPage.getDropdownButtonMunicipality(), "Klaipėdos klientų aptarnavimo departamentas");
//
           // kaip reikalinga parinkti Dapartamentą/Skyrių

//            applicationFormsPage.clickRadioButtonMunicipalitySection();
//            log.debug("Selected 'Section' radio button.");
//
//            applicationFormsPage.selectValueFromDropdown(applicationFormsPage.getDropdownButtonMunicipalitySection(), "Klaipėdos klientų aptarnavimo departamentas / Klaipėdos 1-asis skyrius");

            applicationFormsPage.clickButtonPlus();
            log.debug("Clicked 'Plus' button.");

            applicationFormsPage.clickButtonAddSevenTimes();
            log.debug("Clicked 'Add' button seven times.");

            applicationFormsPage.selectValueAndEnterText();
            log.debug("Entered additional values and text.");

            String applicationName = "VUI paraiška";
            applicationFormsPage.enterApplicationName(applicationName);
            log.debug("Entered application name: {}", applicationName);

            applicationFormsPage.clickButtonSave();
            log.debug("Clicked the 'Save' button.");

            applicationFormsPage.verifySuccessMessage("Duomenys sėkmingai išsaugoti.");
            log.info("Verified success message");

            String json = new String(Files.readAllBytes(Paths.get("C:\\Users\\ingrida.zadorozniene\\Desktop\\Asm\\UZTIS\\UZTIS_tests\\src\\test\\resources\\VUI.json")));

            StringSelection selection = new StringSelection(json);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

            Robot robot = new Robot();
            robot.delay(500);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            boolean isSaveFormButtonVisible = applicationFormsPage.isButtonSaveFormDisplayed();
            Assertions.assertTrue(isSaveFormButtonVisible, "Save form button should be displayed.");
            log.info("Verified that the 'Save Form' button is displayed.");

            applicationFormsPage.clickButtonSaveDynamicForm();
            log.debug("Clicked 'SaveDynamicForm' button");

            applicationFormsPage.verifySuccessMessage("Duomenys sėkmingai išsaugoti.");
            log.info("Verified success message");


            log.info("Test 'testCreateNewVuiApplication' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testCreateNewVuiApplication' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testAddDocumentsVUI() {
        log.info("Starting test:'testAddContractVUI'");

        try {

            TestUtils.loginAllTests(driver);
            log.debug("User logged in with test credentials");

            navigateToApplicationFormsMenu();

            applicationFormsPage.clickLastDescription();
            log.debug("Selected the last application description.");

            applicationFormsPage.clickTabProjectProgress();
            log.debug("Clicked 'Project Progress' tab.");

            applicationFormsPage.clickButtonEdit();
            log.debug("Clicked the 'Edit' button.");

            applicationFormsPage.selectValueFromDropdown(applicationFormsPage.getDropdownButtonDocumentContract(), "Vietinių užimtumo iniciatyvų projekto įgyvendinimo ir finansavimo sutartis");
            applicationFormsPage.selectValueFromDropdown(applicationFormsPage.getDropdownButtonDocumentDecision1(), "Sprendimas dėl išmokos");
            applicationFormsPage.selectValueFromDropdown(applicationFormsPage.getDropdownButtonDocumentDecision2(), "Sprendimas dėl išmokos");

            applicationFormsPage.clickButtonSave();
            log.debug("Clicked the 'Save' button.");

            applicationFormsPage.verifySuccessMessage("Funkcija sėkmingai atlikta.");
            log.info("Verified success message");

            log.info("Test 'testAddContractVUI' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testAddContractVUI' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testCreateNewDvpApplication() throws IOException, AWTException {
        log.info("Starting test:'testCreateNewDvpApplication'");

        try {

            TestUtils.loginAllTests(driver);
            log.debug("User logged in with test credentials");

            navigateToApplicationFormsMenu();

            applicationFormsPage.clickCreateNewApplicationForm();
            log.debug("Clicked to create a new application form.");

            String randomText = getRandomDvpCode();
            applicationFormsPage.enterApplicationCode(randomText);
            log.debug("Entered random application code: {}", randomText);

            //Biudžetas:
            applicationFormsPage.clickRadioButtonBudgetByCountry();
            log.debug("Selected 'Budget By Country' radio button.");

            //Finansavimo eilės sudarymas:
            applicationFormsPage.clickRadioButtonFinancedWithoutQueue();
            log.debug("Selected 'Financed Without Queue' option.");

            applicationFormsPage.selectValueFromDropdown(applicationFormsPage.getDropdownButtonEvaluationOne(), "DVP atitiktis URP");

            //Pateiktos paraiškos priskiriamos:
            applicationFormsPage.clickRadioButtonMunicipality();
            log.debug("Selected 'Municipality' radio button.");

            applicationFormsPage.selectValueFromDropdown(applicationFormsPage.getDropdownButtonMunicipality(), "Klaipėdos klientų aptarnavimo departamentas");

            applicationFormsPage.clickButtonPlus();
            log.debug("Clicked 'Plus' button.");

            applicationFormsPage.clickButtonAddSevenTimes();
            log.debug("Clicked 'Add' button seven times.");

            applicationFormsPage.selectValueAndEnterText();
            log.debug("Entered additional values and text.");

            String applicationName = "DVP paraiška";
            applicationFormsPage.enterApplicationName(applicationName);
            log.debug("Entered application name: {}", applicationName);

            applicationFormsPage.clickButtonSave();
            log.debug("Clicked the 'Save' button.");

            applicationFormsPage.verifySuccessMessage("Duomenys sėkmingai išsaugoti.");
            log.info("Verified success message");

            String json = new String(Files.readAllBytes(Paths.get("C:\\Users\\ingrida.zadorozniene\\Desktop\\Asm\\UZTIS\\UZTIS_tests\\src\\test\\resources\\DVP.json")));

            StringSelection selection = new StringSelection(json);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

            Robot robot = new Robot();
            robot.delay(500);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            boolean isSaveFormButtonVisible = applicationFormsPage.isButtonSaveFormDisplayed();
            Assertions.assertTrue(isSaveFormButtonVisible, "Save form button should be displayed.");
            log.info("Verified that the 'Save Form' button is displayed.");

            applicationFormsPage.clickButtonSaveDynamicForm();
            log.debug("Clicked 'SaveDynamicForm' button");

            applicationFormsPage.verifySuccessMessage("Duomenys sėkmingai išsaugoti.");
            log.info("Verified success message");

            log.info("Test 'testCreateNewDvpApplication' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testCreateNewDvpApplication' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testAddDocumentsDVP() {
        log.info("Starting test:'testAddContractDVP'");

        try {

            TestUtils.loginAllTests(driver);
            log.debug("User logged in with test credentials");

            navigateToApplicationFormsMenu();

            applicationFormsPage.clickLastDescription();
            log.debug("Selected the last application description.");

            applicationFormsPage.clickTabProjectProgress();
            log.debug("Clicked 'Project Progress' tab.");

            applicationFormsPage.clickButtonEdit();
            log.debug("Clicked the 'Edit' button.");

            applicationFormsPage.selectValueFromDropdown(applicationFormsPage.getDropdownButtonDocumentContract(), "Darbo vietų pritaikymo ir finansavimo sutartis");
            applicationFormsPage.selectValueFromDropdown(applicationFormsPage.getDropdownButtonDocumentDecision1(), "Sprendimas dėl išmokos");
            applicationFormsPage.selectValueFromDropdown(applicationFormsPage.getDropdownButtonDocumentDecision2(), "Sprendimas dėl išmokos");

            applicationFormsPage.clickButtonSave();
            log.debug("Clicked the 'Save' button.");

            applicationFormsPage.verifySuccessMessage("Funkcija sėkmingai atlikta.");
            log.info("Verified success message");

            log.info("Test 'testAddContractDVP' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testAddContractDVP' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }


    @Test
    void testCreateNewPvkApplication() throws IOException, AWTException {
        log.info("Starting test:'testCreateNewPvkApplication'");

        try {

            TestUtils.loginAllTests(driver);
            log.debug("User logged in with test credentials");

            navigateToApplicationFormsMenu();

            applicationFormsPage.clickCreateNewApplicationForm();
            log.debug("Clicked to create a new application form.");

            String randomText = getRandomPvkCode();
            applicationFormsPage.enterApplicationCode(randomText);
            log.debug("Entered random application code: {}", randomText);

            //Biudžetas:
            applicationFormsPage.clickRadioButtonBudgetByMunicipality();
            log.debug("Selected 'Budget by Municipality' option.");

            //Finansavimo eilės sudarymas:
            applicationFormsPage.clickRadioButtonFinancedByQueue();
            log.debug("Selected 'Financed By Queue' radio button.");

            applicationFormsPage.clickButtonAdd();
            applicationFormsPage.clickButtonAdd();
            log.debug("Clicked 'Add' button.");

            applicationFormsPage.selectValueFromDropdown(applicationFormsPage.getDropdownButtonEvaluationOne(), "PVK atitiktis URP");
            applicationFormsPage.selectValueFromDropdown(applicationFormsPage.getDropdownButtonEvaluationTwo(), "PVK atitiktis kokybės kriterijams");
            applicationFormsPage.selectValueFromDropdown(applicationFormsPage.getDropdownButtonEvaluationThree(), "PVK gynimo vertinimo forma");

//            Pateiktos paraiškos priskiriamos:
            applicationFormsPage.clickRadioButtonMunicipality();
            log.debug("Selected 'Municipality' radio button.");

            applicationFormsPage.selectValueFromDropdown(applicationFormsPage.getDropdownButtonMunicipality(), "Klaipėdos klientų aptarnavimo departamentas");

            applicationFormsPage.clickButtonPlus();
            log.debug("Clicked 'Plus' button.");

            applicationFormsPage.clickButtonAddSevenTimes();
            log.debug("Clicked 'Add' button seven times.");

            applicationFormsPage.selectValueAndEnterText();
            log.debug("Entered additional values and text.");

            String applicationName = "PVK paraiška";
            applicationFormsPage.enterApplicationName(applicationName);
            log.debug("Entered application name: {}", applicationName);

            applicationFormsPage.clickButtonSave();
            log.debug("Clicked the 'Save' button.");

            applicationFormsPage.verifySuccessMessage("Duomenys sėkmingai išsaugoti.");
            log.info("Verified success message");

            String json = new String(Files.readAllBytes(Paths.get("C:\\Users\\ingrida.zadorozniene\\Desktop\\Asm\\UZTIS\\UZTIS_tests\\src\\test\\resources\\PVK.json")));

            StringSelection selection = new StringSelection(json);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

            Robot robot = new Robot();
            robot.delay(500);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            boolean isSaveFormButtonVisible = applicationFormsPage.isButtonSaveFormDisplayed();
            Assertions.assertTrue(isSaveFormButtonVisible, "Save form button should be displayed.");
            log.info("Verified that the 'Save Form' button is displayed.");

            applicationFormsPage.clickButtonSaveDynamicForm();
            log.debug("Clicked 'SaveDynamicForm' button");

            applicationFormsPage.verifySuccessMessage("Duomenys sėkmingai išsaugoti.");
            log.info("Verified success message");

            log.info("Test 'testCreateNewPvkApplication' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testCreateNewPvkApplication' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testAddDocumentsPVK() {
        log.info("Starting test:'testAddContractPVK'");

        try {

            TestUtils.loginAllTests(driver);
            log.debug("User logged in with test credentials");

            navigateToApplicationFormsMenu();

            applicationFormsPage.clickLastDescription();
            log.debug("Selected the last application description.");

            applicationFormsPage.clickTabProjectProgress();
            log.debug("Clicked 'Project Progress' tab.");

            applicationFormsPage.clickButtonEdit();
            log.debug("Clicked the 'Edit' button.");

            applicationFormsPage.selectValueFromDropdown(applicationFormsPage.getDropdownButtonDocumentContract(), "Paramos verslui kurti sutartis");
            applicationFormsPage.selectValueFromDropdown(applicationFormsPage.getDropdownButtonDocumentDecision1(), "Sprendimas dėl išmokos");
            applicationFormsPage.selectValueFromDropdown(applicationFormsPage.getDropdownButtonDocumentDecision2(), "Sprendimas dėl išmokos");

            applicationFormsPage.clickButtonSave();
            log.debug("Clicked the 'Save' button.");

            applicationFormsPage.verifySuccessMessage("Funkcija sėkmingai atlikta.");
            log.info("Verified success message");

            log.info("Test 'testAddContractPVK' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testAddContractPVK' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }


}