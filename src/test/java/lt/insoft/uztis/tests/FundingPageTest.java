package lt.insoft.uztis.tests;

import lt.insoft.uztis.pages.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class FundingPageTest extends UztisPageTest{
    private static final Logger log = getLogger(lookup().lookupClass());

    protected InvitationsPage invitationsPage;
    protected ApplicationFormsPage applicationFormsPage;
    protected SubmittedApplicationsPage submittedApplicationsPage;
    protected EvaluationsPage evaluationsPage;
    protected FundingPage fundingPage;

    private void navigateToFundingMenu() {
        applicationFormsPage.openMenuApplicationProcessing();
        log.debug("Opened 'Application Processing' menu.");
        fundingPage.clickMenuFundingQueue();
        log.debug("Clicked on 'Funding' menu.");
    }

    public  void getAndInsertInvitationCodeText() {
        applicationFormsPage.openMenuApplicationProcessing();
        log.debug("Opened 'Application Processing' menu.");

        applicationFormsPage.clickMenuApplicationForms();
        log.debug("Clicked on 'Application Forms' menu.");

        String codeText = applicationFormsPage.getLastDescriptionCode();
        log.debug("Retrieved last description code text: '{}'.", codeText);

        invitationsPage.clickMenuInvitation();
        log.debug("Clicked on 'Invitation' menu.");

        invitationsPage.selectValueFromApplication(codeText);
        log.debug("Selected application section by code: '{}'.", codeText);

        invitationsPage.clickButtonSearch();
        log.debug("Clicked 'Search' button.");

        String codeInvitationCodeText = invitationsPage.getInvitationCode();
        log.debug("Retrieved invitation code: '{}'.", codeInvitationCodeText);

        fundingPage.clickMenuFundingQueue();
        log.debug("Clicked on 'Funding Queue' menu.");

        submittedApplicationsPage.selectValueFromInvitation(codeText);
        log.debug("Selected invitation by code: '{}'.", codeText);
    }

    @BeforeEach
    void setUpInvitationPage() {
        invitationsPage = new InvitationsPage(driver);
        applicationFormsPage = new ApplicationFormsPage(driver);
        submittedApplicationsPage = new SubmittedApplicationsPage(driver);
        evaluationsPage = new EvaluationsPage(driver);
        fundingPage = new FundingPage(driver);
    }

    @Test
    void testNavigateToFundingPage() {
        log.info("Starting test:'testNavigateToFundingPage'");

        try {
            applicationFormsPage.login("test", "test");
            log.debug("User logged in with test credentials");

            navigateToFundingMenu();
            log.debug("Navigating to funding menu.");

            log.info("Test 'testNavigateToFundingPage' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testNavigateToFundingPage' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testFundApplication() {
        log.info("Starting test:'testFundApplication'");

        try {

            applicationFormsPage.login("test", "test");
            log.debug("User logged in with test credentials");

            getAndInsertInvitationCodeText();
            log.debug("Invitation code inserted.");

            fundingPage.clickButtonForm();
            log.debug("Clicked 'Form'.");

            fundingPage.clickButtonFinance();
            log.debug("Clicked 'Finance'.");

            String orderN = "VUI_1";
            fundingPage.enterOrderNumber(orderN);
            log.debug("Entered order number '{}'.", orderN);

           fundingPage.enterOrderDate();

            fundingPage.clickButtonFinanceConfirmation();
            log.debug("Clicked 'Finance Confirmation'.");

            applicationFormsPage.verifySuccessMessage("Funkcija sėkmingai atlikta.");
            log.info("Verified success message");

            log.info("Test 'testFundApplication' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testFundApplication' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }


}
