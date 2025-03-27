package lt.insoft.uztis.tests;

import lt.insoft.uztis.pages.ApplicationFormsPage;
import lt.insoft.uztis.pages.InvitationsPage;
import lt.insoft.uztis.tests.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;


public class InvitationsPageTest extends UztisPageTest {
    private static final Logger log = getLogger(lookup().lookupClass());

    protected InvitationsPage invitationsPage;
    protected ApplicationFormsPage applicationFormsPage;


    public void navigateToInvitationMenu() {
        applicationFormsPage.openMenuApplicationProcessing();
        log.debug("Opened 'Application Processing' menu.");
        invitationsPage.clickMenuInvitation();
        log.debug("Clicked on 'Invitation' menu.");
    }

    public void navigateToApplicationFormsMenu() {
        applicationFormsPage.openMenuApplicationProcessing();
        log.debug("Opened 'Application Processing' menu.");
        applicationFormsPage.clickMenuApplicationForms();
        log.debug("Clicked on 'Application Forms' menu.");
    }

   public  void getAndInsertApplicationCodeText() {
        String codeText = applicationFormsPage.getLastDescriptionCode();
        log.debug("Retrieved last description code text: '{}'.", codeText);

        invitationsPage.clickMenuInvitation();
        log.debug("Clicked on 'Invitation' menu.");

        invitationsPage.selectValueFromApplication(codeText);
        log.debug("Selected section by code: '{}'.", codeText);

        invitationsPage.clickButtonSearch();
        log.debug("Clicked 'Search'.");
    }

    @BeforeEach
    void setUpInvitationPage() {
        invitationsPage = new InvitationsPage(driver);
        applicationFormsPage = new ApplicationFormsPage(driver);
    }

    @Test
    void testNavigateToInvitationPageTest() {
        log.info("Starting test:'testNavigateToInvitationPageTest'");

        try {
            applicationFormsPage.login("test", "test");
            log.debug("User logged in with test credentials");

            navigateToInvitationMenu();
            log.debug("Navigating to invitation forms menu.");

            String actualLabel = invitationsPage.getInvitationLabelText();
            String expectedLabel = "Kvietimai teikti paraiškas";
            log.debug("Fetched label text: {}", actualLabel);

            Assertions.assertEquals(expectedLabel, actualLabel,
                    String.format("Expected label '%s' but found '%s'.", expectedLabel, actualLabel));
            log.info("'goToInvitationPageTest' completed. Label is: " + actualLabel);

            TestUtils.takeScreenshot(driver, "testNavigateToInvitationPageTest");

            log.info("Test 'testNavigateToInvitationPageTest' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testNavigateToInvitationPageTest' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testCreateNewVuiInvitationDraft() {
        log.info("Starting test:'testCreateNewVuiInvitationDraft'");

        try {
            applicationFormsPage.login("test", "test");
            log.debug("User logged in with test credentials");

            navigateToApplicationFormsMenu();
            log.debug("Navigating to application forms menu.");

            String codeText = applicationFormsPage.getLastDescriptionCode();
            log.debug("Retrieved last description code text: '{}'.", codeText);

            invitationsPage.clickMenuInvitation();
            log.debug("Clicked on 'Invitation' menu.");

            invitationsPage.clickCreateNewApplicationForm();
            log.debug("Clicked 'Create New Application Form'.");

            invitationsPage.selectValueFromApplication(codeText);
            log.debug("Selected section by code: '{}'.", codeText);

            String valueToSelect = "1";
            invitationsPage.selectValueByListInvitationNumber(valueToSelect);
            log.debug("Selected invitation number from the list.");

            invitationsPage.enterCurrentDate();
            log.debug("Entered current date.");

            String startHours = "00";
            String startMinutes = "00";
            invitationsPage.enterStartTime(startHours, startMinutes);
            log.debug("Entered start time: {}:{}.", startHours, startMinutes);

            invitationsPage.enterEndDate();
            log.debug("Entered end date.");

            String endHours = "11";
            String endMinutes = "11";
            invitationsPage.enterEndTime(endHours, endMinutes);
            log.debug("Entered end time: {}:{}.", endHours, endMinutes);

            invitationsPage.clickButtonSave();
            log.debug("Draft saved.");

            applicationFormsPage.verifySuccessMessage("Duomenys sėkmingai išsaugoti.");
            log.info("Verified success message");

//        TestUtils.takeScreenshot(driver, "testCreateNewVuiInvitationDraft");

            log.info("Test 'testCreateNewVuiInvitationDraft' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testCreateNewVuiInvitationDraft' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testEditAndPublishVuiInvitationDraft() {
        log.info("Starting test:'testEditAndPublishVuiInvitationDraft'");

        try {
            applicationFormsPage.login("test", "test");
            log.debug("User logged in with test credentials");

            navigateToApplicationFormsMenu();
            log.debug("Navigating to application forms menu.");

            getAndInsertApplicationCodeText();
            log.debug("Getting and inserting application code text.");

            invitationsPage.clickInvitationRowDraft();
            log.debug("Clicked 'InvitationDraft'.");

            invitationsPage.clickButtonEdit();
            log.debug("Clicked 'Edit'.");

            invitationsPage.enterPublicationEndDate();
            log.debug("Entered publication end date.");

            invitationsPage.clickButtonAdd();
            invitationsPage.clickButtonAdd();
            log.debug("Added additional values.");

            String valueToSelect1 = "Didžiausia galima paramos suma aplinkos darbo vietai, Eur";
            invitationsPage.selectDropdownAmountInformation1(valueToSelect1);
            log.debug("Selected the value: '{}'", valueToSelect1);

            String valueToSelect2 = "Didžiausia galima paramos suma, Eur";
            invitationsPage.selectDropdownAmountInformation2(valueToSelect2);
            log.debug("Selected the value: '{}'", valueToSelect2);

            String valueToSelect3 = "Didžiausia galima paramos suma vienai darbo vietai, Eur";
            invitationsPage.selectDropdownAmountInformation3(valueToSelect3);
            log.debug("Selected the value: '{}'", valueToSelect3);

            String value1 = "11000";
            invitationsPage.enterValueOne(value1);
            log.debug("Entered value: {}", value1);

            String value2 = "12000";
            invitationsPage.enterValueTwo(value2);
            log.debug("Entered value: {}", value2);

            String value3 = "13000";
            invitationsPage.enterValueThree(value3);
            log.debug("Entered value: {}", value3);

            invitationsPage.setCheckbox(invitationsPage.checkboxConfirm, true);
            log.debug("Checked 'Legal Entities' checkbox.");

            invitationsPage.setCheckbox(invitationsPage.checkboxLegalEntities, true);
            log.debug("Checked 'Legal Entities' checkbox.");

            invitationsPage.setCheckbox(invitationsPage.checkboxPersonsRegisteredWithEmploymentService, false); // Jei nereikia pažymėti false
            log.debug("Unchecked 'Persons Registered with Employment Service' checkbox.");

            invitationsPage.setCheckbox(invitationsPage.checkboxPersonsRegisteredWithEmploymentServiceAsEmployers, true);
            log.debug("Checked 'Persons Registered as Employers' checkbox.");

            String newGradeSecond = "5";
            invitationsPage.enterSecondEvaluationGrade(newGradeSecond);
            log.debug("Entered second evaluation grade: '{}'.", newGradeSecond);

            String newGradeThird = "6";
            invitationsPage.enterThirdEvaluationGrade(newGradeThird);
            log.debug("Entered third evaluation grade: '{}'.", newGradeThird);

            String newGradeSecondWeight = "0,45";
            invitationsPage.enterSecondEvaluationGradeWeight(newGradeSecondWeight);
            log.debug("Entered second evaluation grade weight: '{}'.", newGradeSecondWeight);

            String newGradeThirdWeight = "0,55";
            invitationsPage.enterThirdEvaluationGradeWeight(newGradeThirdWeight);
            log.debug("Entered third evaluation grade weight: '{}'.", newGradeThirdWeight);

            String newBudget = "2000";
            invitationsPage.enterBudget(newBudget);
            log.debug("Entered budget: '{}'.", newBudget);

            String newText1 = "Aprašo tekstas";
            invitationsPage.enterDescription(newText1);
            log.debug("Entered description text: '{}'.", newText1);

            String newText2 = "Pareiškėjų tekstas";
            invitationsPage.enterApplicants(newText2);
            log.debug("Entered applicants text: '{}'.", newText2);

            String newText3 = "Išlaidų tekstas";
            invitationsPage.enterExpenditures(newText3);
            log.debug("Entered expenditures text: '{}'.", newText3);

            String newText4 = "Teisės aktų tekstas";
            invitationsPage.enterActs(newText4);
            log.debug("Entered legislation text: '{}'.", newText4);

            String newText5 = "Mokymų tekstas";
            invitationsPage.enterEducation(newText5);
            log.debug("Entered education text: '{}'.", newText5);

            invitationsPage.uploadFile();
            log.debug("File uploaded.");

            invitationsPage.clickButtonPublish();
            log.debug("Clicked 'Publish'.");

            invitationsPage.clickButtonPublicationConfirmation();
            log.debug("Publication confirmed.");

            applicationFormsPage.verifySuccessMessage("Funkcija sėkmingai atlikta.");
            log.info("Verified success message");

//        TestUtils.takeScreenshot(driver, "testEditAndPublishVuiInvitationDraft");

            log.info("Test 'testEditAndPublishVuiInvitationDraft' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testEditAndPublishVuiInvitationDraft' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testInsertVui_PvkStatisticInformation() {
        log.info("Starting test:'testInsertVui_PvkStatisticInformation'");
        try {
            applicationFormsPage.login("test", "test");
            log.debug("User logged in with test credentials");

            navigateToApplicationFormsMenu();
            log.debug("Navigating to invitation forms menu.");

            getAndInsertApplicationCodeText();
            log.debug("Getting and inserting application code text.");

            invitationsPage.clickInvitationRowPublishing();
            log.debug("Clicked 'Publishing'.");

            invitationsPage.clickTabStatisticInformation();
            log.debug("Clicked 'Statistic Information' tab.");

            invitationsPage.clickButtonEdit();
            log.debug("Clicked 'Edit' button.");

            invitationsPage.enterCurrentDateStatistic();
            log.debug("Entered current date.");

            String valueUnemployment = "8.7";
            invitationsPage.enterUnemploymentValue(valueUnemployment);
            log.debug("Entered value: {}", valueUnemployment);

            String salary = "2237.9";
            invitationsPage.enterAverageSalary(salary);
            log.debug("Entered average salary: {}", salary);

            invitationsPage.uploadFileStatistic();
            log.debug("File uploaded.");

            invitationsPage.clickButtonSave();
            log.debug("Clicked 'Save' button.");

            applicationFormsPage.verifySuccessMessage("Funkcija sėkmingai atlikta.");
            log.info("Verified success message");

            log.info("Test 'testInsertVui_PvkStatisticInformation' completed successfully.");
        } catch (AssertionError |
                 Exception e) {
            log.error("Test 'testInsertVui_PvkStatisticInformation' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testStopPublicationVuiInvitation() {
        log.info("Starting test:'testStopPublicationVuiInvitation'");

        try {
            applicationFormsPage.login("test", "test");
            log.debug("User logged in with test credentials");

            navigateToApplicationFormsMenu();
            log.debug("Navigating to application forms menu.");

            getAndInsertApplicationCodeText();
            log.debug("Getting and inserting application code text.");

            invitationsPage.clickInvitationRowPublishing();
            log.debug("Clicked 'Publishing'.");

            invitationsPage.clickButtonAction();
            log.debug("Clicked 'Action'.");

            invitationsPage.clickButtonStopPublication();
            log.debug("Clicked 'Stop Publication'.");

            String newText5 = "Priežasties aprašas";
            invitationsPage.enterReason(newText5);
            log.debug("Entered expenditures text: '{}'.", newText5);

            invitationsPage.clickButtonStatusChangeConfirmation();
            log.debug("Clicked 'Stop Publication Confirmation'.");

            String actualStatus = invitationsPage.getInvitationStatusText();
            String expectedStatus = "Sustabdytas publikavimas";
            log.debug("Fetched label text: {}", actualStatus);

            Assertions.assertEquals(expectedStatus, actualStatus,
                    String.format("Expected label '%s' but found '%s'.", expectedStatus, actualStatus));
            log.info("'goToInvitationPageTest' completed. Label is: " + actualStatus);

//        TestUtils.takeScreenshot(driver, "testStopPublicationVuiInvitation");

            log.info("Test 'testStopPublicationVuiInvitation' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testEditAndPublishVuiInvitationDraft' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testArchiveVuiInvitation() {
        log.info("Starting test:'testArchiveVuiInvitation'");

        try {
            applicationFormsPage.login("test", "test");
            log.debug("User logged in with test credentials");

            navigateToApplicationFormsMenu();
            log.debug("Navigating to application forms menu.");

            getAndInsertApplicationCodeText();
            log.debug("Getting and inserting application code text.");

            invitationsPage.clickInvitationRowPublicationStopped();
            log.debug("Clicked 'Publication Stopped'.");

            invitationsPage.clickButtonAction();
            log.debug("Clicked 'Action'.");

            invitationsPage.clickButtonArchive();
            log.debug("Clicked 'Archive'.");

            String newText5 = "Priežasties aprašas";
            invitationsPage.enterReason(newText5);
            log.debug("Entered expenditures text: '{}'.", newText5);

            invitationsPage.clickButtonStatusChangeConfirmation();
            log.debug("Clicked 'Stop Publication Confirmation'.");

            String actualStatus = invitationsPage.getInvitationStatusTextArchive();
            String expectedStatus = "Archyvuotas";
            log.debug("Fetched label text: {}", actualStatus);

            Assertions.assertEquals(expectedStatus, actualStatus,
                    String.format("Expected label '%s' but found '%s'.", expectedStatus, actualStatus));
            log.info("'goToInvitationPageTest' completed. Label is: " + actualStatus);

//        TestUtils.takeScreenshot(driver, "testArchiveVuiInvitation");

            log.info("Test 'testArchiveVuiInvitation' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testArchiveVuiInvitation' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testDeleteVuiInvitation() {
        log.info("Starting test:'testDeleteVuiInvitation'");

        try {
            applicationFormsPage.login("test", "test");
            log.debug("User logged in with test credentials");

            navigateToApplicationFormsMenu();
            log.debug("Navigating to application forms menu.");

            getAndInsertApplicationCodeText();
            log.debug("Getting and inserting application code text.");

            invitationsPage.clickInvitationRowArchived();
            log.debug("Clicked 'Archived'.");

            invitationsPage.clickButtonAction();
            log.debug("Clicked 'Action'.");

            invitationsPage.clickButtonDelete();
            log.debug("Clicked 'Delete'.");

            invitationsPage.clickButtonStatusChangeConfirmation();
            log.debug("Clicked 'Stop Publication Confirmation'.");

            applicationFormsPage.verifySuccessMessage("Įrašas sėkmingai pašalintas.");
            log.info("Verified success message");

//        TestUtils.takeScreenshot(driver, "testDeleteVuiInvitation");

            log.info("Test 'testDeleteVuiInvitation' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testDeleteVuiInvitation' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testCreateNewDvpInvitation() {
        log.info("Starting test:'testCreateNewDvpInvitation'");

        try {
            applicationFormsPage.login("test", "test");
            log.debug("User logged in with test credentials");

            navigateToApplicationFormsMenu();
            log.debug("Navigating to application forms menu.");

            String codeText = applicationFormsPage.getLastDescriptionCode();
            log.debug("Retrieved last description code text: '{}'.", codeText);

            invitationsPage.clickMenuInvitation();
            log.debug("Clicked on 'Invitation' menu.");

            invitationsPage.clickCreateNewApplicationForm();
            log.debug("Clicked 'Create New Application Form'.");

            invitationsPage.selectValueFromApplication(codeText);
            log.debug("Selected section by code: '{}'.", codeText);

            String valueToSelect = "1";
            invitationsPage.selectValueByListInvitationNumber(valueToSelect);
            log.debug("Selected invitation number from the list.");

            invitationsPage.enterCurrentDate();
            log.debug("Entered current date.");

            String startHours = "00";
            String startMinutes = "00";
            invitationsPage.enterStartTime(startHours, startMinutes);
            log.debug("Entered start time: {}:{}.", startHours, startMinutes);

            invitationsPage.enterEndDate();
            log.debug("Entered end date.");

            String endHours = "11";
            String endMinutes = "11";
            invitationsPage.enterEndTime(endHours, endMinutes);
            log.debug("Entered end time: {}:{}.", endHours, endMinutes);

            invitationsPage.enterPublicationEndDate();
            log.debug("Entered publication end date.");

            invitationsPage.clickButtonAdd();
            invitationsPage.clickButtonAdd();
            log.debug("Added additional values.");

            String valueToSelect1 = "Didžiausia galima paramos suma aplinkos darbo vietai, Eur";
            invitationsPage.selectDropdownAmountInformation1(valueToSelect1);
            log.debug("Selected the value: '{}'", valueToSelect1);

            String valueToSelect2 = "Didžiausia galima paramos suma, Eur";
            invitationsPage.selectDropdownAmountInformation2(valueToSelect2);
            log.debug("Selected the value: '{}'", valueToSelect2);

            String valueToSelect3 = "Didžiausia galima paramos suma vienai darbo vietai, Eur";
            invitationsPage.selectDropdownAmountInformation3(valueToSelect3);
            log.debug("Selected the value: '{}'", valueToSelect3);

            String value1 = "11000";
            invitationsPage.enterValueOne(value1);
            log.debug("Entered value: {}", value1);

            String value2 = "12000";
            invitationsPage.enterValueTwo(value2);
            log.debug("Entered value: {}", value2);

            String value3 = "13000";
            invitationsPage.enterValueThree(value3);
            log.debug("Entered value: {}", value3);

            invitationsPage.setCheckbox(invitationsPage.checkboxLegalEntities, true);
            log.debug("Checked 'Legal Entities' checkbox.");

            invitationsPage.setCheckbox(invitationsPage.checkboxPersonsRegisteredWithEmploymentService, false); // Jei nereikia pažymėti false
            log.debug("Unchecked 'Persons Registered with Employment Service' checkbox.");

            invitationsPage.setCheckbox(invitationsPage.checkboxPersonsRegisteredWithEmploymentServiceAsEmployers, true);
            log.debug("Checked 'Persons Registered as Employers' checkbox.");

            String newBudget = "2000";
            invitationsPage.enterBudget(newBudget);
            log.debug("Entered budget: '{}'.", newBudget);

            String newText1 = "Aprašo tekstas";
            invitationsPage.enterDescription(newText1);
            log.debug("Entered description text: '{}'.", newText1);

            String newText2 = "Pareiškėjų tekstas";
            invitationsPage.enterApplicants(newText2);
            log.debug("Entered applicants text: '{}'.", newText2);

            String newText3 = "Išlaidų tekstas";
            invitationsPage.enterExpenditures(newText3);
            log.debug("Entered expenditures text: '{}'.", newText3);

            String newText4 = "Teisės aktų tekstas";
            invitationsPage.enterActs(newText4);
            log.debug("Entered legislation text: '{}'.", newText4);

            String newText5 = "Mokymų tekstas";
            invitationsPage.enterEducation(newText5);
            log.debug("Entered education text: '{}'.", newText5);

            invitationsPage.uploadFile();
            log.debug("File uploaded.");

            invitationsPage.clickButtonPublish();
            log.debug("Clicked 'Publish'.");

            invitationsPage.clickButtonPublicationConfirmation();
            log.debug("Publication confirmed.");

            applicationFormsPage.verifySuccessMessage("Funkcija sėkmingai atlikta.");
            log.info("Verified success message");

//        TestUtils.takeScreenshot(driver, "testCreateNewVuiInvitationDraft");

            log.info("Test 'testCreateNewDvpInvitation' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testCreateNewDvpInvitation' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testCreateNewPvkInvitation() {
        log.info("Starting test:'testCreateNewPvkInvitation'");

        try {
            applicationFormsPage.login("test", "test");
            log.debug("User logged in with test credentials");

            navigateToApplicationFormsMenu();
            log.debug("Navigating to application forms menu.");

            String codeText = applicationFormsPage.getLastDescriptionCode();
            log.debug("Retrieved last description code text: '{}'.", codeText);

            invitationsPage.clickMenuInvitation();
            log.debug("Clicked on 'Invitation' menu.");

            invitationsPage.clickCreateNewApplicationForm();
            log.debug("Clicked 'Create New Application Form'.");

            invitationsPage.selectValueFromApplication(codeText);
            log.debug("Selected section by code: '{}'.", codeText);

            String valueToSelect = "1";
            invitationsPage.selectValueByListInvitationNumber(valueToSelect);
            log.debug("Selected invitation number from the list.");

            invitationsPage.enterCurrentDate();
            log.debug("Entered current date.");

            String startHours = "00";
            String startMinutes = "00";
            invitationsPage.enterStartTime(startHours, startMinutes);
            log.debug("Entered start time: {}:{}.", startHours, startMinutes);

            invitationsPage.enterEndDate();
            log.debug("Entered end date.");

            String endHours = "11";
            String endMinutes = "11";
            invitationsPage.enterEndTime(endHours, endMinutes);
            log.debug("Entered end time: {}:{}.", endHours, endMinutes);

            invitationsPage.enterPublicationEndDate();
            log.debug("Entered publication end date.");

            invitationsPage.clickButtonAdd();
            invitationsPage.clickButtonAdd();
            log.debug("Added additional values.");

            String valueToSelect1 = "Didžiausia galima paramos suma aplinkos darbo vietai, Eur";
            invitationsPage.selectDropdownAmountInformation1(valueToSelect1);
            log.debug("Selected the value: '{}'", valueToSelect1);

            String valueToSelect2 = "Didžiausia galima paramos suma, Eur";
            invitationsPage.selectDropdownAmountInformation2(valueToSelect2);
            log.debug("Selected the value: '{}'", valueToSelect2);

            String valueToSelect3 = "Didžiausia galima paramos suma vienai darbo vietai, Eur";
            invitationsPage.selectDropdownAmountInformation3(valueToSelect3);
            log.debug("Selected the value: '{}'", valueToSelect3);

            String value1 = "11000";
            invitationsPage.enterValueOne(value1);
            log.debug("Entered value: {}", value1);

            String value2 = "12000";
            invitationsPage.enterValueTwo(value2);
            log.debug("Entered value: {}", value2);

            String value3 = "13000";
            invitationsPage.enterValueThree(value3);
            log.debug("Entered value: {}", value3);

            invitationsPage.setCheckbox(invitationsPage.checkboxLegalEntities, false);
            log.debug("Checked 'Legal Entities' checkbox.");

            invitationsPage.setCheckbox(invitationsPage.checkboxPersonsRegisteredWithEmploymentService, true); // Jei nereikia pažymėti false
            log.debug("Unchecked 'Persons Registered with Employment Service' checkbox.");

            invitationsPage.setCheckbox(invitationsPage.checkboxPersonsRegisteredWithEmploymentServiceAsEmployers, false);
            log.debug("Checked 'Persons Registered as Employers' checkbox.");

            String newGradeSecond = "5";
            invitationsPage.enterSecondEvaluationGrade(newGradeSecond);
            log.debug("Entered second evaluation grade: '{}'.", newGradeSecond);

            String newGradeThird = "6";
            invitationsPage.enterThirdEvaluationGrade(newGradeThird);
            log.debug("Entered third evaluation grade: '{}'.", newGradeThird);

            String newGradeSecondWeight = "0,45";
            invitationsPage.enterSecondEvaluationGradeWeight(newGradeSecondWeight);
            log.debug("Entered second evaluation grade weight: '{}'.", newGradeSecondWeight);

            String newGradeThirdWeight = "0,55";
            invitationsPage.enterThirdEvaluationGradeWeight(newGradeThirdWeight);
            log.debug("Entered third evaluation grade weight: '{}'.", newGradeThirdWeight);

            String newBudget1 = "2000";
            invitationsPage.enterBudgetKaunas(newBudget1);
            log.debug("Entered budget: '{}'.", newBudget1);

            String newBudget2 = "2000";
            invitationsPage.enterBudgetKlaipeda(newBudget2);
            log.debug("Entered budget: '{}'.", newBudget2);

            String newBudget3 = "2000";
            invitationsPage.enterBudgetPanevezys(newBudget3);
            log.debug("Entered budget: '{}'.", newBudget3);

            String newBudget4 = "2000";
            invitationsPage.enterBudgetSiauliai(newBudget4);
            log.debug("Entered budget: '{}'.", newBudget4);

            String newBudget5 = "2000";
            invitationsPage.enterBudgetVilnius(newBudget5);
            log.debug("Entered budget: '{}'.", newBudget5);

            String newText1 = "Aprašo tekstas";
            invitationsPage.enterDescription(newText1);
            log.debug("Entered description text: '{}'.", newText1);

            String newText2 = "Pareiškėjų tekstas";
            invitationsPage.enterApplicants(newText2);
            log.debug("Entered applicants text: '{}'.", newText2);

            String newText3 = "Išlaidų tekstas";
            invitationsPage.enterExpenditures(newText3);
            log.debug("Entered expenditures text: '{}'.", newText3);

            String newText4 = "Teisės aktų tekstas";
            invitationsPage.enterActs(newText4);
            log.debug("Entered legislation text: '{}'.", newText4);

            String newText5 = "Mokymų tekstas";
            invitationsPage.enterEducation(newText5);
            log.debug("Entered education text: '{}'.", newText5);

            invitationsPage.uploadFile();
            log.debug("File uploaded.");

            invitationsPage.clickButtonPublish();
            log.debug("Clicked 'Publish'.");

            invitationsPage.clickButtonPublicationConfirmation();
            log.debug("Publication confirmed.");

            applicationFormsPage.verifySuccessMessage("Funkcija sėkmingai atlikta.");
            log.info("Verified success message");

//        TestUtils.takeScreenshot(driver, "testCreateNewVuiInvitationDraft");

            log.info("Test 'testCreateNewPvkInvitation' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testCreateNewPvkInvitation' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }
//run test with statistic information

}
