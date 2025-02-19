package lt.insoft.uztis.tests;

import lt.insoft.uztis.pages.ApplicationFormsPage;
import lt.insoft.uztis.pages.InvitationsPage;
import lt.insoft.uztis.pages.SubmittedApplicationsPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class SubmittedApplicationsPageTest extends UztisPageTest {
    private static final Logger log = getLogger(lookup().lookupClass());

    protected InvitationsPage invitationsPage;
    protected ApplicationFormsPage applicationFormsPage;
    protected SubmittedApplicationsPage submittedApplicationsPage;


//    private void navigateToMainMenu() {
//        applicationFormsPage.openMenuApplicationProcessing();
//        log.debug("Opened 'Application Processing' menu.");
//        invitationsPage.clickMenuInvitation();
//        log.debug("Clicked on 'Invitation' menu.");
//    }

    private void navigateToSubmittedApplicationsMenu() {
        applicationFormsPage.openMenuApplicationProcessing();
        log.debug("Opened 'Application Processing' menu.");
        submittedApplicationsPage.clickMenuSubmittedApplications();
        log.debug("Clicked on 'Submitted Applications' menu.");
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

        submittedApplicationsPage.clickMenuSubmittedApplications();
        log.debug("Clicked on 'Submitted Applications' menu.");

        submittedApplicationsPage.selectValueFromInvitation(codeText);
        log.debug("Selected invitation by code: '{}'.", codeText);

    }

//    public void verifySuccessMessage(String expectedMessage) {
//        List<String> actualMessages = invitationsPage.getAllMessagesText();
//        boolean messageFound = actualMessages.stream()
//                .anyMatch(message -> message.equals(expectedMessage));
//        Assertions.assertTrue(messageFound, "Expected message not found: " + expectedMessage);
//        log.debug("Verified success message: '{}'", expectedMessage);
//    }

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
            applicationFormsPage.login("test", "test");
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
            applicationFormsPage.login("test", "test");
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
            applicationFormsPage.login("test", "test");
            log.debug("User logged in with test credentials.");

            getAndInsertInvitationCodeText();
            log.debug("Invitation code inserted.");

            submittedApplicationsPage.clickButtonActionApplication();
            log.debug("Clicked on 'Action Application' button.");

            submittedApplicationsPage.clickButtonNewApplication();
            log.debug("Clicked on 'New Application' button.");

            submittedApplicationsPage.clickRadioButtonFA();
            log.debug("Selected the 'RadioButtonFA' option.");

            String phoneNumber = "61298745";
            submittedApplicationsPage.enterPhoneNumber(phoneNumber);
            log.debug("Entered phone number: '{}'.", phoneNumber);

            String emailInput = "test@autotest.com";
            submittedApplicationsPage.enterEmailAddress(emailInput);
            log.debug("Entered email: '{}'.", emailInput);

            String valueToSelect = "01.11 - Grūdinių (išskyrus ryžius), ankštinių ir aliejingų sėklų augalų auginimas";
            submittedApplicationsPage.selectValueByListEVRK(valueToSelect);
            log.debug("Selected EVRK value: '{}'.", valueToSelect);

            String addressInput = "Vilniaus m. sav., Vilnius, Testo g. 10T";
            submittedApplicationsPage.enterAddress(addressInput);
            log.debug("Entered address: '{}'.", addressInput);

            submittedApplicationsPage.enterDate();
            log.debug("Entered project date.");

            String jobCount = "1";
            submittedApplicationsPage.enterJobCount(jobCount);
            log.debug("Entered job count: '{}'.", jobCount);

            submittedApplicationsPage.clickButtonNext();
            log.debug("Clicked 'Next' button.");

            //--------------------
            String valueToSelect1 = "Administratoriai";
            submittedApplicationsPage.selectDropdownJobName(valueToSelect1);
            log.debug("Selected job name: '{}'.", valueToSelect1);

            String text = "Funkcija 1";
            submittedApplicationsPage.enterInputJobFunction(text);
            log.debug("Entered job function: '{}'.", text);

            submittedApplicationsPage.clickRadioButtonWithDisabilitiesVUI();
            log.debug("Selected 'RadioButtonWithDisabilities' option.");

            String valueToSelect2 = "Bedarbiai, kurie yra darbingo amžiaus neįgalieji, kuriems nustatytas iki 25 procentų darbingumo lygis arba sunkus neįgalumo lygis";
            submittedApplicationsPage.selectValueByListDisabilitiesType(valueToSelect2);
            log.debug("Selected disabilities type: '{}'.", valueToSelect2);

            String text1 = "Negalia 1";
            submittedApplicationsPage.enterDisabilities(text1);
            log.debug("Entered disability information: '{}'.", text1);

            String valueToSelect3 = "Kita";
            submittedApplicationsPage.selectDropdownTimeMode(valueToSelect3);
            log.debug("Selected time mode: '{}'.", valueToSelect3);

            String valueToSelect4 = "6 val. per dieną ir 4 d. per savaitę";
            submittedApplicationsPage.enterInputTimeModeOthers(valueToSelect4);
            log.debug("Selected time mode other: '{}'.", valueToSelect4);

            String text2 = "Kvalifikacija 1";
            submittedApplicationsPage.enterQualification(text2);
            log.debug("Entered qualification: '{}'.", text2);

            submittedApplicationsPage.enterJobDate();
            log.debug("Entered job date.");

            String amount1 = "1200";
            submittedApplicationsPage.enterSalary(amount1);
            log.debug("Entered salary amount: '{}'.", amount1);

            submittedApplicationsPage.clickRadioButtonTemporaryJob();
            log.debug("Selected 'Temporary Job' radio button.");

            submittedApplicationsPage.clickRadioButtonSeasonJob();
            log.debug("Selected 'Season Job' radio button.");

            submittedApplicationsPage.clickRadioButtonEnergy();
            log.debug("Selected 'Energy' radio button.");

            submittedApplicationsPage.clickRadioButtonRepair();
            log.debug("Selected 'Repair' radio button.");

            String text3 = "Veikla 1";
            submittedApplicationsPage.enterJobDescription(text3);
            log.debug("Entered job description: '{}'.", text3);

            String text4 = "Procesas 1";
            submittedApplicationsPage.enterProsesDescriptionVUI_equipmentDescriptionDVP(text4);
            log.debug("Entered process description: '{}'.", text4);

            String text5 = "Energija 1";
            submittedApplicationsPage.enterEnergyInformationVUI_workInformationDVP(text5);
            log.debug("Entered energy information: '{}'.", text5);

            String text6 = "Remontas 1";
            submittedApplicationsPage.enterRepairInformation(text6);
            log.debug("Entered repair information: '{}'.", text6);

            submittedApplicationsPage.clickRadioButtonPVM();
            log.debug("Selected 'PVM' radio button.");

            String valueToSelect5 = "Įsigyti";
            submittedApplicationsPage.selectDropdownNecessaryForJobOne(valueToSelect5);
            log.debug("Selected necessary for job: '{}'.", valueToSelect5);

            String valueToSelect6 = "Darbo priemonė 1";
            submittedApplicationsPage.enterTool(valueToSelect6);
            log.debug("Selected tool: '{}'.", valueToSelect6);

            String valueToSelect7 = "Darbo priemonės 1 parametras 1";
            submittedApplicationsPage.enterToolParameterOne(valueToSelect7);
            log.debug("Selected tool parameter one: '{}'.", valueToSelect7);

            String valueToSelect8 = "Darbo priemonės 1 parametras 2";
            submittedApplicationsPage.enterToolParameterTwo(valueToSelect8);
            log.debug("Selected tool parameter two: '{}'.", valueToSelect8);

            String valueToSelect9 = "Darbo priemonės 1 parametras 3";
            submittedApplicationsPage.enterToolParameterThree(valueToSelect9);
            log.debug("Selected tool parameter three: '{}'.", valueToSelect9);

            String count1 = "1";
            submittedApplicationsPage.enterToolsCount(count1);
            log.debug("Entered tool count: '{}'.", count1);

            String amount2 = "1000";
            submittedApplicationsPage.enterPriceOne(amount2);
            log.debug("Entered price amount 1: '{}'.", amount2);

            String amount3 = "800";
            submittedApplicationsPage.enterOwnFundsOne(amount3);
            log.debug("Entered own funds amount 1: '{}'.", amount3);

            submittedApplicationsPage.clickButtonAdd();
            log.debug("Clicked 'Add' button.");

            String valueToSelect5_1 = "Remontuoti";
            submittedApplicationsPage.selectDropdownNecessaryForJobTwo(valueToSelect5_1);
            log.debug("Selected necessary for job 'to repair': '{}'.", valueToSelect5_1);

            String valueToSelect10 = "Remonto pavadinimas 1";
            submittedApplicationsPage.enterRepairName(valueToSelect10);
            log.debug("Selected repair name: '{}'.", valueToSelect10);

            String text7 = "Remonto aprašymas 1";
            submittedApplicationsPage.enterRepairDescription(text7);
            log.debug("Entered repair information: '{}'.", text7);

            String amount4 = "1000";
            submittedApplicationsPage.enterPriceTwo(amount4);
            log.debug("Entered price amount 2: '{}'.", amount4);

            String amount5 = "900";
            submittedApplicationsPage.enterOwnFundsTwo(amount5);
            log.debug("Entered own funds amount 2: '{}'.", amount5);

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

            invitationsPage.verifySuccessMessage("Ruošinys sėkmingai išsaugotas.");

            submittedApplicationsPage.clickButtonReview();
            log.debug("Clicked 'Review' button after checkboxes.");

            //--------------------

            submittedApplicationsPage.clickButtonSubmit();
            log.debug("Clicked 'Submit' button after checkboxes.");

            submittedApplicationsPage.clickButtonSubmitConfirmation();
            log.debug("Clicked 'SubmitConfirmation' button after checkboxes.");

            invitationsPage.verifySuccessMessage("Paraiška sėkmingai pateikta ir užregistruota.");

            log.info("Test 'testFillNewVUIApplicationFA' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testFillNewVUIApplicationFA' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    //ĮVESTI Į DB REDION_ID IR MINICIPALITY_ID (kol kas)

    @Test
    void testAddEvaluators() {

        log.info("Starting test:'testAddEvaluators'");

        try {
            applicationFormsPage.login("test", "test");
            log.debug("User logged in with test credentials.");

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

            submittedApplicationsPage.clickButtonAddEvaluatorsConfirmation();
            log.debug("Clicked on 'Add Evaluators' confirmation button.");

            invitationsPage.verifySuccessMessage("Paraiškų vertinimai sėkmingai priskirti vertintojams.");

            log.info("Test 'testAddEvaluators' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testAddEvaluators' failed with error: {}", e.getMessage(), e);
            throw e;
        }

    }


    @Test
    void testFillNewDVPApplicationFA() {

        log.info("Starting test:'testFillNewDVPApplicationFA'");

        try {
            applicationFormsPage.login("test", "test");
            log.debug("User logged in with test credentials.");

            getAndInsertInvitationCodeText();
            log.debug("Invitation code inserted.");

            submittedApplicationsPage.clickButtonActionApplication();
            log.debug("Clicked on 'Action Application' button.");

            submittedApplicationsPage.clickButtonNewApplication();
            log.debug("Clicked on 'New Application' button.");

            submittedApplicationsPage.clickRadioButtonFA();
            log.debug("Selected the 'RadioButtonFA' option.");

            String phoneNumber = "61298745";
            submittedApplicationsPage.enterPhoneNumber(phoneNumber);
            log.debug("Entered phone number: '{}'.", phoneNumber);

            String emailInput = "test@autotest.com";
            submittedApplicationsPage.enterEmailAddress(emailInput);
            log.debug("Entered email: '{}'.", emailInput);

            //DVP
            SubmittedApplicationsPage.CheckboxHelper.setConfirmationCheckbox(driver, submittedApplicationsPage.checkboxConfirmationOne, true);
            log.debug("Checked 'For workplace adaptation' checkbox 1.");

            SubmittedApplicationsPage.CheckboxHelper.setConfirmationCheckbox(driver, submittedApplicationsPage.checkboxConfirmationTwo, true);
            log.debug("Checked 'For environmental adaptation' checkbox 2.");
            //DVP

            String valueToSelect = "01.11 - Grūdinių (išskyrus ryžius), ankštinių ir aliejingų sėklų augalų auginimas";
            submittedApplicationsPage.selectValueByListEVRK_DVP(valueToSelect);
            log.debug("Selected EVRK value: '{}'.", valueToSelect);

            String addressInput = "Vilniaus m. sav., Vilnius, Testo g. 10T";
            submittedApplicationsPage.enterAddress_DVP(addressInput);
            log.debug("Entered address: '{}'.", addressInput);


            //oDVP
            submittedApplicationsPage.clickRadioButtonForAlreadyWorkingDVP_step1();
            log.debug("Selected 'For already working' option.");

            String jobCount1 = "1";
            submittedApplicationsPage.enterPersonCountOneDVP(jobCount1);
            log.debug("Entered person count1: '{}'.", jobCount1);

            submittedApplicationsPage.clickRadioButtonForNewWorkingDVP_step1();
            log.debug("Selected 'For new working' option.");

            String jobCount2 = "1";
            submittedApplicationsPage.enterPersonCountTwoDVP(jobCount2);
            log.debug("Entered person count2: '{}'.", jobCount2);
            //DVP


            submittedApplicationsPage.clickButtonNext();
            log.debug("Clicked 'Next' button after checkboxes.");

            //--------------------

            String valueToSelect1 = "Administratoriai";
            submittedApplicationsPage.selectDropdownJobNameAdaptableDVP(valueToSelect1);
            log.debug("Selected adaptable job name: '{}'.", valueToSelect1);

            submittedApplicationsPage.enterJobDateDVP();
            log.debug("Entered job date.");

            String text = "Funkcija 1";
            submittedApplicationsPage.enterInputJobFunction(text);
            log.debug("Entered job function: '{}'.", text);

            submittedApplicationsPage.clickRadioButtonForAlreadyWorkingDVP_step2();
            log.debug("Selected 'ForAlreadyWorking' 'no' option.");

            String valueToSelect2 = "Sunkaus neįgalumo lygis ar neviršijantis 25 procentų dalyvumo lygis (iki 2023 metų gruodžio 31 dienos – iki 25 procentų darbingumo lygis)";
            submittedApplicationsPage.selectValueByListDisabilitiesLevelDVP(valueToSelect2);
            log.debug("Selected disabilities type: '{}'.", valueToSelect2);

            String text1 = "Negalia 1";
            submittedApplicationsPage.enterDisabilities(text1);
            log.debug("Entered disability information: '{}'.", text1);

            String text2 = "Kvalifikacija 1";
            submittedApplicationsPage.enterQualification(text2);
            log.debug("Entered qualification: '{}'.", text2);

            String text3 = "Darbo vietos aprašymas 1";
            submittedApplicationsPage.enterJobDescription(text3);
            log.debug("Entered job description: '{}'.", text3);

            String text4 = "Priemonių įsigyjimas 1";
            submittedApplicationsPage.enterProsesDescriptionVUI_equipmentDescriptionDVP(text4);
            log.debug("Entered equipment description: '{}'.", text4);

            String text5 = "Darbai 1";
            submittedApplicationsPage.enterEnergyInformationVUI_workInformationDVP(text5);
            log.debug("Entered work information: '{}'.", text5);

            String valueToSelect3 = "Kita";
            submittedApplicationsPage.selectDropdownTimeMode(valueToSelect3);
            log.debug("Selected time mode: '{}'.", valueToSelect3);

            String valueToSelect4 = "6 val. per dieną ir 4 d. per savaitę";
            submittedApplicationsPage.enterInputTimeModeOthers(valueToSelect4);
            log.debug("Selected time mode other: '{}'.", valueToSelect4);

            String amount1 = "1200";
            submittedApplicationsPage.enterSalary(amount1);
            log.debug("Entered salary amount: '{}'.", amount1);

            submittedApplicationsPage.clickRadioButtonTemporaryJobDVP();
            log.debug("Selected 'Temporary Job' radio button.");

            submittedApplicationsPage.clickRadioButtonSeasonJobDVP();
            log.debug("Selected 'Season Job' radio button.");

            submittedApplicationsPage.clickRadioButtonPVM();
            log.debug("Selected 'PVM' radio button.");

            String type1 = "Darbo vietos pritaikymui";
            submittedApplicationsPage.selectDropdownExpensesTypDVP(type1);
            log.debug("Selected adaptable job name: '{}'.", type1);

            String valueToSelect5 = "Gaminti";
            submittedApplicationsPage.selectDropdownNecessaryForJobOneDVP(valueToSelect5);
            log.debug("Selected necessary for job: '{}'.", valueToSelect5);

            String valueToSelect6 = "Darbo priemonė 1";
            submittedApplicationsPage.enterToolDVP(valueToSelect6);
            log.debug("Selected tool: '{}'.", valueToSelect6);

            String valueToSelect7 = "Darbo priemonės 1 parametras 1";
            submittedApplicationsPage.enterToolParameterOneDVP(valueToSelect7);
            log.debug("Selected tool parameter one: '{}'.", valueToSelect7);

            String valueToSelect8 = "Darbo priemonės 1 parametras 2";
            submittedApplicationsPage.enterToolParameterTwoDVP(valueToSelect8);
            log.debug("Selected tool parameter two: '{}'.", valueToSelect8);

            String valueToSelect9 = "Darbo priemonės 1 parametras 3";
            submittedApplicationsPage.enterToolParameterThreeDVP(valueToSelect9);
            log.debug("Selected tool parameter three: '{}'.", valueToSelect9);

            String count1 = "1";
            submittedApplicationsPage.enterToolsCountDVP(count1);
            log.debug("Entered tool count: '{}'.", count1);

            String amount2 = "1000";
            submittedApplicationsPage.enterPriceOne(amount2);
            log.debug("Entered price amount: '{}'.", amount2);

            String amount3 = "800";
            submittedApplicationsPage.enterOwnFundsOne(amount3);
            log.debug("Entered own funds amount: '{}'.", amount3);

            submittedApplicationsPage.clickRadioButtonDeMinimis();
            log.debug("Selected 'De Minimis' radio button.");

            String programName = "Programa 1";
            submittedApplicationsPage.enterProgramName(programName);
            log.debug("Entered program name: '{}'.", programName);

            submittedApplicationsPage.enterProjectDateFromDVP();
            log.debug("Entered project date from.");

            submittedApplicationsPage.enterProjectDateUntilDVP();
            log.debug("Entered project date until.");

            String amount4 = "2000";
            submittedApplicationsPage.enterSupportAmount(amount4);
            log.debug("Entered support amount: '{}'.", amount4);

            submittedApplicationsPage.clickButtonNext();
            log.debug("Clicked 'Next' button.");

            //--------------------

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

            SubmittedApplicationsPage.CheckboxHelper.setConfirmationCheckbox(driver, submittedApplicationsPage.checkboxConfirmationDocument, true);
            log.debug("Checked 'ConfirmationDocument'");
            SubmittedApplicationsPage.CheckboxHelper.setConfirmationCheckbox(driver, submittedApplicationsPage.checkboxConfirmationApplication, true);
            log.debug("Checked 'ConfirmationApplication'");

            submittedApplicationsPage.clickButtonReview();
            log.debug("Clicked 'Review' button after checkboxes.");

            //--------------------

            submittedApplicationsPage.clickButtonSaveDraft();
            log.debug("Clicked 'SaveDraft' button after checkboxes.");

            invitationsPage.verifySuccessMessage("Ruošinys sėkmingai išsaugotas.");

            submittedApplicationsPage.clickButtonSubmit();
            log.debug("Clicked 'Submit' button after checkboxes.");

            submittedApplicationsPage.clickButtonSubmitConfirmation();
            log.debug("Clicked 'SubmitConfirmation' button after checkboxes.");

            invitationsPage.verifySuccessMessage("Paraiška sėkmingai pateikta ir užregistruota.");

            log.info("Test 'testFillNewDVPApplicationFA' completed successfully.");
        } catch (AssertionError | Exception e) {
            log.error("Test 'testFillNewDVPApplicationFA' failed with error: {}", e.getMessage(), e);
            throw e;
        }
    }

    //ĮVESTI Į DB REDION_ID IR MINICIPALITY_ID (kol kas)




}
