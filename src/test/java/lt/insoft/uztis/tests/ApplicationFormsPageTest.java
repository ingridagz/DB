package lt.insoft.uztis.tests;


import lt.insoft.uztis.pages.ApplicationFormsPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.slf4j.LoggerFactory.getLogger;

public class ApplicationFormsPageTest extends UztisPageTest {
    private static final Logger log = getLogger(lookup().lookupClass());

    private ApplicationFormsPage applicationFormsPage;

    public void navigateToApplicationFormsMenu() {
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
            applicationFormsPage.login("test", "test");
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

//        TestUtils.takeScreenshot(driver, "testNavigateToAndReviewApplicationFormsPageTest");

            log.info("Test 'testNavigateToAndReviewApplicationFormsPageTest' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testNavigateToAndReviewApplicationFormsPageTest' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }


    @Test
    void testCreateNewVuiApplication() {
        log.info("Starting test:'testCreateNewVuiApplication'");

        try {
            applicationFormsPage.login("test", "test");
            log.debug("User logged in with test credentials");

            navigateToApplicationFormsMenu();

            applicationFormsPage.clickCreateNewApplicationForm();
            log.debug("Clicked to create a new application form.");

            String randomText = getRandomVuiCode();
            applicationFormsPage.enterApplicationCode(randomText);
            log.debug("Entered random application code: {}", randomText);

            //Pateiktos paraiškos priskiriamos:
            applicationFormsPage.clickRadioButtonSection();
            log.debug("Selected 'Section' radio button.");
            String valueToSelectS = "Skyrius 1";
            applicationFormsPage.selectValueByListSection(valueToSelectS);
            log.debug("Selected a value from the 'Section' dropdown: '{}'", valueToSelectS);

            //Biudžetas:
            applicationFormsPage.clickRadioButtonBudgetByMunicipality();
            log.debug("Selected 'Budget by Municipality' option.");

            //Finansavimo eilės sudarymas:
            applicationFormsPage.clickRadioButtonFinancedWithoutQueue();
            log.debug("Selected 'Financed Without Queue' option.");

            applicationFormsPage.clickButtonAdd();
            applicationFormsPage.clickButtonAdd();
            log.debug("Clicked 'Add' button.");

            String valueToSelect1 = "VUI paraiškos vertinimo forma - I etapas";
            applicationFormsPage.selectValuesByListEvaluationOne(valueToSelect1);
            log.debug("Selected a value from the 'EvaluationOne' dropdown: '{}'", valueToSelect1);

            String valueToSelect2 = "VUI paraiškos vertinimo forma - II etapas";
            applicationFormsPage.selectValuesByListEvaluationTwo(valueToSelect2);
            log.debug("Selected a value from the 'EvaluationTwon' dropdown: '{}'", valueToSelect2);

            String valueToSelect3 = "VUI paraiškos vertinimo forma - III etapas";
            applicationFormsPage.selectValuesByListEvaluationThree(valueToSelect3);
            log.debug("Selected a value from the 'EvaluationThree' dropdown: '{}'", valueToSelect3);

            //Nustatymai - nepamiršti aktyvuoti, kai testai bus paruošti.
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

            //ĮDĖTI DINAMINĘ FORMĄ RANKINIU BŪDU, nes:
            //neveikia:
//        applicationFormsPage.enterFormData("");

            applicationFormsPage.verifySuccessMessage("Duomenys sėkmingai išsaugoti.");
            log.info("Verified success message");

            boolean isSaveFormButtonVisible = applicationFormsPage.isButtonSaveFormDisplayed();
            Assertions.assertTrue(isSaveFormButtonVisible, "Save form button should be displayed.");
            log.info("Verified that the 'Save Form' button is displayed.");

//        TestUtils.takeScreenshot(driver, "testCreateNewVuiApplication");

            log.info("Test 'testCreateNewVuiApplication' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testCreateNewVuiApplication' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testEditAndSaveVuiApplication() {
        log.info("Starting test:'testEditAndSaveVuiApplication'");

        try {
            applicationFormsPage.login("test", "test");
            log.debug("User logged in with test credentials");

            navigateToApplicationFormsMenu();

            applicationFormsPage.clickLastDescription();
            log.debug("Selected the last application description.");

            applicationFormsPage.clickButtonEdit();
            log.debug("Clicked the 'Edit' button.");

            //Pateiktos paraiškos priskiriamos:
            applicationFormsPage.clickRadioButtonMunicipality();
            log.debug("Selected 'Municipality' radio button.");
            String valueToSelectM = "Kauno departamentas";
            applicationFormsPage.selectValueByListMunicipality(valueToSelectM);
            log.debug("Selected a value from the 'Municipality' dropdown: '{}'", valueToSelectM);

            //Biudžetas:
            applicationFormsPage.clickRadioButtonBudgetByCountry();
            log.debug("Selected 'Budget By Country' radio button.");

            //Finansavimo eilės sudarymas:
            applicationFormsPage.clickRadioButtonFinancedByQueue();
            log.debug("Selected 'Financed By Queue' radio button.");

            String newApplicationName = "_paredaguota";
            applicationFormsPage.enterApplicationName(newApplicationName);
            log.debug("Entered new application name: {}", newApplicationName);

            applicationFormsPage.clickButtonSave();
            log.debug("Clicked the 'Save' button.");

            applicationFormsPage.verifySuccessMessage("Duomenys sėkmingai išsaugoti.");
            log.info("Verified success message");

//        TestUtils.takeScreenshot(driver, "testEditAndSaveVuiApplication");

            log.info("Test 'testEditAndSaveVuiApplication' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testEditAndSaveVuiApplication' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testAddContractVUI() {
        log.info("Starting test:'testAddContractVUI'");

        try {
            applicationFormsPage.login("test", "test");
            log.debug("User logged in with test credentials");

            navigateToApplicationFormsMenu();

            applicationFormsPage.clickLastDescription();
            log.debug("Selected the last application description.");

            applicationFormsPage.clickTabProjectProgress();
            log.debug("Clicked 'Project Progress' tab.");

            applicationFormsPage.clickButtonEdit();
            log.debug("Clicked the 'Edit' button.");

            String document1 = "Vietinių užimtumo iniciatyvų projekto įgyvendinimo ir finansavimo sutartis";
            applicationFormsPage.selectValuesByListDocument(document1);
            log.debug("Selected a value from the 'Document' dropdown: '{}'", document1);

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

//-----------------------------------------------------

    @Test
    void testCreateNewDvpApplication() {
        log.info("Starting test:'testCreateNewDvpApplication'");

        try {
            applicationFormsPage.login("test", "test");
            log.debug("User logged in with test credentials");

            navigateToApplicationFormsMenu();

            applicationFormsPage.clickCreateNewApplicationForm();
            log.debug("Clicked to create a new application form.");

            String randomText = getRandomDvpCode();
            applicationFormsPage.enterApplicationCode(randomText);
            log.debug("Entered random application code: {}", randomText);

            //Pateiktos paraiškos priskiriamos:
            applicationFormsPage.clickRadioButtonMunicipality();
            log.debug("Selected 'Municipality' radio button.");
            String valueToSelectM = "Kauno departamentas";
            applicationFormsPage.selectValueByListMunicipality(valueToSelectM);
            log.debug("Selected a value from the 'Municipality' dropdown: '{}'", valueToSelectM);

            //Biudžetas:
            applicationFormsPage.clickRadioButtonBudgetByCountry();
            log.debug("Selected 'Budget By Country' radio button.");

            //Finansavimo eilės sudarymas:
            applicationFormsPage.clickRadioButtonFinancedWithoutQueue();
            log.debug("Selected 'Financed Without Queue' option.");

            String valueToSelect1 = "DVP paraiškos vertinimo forma - I etapas";
            applicationFormsPage.selectValuesByListEvaluationOne(valueToSelect1);
            log.debug("Selected a value from the 'EvaluationOne' dropdown: '{}'", valueToSelect1);

            //Nustatymai - nepamiršti aktyvuoti, kai testai bus paruošti.
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

            //ĮDĖTI DINAMINĘ FORMĄ RANKINIU BŪDU, nes:
            //neveikia:
//        applicationFormsPage.enterFormData("");

            applicationFormsPage.verifySuccessMessage("Duomenys sėkmingai išsaugoti.");
            log.info("Verified success message");

            boolean isSaveFormButtonVisible = applicationFormsPage.isButtonSaveFormDisplayed();
            Assertions.assertTrue(isSaveFormButtonVisible, "Save form button should be displayed.");
            log.info("Verified that the 'Save Form' button is displayed.");

//        TestUtils.takeScreenshot(driver, "testCreateNewVuiApplication");

            log.info("Test 'testCreateNewDvpApplication' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testCreateNewDvpApplication' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testAddContractDVP() {
        log.info("Starting test:'testAddContractDVP'");

        try {
            applicationFormsPage.login("test", "test");
            log.debug("User logged in with test credentials");

            navigateToApplicationFormsMenu();

            applicationFormsPage.clickLastDescription();
            log.debug("Selected the last application description.");

            applicationFormsPage.clickTabProjectProgress();
            log.debug("Clicked 'Project Progress' tab.");

            applicationFormsPage.clickButtonEdit();
            log.debug("Clicked the 'Edit' button.");

            String document1 = "Darbo vietų pritaikymo ir finansavimo sutartis";
            applicationFormsPage.selectValuesByListDocument(document1);
            log.debug("Selected a value from the 'Document' dropdown: '{}'", document1);

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
    void testCreateNewPvkApplication() {
        log.info("Starting test:'testCreateNewPvkApplication'");

        try {
            applicationFormsPage.login("test", "test");
            log.debug("User logged in with test credentials");

            navigateToApplicationFormsMenu();

            applicationFormsPage.clickCreateNewApplicationForm();
            log.debug("Clicked to create a new application form.");

            String randomText = getRandomPvkCode();
            applicationFormsPage.enterApplicationCode(randomText);
            log.debug("Entered random application code: {}", randomText);

            //Pateiktos paraiškos priskiriamos:
            applicationFormsPage.clickRadioButtonMunicipality();
            log.debug("Selected 'Municipality' radio button.");
            String valueToSelectM = "Kauno departamentas";
            applicationFormsPage.selectValueByListMunicipality(valueToSelectM);
            log.debug("Selected a value from the 'Municipality' dropdown: '{}'", valueToSelectM);

            //Biudžetas:
            applicationFormsPage.clickRadioButtonBudgetByMunicipality();
            log.debug("Selected 'Budget by Municipality' option.");

            //Finansavimo eilės sudarymas:
            applicationFormsPage.clickRadioButtonFinancedByQueue();
            log.debug("Selected 'Financed By Queue' radio button.");

            applicationFormsPage.clickButtonAdd();
            applicationFormsPage.clickButtonAdd();
            log.debug("Clicked 'Add' button.");

            String valueToSelect1 = "PVK paraiškos vertinimo forma - I etapas";
            applicationFormsPage.selectValuesByListEvaluationOne(valueToSelect1);
            log.debug("Selected a value from the 'EvaluationOne' dropdown: '{}'", valueToSelect1);

            String valueToSelect2 = "PVK paraiškos vertinimo forma - II etapas";
            applicationFormsPage.selectValuesByListEvaluationTwo(valueToSelect2);
            log.debug("Selected a value from the 'EvaluationTwon' dropdown: '{}'", valueToSelect2);

            String valueToSelect3 = "PVK paraiškos vertinimo forma - III etapas";
            applicationFormsPage.selectValuesByListEvaluationThree(valueToSelect3);
            log.debug("Selected a value from the 'EvaluationThree' dropdown: '{}'", valueToSelect3);

            //Nustatymai - nepamiršti aktyvuoti, kai testai bus paruošti.
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

            //ĮDĖTI DINAMINĘ FORMĄ RANKINIU BŪDU, nes:
            //neveikia:
////        applicationFormsPage.enterFormData("");

            applicationFormsPage.verifySuccessMessage("Duomenys sėkmingai išsaugoti.");
            log.info("Verified success message");

            boolean isSaveFormButtonVisible = applicationFormsPage.isButtonSaveFormDisplayed();
            Assertions.assertTrue(isSaveFormButtonVisible, "Save form button should be displayed.");
            log.info("Verified that the 'Save Form' button is displayed.");

//        TestUtils.takeScreenshot(driver, "testCreateNewVuiApplication");

            log.info("Test 'testCreateNewPvkApplication' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testCreateNewPvkApplication' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testAddContractPVK() {
        log.info("Starting test:'testAddContractPVK'");

        try {
            applicationFormsPage.login("test", "test");
            log.debug("User logged in with test credentials");

            navigateToApplicationFormsMenu();

            applicationFormsPage.clickLastDescription();
            log.debug("Selected the last application description.");

            applicationFormsPage.clickTabProjectProgress();
            log.debug("Clicked 'Project Progress' tab.");

            applicationFormsPage.clickButtonEdit();
            log.debug("Clicked the 'Edit' button.");

            String document1 = "Paramos verslui kurti sutartis";
            applicationFormsPage.selectValuesByListDocument(document1);
            log.debug("Selected a value from the 'Document' dropdown: '{}'", document1);

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