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
    void setUpInvitationPage() {
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

//            submittedApplicationsPage.clickRadioButtonFA();
//            log.debug("Selected the 'RadioButtonFA' option.");
//
//            String phoneNumber = "61298745";
//            submittedApplicationsPage.enterPhoneNumber(phoneNumber);
//            log.debug("Entered phone number: '{}'.", phoneNumber);
//
//            String emailInput = "test@autotest.com";
//            submittedApplicationsPage.enterEmailAddress(emailInput);
//            log.debug("Entered email: '{}'.", emailInput);

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
            submittedApplicationsPage.enterJobCount(jobCount);
            log.debug("Entered job count: '{}'.", jobCount);

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

            submittedApplicationsPage.clickRadioButtonWithDisabilitiesVUI();
            log.debug("Selected 'RadioButtonWithDisabilities' option.");

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

            String salary = "1200";
            submittedApplicationsPage.enterSalary(salary);
            log.debug("Entered salary amount: '{}'.", salary);

            String salaryDescription = "Užmokestis 4";
            submittedApplicationsPage.enterTextArea("4", salaryDescription);
            log.debug("Entered salary description: '{}'", salaryDescription);

            submittedApplicationsPage.clickRadioButtonTemporaryJob();
            log.debug("Selected 'Temporary Job' radio button.");

            submittedApplicationsPage.clickRadioButtonSeasonJob();
            log.debug("Selected 'Season Job' radio button.");

            submittedApplicationsPage.clickRadioButtonEnergy();
            log.debug("Selected 'Energy' radio button.");

            submittedApplicationsPage.clickRadioButtonRepair();
            log.debug("Selected 'Repair' radio button.");

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

            submittedApplicationsPage.clickRadioButtonPVM();
            log.debug("Selected 'PVM' radio button.");

            String necessaryForJob = "Įsigyti";
            submittedApplicationsPage.selectDropdownNecessaryForJobOne(necessaryForJob);
            log.debug("Selected necessary for job: '{}'.", necessaryForJob);

            String tool = "Darbo priemonė 1";
            submittedApplicationsPage.enterTool(tool);
            log.debug("Selected tool: '{}'.", tool);

            String toolParameter1 = "Darbo priemonės 1 parametras 1";
            submittedApplicationsPage.enterToolParameterOne(toolParameter1);
            log.debug("Selected tool parameter one: '{}'.", toolParameter1);

            String toolParameter2 = "Darbo priemonės 1 parametras 2";
            submittedApplicationsPage.enterToolParameterTwo(toolParameter2);
            log.debug("Selected tool parameter two: '{}'.", toolParameter2);

            String toolParameter3 = "Darbo priemonės 1 parametras 3";
            submittedApplicationsPage.enterToolParameterThree(toolParameter3);
            log.debug("Selected tool parameter three: '{}'.", toolParameter3);

            String toolCount = "1";
            submittedApplicationsPage.enterToolsCount(toolCount);
            log.debug("Entered tool count: '{}'.", toolCount);

            String amount2 = "1000";
            submittedApplicationsPage.enterPriceOne(amount2);
            log.debug("Entered price amount 1: '{}'.", amount2);

            stay();

            String amount3 = "800";
            submittedApplicationsPage.enterOwnFundsOne(amount3);
            log.debug("Entered own funds amount 1: '{}'.", amount3);

            stay();

            submittedApplicationsPage.clickButtonAdd();
            log.debug("Clicked 'Add' button.");

            String necessaryForJob1 = "Remontuoti";
            submittedApplicationsPage.selectDropdownNecessaryForJobTwo(necessaryForJob1);
            log.debug("Selected necessary for job 'to repair': '{}'.", necessaryForJob1);

            String repairName = "Remonto pavadinimas 1";
            submittedApplicationsPage.enterRepairName(repairName);
            log.debug("Selected repair name: '{}'.", repairName);

            String repairDescription = "Remonto aprašymas 9";
            submittedApplicationsPage.enterTextArea("9", repairDescription);
            log.debug("Entered repair description proses: '{}'", repairDescription);

            String amount2_1 = "1000";
            submittedApplicationsPage.enterPriceTwo(amount2_1);
            log.debug("Entered price amount 2: '{}'.", amount2_1);

            stay();

            String amount3_1 = "900";
            submittedApplicationsPage.enterOwnFundsTwo(amount3_1);
            log.debug("Entered own funds amount 2: '{}'.", amount3_1);

            stay();

            submittedApplicationsPage.clickButtonAddRemoved();
            log.debug("Clicked 'AddRemoved' button.");

            stay();

            submittedApplicationsPage.clickButtonRemove();
            log.debug("Clicked 'Remove' button.");

            stay();

            submittedApplicationsPage.clickButtonRemoveConfirmation();
            log.debug("Clicked 'Remove Confirmation' button.");

            stay();

            String perc1 = "50";
            submittedApplicationsPage.enterCountryPerc(perc1);
            log.debug("Entered country percentage: '{}'.", perc1);

            String perc2 = "50";
            submittedApplicationsPage.enterInstitutionPerc(perc2);
            log.debug("Entered institution percentage: '{}'.", perc2);

            String perc3 = "50";
            submittedApplicationsPage.enterMunicipalityPerc(perc3);
            log.debug("Entered municipality percentage: '{}'.", perc3);

            submittedApplicationsPage.clickRadioButtonDeMinimis();
            log.debug("Selected 'De Minimis' radio button.");

            String programName = "Programa 1";
            submittedApplicationsPage.enterProgramName(programName);
            log.debug("Entered program name: '{}'.", programName);

            submittedApplicationsPage.enterProjectDateFrom();
            log.debug("Entered project date from.");

            submittedApplicationsPage.enterProjectDateUntil();
            log.debug("Entered project date until.");

            String amount6 = "2000";
            submittedApplicationsPage.enterSupportAmount(amount6);
            log.debug("Entered support amount: '{}'.", amount6);

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
            //istrinti
//            submittedApplicationsPage.clickButtonSaveDraft();
//            log.debug("Clicked 'SaveDraft' button after checkboxes.");

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


//            String phoneNumber = "61298745";
//            submittedApplicationsPage.enterPhoneNumber(phoneNumber);
//            log.debug("Entered phone number: '{}'.", phoneNumber);
//
//            String emailInput = "test@autotest.com";
//            submittedApplicationsPage.enterEmailAddress(emailInput);
//            log.debug("Entered email: '{}'.", emailInput);

            //DVP
            invitationsPage.setCheckbox(submittedApplicationsPage.checkboxConfirmationOne, true);
            log.debug("Checked 'For workplace adaptation' checkbox 1.");

            invitationsPage.setCheckbox(submittedApplicationsPage.checkboxConfirmationTwo, true);
            log.debug("Checked 'For environmental adaptation' checkbox 2.");
            //DVP

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

            //DVP
            submittedApplicationsPage.selectRadioButtonForAlreadyWorking_DVP_step1();
            log.debug("Selected 'For already working' option.");

//            String jobCount1 = "1";
//            submittedApplicationsPage.enterPersonCountOne_DVP(jobCount1);
//            log.debug("Entered person count1: '{}'.", jobCount1);

            submittedApplicationsPage.selectRadioButtonForNewWorking_DVP_step1();
            log.debug("Selected 'For new working' option.");

            String jobCount2 = "1";
            submittedApplicationsPage.enterPersonCountTwo_DVP(jobCount2);
            log.debug("Entered person count2: '{}'.", jobCount2);
            //DVP

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

            submittedApplicationsPage.clickRadioButtonForAlreadyWorking_DVP_step2();
            log.debug("Selected 'For already working' 'no' option.");

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

            String amount1 = "1200";
            submittedApplicationsPage.enterSalary(amount1);
            log.debug("Entered salary amount: '{}'.", amount1);

            String salary = "Užmokestis 7";
            submittedApplicationsPage.enterTextArea("7", salary);
            log.debug("Entered salary: '{}'", salary);

            submittedApplicationsPage.clickRadioButtonTemporaryJob_DVP();
            log.debug("Selected 'Temporary Job' radio button.");

            submittedApplicationsPage.clickRadioButtonSeasonJob_DVP();
            log.debug("Selected 'Season Job' radio button.");

            submittedApplicationsPage.clickRadioButtonPVM();
            log.debug("Selected 'PVM' radio button.");

            String type1 = "Darbo vietos pritaikymui";
            submittedApplicationsPage.selectDropdownExpensesTyp_DVP(type1);
            log.debug("Selected adaptable job name: '{}'.", type1);

            String necessaryForJob = "Įsigyti";
            submittedApplicationsPage.selectDropdownNecessaryForJobOne_DVP(necessaryForJob);
            log.debug("Selected necessary for job: '{}'.", necessaryForJob);

            String tool1 = "Darbo priemonė 1";
            submittedApplicationsPage.enterTool_DVP(tool1);
            log.debug("Selected tool: '{}'.", tool1);

            String toolParameter1 = "Darbo priemonės 1 parametras 1";
            submittedApplicationsPage.enterToolParameterOne_DVP(toolParameter1);
            log.debug("Selected tool parameter one: '{}'.", toolParameter1);

            String toolParameter2 = "Darbo priemonės 1 parametras 2";
            submittedApplicationsPage.enterToolParameterTwo_DVP(toolParameter2);
            log.debug("Selected tool parameter two: '{}'.", toolParameter2);

            String toolParameter3 = "Darbo priemonės 1 parametras 3";
            submittedApplicationsPage.enterToolParameterThree_DVP(toolParameter3);
            log.debug("Selected tool parameter three: '{}'.", toolParameter3);

            String toolCount1 = "1";
            submittedApplicationsPage.enterToolsCount_DVP(toolCount1);
            log.debug("Entered tool count: '{}'.", toolCount1);

            String amount2 = "1000";
            submittedApplicationsPage.enterPriceOne(amount2);
            log.debug("Entered price amount: '{}'.", amount2);

            String amount3 = "800";
            submittedApplicationsPage.enterOwnFundsOne(amount3);
            log.debug("Entered own funds amount: '{}'.", amount3);

            stay();

            submittedApplicationsPage.clickButtonAdd();
            log.debug("Clicked 'Add' button.");

            String type2 = "Aplinkos pritaikymui";
            submittedApplicationsPage.selectDropdownExpensesTyp_DVP_2(type2);
            log.debug("Selected adaptable job name: '{}'.", type2);

            String necessaryForJob_2 = "Remontuoti";
            submittedApplicationsPage.selectDropdownNecessaryForJobOne_DVP_2(necessaryForJob_2);
            log.debug("Selected necessary for job: '{}'.", necessaryForJob_2);

            String tool1_2 = "Darbo priemonė 2 remontui";
            submittedApplicationsPage.enterTool_DVP_2(tool1_2);
            log.debug("Selected tool: '{}'.", tool1_2);

            String workDescription = "Remonto darbai 8";
            submittedApplicationsPage.enterTextArea("8", workDescription);
            log.debug("Entered work description: '{}'", workDescription);

            String toolCount1_2 = "2";
            submittedApplicationsPage.enterToolsCount_DVP_2(toolCount1_2);
            log.debug("Entered tool count: '{}'.", toolCount1_2);

            String amount2_2 = "1000";
            submittedApplicationsPage.enterPriceTwo(amount2_2);
            log.debug("Entered price amount: '{}'.", amount2_2);

            String amount3_2 = "900";
            submittedApplicationsPage.enterOwnFundsTwo(amount3_2);
            log.debug("Entered own funds amount: '{}'.", amount3_2);

            stay();

            submittedApplicationsPage.clickButtonAddRemoved();
            log.debug("Clicked 'AddRemoved' button.");

            stay();

            submittedApplicationsPage.clickButtonRemove();
            log.debug("Clicked 'Remove' button.");

            stay();

            submittedApplicationsPage.clickButtonRemoveConfirmation();
            log.debug("Clicked 'Remove Confirmation' button.");

            stay();

            submittedApplicationsPage.clickRadioButtonDeMinimis();
            log.debug("Selected 'De Minimis' radio button.");

            String programName = "Programa 1";
            submittedApplicationsPage.enterProgramName(programName);
            log.debug("Entered program name: '{}'.", programName);

            submittedApplicationsPage.enterProjectDateFrom_DVP();
            log.debug("Entered project date from.");

            submittedApplicationsPage.enterProjectDateUntil_DVP();
            log.debug("Entered project date until.");

            String amount4 = "2000";
            submittedApplicationsPage.enterSupportAmount(amount4);
            log.debug("Entered support amount: '{}'.", amount4);

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


//            String phoneNumber = "61298745";
//            submittedApplicationsPage.enterPhoneNumber(phoneNumber);
//            log.debug("Entered phone number: '{}'.", phoneNumber);
//
//            String emailInput = "test@autotest.com";
//            submittedApplicationsPage.enterEmailAddress(emailInput);
//            log.debug("Entered email: '{}'.", emailInput);

            submittedApplicationsPage.selectRadioButtonY_N_PVK("jobForYourself_PVK");

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

            submittedApplicationsPage.selectRadioButtonY_N_PVK("selfEmploymentTerminated_PVK");

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

//            submittedApplicationsPage.selectRadioButtonY_N_PVK("qualification_PVK_1");
//            log.debug("Selected 'ForQualification' 'yes' option.");

            submittedApplicationsPage.clickRadioButtonForQualification_PVK_step2();
            log.debug("Selected 'ForQualification' 'yes' option.");

            submittedApplicationsPage.selectRadioButtonY_N_PVK("experience_PVK_1");
            log.debug("Selected 'ForExperience' 'yes' option.");

            submittedApplicationsPage.selectRadioButtonY_N_PVK("supportedPerson_PVK");
            log.debug("Selected 'ForSupportedPerson' 'yes' option.");

            submittedApplicationsPage.selectRadioButtonY_N_PVK("repair_PVK_1");
            log.debug("Selected 'ForRepair' 'yes' option.");

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
            submittedApplicationsPage.enterText_PVK("salary_1", salary_1);
            log.debug("Entered salary: '{}'", salary_1);

            submittedApplicationsPage.selectRadioButtonY_N_PVK("temporaryJob_PVK");
            log.debug("Selected 'Temporary Job' 'no' option.");

            submittedApplicationsPage.selectRadioButtonY_N_PVK("seasonJob_PVK");
            log.debug("Selected 'Season Job' 'no' option.");

            String necessaryForJob = "Įsigyti";
            submittedApplicationsPage.selectDropdownNecessaryForJobOne_PVK(necessaryForJob);
            log.debug("Selected necessary for job: '{}'.", necessaryForJob);

            String tool1 = "Darbo priemonė 1";
            submittedApplicationsPage.enterTool_PVK(tool1);
            log.debug("Selected tool: '{}'.", tool1);

            String toolParameter1 = "Darbo priemonės 1 parametras 1";
            submittedApplicationsPage.enterToolParameterOne_PVK(toolParameter1);
            log.debug("Selected tool parameter one: '{}'.", toolParameter1);

            String toolParameter2 = "Darbo priemonės 1 parametras 2";
            submittedApplicationsPage.enterToolParameterTwo_PVK(toolParameter2);
            log.debug("Selected tool parameter two: '{}'.", toolParameter2);

            String toolParameter3 = "Darbo priemonės 1 parametras 3";
            submittedApplicationsPage.enterToolParameterThree_PVK(toolParameter3);
            log.debug("Selected tool parameter three: '{}'.", toolParameter3);

            String toolCount1 = "1";
            submittedApplicationsPage.enterToolsCount_PVK(toolCount1);
            log.debug("Entered tool count: '{}'.", toolCount1);

            String amount2 = "1000";
            submittedApplicationsPage.enterPriceOne(amount2);
            log.debug("Entered price amount: '{}'.", amount2);

            String amount3 = "800";
            submittedApplicationsPage.enterOwnFundsOne(amount3);
            log.debug("Entered own funds amount: '{}'.", amount3);

            stay();

            submittedApplicationsPage.clickButtonAdd();
            log.debug("Clicked 'Add' button.");

            String necessaryForJob_2 = "Remontuoti";
            submittedApplicationsPage.selectDropdownNecessaryForJobOne_PVK_2(necessaryForJob_2);
            log.debug("Selected necessary for job: '{}'.", necessaryForJob_2);

            String tool1_2 = "Darbo priemonė 2 remontui";
            submittedApplicationsPage.enterTool_PVK_2(tool1_2);
            log.debug("Selected tool: '{}'.", tool1_2);

            String repair = "Remonto darbai 9";
            submittedApplicationsPage.enterTextArea("9", repair);
            log.debug("Entered qualification: '{}'", repair);

            String amount2_2 = "1000";
            submittedApplicationsPage.enterPriceTwo(amount2_2);
            log.debug("Entered price amount: '{}'.", amount2_2);

            String amount3_2 = "900";
            submittedApplicationsPage.enterOwnFundsTwo(amount3_2);
            log.debug("Entered own funds amount: '{}'.", amount3_2);

            stay();

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
