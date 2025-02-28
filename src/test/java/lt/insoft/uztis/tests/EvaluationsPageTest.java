package lt.insoft.uztis.tests;

import lt.insoft.uztis.pages.ApplicationFormsPage;
import lt.insoft.uztis.pages.EvaluationsPage;
import lt.insoft.uztis.pages.InvitationsPage;
import lt.insoft.uztis.pages.SubmittedApplicationsPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

import java.time.Duration;
import java.time.Instant;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class EvaluationsPageTest extends UztisPageTest{
    private static final Logger log = getLogger(lookup().lookupClass());

    protected InvitationsPage invitationsPage;
    protected ApplicationFormsPage applicationFormsPage;
    protected SubmittedApplicationsPage submittedApplicationsPage;
    protected EvaluationsPage evaluationsPage;

    private void navigateToEvaluationsMenu() {
        applicationFormsPage.openMenuApplicationProcessing();
        log.debug("Opened 'Application Processing' menu.");
        evaluationsPage.clickMenuEvaluations();
        log.debug("Clicked on 'Evaluations' menu.");
    }

    @BeforeEach
    void setUpInvitationPage() {
        invitationsPage = new InvitationsPage(driver);
        applicationFormsPage = new ApplicationFormsPage(driver);
        submittedApplicationsPage = new SubmittedApplicationsPage(driver);
        evaluationsPage = new EvaluationsPage(driver);

    }

    @Test
    void testNavigateToEvaluationsPage() {
        log.info("Starting test:'testNavigateToEvaluationsPage'");

        try {
            applicationFormsPage.login("test", "test");
            log.debug("User logged in with test credentials");

            navigateToEvaluationsMenu();
            log.debug("Navigating to evaluations menu.");

            log.info("Test 'testNavigateToEvaluationsPage' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testNavigateToEvaluationsPage' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testFillSaveDraftEvaluationOne() {
        log.info("Starting test:'testFillSaveDraftEvaluationOne'");

        try {
            testNavigateToEvaluationsPage();

            evaluationsPage.clickLastEvaluation();
            log.debug("Clicked on last evaluation.");

            invitationsPage.clickButtonEdit();
            log.debug("Clicked 'Edit'.");

            evaluationsPage.clickRadioButton1_1();
            log.debug("Selected '1.1' radio button.");

            evaluationsPage.clickRadioButton1_2();
            log.debug("Selected '1.2' radio button.");

            evaluationsPage.clickRadioButton1_3();
            log.debug("Selected '1.3' radio button.");

            evaluationsPage.clickRadioButton1_4();
            log.debug("Selected '1.4' radio button.");

            evaluationsPage.clickRadioButton1_5();
            log.debug("Selected '1.5' radio button.");

            evaluationsPage.clickRadioButton2_1();
            log.debug("Selected '2.1' radio button.");

            evaluationsPage.clickRadioButton3_1();
            log.debug("Selected '3.1' radio button.");

            evaluationsPage.clickRadioButton4_2();
            log.debug("Selected '4.2' radio button.");

            evaluationsPage.clickRadioButton5_1();
            log.debug("Selected '5.1' radio button.");

            evaluationsPage.clickRadioButton5_2();
            log.debug("Selected '5.2' radio button.");

            evaluationsPage.clickRadioButton6_1();
            log.debug("Selected '6.1' radio button.");

            evaluationsPage.clickRadioButton6_2();
            log.debug("Selected '6.2' radio button.");

            evaluationsPage.clickExpansionPanelNotCritical();
            log.debug("Clicked on expansion panel 'Not Critical'.");

            evaluationsPage.clickRadioButton7();
            log.debug("Selected '7' radio button.");

            evaluationsPage.clickRadioButton8_1();
            log.debug("Selected '8_1' radio button.");

            evaluationsPage.clickRadioButton8_2();
            log.debug("Selected '8_2' radio button.");

            evaluationsPage.clickRadioButton8_3();
            log.debug("Selected '8_3' radio button.");

            evaluationsPage.clickRadioButton8_4();
            log.debug("Selected '8_4' radio button.");

            evaluationsPage.clickRadioButton8_5();
            log.debug("Selected '8_5' radio button.");

            evaluationsPage.clickRadioButton8_6();
            log.debug("Selected '8_6' radio button.");

            evaluationsPage.clickRadioButton8_7();
            log.debug("Selected '8_7' radio button.");

            evaluationsPage.clickRadioButton8_8();
            log.debug("Selected '8_8' radio button.");

            evaluationsPage.clickRadioButton8_9();
            log.debug("Selected '8_9' radio button.");

            evaluationsPage.clickRadioButton8_10();
            log.debug("Selected '8_10' radio button.");

            evaluationsPage.clickRadioButton9();
            log.debug("Selected '9' radio button.");

            evaluationsPage.clickRadioButton10_1();
            log.debug("Selected '10.1' radio button.");

            evaluationsPage.clickRadioButton10_2();
            log.debug("Selected '10.2' radio button.");

            evaluationsPage.clickRadioButton10_3();
            log.debug("Selected '10.3' radio button.");

            String explanation1 = "Balas pakoreduotas ranka";
            evaluationsPage.enterExplanation1(explanation1);
            log.debug("Entered  explanation: '{}'.", explanation1);

            evaluationsPage.clickButtonSaveDraft();
            log.debug("Clicked 'Save Draft'.");

            applicationFormsPage.verifySuccessMessage("Duomenys sėkmingai išsaugoti.");
            log.info("Verified success message");

            log.info("Test 'testFillSaveDraftEvaluationOne' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testFillSaveDraftEvaluationOne' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testConfirmEvaluationOne() {
        log.info("Starting test:'testConfirmEvaluationOne'");

        try {
            testNavigateToEvaluationsPage();

            evaluationsPage.clickLastEvaluation();
            log.debug("Clicked on last evaluation.");

            invitationsPage.clickButtonEdit();
            log.debug("Clicked 'Edit'.");

            evaluationsPage.clickButtonConfirm1();
            log.debug("Clicked 'Confirm'.");

            evaluationsPage.clickButtonConfirmConfirmation();
            log.debug("Clicked 'Confirm Confirmation'.");

            String actualLabel = evaluationsPage.getLabelStatusConfirmed();
            String expectedLabel = "Įvertinta";
            log.debug("Fetched label text: {}", actualLabel);

            Assertions.assertEquals(expectedLabel, actualLabel,
                    String.format("Expected label '%s' but found '%s'", expectedLabel, actualLabel));
            log.info("Verified that the label text matches the expected value.");

        log.info("Test 'testConfirmEvaluationOne' completed successfully.");
    } catch (AssertionError | Exception e) {
        log.error("Test 'testConfirmEvaluationOne' failed with error: {}", e.getMessage(), e);
        throw e;
    }
}

    @Test
    void testFillSaveDraftEvaluationTwo() {
        log.info("Starting test:'testFillSaveDraftEvaluationTwo'");

        try {
            testNavigateToEvaluationsPage();

            evaluationsPage.clickLastEvaluation();
            log.debug("Clicked on last evaluation.");

            invitationsPage.clickButtonEdit();
            log.debug("Clicked 'Edit'.");

            evaluationsPage.enterCommissionDate();
            log.debug("Entered commission date.");

            evaluationsPage.clickRadioButtonWithoutCommissionNO();
            log.debug("Selected 'Without Commission' radio button.");

            String chairman = "Pirmininkas Pirmininkauskas";
            evaluationsPage.enterChairmanName(chairman);
            log.debug("Entered chairman name: '{}'.", chairman);

            String member = "Narys Narauskas";
            evaluationsPage.enterMemberName(member);
            log.debug("Entered member name: '{}'.", member);

            evaluationsPage.clickButtonContinue();
            log.debug("Clicked 'Continue'.");

            String scoreE = "9";
            evaluationsPage.enterScoreEdition(scoreE);
            log.debug("Entered score edition: '{}'.", scoreE);

            String score0 = "10";
            evaluationsPage.enterScoreChairman(score0);
            log.debug("Entered chairman score: '{}'.", score0);

            String score1 = "11";
            evaluationsPage.enterScoreMember(score1);
            log.debug("Entered member score: '{}'.", score1);

            String scoreC = "Išvada - palanki";
            evaluationsPage.enterScoreConclusions(scoreC);
            log.debug("Entered conclusion: '{}'.", scoreC);

            String explanation2 = "Balas pakoreduotas ranka";
            evaluationsPage.enterExplanation2(explanation2);
            log.debug("Entered  explanation: '{}'.", explanation2);

            evaluationsPage.clickButtonSaveDraft();
            log.debug("Clicked 'Save Draft'.");

            applicationFormsPage.verifySuccessMessage("Duomenys sėkmingai išsaugoti.");
            log.info("Verified success message");

            log.info("Test 'testFillSaveDraftEvaluationTwo' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testFillSaveDraftEvaluationTwo' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testConfirmEvaluationTwo() {
        log.info("Starting test:'testConfirmEvaluationTwo'");

        try {
            testNavigateToEvaluationsPage();

            evaluationsPage.clickLastEvaluation();
            log.debug("Clicked on last evaluation.");

            invitationsPage.clickButtonEdit();
            log.debug("Clicked 'Edit'.");

            evaluationsPage.clickButtonContinue();
            log.debug("Clicked 'Continue'.");

            String scoreEC = "Išvada - papildyta";
            evaluationsPage.enterScoreEditedConclusions(scoreEC);
            log.debug("Entered conclusion: '{}'.", scoreEC);

            String explanation2 = "; Išvada pakoreduota ranka";
            evaluationsPage.enterExplanation2_1(explanation2);
            log.debug("Entered  explanation: '{}'.", explanation2);

            evaluationsPage.clickButtonConfirm2();
            log.debug("Clicked 'Confirm'.");

            evaluationsPage.clickButtonConfirmConfirmation();
            log.debug("Clicked 'Confirm Confirmation'.");

            String actualLabel = evaluationsPage.getLabelStatusConfirmed();
            String expectedLabel = "Įvertinta";
            log.debug("Fetched label text: {}", actualLabel);

            Assertions.assertEquals(expectedLabel, actualLabel,
                    String.format("Expected label '%s' but found '%s'", expectedLabel, actualLabel));
            log.info("Verified that the label text matches the expected value.");

            log.info("Test 'testConfirmEvaluationTwo' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testConfirmEvaluationTwo' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }


    @Test
    void testFillSaveDraftEvaluationThree() throws InterruptedException {
        log.info("Starting test:'testFillSaveDraftEvaluationThree'");

        try {
            testNavigateToEvaluationsPage();

            evaluationsPage.clickLastEvaluation();
            log.debug("Clicked on last evaluation.");

            invitationsPage.clickButtonEdit();
            log.debug("Clicked 'Edit'.");

            evaluationsPage.enterCommissionDate();
            log.debug("Entered commission date.");

            evaluationsPage.clickRadioButtonWithoutCommissionNO();
            log.debug("Selected 'Without Commission' radio button.");

            String chairman = "Pirmininkas Pirmininkauskas";
            evaluationsPage.enterChairmanName(chairman);
            log.debug("Entered chairman name: '{}'.", chairman);

            String member = "Narys Narauskas";
            evaluationsPage.enterMemberName(member);
            log.debug("Entered member name: '{}'.", member);

            evaluationsPage.clickButtonContinue();
            log.debug("Clicked 'Continue'.");

            String scoreCh1 = "1";
            evaluationsPage.enterScoreCh1(scoreCh1);
            log.debug("Entered chairman score 1: '{}'.", scoreCh1);

            String scoreM1 = "2";
            evaluationsPage.enterScoreM1(scoreM1);
            log.debug("Entered member score 1: '{}'.", scoreM1);

            String scoreCc1 = "Išvada - palanki 1";
            evaluationsPage.enterScoreConclusions1(scoreCc1);
            log.debug("Entered member conclusion 1: '{}'.", scoreCc1);

            String scoreCh2 = "3";
            evaluationsPage.enterScoreCh2(scoreCh2);
            log.debug("Entered chairman score 2: '{}'.", scoreCh2);

            String scoreM2 = "4";
            evaluationsPage.enterScoreM2(scoreM2);
            log.debug("Entered member score 2: '{}'.", scoreM2);

            String scoreCc2 = "Išvada - palanki 2";
            evaluationsPage.enterScoreConclusions2(scoreCc2);
            log.debug("Entered member conclusion 2: '{}'.", scoreCc2);


            String scoreCh3 = "5";
            evaluationsPage.enterScoreCh3(scoreCh3);
            log.debug("Entered chairman score 3: '{}'.", scoreCh3);

            String scoreM3 = "6";
            evaluationsPage.enterScoreM3(scoreM3);
            log.debug("Entered member score 3: '{}'.", scoreM3);

            String scoreCc3 = "Išvada - palanki 3";
            evaluationsPage.enterScoreConclusions3(scoreCc3);
            log.debug("Entered member conclusion 3: '{}'.", scoreCc3);

            String scoreCh4 = "7";
            evaluationsPage.enterScoreCh4(scoreCh4);
            log.debug("Entered chairman score 4: '{}'.", scoreCh4);

            String scoreM4 = "8";
            evaluationsPage.enterScoreM4(scoreM4);
            log.debug("Entered member score 4: '{}'.", scoreM4);

            String scoreCc4 = "Išvada - palanki 4";
            evaluationsPage.enterScoreConclusions4(scoreCc4);
            log.debug("Entered member conclusion 4: '{}'.", scoreCc4);

            String scoreCh5 = "9";
            evaluationsPage.enterScoreCh5(scoreCh5);
            log.debug("Entered chairman score 5: '{}'.", scoreCh5);

            String scoreM5 = "10";
            evaluationsPage.enterScoreM5(scoreM5);
            log.debug("Entered member score 5: '{}'.", scoreM5);

            String scoreCc5 = "Išvada - palanki 5";
            evaluationsPage.enterScoreConclusions5(scoreCc5);
            log.debug("Entered member conclusion 5: '{}'.", scoreCc5);


            // Tikslus XPath, kad rastume lauką su klase 'primary-control' ir 'mat-mdc-form-field-input'
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement inputField3 = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//mat-form-field[contains(@class, 'mat-mdc-form-field')]//input[contains(@class, 'mat-mdc-form-field-input')]")
            ));
            // Naudojame JavascriptExecutor, kad gauti reikšmę
            JavascriptExecutor js = (JavascriptExecutor) driver;
            // Gauti reikšmę iš neaktyvaus įvesties laukelio
            String value = (String) js.executeScript("return arguments[0].value;", inputField3);
            System.out.println("Reikšmė iš neaktyvaus įvesties lauko: " + value);
            // Jei reikšmė pateikiama su kableliu, pakeičiame jį į tašką
            value = value.replace(",", ".");
            // Jei reikšmė vis tiek 0.00, laukiame, kol reikšmė atsinaujins
            int attempts = 0;
            while (value.equals("0.00") && attempts < 5) {
                Thread.sleep(100); // Palaukiame 100ms ir bandome vėl
                value = (String) js.executeScript("return arguments[0].value;", inputField3);
                attempts++;
            }
            System.out.println("Overall rating (from input field): " + value);
            Assertions.assertNotNull(value, "The value should not be null");

            evaluationsPage.clickButtonSaveDraft();
            log.debug("Clicked 'Save Draft'.");

            applicationFormsPage.verifySuccessMessage("Duomenys sėkmingai išsaugoti.");
            log.info("Verified success message");

            log.info("Test 'testFillSaveDraftEvaluationThree' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testFillSaveDraftEvaluationThree' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testConfirmEvaluationThree() {
        log.info("Starting test:'testConfirmEvaluationThree'");

        try {
            testNavigateToEvaluationsPage();

            evaluationsPage.clickLastEvaluation();
            log.debug("Clicked on last evaluation.");

            invitationsPage.clickButtonEdit();
            log.debug("Clicked 'Edit'.");

            evaluationsPage.clickButtonContinue();
            log.debug("Clicked 'Continue'.");

            evaluationsPage.clickButtonConfirm3();
            log.debug("Clicked 'Confirm'.");

            evaluationsPage.clickButtonConfirmConfirmation();
            log.debug("Clicked 'Confirm Confirmation'.");

            String actualLabel = evaluationsPage.getLabelStatusConfirmed();
            String expectedLabel = "Įvertinta";
            log.debug("Fetched label text: {}", actualLabel);

            Assertions.assertEquals(expectedLabel, actualLabel,
                    String.format("Expected label '%s' but found '%s'", expectedLabel, actualLabel));
            log.info("Verified that the label text matches the expected value.");

            log.info("Test 'testConfirmEvaluationThree' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testConfirmEvaluationThree' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }


}


