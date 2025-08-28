package lt.insoft.uztis.tests;

import lt.insoft.uztis.pages.ApplicationFormsPage;
import lt.insoft.uztis.pages.InvitationsPage;
import lt.insoft.uztis.pages.SubmittedApplicationsPage;
import lt.insoft.uztis.tests.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.testng.Assert;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class SubmittedApplicationsPageTest extends UztisPageTest {
    private static final Logger log = getLogger(lookup().lookupClass());

    protected InvitationsPage invitationsPage;
    protected ApplicationFormsPage applicationFormsPage;
    protected SubmittedApplicationsPage submittedApplicationsPage;


    private void navigateToSubmittedApplicationsMenu() {
        applicationFormsPage.clickMenuExpand();
        applicationFormsPage.clickMenuMore();
        applicationFormsPage.openMenuApplicationProcessing();
        log.debug("Opened 'Application Processing' menu.");
        submittedApplicationsPage.clickMenuSubmittedApplications();
        log.debug("Clicked on 'Submitted Applications' menu.");
    }

    public  void getAndInsertInvitationCodeText() {
        applicationFormsPage.clickMenuExpand();
        applicationFormsPage.clickMenuMore();
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

        submittedApplicationsPage.clickMenuSubmittedApplications();
        log.debug("Clicked on 'Submitted Applications' menu.");

        submittedApplicationsPage.selectValueFromInvitation(codeText);
        log.debug("Selected invitation by code: '{}'.", codeText);
    }


    @BeforeEach
    void setUpSubmittedApplicationsPage() {
        invitationsPage = new InvitationsPage(driver);
        applicationFormsPage = new ApplicationFormsPage(driver);
        submittedApplicationsPage = new SubmittedApplicationsPage(driver);
    }

    @Test
    void testNavigateToSubmittedApplicationsPage() {
        log.info("Starting test:'testNavigateToSubmittedApplicationsPage'");

        try {
            TestUtils.loginAllTests(driver);
            log.debug("User logged in with test credentials");

            navigateToSubmittedApplicationsMenu();
            log.debug("Navigating to submitted applications menu.");

            String actualLabel = submittedApplicationsPage.getSubmittedApplicationsLabelText();
            String expectedLabel = "Kvietimui pateiktos paraiškos";
            log.debug("Fetched label text: {}", actualLabel);

            Assertions.assertEquals(expectedLabel, actualLabel,
                    String.format("Expected label '%s' but found '%s'.", expectedLabel, actualLabel));
            log.info("'goToInvitationPageTest' completed. Label is: " + actualLabel);

            log.info("Test 'testNavigateToSubmittedApplicationsPage' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testNavigateToSubmittedApplicationsPage' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testGoToSubmitApplication() {
        log.info("Starting test:'testGoToSubmitApplication'");

        try {
            TestUtils.loginAllTests(driver);
            log.debug("User logged in with test credentials");

            getAndInsertInvitationCodeText();

            submittedApplicationsPage.clickButtonActionApplication();
            log.debug("Clicked on 'Action Application' button.");

            submittedApplicationsPage.clickButtonNewApplication();
            log.debug("Clicked on 'New Application' button.");

            boolean isEditFormButtonVisible = submittedApplicationsPage.isButtonNextDisplayed();
            Assertions.assertTrue(isEditFormButtonVisible, "'Next' button should be displayed.");
            log.info("Verified that the 'Next' button is displayed.");

            log.info("Test 'testGoToSubmitApplication' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testGoToSubmitApplication' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testFillNewVUIApplicationFA() {

        log.info("Starting test:'testFillNewVUIPApplicationFA'");

        try {
            TestUtils.loginAllTests(driver);
            log.debug("User logged in with test credentials");

            getAndInsertInvitationCodeText();
            log.debug("Invitation code inserted.");

            submittedApplicationsPage.clickButtonActionApplication();
            log.debug("Clicked on 'Action Application' button.");

            submittedApplicationsPage.clickButtonNewApplication();
            log.debug("Clicked on 'New Application' button.");

            submittedApplicationsPage.selectRadioButtonFA();
            log.debug("Selected the 'RadioButtonFA' option.");

            String applicant = "Pavardenis GR777";
            submittedApplicationsPage.selectValueApplicant_FA(applicant);
            log.debug("Selected applicant: '{}'.", applicant);

            String phone = "61298745";
            submittedApplicationsPage.enterText("phone", phone);
            log.debug("Entered phone number: '{}'", phone);

            String email = "test@autotest.com";
            submittedApplicationsPage.enterText("e_mail", email);
            log.debug("Entered email: '{}'", email);

            String value = "01.11 - Grūdinių (išskyrus ryžius), ankštinių ir aliejingų sėklų augalų auginimas";
            submittedApplicationsPage.selectDropdownButtonCheckBoxValueEVRK_VUI_DVP_PVK("evrk_VUI", value);
            log.debug("Selected EVRK value: '{}'.", value);

            //address
            //--------------
            submittedApplicationsPage.clickAddressComponent();
            log.debug("Clicked on 'Address Component' button.");

            String country = "Lietuva";
            submittedApplicationsPage.selectDropdownAddressCountry(country);
            log.debug("Selected country: '{}'.", country);

            String citySearchTerm = "Klaipėda";

            try {
                submittedApplicationsPage.selectDropdownAddressCity(citySearchTerm);

                WebElement selectedCity = driver.findElement(By.xpath("//mat-option[@aria-selected='true']//span[contains(@class, 'area-center')]"));
                String selectedCityText = selectedCity.getText().trim();

                Assert.assertTrue(selectedCityText.equalsIgnoreCase(citySearchTerm),
                        "The selected city is incorrect. Found: " + selectedCityText);

                System.out.println("Test passed: correct city selected - " + selectedCityText);

            } catch (Exception e) {
                Assert.fail("Test failed: " + e.getMessage());
            }

            String street = "Agluonos g.";
            submittedApplicationsPage.selectDropdownAddressStreet(street);
            log.debug("Selected street: '{}'.", street);

            String house = "2";
            submittedApplicationsPage.selectDropdownAddressHouse(house);
            log.debug("Selected house: '{}'.", house);

//            String apartment = "1";
//            submittedApplicationsPage.selectDropdownAddressApartment(apartment);
//            log.debug("Selected apartment: '{}'.", apartment);

            String buttonAddressConfirm = "addressConfirm";
            submittedApplicationsPage.clickApplicationButton(buttonAddressConfirm);
            log.info("Clicked button with key: '{}'.", buttonAddressConfirm);

            //-------------

            submittedApplicationsPage.enterDate_VUI();
            log.debug("Entered project date.");

            String jobCount = "1";
            submittedApplicationsPage.enterText("job_count_vui", jobCount);
            log.debug("Entered job count: '{}'", jobCount);

            String next_1 = "next";
            submittedApplicationsPage.clickApplicationButton(next_1);
            log.info("Clicked button with key: '{}'.", next_1);

            //--------------------

            String value_A = "Administratoriai";
            submittedApplicationsPage.selectDropdownButtonValue_VUI_DVP_PVK("jobName_VUI", value_A);
            log.debug("Selected 'Job Name' value: '{}'.", value_A);

            String function = "Funkcija 1";
            submittedApplicationsPage.enterTextArea("1", function);
            log.debug("Entered function: '{}'", function);

            submittedApplicationsPage.selectRadioButtonY_N("1_true");
            log.debug("Selected 'With Disabilities' 'yes' option.");

            String supported_VUI = "Bedarbiai, kurie yra darbingo amžiaus neįgalieji, kuriems nustatytas iki 25 procentų darbingumo lygis arba sunkus neįgalumo lygis";
            submittedApplicationsPage.selectDropdownButtonCheckBoxValueEVRK_VUI_DVP_PVK("supported_VUI", supported_VUI);
            log.debug("Selected EVRK_true value: '{}'.", supported_VUI);

            String disability = "Negalia 2";
            submittedApplicationsPage.enterTextArea("2", disability);
            log.debug("Entered disability: '{}'", disability);

            String timeMode = "Kita";
            submittedApplicationsPage.selectDropdownTimeMode(timeMode);
            log.debug("Selected time mode: '{}'.", timeMode);

            String timeMode2 = "6 val. per dieną ir 4 d. per savaitę";
            submittedApplicationsPage.enterInputTimeModeOthers(timeMode2);
            log.debug("Selected time mode other: '{}'.", timeMode2);

            String qualification = "Kvalifikacija 3";
            submittedApplicationsPage.enterTextArea("3", qualification);
            log.debug("Entered qualification: '{}'", qualification);

            submittedApplicationsPage.enterJobDate_VUI();
            log.debug("Entered job date.");

            String salary_vui = "1200";
            submittedApplicationsPage.enterText("salary_vui_dvp", salary_vui);
            log.debug("Entered salary: '{}'", salary_vui);

            String salaryDescription = "Užmokestis 4";
            submittedApplicationsPage.enterTextArea("4", salaryDescription);
            log.debug("Entered salary description: '{}'", salaryDescription);

            submittedApplicationsPage.selectRadioButtonY_N("2_false");
            log.debug("Selected 'Temporary Job' 'no' option.");

            submittedApplicationsPage.selectRadioButtonY_N("3_false");
            log.debug("Selected 'Season Job' 'no' option.");

            submittedApplicationsPage.selectRadioButtonY_N("4_true");
            log.debug("Selected 'Energy' 'yes' option.");

            submittedApplicationsPage.selectRadioButtonY_N("5_true");
            log.debug("Selected 'Repair' 'yes' option.");

            String activity = "Veikla 5";
            submittedApplicationsPage.enterTextArea("5", activity);
            log.debug("Entered salary activity: '{}'", activity);

            String proses = "Procesas 6";
            submittedApplicationsPage.enterTextArea("6", proses);
            log.debug("Entered proses proses: '{}'", proses);

            String energy = "Energija 7";
            submittedApplicationsPage.enterTextArea("7", energy);
            log.debug("Entered energy proses: '{}'", energy);

            String repair = "Remontas 8";
            submittedApplicationsPage.enterTextArea("8", repair);
            log.debug("Entered repair proses: '{}'", repair);

            submittedApplicationsPage.selectRadioButtonY_N("6_false");
            log.debug("Selected 'PVM' 'no' option.");

            String necessaryForJob = "Įsigyti";
            submittedApplicationsPage.selectDropdownNecessaryForJobOne(necessaryForJob);
            log.debug("Selected necessary for job: '{}'.", necessaryForJob);

            String tool = "Darbo priemonė 1";
            submittedApplicationsPage.enterText("tool", tool);
            log.debug("Entered tool: '{}'", tool);

            String tool_parameter_1 = "Darbo priemonės 1 parametras 1";
            submittedApplicationsPage.enterText("tool_parameter_1", tool_parameter_1);
            log.debug("Entered tool parameter 1: '{}'", tool_parameter_1);

            String tool_parameter_2 = "Darbo priemonės 1 parametras 2";
            submittedApplicationsPage.enterText("tool_parameter_2", tool_parameter_2);
            log.debug("Entered tool parameter 2: '{}'", tool_parameter_2);

            String tool_parameter_3 = "Darbo priemonės 1 parametras 3";
            submittedApplicationsPage.enterText("tool_parameter_3", tool_parameter_3);
            log.debug("Entered tool parameter 3: '{}'", tool_parameter_3);

            String tool_count = "1";
            submittedApplicationsPage.enterText("tool_count", tool_count);
            log.debug("Entered tool count: '{}'", tool_count);

            String price_amount_1 = "1000";
            submittedApplicationsPage.enterText("price_amount_1", price_amount_1);
            log.debug("Entered price amount 1: '{}'", price_amount_1);

            String funds_amount_1 = "800";
            submittedApplicationsPage.enterText("funds_amount_1", funds_amount_1);
            log.debug("Entered own funds amount 1: '{}'", funds_amount_1);

            String add = "add";
            submittedApplicationsPage.clickApplicationButton(add);
            log.info("Clicked button with key: '{}'.", add);

            String necessaryForJob1 = "Remontuoti";
            submittedApplicationsPage.selectDropdownNecessaryForJobTwo(necessaryForJob1);
            log.debug("Selected necessary for job 'to repair': '{}'.", necessaryForJob1);

            String repair_name = "Remonto pavadinimas 1";
            submittedApplicationsPage.enterText("repair_name", repair_name);
            log.debug("Entered repair name: '{}'", repair_name);

            String repairDescription = "Remonto aprašymas 9";
            submittedApplicationsPage.enterTextArea("9", repairDescription);
            log.debug("Entered repair description proses: '{}'", repairDescription);

            String price_amount_2 = "1000";
            submittedApplicationsPage.enterText("price_amount_2", price_amount_2);
            log.debug("Entered price amount 2: '{}'", price_amount_2);

            String funds_amount_2 = "900";
            submittedApplicationsPage.enterText("funds_amount_2", funds_amount_2);
            log.debug("Entered own funds amount 2: '{}'", funds_amount_2);

            submittedApplicationsPage.clickButtonAddRemoved();
            log.debug("Clicked 'AddRemoved' button.");

            stay();

            submittedApplicationsPage.clickButtonRemove();
            log.debug("Clicked 'Remove' button.");

            stay();

            submittedApplicationsPage.clickButtonRemoveConfirmation();
            log.debug("Clicked 'Remove Confirmation' button.");

            stay();

            String country_perc = "50";
            submittedApplicationsPage.enterText("country_perc", country_perc);
            log.debug("Entered country percentage: '{}'", country_perc);

            String institution_perc = "50";
            submittedApplicationsPage.enterText("institution_perc", institution_perc);
            log.debug("Entered institution percentage: '{}'", institution_perc);

            String municipality_perc = "50";
            submittedApplicationsPage.enterText("municipality_perc", municipality_perc);
            log.debug("Entered municipality percentage: '{}'", municipality_perc);

            submittedApplicationsPage.selectRadioButtonY_N("7_true");
            log.debug("Selected 'De Minimis' 'yes' option.");

            String programName = "Programa 1";
            submittedApplicationsPage.enterText("program_name", programName);
            log.debug("Entered  program name: '{}'", programName);

            submittedApplicationsPage.enterProjectDateFrom();
            log.debug("Entered project date from.");

            submittedApplicationsPage.enterProjectDateUntil();
            log.debug("Entered project date until.");

            String support_amount = "2000";
            submittedApplicationsPage.enterText("support_amount", support_amount);
            log.debug("Entered  support amount: '{}'", support_amount);

            String next_2 = "next";
            submittedApplicationsPage.clickApplicationButton(next_2);
            log.info("Clicked button with key: '{}'.", next_2);

            //--------------------

                SubmittedApplicationsPage.CheckboxHelper.setConfirmationCheckbox(driver, submittedApplicationsPage.checkboxConfirmationOne, true);
                log.debug("Checked 'Legal Entities' checkbox 1.");

                SubmittedApplicationsPage.CheckboxHelper.setConfirmationCheckbox(driver, submittedApplicationsPage.checkboxConfirmationTwo, true);
                log.debug("Checked 'Legal Entities' checkbox 2.");

                SubmittedApplicationsPage.CheckboxHelper.setConfirmationCheckbox(driver, submittedApplicationsPage.checkboxConfirmationThree, true);
                log.debug("Checked 'Legal Entities' checkbox 3.");

                log.debug("Test finished: All 'Legal Entities' checkboxes checked successfully.");

            String next_3 = "next";
            submittedApplicationsPage.clickApplicationButton(next_3);
            log.info("Clicked button with key: '{}'.", next_3);

            //--------------------

            SubmittedApplicationsPage.RadioButtonHelper.selectRadioButton(driver, SubmittedApplicationsPage.RadioButtonHelper.RADIO_NO_1, true);
            log.info("Marking 'Ne' radio button 1");
            SubmittedApplicationsPage.RadioButtonHelper.selectRadioButton(driver, SubmittedApplicationsPage.RadioButtonHelper.RADIO_NO_2, true);
            log.info("Marking 'Ne' radio button 2");
            SubmittedApplicationsPage.RadioButtonHelper.selectRadioButton(driver, SubmittedApplicationsPage.RadioButtonHelper.RADIO_NO_3, true);
            log.info("Marking 'Ne' radio button 3");
            SubmittedApplicationsPage.RadioButtonHelper.selectRadioButton(driver, SubmittedApplicationsPage.RadioButtonHelper.RADIO_NO_4, true);
            log.info("Marking 'Ne' radio button 4");
            SubmittedApplicationsPage.RadioButtonHelper.selectRadioButton(driver, SubmittedApplicationsPage.RadioButtonHelper.RADIO_NO_5, true);
            log.info("Marking 'Ne' radio button 5");
            SubmittedApplicationsPage.RadioButtonHelper.selectRadioButton(driver, SubmittedApplicationsPage.RadioButtonHelper.RADIO_NO_6, true);
            log.info("Marking 'Ne' radio button 6");

            SubmittedApplicationsPage.RadioButtonHelper.selectRadioButton(driver, SubmittedApplicationsPage.RadioButtonHelper.RADIO_YES_7, true);
            log.info("Marking 'Taip' radio button 7");
            SubmittedApplicationsPage.RadioButtonHelper.selectRadioButton(driver, SubmittedApplicationsPage.RadioButtonHelper.RADIO_YES_8, true);
            log.info("Marking 'Taip' radio button 8");
            SubmittedApplicationsPage.RadioButtonHelper.selectRadioButton(driver, SubmittedApplicationsPage.RadioButtonHelper.RADIO_YES_9, true);
            log.info("Marking 'Taip' radio button 9");
            SubmittedApplicationsPage.RadioButtonHelper.selectRadioButton(driver, SubmittedApplicationsPage.RadioButtonHelper.RADIO_YES_10, true);
            log.info("Marking 'Taip' radio button 10");
            SubmittedApplicationsPage.RadioButtonHelper.selectRadioButton(driver, SubmittedApplicationsPage.RadioButtonHelper.RADIO_YES_11, true);
            log.info("Marking 'Taip' radio button 11");
            SubmittedApplicationsPage.RadioButtonHelper.selectRadioButton(driver, SubmittedApplicationsPage.RadioButtonHelper.RADIO_YES_12, true);
            log.info("Marking 'Taip' radio button 12");

            SubmittedApplicationsPage.CheckboxHelper.setConfirmationCheckbox(driver, submittedApplicationsPage.checkboxConfirmationStepFour, true);
            log.debug("Checked 'ConfirmationStepFour'");

            String next_4 = "next";
            submittedApplicationsPage.clickApplicationButton(next_4);
            log.info("Clicked button with key: '{}'.", next_4);

            //--------------------

            submittedApplicationsPage.uploadFileStepFive();

            submittedApplicationsPage.fillDocumentFields(10);

            SubmittedApplicationsPage.CheckboxHelper.setConfirmationCheckbox(driver, submittedApplicationsPage.checkboxConfirmationDocument, true);
            log.debug("Checked 'ConfirmationDocument'");
            SubmittedApplicationsPage.CheckboxHelper.setConfirmationCheckbox(driver, submittedApplicationsPage.checkboxConfirmationApplication, true);
            log.debug("Checked 'ConfirmationApplication'");

            String save_draft = "save_draft";
            submittedApplicationsPage.clickApplicationButton(save_draft);
            log.info("Clicked button with key: '{}'.", save_draft);

            applicationFormsPage.verifySuccessMessage("Ruošinys sėkmingai išsaugotas.");
            log.info("Verified success message");

            String review = "review";
            submittedApplicationsPage.clickApplicationButton(review);
            log.info("Clicked button with key: '{}'.", review);

            stay();

            //--------------------

            String submit = "submit";
            submittedApplicationsPage.clickApplicationButton(submit);
            log.info("Clicked button with key: '{}'.", submit);

            String submitConfirmation = "submitConfirm";
            submittedApplicationsPage.clickApplicationButton(submitConfirmation);
            log.info("Clicked button with key: '{}'.", submitConfirmation);

            applicationFormsPage.verifySuccessMessage("Paraiška sėkmingai pateikta ir užregistruota.");

            log.info("Test 'testFillNewVUIApplicationFA' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testFillNewVUIApplicationFA' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testAddEvaluators_VUI_PVK() {

        log.info("Starting test:'testAddEvaluators'");

        try {
            TestUtils.loginAllTests(driver);
            log.debug("User logged in with test credentials");

            getAndInsertInvitationCodeText();
            log.debug("Invitation code inserted.");

            submittedApplicationsPage.clickButtonActionApplication();
            log.debug("Clicked on 'Action Application' button.");

            submittedApplicationsPage.clickButtonAddEvaluators();
            log.debug("Clicked on 'Add Evaluators' button.");

            submittedApplicationsPage.enterFirstEvaluationEndDate();
            log.debug("Entered first evaluation date.");

            submittedApplicationsPage.enterSecondEvaluationEndDate();
            log.debug("Entered second evaluation date.");

            submittedApplicationsPage.enterThirdEvaluationEndDate();
            log.debug("Entered third evaluation date.");

            String expectedEvaluator = "Adelė Kutienė";
            submittedApplicationsPage.selectValueByListEvaluators(expectedEvaluator);

            submittedApplicationsPage.clickButtonAddEvaluatorsConfirmation();
            log.debug("Clicked on 'Add Evaluators' confirmation button.");

            applicationFormsPage.verifySuccessMessage("Paraiškų vertinimai sėkmingai priskirti vertintojams.");
            log.info("Verified success message");

            log.info("Test 'testAddEvaluators' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testAddEvaluators' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testCreateProjects() {

        log.info("Starting test:'testCreateProjects'");

        try {
            TestUtils.loginAllTests(driver);
            log.debug("User logged in with test credentials");

            getAndInsertInvitationCodeText();
            log.debug("Invitation code inserted.");

            submittedApplicationsPage.clickButtonActionApplication();
            log.debug("Clicked on 'Action Application' button.");

            submittedApplicationsPage.clickButtonCreateProjects();
            log.debug("Clicked on 'Create Projects' button.");

            submittedApplicationsPage.clickButtonProjectsConfirmation();
            log.debug("Clicked on 'Create Projects' confirmation button.");

            applicationFormsPage.verifySuccessMessage("Projektai sėkmingai sukurti..");
            log.info("Verified success message");

            log.info("Test 'testCreateProjects' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testCreateProjects' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testFillNewDVPApplicationFA() {

        log.info("Starting test:'testFillNewDVPApplicationFA'");

        try {
            TestUtils.loginAllTests(driver);
            log.debug("User logged in with test credentials");

            getAndInsertInvitationCodeText();
            log.debug("Invitation code inserted.");

            submittedApplicationsPage.clickButtonActionApplication();
            log.debug("Clicked on 'Action Application' button.");

            submittedApplicationsPage.clickButtonNewApplication();
            log.debug("Clicked on 'New Application' button.");

            submittedApplicationsPage.selectRadioButtonFA();
            log.debug("Selected the 'RadioButtonFA' option.");

            String applicant = "Pavardenis GR777";
            submittedApplicationsPage.selectValueApplicant_FA(applicant);
            log.debug("Selected applicant: '{}'.", applicant);

            String phone = "61298745";
            submittedApplicationsPage.enterText("phone", phone);
            log.debug("Entered phone number: '{}'", phone);

            String email = "test@autotest.com";
            submittedApplicationsPage.enterText("e_mail", email);
            log.debug("Entered email: '{}'", email);

            invitationsPage.setCheckbox(submittedApplicationsPage.checkboxConfirmationOne, true);
            log.debug("Checked 'For workplace adaptation' checkbox 1.");

            invitationsPage.setCheckbox(submittedApplicationsPage.checkboxConfirmationTwo, true);
            log.debug("Checked 'For environmental adaptation' checkbox 2.");

            String value = "01.11 - Grūdinių (išskyrus ryžius), ankštinių ir aliejingų sėklų augalų auginimas";
            submittedApplicationsPage.selectDropdownButtonCheckBoxValueEVRK_VUI_DVP_PVK("evrk_DVP", value);
            log.debug("Selected EVRK value: '{}'.", value);

            //address
            //--------------
            submittedApplicationsPage.clickAddressComponent();
            log.debug("Clicked on 'Address Component' button.");

            String country = "Lietuva";
            submittedApplicationsPage.selectDropdownAddressCountry(country);
            log.debug("Selected country: '{}'.", country);

            String citySearchTerm = "Klaipėda";

            try {
                // Iškviečiame metodą, kuris įveda ir pasirenka miestą
                submittedApplicationsPage.selectDropdownAddressCity(citySearchTerm);

                // Patikriname, ar pasirinktas būtent „Vilnius“
                WebElement selectedCity = driver.findElement(By.xpath("//mat-option[@aria-selected='true']//span[contains(@class, 'area-center')]"));
                String selectedCityText = selectedCity.getText().trim();

                Assert.assertTrue(selectedCityText.equalsIgnoreCase(citySearchTerm),
                        "The selected city is incorrect. Found: " + selectedCityText);

                System.out.println("Test passed: correct city selected - " + selectedCityText);

            } catch (Exception e) {
                Assert.fail("Test failed: " + e.getMessage());
            }

            String street = "Agluonos g.";
            submittedApplicationsPage.selectDropdownAddressStreet(street);
            log.debug("Selected street: '{}'.", street);

            String house = "2";
            submittedApplicationsPage.selectDropdownAddressHouse(house);
            log.debug("Selected house: '{}'.", house);

//            String apartment = "1";
//            submittedApplicationsPage.selectDropdownAddressApartment(apartment);
//            log.debug("Selected apartment: '{}'.", apartment);

            String buttonAddressConfirm = "addressConfirm";
            submittedApplicationsPage.clickApplicationButton(buttonAddressConfirm);
            log.info("Clicked button with key: '{}'.", buttonAddressConfirm);

            //-------------

            submittedApplicationsPage.selectRadioButtonY_N("1_false");
            log.debug("Selected 'For already working' 'no' option.");

            submittedApplicationsPage.selectRadioButtonY_N("2_true");
            log.debug("Selected 'For new working' 'yes' option.");

            String person_count = "1";
            submittedApplicationsPage.enterText("person_count_dvp", person_count);
            log.debug("Entered person count: '{}'", person_count);

            String next_1 = "next";
            submittedApplicationsPage.clickApplicationButton(next_1);
            log.info("Clicked button with key: '{}'.", next_1);

            //--------------------

            String value_A = "Administratoriai";
            submittedApplicationsPage.selectDropdownButtonValue_VUI_DVP_PVK("jobName_DVP", value_A);
            log.debug("Selected 'Job Name' value: '{}'.", value_A);

            submittedApplicationsPage.enterJobDate_DVP();
            log.debug("Entered job date.");

            String function = "Funkcija 1";
            submittedApplicationsPage.enterTextArea("1", function);
            log.debug("Entered function: '{}'", function);

//            submittedApplicationsPage.selectRadioButtonY_N("3_false");
//            log.debug("Selected 'For already working' 'no' option.");

            String value_D = "Sunkaus neįgalumo lygis ar neviršijantis 25 procentų dalyvumo lygis (iki 2023 metų gruodžio 31 dienos – iki 25 procentų darbingumo lygis)";
            submittedApplicationsPage.selectDropdownButtonValue_VUI_DVP_PVK("disability_DVP", value_D);
            log.debug("Selected 'Job Name' value: '{}'.", value_D);

            String disability = "Negalia 2";
            submittedApplicationsPage.enterTextArea("2", disability);
            log.debug("Entered disability: '{}'", disability);

            String qualification = "Kvalifikacija 3";
            submittedApplicationsPage.enterTextArea("3", qualification);
            log.debug("Entered qualification: '{}'", qualification);

            String jobDescription = "Darbo vietos aprašymas 5";
            submittedApplicationsPage.enterTextArea("5", jobDescription);
            log.debug("Entered job description: '{}'", jobDescription);

            String equipment = "Priemonių įsigyjimas 6";
            submittedApplicationsPage.enterTextArea("6", equipment);
            log.debug("Entered job equipment: '{}'", equipment);

            String work = "Darbai 4";
            submittedApplicationsPage.enterTextArea("4", work);
            log.debug("Entered work: '{}'", work);

            String timeMode = "Kita";
            submittedApplicationsPage.selectDropdownTimeMode(timeMode);
            log.debug("Selected time mode: '{}'.", timeMode);

            String timeMode1 = "6 val. per dieną ir 4 d. per savaitę";
            submittedApplicationsPage.enterInputTimeModeOthers(timeMode1);
            log.debug("Selected time mode other: '{}'.", timeMode1);

            String salary_dvp = "1200";
            submittedApplicationsPage.enterText("salary_vui_dvp", salary_dvp);
            log.debug("Entered salary: '{}'", salary_dvp);

            String salary = "Užmokestis 7";
            submittedApplicationsPage.enterTextArea("7", salary);
            log.debug("Entered salary: '{}'", salary);

            submittedApplicationsPage.selectRadioButtonY_N("4_false");
            log.debug("Selected 'Temporary Job' 'no' option.");

            submittedApplicationsPage.selectRadioButtonY_N("5_false");
            log.debug("Selected 'Season Job' 'no' option.");

            submittedApplicationsPage.selectRadioButtonY_N("6_false");
            log.debug("Selected 'PVM' 'no' option.");

            String type1 = "Darbo vietos pritaikymui";
            submittedApplicationsPage.selectDropdownExpensesTyp_DVP(type1);
            log.debug("Selected adaptable job name: '{}'.", type1);

            String necessaryForJob = "Įsigyti";
            submittedApplicationsPage.selectDropdownNecessaryForJobOne_DVP(necessaryForJob);
            log.debug("Selected necessary for job: '{}'.", necessaryForJob);

            String purchase_name_DVP = "Darbo priemonė 1";
            submittedApplicationsPage.enterText("purchase_name_dvp", purchase_name_DVP);
            log.debug("Entered purchase name: '{}'", purchase_name_DVP);

            String purchase_parameter_1 = "Darbo priemonės 1 parametras 1";
            submittedApplicationsPage.enterText("purchase_parameter_dvp_1", purchase_parameter_1);
            log.debug("Entered purchase parameter 1: '{}'", purchase_parameter_1);

            String purchase_parameter_2 = "Darbo priemonės 1 parametras 2";
            submittedApplicationsPage.enterText("purchase_parameter_dvp_2", purchase_parameter_2);
            log.debug("Entered purchase parameter 2: '{}'", purchase_parameter_2);

            String purchase_parameter_3 = "Darbo priemonės 1 parametras 3";
            submittedApplicationsPage.enterText("purchase_parameter_dvp_3", purchase_parameter_3);
            log.debug("Entered purchase parameter 3: '{}'", purchase_parameter_3);

            String tool_count_1 = "1";
            submittedApplicationsPage.enterText("tool_count_dvp_1", tool_count_1);
            log.debug("Entered tool count 1: '{}'", tool_count_1);

            String price_amount_1 = "1000";
            submittedApplicationsPage.enterText("price_amount_1", price_amount_1);
            log.debug("Entered price amount 1: '{}'", price_amount_1);

            String funds_amount_1 = "800";
            submittedApplicationsPage.enterText("funds_amount_1", funds_amount_1);
            log.debug("Entered own funds amount 1: '{}'", funds_amount_1);

            String add = "add";
            submittedApplicationsPage.clickApplicationButton(add);
            log.info("Clicked button with key: '{}'.", add);

            String type2 = "Aplinkos pritaikymui";
            submittedApplicationsPage.selectDropdownExpensesTyp_DVP_2(type2);
            log.debug("Selected adaptable job name: '{}'.", type2);

            String necessaryForJob_2 = "Remontuoti";
            submittedApplicationsPage.selectDropdownNecessaryForJobOne_DVP_2(necessaryForJob_2);
            log.debug("Selected necessary for job: '{}'.", necessaryForJob_2);

            String repair_name_DVP = "Remonto išlaidų pavadinimas 1";
            submittedApplicationsPage.enterText("repair_name_dvp", repair_name_DVP);
            log.debug("Entered repair name: '{}'", repair_name_DVP);

            String workDescription = "Remonto darbai 8";
            submittedApplicationsPage.enterTextArea("8", workDescription);
            log.debug("Entered work description: '{}'", workDescription);

            String tool_count_2 = "2";
            submittedApplicationsPage.enterText("tool_count_dvp_2", tool_count_2);
            log.debug("Entered tool count 2: '{}'", tool_count_2);

            String price_amount_2 = "1000";
            submittedApplicationsPage.enterText("price_amount_2", price_amount_2);
            log.debug("Entered price amount 2: '{}'", price_amount_2);

            String funds_amount_2 = "900";
            submittedApplicationsPage.enterText("funds_amount_2", funds_amount_2);
            log.debug("Entered own funds amount 2: '{}'", funds_amount_2);

            submittedApplicationsPage.clickButtonAddRemoved();
            log.debug("Clicked 'AddRemoved' button.");

            stay();

            submittedApplicationsPage.clickButtonRemove();
            log.debug("Clicked 'Remove' button.");

            stay();

            submittedApplicationsPage.clickButtonRemoveConfirmation();
            log.debug("Clicked 'Remove Confirmation' button.");

            stay();

            submittedApplicationsPage.selectRadioButtonY_N("7_true");
            log.debug("Selected 'De Minimis' 'yes' option.");

            String programName = "Programa 1";
            submittedApplicationsPage.enterText("program_name", programName);
            log.debug("Entered  program name: '{}'", programName);

            submittedApplicationsPage.enterProjectDateFrom_DVP();
            log.debug("Entered project date from.");

            submittedApplicationsPage.enterProjectDateUntil_DVP();
            log.debug("Entered project date until.");

            String support_amount = "2000";
            submittedApplicationsPage.enterText("support_amount", support_amount);
            log.debug("Entered  support amount: '{}'", support_amount);

            String next_2 = "next";
            submittedApplicationsPage.clickApplicationButton(next_2);
            log.info("Clicked button with key: '{}'.", next_2);

            //--------------------
            SubmittedApplicationsPage.CheckboxHelper.setConfirmationCheckbox(driver, submittedApplicationsPage.checkboxConfirmationThree, true);
            log.debug("Checked 'Legal Entities' checkbox 1.");

            SubmittedApplicationsPage.CheckboxHelper.setConfirmationCheckbox(driver, submittedApplicationsPage.checkboxConfirmationFour, true);
            log.debug("Checked 'Legal Entities' checkbox 2.");

            SubmittedApplicationsPage.CheckboxHelper.setConfirmationCheckbox(driver, submittedApplicationsPage.checkboxConfirmationFive, true);
            log.debug("Checked 'Legal Entities' checkbox 3.");

            log.debug("Test finished: All 'Legal Entities' checkboxes checked successfully.");

            String next_3 = "next";
            submittedApplicationsPage.clickApplicationButton(next_3);
            log.info("Clicked button with key: '{}'.", next_3);

            //--------------------

            SubmittedApplicationsPage.RadioButtonHelper.selectRadioButton(driver, SubmittedApplicationsPage.RadioButtonHelper.RADIO_NO_1, true);
            log.info("Marking 'Ne' radio button 1");
            SubmittedApplicationsPage.RadioButtonHelper.selectRadioButton(driver, SubmittedApplicationsPage.RadioButtonHelper.RADIO_NO_2, true);
            log.info("Marking 'Ne' radio button 2");
            SubmittedApplicationsPage.RadioButtonHelper.selectRadioButton(driver, SubmittedApplicationsPage.RadioButtonHelper.RADIO_NO_3, true);
            log.info("Marking 'Ne' radio button 3");
            SubmittedApplicationsPage.RadioButtonHelper.selectRadioButton(driver, SubmittedApplicationsPage.RadioButtonHelper.RADIO_NO_4, true);
            log.info("Marking 'Ne' radio button 4");
            SubmittedApplicationsPage.RadioButtonHelper.selectRadioButton(driver, SubmittedApplicationsPage.RadioButtonHelper.RADIO_NO_5, true);
            log.info("Marking 'Ne' radio button 5");
            SubmittedApplicationsPage.RadioButtonHelper.selectRadioButton(driver, SubmittedApplicationsPage.RadioButtonHelper.RADIO_NO_6, true);
            log.info("Marking 'Ne' radio button 6");

            SubmittedApplicationsPage.RadioButtonHelper.selectRadioButton(driver, SubmittedApplicationsPage.RadioButtonHelper.RADIO_YES_7, true);
            log.info("Marking 'Taip' radio button 7");
            SubmittedApplicationsPage.RadioButtonHelper.selectRadioButton(driver, SubmittedApplicationsPage.RadioButtonHelper.RADIO_YES_8, true);
            log.info("Marking 'Taip' radio button 8");
            SubmittedApplicationsPage.RadioButtonHelper.selectRadioButton(driver, SubmittedApplicationsPage.RadioButtonHelper.RADIO_YES_9, true);
            log.info("Marking 'Taip' radio button 9");
            SubmittedApplicationsPage.RadioButtonHelper.selectRadioButton(driver, SubmittedApplicationsPage.RadioButtonHelper.RADIO_YES_10, true);
            log.info("Marking 'Taip' radio button 10");
            SubmittedApplicationsPage.RadioButtonHelper.selectRadioButton(driver, SubmittedApplicationsPage.RadioButtonHelper.RADIO_YES_11, true);
            log.info("Marking 'Taip' radio button 11");
            SubmittedApplicationsPage.RadioButtonHelper.selectRadioButton(driver, SubmittedApplicationsPage.RadioButtonHelper.RADIO_YES_12, true);
            log.info("Marking 'Taip' radio button 12");

            SubmittedApplicationsPage.CheckboxHelper.setConfirmationCheckbox(driver, submittedApplicationsPage.checkboxConfirmationStepFourDVP, true);
            log.debug("Checked 'ConfirmationStepFour'");

            String next_4 = "next";
            submittedApplicationsPage.clickApplicationButton(next_4);
            log.info("Clicked button with key: '{}'.", next_4);

            //--------------------

            submittedApplicationsPage.uploadFileStepFive();

            submittedApplicationsPage.fillDocumentFields(8);

            SubmittedApplicationsPage.CheckboxHelper.setConfirmationCheckbox(driver, submittedApplicationsPage.checkboxConfirmationDocument_DVP, true);
            log.debug("Checked 'ConfirmationDocument'");
            SubmittedApplicationsPage.CheckboxHelper.setConfirmationCheckbox(driver, submittedApplicationsPage.checkboxConfirmationApplication_DVP, true);
            log.debug("Checked 'ConfirmationApplication'");

            String review = "review";
            submittedApplicationsPage.clickApplicationButton(review);
            log.info("Clicked button with key: '{}'.", review);

            stay();

            //--------------------

            String save_draft = "save_draft";
            submittedApplicationsPage.clickApplicationButton(save_draft);
            log.info("Clicked button with key: '{}'.", save_draft);

            applicationFormsPage.verifySuccessMessage("Ruošinys sėkmingai išsaugotas.");

            String submit = "submit";
            submittedApplicationsPage.clickApplicationButton(submit);
            log.info("Clicked button with key: '{}'.", submit);

            String submitConfirmation = "submitConfirm";
            submittedApplicationsPage.clickApplicationButton(submitConfirmation);
            log.info("Clicked button with key: '{}'.", submitConfirmation);

            applicationFormsPage.verifySuccessMessage("Paraiška sėkmingai pateikta ir užregistruota.");
            log.info("Verified success message");

            log.info("Test 'testFillNewDVPApplicationFA' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testFillNewDVPApplicationFA' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testAddEvaluators_DVP() {

        log.info("Starting test:'testAddEvaluators'");

        try {
            TestUtils.loginAllTests(driver);
            log.debug("User logged in with test credentials");

            getAndInsertInvitationCodeText();
            log.debug("Invitation code inserted.");

            submittedApplicationsPage.clickButtonActionApplication();
            log.debug("Clicked on 'Action Application' button.");

            submittedApplicationsPage.clickButtonAddEvaluators();
            log.debug("Clicked on 'Add Evaluators' button.");

            submittedApplicationsPage.enterFirstEvaluationEndDate();
            log.debug("Entered first evaluation date.");

            String expectedEvaluator = "Adelė Kutienė";
            submittedApplicationsPage.selectValueByListEvaluators(expectedEvaluator);

            submittedApplicationsPage.clickButtonAddEvaluatorsConfirmation();
            log.debug("Clicked on 'Add Evaluators' confirmation button.");

            applicationFormsPage.verifySuccessMessage("Paraiškų vertinimai sėkmingai priskirti vertintojams.");
            log.info("Verified success message");

            log.info("Test 'testAddEvaluators' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testAddEvaluators' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testFillNewPVKApplicationFA() {
        log.info("Starting test:'testFillNewDVPApplicationFA'");

        try {
            TestUtils.loginAllTests(driver);
            log.debug("User logged in with test credentials");

            getAndInsertInvitationCodeText();
            log.debug("Invitation code inserted.");

            submittedApplicationsPage.clickButtonActionApplication();
            log.debug("Clicked on 'Action Application' button.");

            submittedApplicationsPage.clickButtonNewApplication();
            log.debug("Clicked on 'New Application' button.");

            submittedApplicationsPage.selectRadioButtonFA();
            log.debug("Selected the 'RadioButtonFA' option.");

            String applicant = "Pavardenis GR777";
            submittedApplicationsPage.selectValueApplicant_FA(applicant);
            log.debug("Selected applicant: '{}'.", applicant);

            String phone = "61298745";
            submittedApplicationsPage.enterText("phone", phone);
            log.debug("Entered phone number: '{}'", phone);

            String email = "test@autotest.com";
            submittedApplicationsPage.enterText("e_mail", email);
            log.debug("Entered email: '{}'", email);

            submittedApplicationsPage.selectRadioButtonY_N("job_for_yourself_pvk");

            String value_BS = "Individuali įmonė";
            submittedApplicationsPage.selectDropdownButtonValue_VUI_DVP_PVK("businessStructure_PVK", value_BS);
            log.debug("Selected 'BusinessStructure' value: '{}'.", value_BS);

//            //address
//            //--------------
            submittedApplicationsPage.clickAddressComponent_PVK();
            log.debug("Clicked on 'Address Component' button.");
//
            String country = "Lietuva";
            submittedApplicationsPage.selectDropdownAddressCountry(country);
            log.debug("Selected country: '{}'.", country);

            String citySearchTerm = "Klaipėda";
//
            try {
                // Iškviečiame metodą, kuris įveda ir pasirenka miestą
                submittedApplicationsPage.selectDropdownAddressCity(citySearchTerm);

                // Patikriname, ar pasirinktas būtent „Vilnius“
                WebElement selectedCity = driver.findElement(By.xpath("//mat-option[@aria-selected='true']//span[contains(@class, 'area-center')]"));
                String selectedCityText = selectedCity.getText().trim();

                Assert.assertTrue(selectedCityText.equalsIgnoreCase(citySearchTerm),
                        "The selected city is incorrect. Found: " + selectedCityText);

                System.out.println("Test passed: correct city selected - " + selectedCityText);

            } catch (Exception e) {
                Assert.fail("Test failed: " + e.getMessage());
            }
//
            String street = "Agluonos g.";
            submittedApplicationsPage.selectDropdownAddressStreet(street);
            log.debug("Selected street: '{}'.", street);
//
            String house = "2";
            submittedApplicationsPage.selectDropdownAddressHouse(house);
            log.debug("Selected house: '{}'.", house);

//            String apartment = "1";
//            submittedApplicationsPage.selectDropdownAddressApartment(apartment);
//            log.debug("Selected apartment: '{}'.", apartment);

            String buttonAddressConfirm = "addressConfirm";
            submittedApplicationsPage.clickApplicationButton(buttonAddressConfirm);
            log.info("Clicked button with key: '{}'.", buttonAddressConfirm);

            String value_evrk = "01.11 - Grūdinių (išskyrus ryžius), ankštinių ir aliejingų sėklų augalų auginimas";
            submittedApplicationsPage.selectDropdownButtonCheckBoxValueEVRK_VUI_DVP_PVK("evrk_PVK", value_evrk);
            log.debug("Selected EVRK value: '{}'.", value_evrk);

            submittedApplicationsPage.selectRadioButtonY_N("selfEmploymentTerminated_PVK");

            String value_evrk_1 = "02.10 - Miško medžių auginimas ir kita miškininkystės veikla";
            submittedApplicationsPage.selectDropdownButtonCheckBoxValueEVRK_VUI_DVP_PVK("evrk_true_PVK", value_evrk_1);
            log.debug("Selected EVRK_true value: '{}'.", value_evrk_1);

            String labelText = "1.2.1. vykdant veiklą sumažės gamtos ar kitų išteklių naudojimas";
            submittedApplicationsPage.clickMatCheckboxByLabelText_PVK(labelText);
            log.debug("Clicked on mat-checkbox with label containing '{}'.", labelText);

            String priority = "Prioritetas 1";
            submittedApplicationsPage.enterTextArea("1", priority);
            log.debug("Entered priority: '{}'", priority);

            String next_1 = "next";
            submittedApplicationsPage.clickApplicationButton(next_1);
            log.info("Clicked button with key: '{}'.", next_1);

//------------------------------

            String value_JN = "Administratoriai";
            submittedApplicationsPage.selectDropdownButtonValue_VUI_DVP_PVK("jobName_PVK", value_JN);
            log.debug("Selected 'Job Name' value: '{}'.", value_JN);

            submittedApplicationsPage.selectRadioButtonY_N("2_true");
            log.debug("Selected 'For Qualification' 'yes' option.");

            submittedApplicationsPage.selectRadioButtonY_N("3_true");
            log.debug("Selected 'For Experience' 'yes' option.");

            submittedApplicationsPage.selectRadioButtonY_N("4_true");
            log.debug("Selected 'For Supported Person' 'yes' option.");

            submittedApplicationsPage.selectRadioButtonY_N("5_true");
            log.debug("Selected 'For Repair' 'yes' option.");

            String function = "Funkcija 2";
            submittedApplicationsPage.enterTextArea("2", function);
            log.debug("Entered function: '{}'", function);

            String qualification = "Kvalifikacija 3";
            submittedApplicationsPage.enterTextArea("3", qualification);
            log.debug("Entered qualification: '{}'", qualification);

            String experience = "Patirtis 4";
            submittedApplicationsPage.enterTextArea("4", experience);
            log.debug("Entered experience: '{}'", experience);

            String description_1 = "Vietos aprašymas 5";
            submittedApplicationsPage.enterTextArea("5", description_1);
            log.debug("Entered description: '{}'", description_1);

            String description_2 = "Proceso aprašymas 6";
            submittedApplicationsPage.enterTextArea("6", description_2);
            log.debug("Entered description_2: '{}'", description_2);

            String description_3 = "Remonto aprašymas 7";
            submittedApplicationsPage.enterTextArea("7", description_3);
            log.debug("Entered description_3: '{}'", description_3);

            String salary = "Užmokestis 8";
            submittedApplicationsPage.enterTextArea("8", salary);
            log.debug("Entered salary: '{}'", salary);

            String supported_PVK = "Bedarbiai, kurie yra darbingo amžiaus neįgalieji, kuriems nustatytas iki 25 procentų darbingumo lygis arba sunkus neįgalumo lygis";
            submittedApplicationsPage.selectDropdownButtonCheckBoxValueEVRK_VUI_DVP_PVK("supported_PVK", supported_PVK);
            log.debug("Selected EVRK_true value: '{}'.", supported_PVK);

            submittedApplicationsPage.enterJobDate_DVP();
            log.debug("Entered job date.");

            String timeMode = "Kita";
            submittedApplicationsPage.selectDropdownTimeMode(timeMode);
            log.debug("Selected time mode: '{}'.", timeMode);

            String timeMode1 = "6 val. per dieną ir 4 d. per savaitę";
            submittedApplicationsPage.enterInputTimeModeOthers(timeMode1);
            log.debug("Selected time mode other: '{}'.", timeMode1);

            String salary_1 = "1200";
            submittedApplicationsPage.enterText("salary_pvk", salary_1);
            log.debug("Entered salary: '{}'", salary_1);

            submittedApplicationsPage.selectRadioButtonY_N("6_false");
            log.debug("Selected 'Temporary Job' 'no' option.");

            submittedApplicationsPage.selectRadioButtonY_N("7_false");
            log.debug("Selected 'Season Job' 'no' option.");

            String necessaryForJob = "Įsigyti";
            submittedApplicationsPage.selectDropdownNecessaryForJobOne_PVK(necessaryForJob);
            log.debug("Selected necessary for job: '{}'.", necessaryForJob);

            String tool = "Darbo priemonė 1";
            submittedApplicationsPage.enterText("tool", tool);
            log.debug("Entered tool: '{}'", tool);

            String tool_parameter_1 = "Darbo priemonės 1 parametras 1";
            submittedApplicationsPage.enterText("tool_parameter_1", tool_parameter_1);
            log.debug("Entered tool parameter 1: '{}'", tool_parameter_1);

            String tool_parameter_2 = "Darbo priemonės 1 parametras 2";
            submittedApplicationsPage.enterText("tool_parameter_2", tool_parameter_2);
            log.debug("Entered tool parameter 2: '{}'", tool_parameter_2);

            String tool_parameter_3 = "Darbo priemonės 1 parametras 3";
            submittedApplicationsPage.enterText("tool_parameter_3", tool_parameter_3);
            log.debug("Entered tool parameter 3: '{}'", tool_parameter_3);

            String toolCount = "1";
            submittedApplicationsPage.enterText("tool_count", toolCount);
            log.debug("Entered tool count: '{}'", toolCount);

            String price_amount_1 = "1000";
            submittedApplicationsPage.enterText("price_amount_1", price_amount_1);
            log.debug("Entered price amount 1: '{}'", price_amount_1);

            String funds_amount_1 = "800";
            submittedApplicationsPage.enterText("funds_amount_1", funds_amount_1);
            log.debug("Entered own funds amount 1: '{}'", funds_amount_1);
            String add = "add";

            submittedApplicationsPage.clickApplicationButton(add);
            log.info("Clicked button with key: '{}'.", add);

            String necessaryForJob_2 = "Remontuoti";
            submittedApplicationsPage.selectDropdownNecessaryForJobOne_PVK_2(necessaryForJob_2);
            log.debug("Selected necessary for job: '{}'.", necessaryForJob_2);

            String repair_name = "Remonto pavadinimas 1";
            submittedApplicationsPage.enterText("repair_name", repair_name);
            log.debug("Entered repair name: '{}'", repair_name);

            String repair = "Remonto darbai 9";
            submittedApplicationsPage.enterTextArea("9", repair);
            log.debug("Entered qualification: '{}'", repair);

            String price_amount_2 = "1000";
            submittedApplicationsPage.enterText("price_amount_2", price_amount_2);
            log.debug("Entered price amount 2: '{}'", price_amount_2);

            String funds_amount_2 = "900";
            submittedApplicationsPage.enterText("funds_amount_2", funds_amount_2);
            log.debug("Entered own funds amount 2: '{}'", funds_amount_2);

            submittedApplicationsPage.clickButtonAddRemoved();
            log.debug("Clicked 'AddRemoved' button.");

            stay();

            submittedApplicationsPage.clickButtonRemove();
            log.debug("Clicked 'Remove' button.");

            stay();

            submittedApplicationsPage.clickButtonRemoveConfirmation();
            log.debug("Clicked 'Remove Confirmation' button.");

            stay();

            String next_2 = "next";
            submittedApplicationsPage.clickApplicationButton(next_2);
            log.info("Clicked button with key: '{}'.", next_2);

            //--------------------

            submittedApplicationsPage.uploadFileStepFive();

            submittedApplicationsPage.fillDocumentFields(9);

            String labelText_2 = "Patvirtinu, kad paraiškoje ir kituose dokumentuose pateikta informacija yra teisinga.";
            submittedApplicationsPage.clickMatCheckboxByLabelText_PVK(labelText_2);
            log.debug("Clicked on mat-checkbox with label containing '{}'.", labelText_2);

            String labelText_3 = "Įsipareigoju leisti Užimtumo tarnybai patikrinti pateiktą informaciją, jeigu, jos manymu, tai yra būtina, ir man žinoma, kad, jeigu gausiu subsidiją, darbo vietą (-as) turėsiu įsteigti per 10 mėnesių nuo Paramos verslui kurti sutarties pasirašymo ir nepanaikinti už subsidijos lėšas įsteigtos (-ų) darbo vietos (-ų) 36 mėnesius nuo jos (jų) įsteigimo dienos.";
            submittedApplicationsPage.clickMatCheckboxByLabelText_PVK(labelText_3);
            log.debug("Clicked on mat-checkbox with label containing '{}'.", labelText_3);

            String labelText_4 = "Patvirtinu, kad darbo vietą sau arba sau ir Užimtumo tarnybos siųstam bedarbiui (siųstiems bedarbiams) ketinu steigti Lietuvos Respublikos smulkiojo ir vidutinio verslo plėtros įstatyme apibrėžtoje labai mažoje įmonėje.";
            submittedApplicationsPage.clickMatCheckboxByLabelText_PVK(labelText_4);
            log.debug("Clicked on mat-checkbox with label containing '{}'.", labelText_4);

            String review = "review";
            submittedApplicationsPage.clickApplicationButton(review);
            log.info("Clicked button with key: '{}'.", review);

            //--------------------

            String save_draft = "save_draft";
            submittedApplicationsPage.clickApplicationButton(save_draft);
            log.info("Clicked button with key: '{}'.", save_draft);

            applicationFormsPage.verifySuccessMessage("Ruošinys sėkmingai išsaugotas.");

            String submit = "submit";
            submittedApplicationsPage.clickApplicationButton(submit);
            log.info("Clicked button with key: '{}'.", submit);

            String submitConfirmation = "submitConfirm";
            submittedApplicationsPage.clickApplicationButton(submitConfirmation);
            log.info("Clicked button with key: '{}'.", submitConfirmation);

            applicationFormsPage.verifySuccessMessage("Paraiška sėkmingai pateikta ir užregistruota.");
            log.info("Verified success message");

            log.info("Test 'testFillNewDVPApplicationFA' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testFillNewDVPApplicationFA' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }


}
