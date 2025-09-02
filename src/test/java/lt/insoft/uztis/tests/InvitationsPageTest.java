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
        applicationFormsPage.clickMenuExpand();
        applicationFormsPage.clickMenuMore();
        applicationFormsPage.openMenuApplicationProcessing();
        log.debug("Opened 'Application Processing' menu.");
        invitationsPage.clickMenuInvitation();
        log.debug("Clicked on 'Invitation' menu.");
    }

    public void navigateToApplicationFormsMenu() {
        applicationFormsPage.clickMenuExpand();
        applicationFormsPage.clickMenuMore();
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
            TestUtils.loginAllTests(driver);
            log.debug("User logged in with test credentials");
//            applicationFormsPage.login("evaluation_chief", "test");
//            log.debug("User logged in with test credentials");

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
            TestUtils.loginAllTests(driver);
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
    void testEditAndPublishVuiInvitation() {
        log.info("Starting test:'testEditAndPublishVuiInvitationDraft'");

        try {
            TestUtils.loginAllTests(driver);
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

            String option1 = "Didžiausia galima paramos suma aplinkos darbo vietai, Eur";
            String option2 = "Didžiausia galima paramos suma, Eur";
            String option3 = "Didžiausia galima paramos suma vienai darbo vietai, Eur";

            invitationsPage.selectAmountInfo1(option1);
            invitationsPage.selectAmountInfo2(option2);
            invitationsPage.selectAmountInfo3(option3);

            String value1 = "11000";
            String value2 = "12000";
            String value3 = "13000";

            invitationsPage.enterAmountValueOne(value1);
            invitationsPage.enterAmountValueTwo(value2);
            invitationsPage.enterAmountValueThree(value3);

            invitationsPage.setCheckbox(invitationsPage.checkboxConfirm, true);
            log.debug("Checked 'Legal Entities' checkbox.");

            invitationsPage.setCheckbox(invitationsPage.checkboxLegalEntities, true);
            log.debug("Checked 'Legal Entities' checkbox.");

            invitationsPage.setCheckbox(invitationsPage.checkboxPersonsRegisteredWithEmploymentService, false); // Jei nereikia pažymėti false
            log.debug("Unchecked 'Persons Registered with Employment Service' checkbox.");

            invitationsPage.setCheckbox(invitationsPage.checkboxPersonsRegisteredWithEmploymentServiceAsEmployers, true);
            log.debug("Checked 'Persons Registered as Employers' checkbox.");

            invitationsPage.enterEvaluationValue("secondGrade", "5");
            invitationsPage.enterEvaluationValue("thirdGrade", "6");
            invitationsPage.enterEvaluationValue("secondWeight", "0.45");
            invitationsPage.enterEvaluationValue("thirdWeight", "0.55");
            invitationsPage.enterEvaluationValue("budget", "2000");

            String label = "Finansavimo straipsnis";
            String article = "KarjerON, ESF lėšos";
            invitationsPage.selectDropdownLabel(label, article);
            log.debug("Selected the article: '{}'", article);

            String descriptionText = "Aprašo tekstas";
            invitationsPage.enterText(InvitationsPage.Field.DESCRIPTION, descriptionText);
            log.debug("Entered description text: '{}'.", descriptionText);

            String applicantsText = "Pareiškėjų tekstas";
            invitationsPage.enterText(InvitationsPage.Field.APPLICANTS, applicantsText);
            log.debug("Entered applicants text: '{}'.", applicantsText);

            String expendituresText = "Išlaidų tekstas";
            invitationsPage.enterText(InvitationsPage.Field.EXPENDITURES, expendituresText);
            log.debug("Entered expenditures text: '{}'.", expendituresText);

            String actsText = "Teisės aktų tekstas";
            invitationsPage.enterText(InvitationsPage.Field.ACTS, actsText);
            log.debug("Entered legislation text: '{}'.", actsText);

            String educationText = "Mokymų tekstas";
            invitationsPage.enterText(InvitationsPage.Field.EDUCATION, educationText);
            log.debug("Entered education text: '{}'.", educationText);

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
            TestUtils.loginAllTests(driver);
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
            TestUtils.loginAllTests(driver);
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
            TestUtils.loginAllTests(driver);
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
            TestUtils.loginAllTests(driver);
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
            TestUtils.loginAllTests(driver);
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

            String valueToSelect = "4";
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

            String option1 = "Didžiausia galima paramos suma aplinkos darbo vietai, Eur";
            String option2 = "Didžiausia galima paramos suma, Eur";
            String option3 = "Didžiausia galima paramos suma vienai darbo vietai, Eur";

            invitationsPage.selectAmountInfo1(option1);
            invitationsPage.selectAmountInfo2(option2);
            invitationsPage.selectAmountInfo3(option3);

            String value1 = "11000";
            String value2 = "12000";
            String value3 = "13000";

            invitationsPage.enterAmountValueOne(value1);
            invitationsPage.enterAmountValueTwo(value2);
            invitationsPage.enterAmountValueThree(value3);

            invitationsPage.setCheckbox(invitationsPage.checkboxConfirm, true);
            log.debug("Checked 'Legal Entities' checkbox.");

            invitationsPage.setCheckbox(invitationsPage.checkboxLegalEntities, true);
            log.debug("Checked 'Legal Entities' checkbox.");

            invitationsPage.setCheckbox(invitationsPage.checkboxPersonsRegisteredWithEmploymentService, false); // Jei nereikia pažymėti false
            log.debug("Unchecked 'Persons Registered with Employment Service' checkbox.");

            invitationsPage.setCheckbox(invitationsPage.checkboxPersonsRegisteredWithEmploymentServiceAsEmployers, true);
            log.debug("Checked 'Persons Registered as Employers' checkbox.");

            invitationsPage.enterEvaluationValue("budget", "2000");

            String label = "Finansavimo straipsnis";
            String article = "KarjerON, ESF lėšos";
            invitationsPage.selectDropdownLabel(label, article);
            log.debug("Selected the article: '{}'", article);

            String descriptionText = "Aprašo tekstas";
            invitationsPage.enterText(InvitationsPage.Field.DESCRIPTION, descriptionText);
            log.debug("Entered description text: '{}'.", descriptionText);

            String applicantsText = "Pareiškėjų tekstas";
            invitationsPage.enterText(InvitationsPage.Field.APPLICANTS, applicantsText);
            log.debug("Entered applicants text: '{}'.", applicantsText);

            String expendituresText = "Išlaidų tekstas";
            invitationsPage.enterText(InvitationsPage.Field.EXPENDITURES, expendituresText);
            log.debug("Entered expenditures text: '{}'.", expendituresText);

            String actsText = "Teisės aktų tekstas";
            invitationsPage.enterText(InvitationsPage.Field.ACTS, actsText);
            log.debug("Entered legislation text: '{}'.", actsText);

            String educationText = "Mokymų tekstas";
            invitationsPage.enterText(InvitationsPage.Field.EDUCATION, educationText);
            log.debug("Entered education text: '{}'.", educationText);

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
            TestUtils.loginAllTests(driver);
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

            String option1 = "Didžiausia galima paramos suma aplinkos darbo vietai, Eur";
            String option2 = "Didžiausia galima paramos suma, Eur";
            String option3 = "Didžiausia galima paramos suma vienai darbo vietai, Eur";

            invitationsPage.selectAmountInfo1(option1);
            invitationsPage.selectAmountInfo2(option2);
            invitationsPage.selectAmountInfo3(option3);

            String value1 = "11000";
            String value2 = "12000";
            String value3 = "13000";

            invitationsPage.enterAmountValueOne(value1);
            invitationsPage.enterAmountValueTwo(value2);
            invitationsPage.enterAmountValueThree(value3);

            invitationsPage.setCheckbox(invitationsPage.checkboxConfirm, true);
            log.debug("Checked 'Legal Entities' checkbox.");

            invitationsPage.setCheckbox(invitationsPage.checkboxLegalEntities, false);
            log.debug("Checked 'Legal Entities' checkbox.");

            invitationsPage.setCheckbox(invitationsPage.checkboxPersonsRegisteredWithEmploymentService, true); // Jei nereikia pažymėti false
            log.debug("Unchecked 'Persons Registered with Employment Service' checkbox.");

            invitationsPage.setCheckbox(invitationsPage.checkboxPersonsRegisteredWithEmploymentServiceAsEmployers, false);
            log.debug("Checked 'Persons Registered as Employers' checkbox.");

            invitationsPage.enterEvaluationValue("secondGrade", "5");
            invitationsPage.enterEvaluationValue("thirdGrade", "6");
            invitationsPage.enterEvaluationValue("secondWeight", "0.45");
            invitationsPage.enterEvaluationValue("thirdWeight", "0.55");

            String label = "Finansavimo straipsnis";
            String article = "KarjerON, ESF lėšos";
            invitationsPage.selectDropdownLabel(label, article);
            log.debug("Selected the article: '{}'", article);

            log.info("Įvedame biudžetą miestui KAUNAS: 10000");
            invitationsPage.enterBudget(InvitationsPage.City.KAUNAS, "2000");

            log.info("Įvedame biudžetą miestui KLAIPEDA: 8000");
            invitationsPage.enterBudget(InvitationsPage.City.KLAIPEDA, "2000");

            log.info("Įvedame biudžetą miestui PANEVEZYS: 5000");
            invitationsPage.enterBudget(InvitationsPage.City.PANEVEZYS, "2000");

            log.info("Įvedame biudžetą miestui SIAULIAI: 6000");
            invitationsPage.enterBudget(InvitationsPage.City.SIAULIAI, "2000");

            log.info("Įvedame biudžetą miestui VILNIUS: 12000");
            invitationsPage.enterBudget(InvitationsPage.City.VILNIUS, "2000");

            String descriptionText = "Aprašo tekstas";
            invitationsPage.enterText(InvitationsPage.Field.DESCRIPTION, descriptionText);
            log.debug("Entered description text: '{}'.", descriptionText);

            String applicantsText = "Pareiškėjų tekstas";
            invitationsPage.enterText(InvitationsPage.Field.APPLICANTS, applicantsText);
            log.debug("Entered applicants text: '{}'.", applicantsText);

            String expendituresText = "Išlaidų tekstas";
            invitationsPage.enterText(InvitationsPage.Field.EXPENDITURES, expendituresText);
            log.debug("Entered expenditures text: '{}'.", expendituresText);

            String actsText = "Teisės aktų tekstas";
            invitationsPage.enterText(InvitationsPage.Field.ACTS, actsText);
            log.debug("Entered legislation text: '{}'.", actsText);

            String educationText = "Mokymų tekstas";
            invitationsPage.enterText(InvitationsPage.Field.EDUCATION, educationText);
            log.debug("Entered education text: '{}'.", educationText);

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

//run test testInsertVui_PvkStatisticInformation

}
