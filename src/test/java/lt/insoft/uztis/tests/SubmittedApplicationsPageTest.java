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

            submittedApplicationsPage.clickRadioButtonFA();
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

            String evrk = "01.11 - Grūdinių (išskyrus ryžius), ankštinių ir aliejingų sėklų augalų auginimas";
            submittedApplicationsPage.selectValueByListEVRK_VUI(evrk);
            log.debug("Selected EVRK value: '{}'.", evrk);

            //address
            //--------------
            submittedApplicationsPage.clickAddressComponent();
            log.debug("Clicked on 'Address Component' button.");

            String country = "Lietuva";
            submittedApplicationsPage.selectDropdownAddressCountry(country);
            log.debug("Selected country: '{}'.", country);

            String citySearchTerm = "Vilnius";

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

            String street = "A. Domaševičiaus g.";
            submittedApplicationsPage.selectDropdownAddressStreet(street);
            log.debug("Selected street: '{}'.", street);

            String house = "1";
            submittedApplicationsPage.selectDropdownAddressHouse(house);
            log.debug("Selected house: '{}'.", house);

            String apartment = "1";
            submittedApplicationsPage.selectDropdownAddressApartment(apartment);
            log.debug("Selected apartment: '{}'.", apartment);

            submittedApplicationsPage.clickButtonConfirm();
            log.debug("Clicked 'Confirm' button.");
            //-------------

            submittedApplicationsPage.enterDate();
            log.debug("Entered project date.");

            String jobCount = "1";
            submittedApplicationsPage.enterJobCount(jobCount);
            log.debug("Entered job count: '{}'.", jobCount);

            submittedApplicationsPage.clickButtonNext();
            log.debug("Clicked 'Next' button.");

            //--------------------
            String jobName = "Administratoriai";
            submittedApplicationsPage.selectDropdownJobName(jobName);
            log.debug("Selected job name: '{}'.", jobName);

            String function = "Funkcija 1";
            submittedApplicationsPage.enterInputJobFunction(function);
            log.debug("Entered job function: '{}'.", function);

            submittedApplicationsPage.clickRadioButtonWithDisabilitiesVUI();
            log.debug("Selected 'RadioButtonWithDisabilities' option.");

            String disabilities = "Bedarbiai, kurie yra darbingo amžiaus neįgalieji, kuriems nustatytas iki 25 procentų darbingumo lygis arba sunkus neįgalumo lygis";
            submittedApplicationsPage.selectValueByListDisabilitiesType_VUI(disabilities);
            log.debug("Selected disabilities type: '{}'.", disabilities);

            String disabilitiesDescription = "Negalia 1";
            submittedApplicationsPage.enterDisabilities(disabilitiesDescription);
            log.debug("Entered disability information: '{}'.", disabilitiesDescription);

            String timeMode = "Kita";
            submittedApplicationsPage.selectDropdownTimeMode(timeMode);
            log.debug("Selected time mode: '{}'.", timeMode);

            String timeMode2 = "6 val. per dieną ir 4 d. per savaitę";
            submittedApplicationsPage.enterInputTimeModeOthers(timeMode2);
            log.debug("Selected time mode other: '{}'.", timeMode2);

            String qualificationDescription = "Kvalifikacija 1";
            submittedApplicationsPage.enterQualification(qualificationDescription);
            log.debug("Entered qualification: '{}'.", qualificationDescription);

            submittedApplicationsPage.enterJobDate();
            log.debug("Entered job date.");

            String salary = "1200";
            submittedApplicationsPage.enterSalary(salary);
            log.debug("Entered salary amount: '{}'.", salary);

            String salaryDescription = "Užmokestis 1";
            submittedApplicationsPage.enterSalaryDescription(salaryDescription);
            log.debug("Entered salary description: '{}'.", salaryDescription);

            submittedApplicationsPage.clickRadioButtonTemporaryJob();
            log.debug("Selected 'Temporary Job' radio button.");

            submittedApplicationsPage.clickRadioButtonSeasonJob();
            log.debug("Selected 'Season Job' radio button.");

            submittedApplicationsPage.clickRadioButtonEnergy();
            log.debug("Selected 'Energy' radio button.");

            submittedApplicationsPage.clickRadioButtonRepair();
            log.debug("Selected 'Repair' radio button.");

            String activity = "Veikla 1";
            submittedApplicationsPage.enterJobDescription(activity);
            log.debug("Entered job description: '{}'.", activity);

            String elementDescription = "Procesas 1";
            submittedApplicationsPage.enterProsesDescriptionVUI_equipmentDescriptionDVP(elementDescription);
            log.debug("Entered process description: '{}'.", elementDescription);

            String energyDescription = "Energija 1";
            submittedApplicationsPage.enterEnergyInformationVUI(energyDescription);
            log.debug("Entered energy information: '{}'.", energyDescription);

            String repairDescription = "Remontas 1";
            submittedApplicationsPage.enterRepairInformation(repairDescription);
            log.debug("Entered repair information: '{}'.", repairDescription);

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

            String repairDescription1 = "Remonto aprašymas 1";
            submittedApplicationsPage.enterRepairDescription(repairDescription1);
            log.debug("Entered repair information: '{}'.", repairDescription1);


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

            submittedApplicationsPage.clickButtonNext();
            log.debug("Clicked 'Next' button.");

            //--------------------
//            invitationsPage.setCheckbox(submittedApplicationsPage.checkboxConfirmationOne, true);
//            log.debug("Checked 'For workplace adaptation' checkbox 1.");
//
//            invitationsPage.setCheckbox(submittedApplicationsPage.checkboxConfirmationTwo, true);
//            log.debug("Checked 'For environmental adaptation' checkbox 2.");

                SubmittedApplicationsPage.CheckboxHelper.setConfirmationCheckbox(driver, submittedApplicationsPage.checkboxConfirmationOne, true);
                log.debug("Checked 'Legal Entities' checkbox 1.");

                SubmittedApplicationsPage.CheckboxHelper.setConfirmationCheckbox(driver, submittedApplicationsPage.checkboxConfirmationTwo, true);
                log.debug("Checked 'Legal Entities' checkbox 2.");

                SubmittedApplicationsPage.CheckboxHelper.setConfirmationCheckbox(driver, submittedApplicationsPage.checkboxConfirmationThree, true);
                log.debug("Checked 'Legal Entities' checkbox 3.");

                log.debug("Test finished: All 'Legal Entities' checkboxes checked successfully.");

            submittedApplicationsPage.clickButtonNext();
            log.debug("Clicked 'Next' button after checkboxes.");

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

            submittedApplicationsPage.clickButtonNext();
            log.debug("Clicked 'Next' button after checkboxes.");

            //--------------------

            submittedApplicationsPage.uploadFileStepFive();

            String documentName1 = "Dokumentas 1";
            submittedApplicationsPage.enterDocumentNameOne(documentName1);
            log.debug("Entered program name: '{}'.", documentName1);

            String documentName2 = "Dokumentas 2";
            submittedApplicationsPage.enterDocumentNameTwo(documentName2);
            log.debug("Entered program name: '{}'.", documentName2);

            String documentName3 = "Dokumentas 3";
            submittedApplicationsPage.enterDocumentNameThree(documentName3);
            log.debug("Entered program name: '{}'.", documentName3);

            String documentName4 = "Dokumentas 4";
            submittedApplicationsPage.enterDocumentNameFour(documentName4);
            log.debug("Entered program name: '{}'.", documentName4);

            String documentName5 = "Dokumentas 5";
            submittedApplicationsPage.enterDocumentNameFive(documentName5);
            log.debug("Entered program name: '{}'.", documentName5);

            String documentName6 = "Dokumentas 6";
            submittedApplicationsPage.enterDocumentNameSix(documentName6);
            log.debug("Entered program name: '{}'.", documentName6);

            String documentName7 = "Dokumentas 7";
            submittedApplicationsPage.enterDocumentNameSeven(documentName7);
            log.debug("Entered program name: '{}'.", documentName4);

            String documentName8 = "Dokumentas 8";
            submittedApplicationsPage.enterDocumentNameEight(documentName8);
            log.debug("Entered program name: '{}'.", documentName8);

            String documentName9 = "Dokumentas 9";
            submittedApplicationsPage.enterDocumentNameNine(documentName9);
            log.debug("Entered program name: '{}'.", documentName9);

            String documentName10 = "Dokumentas 10";
            submittedApplicationsPage.enterDocumentNameTen(documentName10);
            log.debug("Entered program name: '{}'.", documentName10);

            SubmittedApplicationsPage.CheckboxHelper.setConfirmationCheckbox(driver, submittedApplicationsPage.checkboxConfirmationDocument, true);
            log.debug("Checked 'ConfirmationDocument'");
            SubmittedApplicationsPage.CheckboxHelper.setConfirmationCheckbox(driver, submittedApplicationsPage.checkboxConfirmationApplication, true);
            log.debug("Checked 'ConfirmationApplication'");

            submittedApplicationsPage.clickButtonSaveDraft();
            log.debug("Clicked 'SaveDraft' button after checkboxes.");

            applicationFormsPage.verifySuccessMessage("Ruošinys sėkmingai išsaugotas.");
            log.info("Verified success message");

            submittedApplicationsPage.clickButtonReview();
            log.debug("Clicked 'Review' button after checkboxes.");

            stay();

            //--------------------

            submittedApplicationsPage.clickButtonSubmit();
            log.debug("Clicked 'Submit' button after checkboxes.");

            submittedApplicationsPage.clickButtonSubmitConfirmation();
            log.debug("Clicked 'SubmitConfirmation' button after checkboxes.");

            applicationFormsPage.verifySuccessMessage("Paraiška sėkmingai pateikta ir užregistruota.");

            log.info("Test 'testFillNewVUIApplicationFA' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testFillNewVUIApplicationFA' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Test
    void testAddEvaluators_VUI_PVK() throws InterruptedException {

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
//            applicationFormsPage.login("evaluation_chief", "test");
//            log.debug("User logged in with test credentials.");

            getAndInsertInvitationCodeText();
            log.debug("Invitation code inserted.");

            submittedApplicationsPage.clickButtonActionApplication();
            log.debug("Clicked on 'Action Application' button.");

            submittedApplicationsPage.clickButtonNewApplication();
            log.debug("Clicked on 'New Application' button.");

            submittedApplicationsPage.clickRadioButtonFA();
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

//            SubmittedApplicationsPage.CheckboxHelper.setConfirmationCheckbox(driver, submittedApplicationsPage.checkboxConfirmationOne, true);
//            log.debug("Checked 'For workplace adaptation' checkbox 1.");
//
//            SubmittedApplicationsPage.CheckboxHelper.setConfirmationCheckbox(driver, submittedApplicationsPage.checkboxConfirmationTwo, true);
//            log.debug("Checked 'For environmental adaptation' checkbox 2.");
            //DVP

            String evrkDVP = "01.11 - Grūdinių (išskyrus ryžius), ankštinių ir aliejingų sėklų augalų auginimas";
            submittedApplicationsPage.selectValueByListEVRK_DVP(evrkDVP);
            log.debug("Selected EVRK value: '{}'.", evrkDVP);

            //address
            //--------------
            submittedApplicationsPage.clickAddressComponent();
            log.debug("Clicked on 'Address Component' button.");

            String country = "Lietuva";
            submittedApplicationsPage.selectDropdownAddressCountry(country);
            log.debug("Selected country: '{}'.", country);

            String citySearchTerm = "Vilnius";

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

            String street = "A. Domaševičiaus g.";
            submittedApplicationsPage.selectDropdownAddressStreet(street);
            log.debug("Selected street: '{}'.", street);

            String house = "1";
            submittedApplicationsPage.selectDropdownAddressHouse(house);
            log.debug("Selected house: '{}'.", house);

            String apartment = "1";
            submittedApplicationsPage.selectDropdownAddressApartment(apartment);
            log.debug("Selected apartment: '{}'.", apartment);

            submittedApplicationsPage.clickButtonConfirm();
            log.debug("Clicked 'Confirm' button.");
            //-------------

            //DVP
            submittedApplicationsPage.clickRadioButtonForAlreadyWorking_DVP_step1();
            log.debug("Selected 'For already working' option.");

//            String jobCount1 = "1";
//            submittedApplicationsPage.enterPersonCountOne_DVP(jobCount1);
//            log.debug("Entered person count1: '{}'.", jobCount1);

            submittedApplicationsPage.clickRadioButtonForNewWorking_DVP_step1();
            log.debug("Selected 'For new working' option.");

            String jobCount2 = "1";
            submittedApplicationsPage.enterPersonCountTwo_DVP(jobCount2);
            log.debug("Entered person count2: '{}'.", jobCount2);
            //DVP

            submittedApplicationsPage.clickButtonNext();
            log.debug("Clicked 'Next' button after checkboxes.");

            //--------------------

            String jobName = "Administratoriai";
            submittedApplicationsPage.selectDropdownJobNameAdaptable_DVP(jobName);
            log.debug("Selected adaptable job name: '{}'.", jobName);

            submittedApplicationsPage.enterJobDate_DVP();
            log.debug("Entered job date.");

            String function = "Funkcija 1";
            submittedApplicationsPage.enterInputJobFunction(function);
            log.debug("Entered job function: '{}'.", function);

            submittedApplicationsPage.clickRadioButtonForAlreadyWorking_DVP_step2();
            log.debug("Selected 'For already working' 'no' option.");

            String disabilitiesLevel = "Lengvo neįgalumo lygis ar 45-55 procentų dalyvumo lygis (iki 2023 m. gruodžio 31 d. – 45-55 procentų darbingumo lygis)";
            submittedApplicationsPage.selectValueByListDisabilitiesLevel_DVP(disabilitiesLevel);
            log.debug("Selected disabilities type: '{}'.", disabilitiesLevel);

            String disabilitiesDescription = "Negalia 1";
            submittedApplicationsPage.enterDisabilities(disabilitiesDescription);
            log.debug("Entered disability information: '{}'.", disabilitiesDescription);

            String qualification = "Kvalifikacija 1";
            submittedApplicationsPage.enterQualification(qualification);
            log.debug("Entered qualification: '{}'.", qualification);

            String jobDescription = "Darbo vietos aprašymas 1";
            submittedApplicationsPage.enterJobDescription(jobDescription);
            log.debug("Entered job description: '{}'.", jobDescription);

            String equipmentDescription_DVP = "Priemonių įsigyjimas 1";
            submittedApplicationsPage.enterProsesDescriptionVUI_equipmentDescriptionDVP(equipmentDescription_DVP);
            log.debug("Entered equipment description: '{}'.", equipmentDescription_DVP);

            String workInformation_DVP = "Darbai 1";
            submittedApplicationsPage.enterWorkInformationDVP(workInformation_DVP);
            log.debug("Entered work information: '{}'.", workInformation_DVP);

            String timeMode = "Kita";
            submittedApplicationsPage.selectDropdownTimeMode(timeMode);
            log.debug("Selected time mode: '{}'.", timeMode);

            String timeMode1 = "6 val. per dieną ir 4 d. per savaitę";
            submittedApplicationsPage.enterInputTimeModeOthers(timeMode1);
            log.debug("Selected time mode other: '{}'.", timeMode1);

            String amount1 = "1200";
            submittedApplicationsPage.enterSalary(amount1);
            log.debug("Entered salary amount: '{}'.", amount1);

            String salaryDescription_DVP = "Užmokestis 1";
            submittedApplicationsPage.enterSalaryDescription_DVP(salaryDescription_DVP);
            log.debug("Entered salary description: '{}'.", salaryDescription_DVP);

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

            String workDescription_DVP = "Remonto darbai 1";
            submittedApplicationsPage.enterWorkDescriptionDVP(workDescription_DVP);
            log.debug("Entered work information: '{}'.", workDescription_DVP);

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

            submittedApplicationsPage.clickButtonNext();
            log.debug("Clicked 'Next' button.");

            //--------------------
            SubmittedApplicationsPage.CheckboxHelper.setConfirmationCheckbox(driver, submittedApplicationsPage.checkboxConfirmationThree, true);
            log.debug("Checked 'Legal Entities' checkbox 1.");

            SubmittedApplicationsPage.CheckboxHelper.setConfirmationCheckbox(driver, submittedApplicationsPage.checkboxConfirmationFour, true);
            log.debug("Checked 'Legal Entities' checkbox 2.");

            SubmittedApplicationsPage.CheckboxHelper.setConfirmationCheckbox(driver, submittedApplicationsPage.checkboxConfirmationFive, true);
            log.debug("Checked 'Legal Entities' checkbox 3.");

            log.debug("Test finished: All 'Legal Entities' checkboxes checked successfully.");

            submittedApplicationsPage.clickButtonNext();
            log.debug("Clicked 'Next' button after checkboxes.");

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

            submittedApplicationsPage.clickButtonNext();
            log.debug("Clicked 'Next' button after checkboxes.");

            //--------------------

            submittedApplicationsPage.uploadFileStepFive();

            String documentName1 = "Dokumentas 1";
            submittedApplicationsPage.enterDocumentNameOne(documentName1);
            log.debug("Entered program name: '{}'.", documentName1);

            String documentName2 = "Dokumentas 2";
            submittedApplicationsPage.enterDocumentNameTwo(documentName2);
            log.debug("Entered program name: '{}'.", documentName2);

            String documentName3 = "Dokumentas 3";
            submittedApplicationsPage.enterDocumentNameThree(documentName3);
            log.debug("Entered program name: '{}'.", documentName3);

            String documentName4 = "Dokumentas 4";
            submittedApplicationsPage.enterDocumentNameFour(documentName4);
            log.debug("Entered program name: '{}'.", documentName4);

            String documentName5 = "Dokumentas 5";
            submittedApplicationsPage.enterDocumentNameFive(documentName5);
            log.debug("Entered program name: '{}'.", documentName5);

            String documentName6 = "Dokumentas 6";
            submittedApplicationsPage.enterDocumentNameSix(documentName6);
            log.debug("Entered program name: '{}'.", documentName6);

            String documentName7 = "Dokumentas 7";
            submittedApplicationsPage.enterDocumentNameSeven(documentName7);
            log.debug("Entered program name: '{}'.", documentName4);

            String documentName8 = "Dokumentas 8";
            submittedApplicationsPage.enterDocumentNameEight(documentName8);
            log.debug("Entered program name: '{}'.", documentName8);

            SubmittedApplicationsPage.CheckboxHelper.setConfirmationCheckbox(driver, submittedApplicationsPage.checkboxConfirmationDocument_DVP, true);
            log.debug("Checked 'ConfirmationDocument'");
            SubmittedApplicationsPage.CheckboxHelper.setConfirmationCheckbox(driver, submittedApplicationsPage.checkboxConfirmationApplication_DVP, true);
            log.debug("Checked 'ConfirmationApplication'");

            submittedApplicationsPage.clickButtonReview();
            log.debug("Clicked 'Review' button after checkboxes.");

            //--------------------

            submittedApplicationsPage.clickButtonSaveDraft();
            log.debug("Clicked 'SaveDraft' button after checkboxes.");

            applicationFormsPage.verifySuccessMessage("Ruošinys sėkmingai išsaugotas.");

            submittedApplicationsPage.clickButtonSubmit();
            log.debug("Clicked 'Submit' button after checkboxes.");

            submittedApplicationsPage.clickButtonSubmitConfirmation();
            log.debug("Clicked 'SubmitConfirmation' button after checkboxes.");

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

}
