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
    void testRunProject() {
        log.info("Starting test:'testRunProject'");

        try {
            testNavigateToProjectsPage();

            projectPage.clickProjectRow();
            log.debug("Clicked on last project.");

            projectPage.clickButtonEditProject();
            log.debug("Clicked button 'Edit'.");

            projectPage.clickButtonEditConfirmProjectDocument();
            log.debug("Clicked button 'Confirm'.");

            applicationFormsPage.verifySuccessMessage("Duomenys sėkmingai išsaugoti.");
            log.info("Verified success message");

            log.info("Test 'testRunProject' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testRunProject' failed with error: {}", e.getMessage(), e);
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

            projectPage.clickButtonEditProject();
            log.debug("Clicked button 'Edit'.");

            projectPage.clickButtonEditConfirmProjectDocument();
            log.debug("Clicked button 'Confirm'.");

            applicationFormsPage.verifySuccessMessage("Duomenys sėkmingai išsaugoti.");
            log.info("Verified success message");

            projectPage.clickOrderDocument();
            log.debug("Clicked on order document.");

            projectPage.clickButtonSaveDocumentDraft();
            log.debug("Clicked button 'SaveDraft'.");

            projectPage.clickButtonEditProject();
            log.debug("Clicked button 'Edit'.");
//
            invitationsPage.uploadFile();
            log.debug("File uploaded.");

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

            //Teikti
            //Perziureti

            log.info("Test 'testFillProjectOrderDocument' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testFillProjectOrderDocument' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }





}
