package lt.insoft.uztis.tests;

import lt.insoft.uztis.pages.*;
import lt.insoft.uztis.tests.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;


public class ProjectPageTest extends UztisPageTest {
    private static final Logger log = getLogger(lookup().lookupClass());

    protected InvitationsPage invitationsPage;
    protected ApplicationFormsPage applicationFormsPage;
    protected SubmittedApplicationsPage submittedApplicationsPage;
    protected EvaluationsPage evaluationsPage;
    protected FundingPage fundingPage;
    protected ProjectPage projectPage;

    private void navigateToProjectMenu() {
        applicationFormsPage.clickMenuExpand();
        applicationFormsPage.clickMenuMore();
        applicationFormsPage.openMenuApplicationProcessing();
        log.debug("Opened 'Application Processing' menu.");
        projectPage.clickMenuProjects();
        log.debug("Clicked on 'Project' menu.");
    }

        @BeforeEach
        void setUpProjectPage() {
            invitationsPage = new InvitationsPage(driver);
            applicationFormsPage = new ApplicationFormsPage(driver);
            submittedApplicationsPage = new SubmittedApplicationsPage(driver);
            evaluationsPage = new EvaluationsPage(driver);
            fundingPage = new FundingPage(driver);
            projectPage= new ProjectPage(driver);
        }

    @Test
    void testNavigateToProjectsPage() {
        log.info("Starting test:'testNavigateToProjectsPage'");

        try {
            TestUtils.loginAllTests(driver);
            log.debug("User logged in with test credentials");

            navigateToProjectMenu();
            log.debug("Navigating to projects menu.");

            String actualLabel = projectPage.getProjectLabelText();
            String expectedLabel = "Projektai";
            log.debug("Fetched label text: {}", actualLabel);

            Assertions.assertEquals(expectedLabel, actualLabel,
                    String.format("Expected label '%s' but found '%s'", expectedLabel, actualLabel));
            log.info("Verified that the label text matches the expected value.");

            log.info("Test 'testNavigateToProjectsPage' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testNavigateToProjectsPage' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testRunProjectUntil_VFA() {
        log.info("Starting test:'testRunProjectUntil_VFA'");

        try {
            testNavigateToProjectsPage();

            projectPage.clickProjectRow();
            log.debug("Clicked on last project.");

            projectPage.clickButtonEditProject_Document();
            log.debug("Clicked button 'Edit'.");

            applicationFormsPage.selectValueFromDropdown(projectPage.getDropdownButtonOrderStatus(), "Vykdomas");
            stay();
            applicationFormsPage.selectValueFromDropdown(projectPage.getDropdownButtonAccountStatus(), "Vykdomas");
            stay();
            applicationFormsPage.selectValueFromDropdown(projectPage.getDropdownButtonKOT_Status(), "Vykdomas");
            stay();
            applicationFormsPage.selectValueFromDropdown(projectPage.getDropdownButtonContractStatus(), "Vykdomas");
            stay();
            applicationFormsPage.selectValueFromDropdown(projectPage.getDropdownButtonAdvancePaymentStatus(), "Vykdomas");
            stay();

            projectPage.clickButtonEditConfirmProjectDocument();
            log.debug("Clicked button 'Confirm'.");

            applicationFormsPage.verifySuccessMessage("Duomenys sėkmingai išsaugoti.");
            log.info("Verified success message");

            log.info("Test 'testRunProjectUntil_VFA' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testRunProjectUntil_VFA' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testFillProjectOrderDocument() {
        log.info("Starting test:'testFillProjectOrderDocument'");

        try {
            testNavigateToProjectsPage();

            projectPage.clickProjectRow();
            log.debug("Clicked on last project.");

            projectPage.clickOrderDocument();
            log.debug("Clicked on order document.");

            projectPage.clickButtonSaveDocumentDraft();
            log.debug("Clicked button 'SaveDraft'.");

            applicationFormsPage.verifySuccessMessage("Duomenys sėkmingai išsaugoti.");
            log.info("Verified success message");

            stay();

            projectPage.clickButtonEditProject_Document();
            log.debug("Clicked button 'Edit'.");

//            invitationsPage.uploadFile();
//            log.debug("File uploaded.");

            submittedApplicationsPage.uploadFiles();

            projectPage.clickButtonEditConfirmProjectDocument();
            log.debug("Clicked button 'Confirm'.");

            applicationFormsPage.verifySuccessMessage("Dokumentas sėkmingai patvirtintas.");
            log.info("Verified success message");

            projectPage.clickButtonSendForSignature();
            log.debug("Clicked button 'SendForSignature'.");

            projectPage.clickButtonSign();
            log.debug("Clicked button 'Sign'.");

            projectPage.clickButtonReview();
            log.debug("Clicked button 'Review'.");

            projectPage.clickButtonConfirmReview();
            log.debug("Clicked button 'ConfirmReview'.");

            //Perziureti is isorinio portalo

            log.info("Test 'testFillProjectOrderDocument' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testFillProjectOrderDocument' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testFillProjectAccountDocument() {
        log.info("Starting test:'testFillProjectAccountDocument'");

        try {
            testNavigateToProjectsPage();

            projectPage.clickProjectRow();
            log.debug("Clicked on last project.");

            projectPage.clickAccountDocument();
            log.debug("Clicked on account document.");

            projectPage.enterAccountValue("LT000000000000000000");

            Assertions.assertEquals("LT000000000000000000", projectPage.getInputAccount().getAttribute("value"));

            invitationsPage.uploadFile();
            log.debug("File uploaded.");

            projectPage.clickButtonSaveDocumentDraft();
            log.debug("Clicked button 'SaveDraft'.");

            applicationFormsPage.verifySuccessMessage("Duomenys sėkmingai išsaugoti.");
            log.info("Verified success message");

            projectPage.clickButtonEditProject_Document();
            log.debug("Clicked button 'Edit'.");

            projectPage.clickButtonEditConfirmProjectDocument();
            log.debug("Clicked button 'Confirm'.");

            applicationFormsPage.verifySuccessMessage("Dokumentas sėkmingai patvirtintas.");
            log.info("Verified success message");

            log.info("Test 'testFillProjectAccountDocument' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testFillProjectAccountDocument' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testFillProjectContractDocument() {
        log.info("Starting test:'testFillProjectContractDocument'");

        try {
            testNavigateToProjectsPage();

            projectPage.clickProjectRow();
            log.debug("Clicked on last project.");

            projectPage.clickContractVUI_Document();

            projectPage.enterPhoneValue("61200000");

            projectPage.clickButtonSaveDocumentDraft();
            log.debug("Clicked button 'SaveDraft'.");

            applicationFormsPage.verifySuccessMessage("Duomenys sėkmingai išsaugoti.");
            log.info("Verified success message");

            stay();

            projectPage.clickButtonEditProject_Document();
            log.debug("Clicked button 'Edit'.");

            projectPage.clickButtonEditConfirmProjectDocument();
            log.debug("Clicked button 'Confirm'.");

            applicationFormsPage.verifySuccessMessage("Dokumentas sėkmingai patvirtintas.");
            log.info("Verified success message");

            projectPage.clickButtonSendForSignature();
            log.debug("Clicked button 'SendForSignature'.");

            projectPage.clickButtonSign();
            log.debug("Clicked button 'Sign'.");

            log.info("Test 'testFillProjectContractDocument' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testFillProjectContractDocument' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testRunProjectFrom_VFA() {
        log.info("Starting test:'testRunProjectFrom_VFA'");

        try {
            testNavigateToProjectsPage();

            projectPage.clickProjectRow();
            log.debug("Clicked on last project.");

            projectPage.clickButtonEditProject_Document();
            log.debug("Clicked button 'Edit'.");

            applicationFormsPage.selectValueFromDropdown(projectPage.getDropdownButtonVFA_Status(), "Vykdomas");
            stay();
            applicationFormsPage.selectValueFromDropdown(projectPage.getDropdownButtonNotificationStatus(), "Vykdomas");
            stay();
            applicationFormsPage.selectValueFromDropdown(projectPage.getDropdownButtonActStatus(), "Vykdomas");
            stay();
            applicationFormsPage.selectValueFromDropdown(projectPage.getDropdownButtonFinalPaymentStatus(), "Vykdomas");
            stay();
            applicationFormsPage.selectValueFromDropdown(projectPage.getDropdownButtonCommitmentStatus(), "Vykdomas");
            stay();
            applicationFormsPage.selectValueFromDropdown(projectPage.getDropdownButtonAnnualReportStatus(), "Vykdomas");
            stay();

            projectPage.clickButtonEditConfirmProjectDocument();
            log.debug("Clicked button 'Confirm'.");

            applicationFormsPage.verifySuccessMessage("Duomenys sėkmingai išsaugoti.");
            log.info("Verified success message");

            log.info("Test 'testRunProjectFrom_VFA' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testRunProjectFrom_VFA' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testRunProjectAddDocuments() {
        log.info("Starting test:'testRunProjectAddDocuments'");

        try {
            testNavigateToProjectsPage();

            projectPage.clickProjectRow();
            log.debug("Clicked on last project.");

            projectPage.clickButtonEditProject_Document();
            log.debug("Clicked button 'Edit'.");

            projectPage.clickButtonAdd();
            log.debug("Clicked button 'Add'.");

            applicationFormsPage.selectValueFromDropdown(projectPage.getDropdownButtonDocumentInsurance(), "Draudimo įrodymas arba atsisakymas");

            projectPage.clickButtonEditConfirmProjectDocument();
            log.debug("Clicked button 'Confirm'.");

            applicationFormsPage.verifySuccessMessage("Duomenys sėkmingai išsaugoti.");
            log.info("Verified success message");

            log.info("Test 'testRunProjectAddDocuments' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testRunProjectAddDocuments' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testFillProjectVFA_Document() {
        log.info("Starting test:'testFillProjectContractDocument'");

        try {
            testNavigateToProjectsPage();

            projectPage.clickProjectRow();
            log.debug("Clicked on last project.");

            projectPage.clickContractVFA_Document();

            applicationFormsPage.selectValueFromDropdown(projectPage.getDropdownButtonWorkPlace(), "Darbo vieta Nr. 1");

            projectPage.enterAmountValue("100");

            projectPage.enterAmountValue2("200");

            submittedApplicationsPage.uploadFiles();

            projectPage.clickButtonSaveDocumentDraft();
            log.debug("Clicked button 'SaveDraft'.");

            applicationFormsPage.verifySuccessMessage("Duomenys sėkmingai išsaugoti.");
            log.info("Verified success message");

            stay();

            projectPage.clickButtonEditProject_Document();
            log.debug("Clicked button 'Edit'.");

            projectPage.clickButtonEditConfirmProjectDocument();
            log.debug("Clicked button 'Confirm'.");

            applicationFormsPage.verifySuccessMessage("Dokumentas sėkmingai patvirtintas.");
            log.info("Verified success message");

            log.info("Test 'testFillProjectContractDocument' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testFillProjectContractDocument' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }


    @Test
    void testFillProjectInsuranceDocument() {
        log.info("Starting test:'testFillProjectInsuranceDocument'");

        try {
            testNavigateToProjectsPage();

            projectPage.clickProjectRow();
            log.debug("Clicked on last project.");

            projectPage.clickInsuranceDocument();

            projectPage.checkCheckboxByLabel("Veiklos finansinė ataskaita");

            projectPage.clickButtonEditConfirmProjectDocument();
            log.debug("Clicked button 'Confirm'.");

            applicationFormsPage.selectValueFromDropdown(projectPage.getDropdownButtonInsuranceType(), "Civilinis draudimas");

            evaluationsPage.enterTodayDate();

            projectPage.enterRandomDate();

            invitationsPage.setCheckbox(invitationsPage.checkboxConfirm, true);
            log.debug("Checked checkbox.");

            applicationFormsPage.selectValueFromDropdown(projectPage.getDropdownButtonPaymentPeriod(), "Metai");

            submittedApplicationsPage.uploadFiles();

            projectPage.clickButtonSaveDocumentDraft();
            log.debug("Clicked button 'SaveDraft'.");

            applicationFormsPage.verifySuccessMessage("Duomenys sėkmingai išsaugoti.");
            log.info("Verified success message");

            stay();

            projectPage.clickButtonEditConfirmProjectDocument();
            log.debug("Clicked button 'Confirm'.");

            applicationFormsPage.verifySuccessMessage("Dokumentas sėkmingai patvirtintas.");
            log.info("Verified success message");

            log.info("Test 'testFillProjectInsuranceDocument' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testFillProjectInsuranceDocument' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }


    @Test
    void testFillProjectNotificationDocument() {
        log.info("Starting test:'testFillProjectNotificationDocument'");

        try {
            testNavigateToProjectsPage();

            projectPage.clickProjectRow();
            log.debug("Clicked on last project.");

            projectPage.clickNotificationDocument();

            projectPage.checkCheckboxByLabel("Veiklos finansinė ataskaita");

            projectPage.clickButtonEditConfirmProjectDocument();
            log.debug("Clicked button 'Confirm'.");

            applicationFormsPage.selectValueFromDropdown(projectPage.getDropdownButtonWorkPlaceType(), "Stacionari");

            projectPage.enterUserFirstName("Vardenis GR561");
            projectPage.enterUserLastName("Pavardenis GR799");
            projectPage.enterUserPersonCode("50010155510");

            submittedApplicationsPage.uploadFiles();

            projectPage.clickButtonSaveDocumentDraft();
            log.debug("Clicked button 'SaveDraft'.");

            applicationFormsPage.verifySuccessMessage("Duomenys sėkmingai išsaugoti.");
            log.info("Verified success message");

            stay();

            projectPage.clickButtonEditConfirmProjectDocument();
            log.debug("Clicked button 'Confirm'.");

            applicationFormsPage.verifySuccessMessage("Dokumentas sėkmingai patvirtintas.");
            log.info("Verified success message");

            log.info("Test 'testFillProjectNotificationDocument' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testFillProjectNotificationDocument' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

//aktas liko



}
