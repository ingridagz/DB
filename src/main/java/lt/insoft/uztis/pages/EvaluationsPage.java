package lt.insoft.uztis.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class EvaluationsPage extends UztisPage{

    private static final Logger log = getLogger(lookup().lookupClass());
    private static org.openqa.selenium.By By;

    public EvaluationsPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//a[@href='/application/evaluation']")
    WebElement buttonMenuEvaluations;

    @FindBy(xpath = "(//tr[contains(@class, 'mdc-data-table__row')])[1]")
    WebElement lastEvaluation;

//    @FindBy(css = "button.btn.btn--primary")
//    WebElement buttonEdit;

//    @FindBy(css = "//common-button[@btnclass='btn btn--accent' and @btntype='submit']//button[@type='submit']")
//    @FindBy(xpath = "//button[text()='Tvirtinti vertinimą']")
//    @FindBy(xpath = "//button[@type='submit' and text()='Tvirtinti vertinimą']")
    @FindBy(xpath = "//common-button[@btntype='submit' and contains(@btnclass, 'btn--accent')]//button")
    WebElement buttonConfirm1;

    @FindBy(xpath = "//common-button[@btntype='submit' and contains(@btnclass, 'btn--accent')]//button")
    WebElement buttonConfirm2;

//    @FindBy(xpath = "//button[@type='submit' and contains(@class, 'btn--accent')]")
//    @FindBy(xpath = "//common-button[@btntype='submit' and contains(@btnclass, 'btn--accent')]//button")
//    @FindBy(xpath = "//common-button[contains(@btnclass, 'btn--accent')]//button")
//    @FindBy(xpath = "//common-button[@btnclass='btn btn--accent']//button")
    @FindBy(xpath = "//common-button//button[text()='Tvirtinti vertinimą']")
    WebElement buttonConfirm3;

    @FindBy(xpath = "//button[text()='Saugoti ruošinį']")
    WebElement buttonSaveDraft;

    @FindBy(xpath = "//button[text()='Tęsti']")
    WebElement buttonContinue;

//    @FindBy(css = ".reverse-mobile .ng-star-inserted:nth-of-type(2)")
    @FindBy(css = ".reverse-mobile .btn--primary")
    WebElement buttonConfirmConfirmation;

    @FindBy(xpath = "//span[@class='status status-success' and text()='Įvertinta']")
    WebElement labelStatusConfirmed;

    //-------------

    @FindBy(xpath = "//input[@type='radio' and @name='mat-radio-group-1' and @value='YES']")
//    @FindBy(xpath = "(//input[@value='YES'])[1]")
    WebElement radioButton1_1;

    @FindBy(xpath = "//input[@type='radio' and @name='mat-radio-group-3' and @value='YES']")
//    @FindBy(xpath = "(//input[@value='YES'])[2]")
    WebElement radioButton1_2;

    @FindBy(xpath = "//input[@type='radio' and @name='mat-radio-group-5' and @value='YES']")
    WebElement radioButton1_3;

    @FindBy(xpath = "//input[@type='radio' and @name='mat-radio-group-7' and @value='YES']")
    WebElement radioButton1_4;

    @FindBy(xpath = "//input[@type='radio' and @name='mat-radio-group-9' and @value='YES']")
    WebElement radioButton1_5;

    @FindBy(xpath = "//input[@type='radio' and @name='mat-radio-group-13' and @value='YES']")
    WebElement radioButton2_1;

    @FindBy(xpath = "//input[@type='radio' and @name='mat-radio-group-17' and @value='YES']")
    WebElement radioButton3_1;

    @FindBy(xpath = "//input[@type='radio' and @name='mat-radio-group-25' and @value='YES']")
    WebElement radioButton4_2;

    @FindBy(xpath = "//input[@type='radio' and @name='mat-radio-group-27' and @value='YES']")
    WebElement radioButton5_1;

    @FindBy(xpath = "//input[@type='radio' and @name='mat-radio-group-29' and @value='YES']")
    WebElement radioButton5_2;

    @FindBy(xpath = "//input[@type='radio' and @name='mat-radio-group-31' and @value='YES']")
    WebElement radioButton6_1;

    //pakeista ranka
    @FindBy(xpath = "//input[@type='radio' and @name='mat-radio-group-33' and @value='NOT_EVALUABLE']")
    WebElement radioButton6_2;

    @FindBy(xpath = "//mat-expansion-panel-header[.//span[contains(text(), 'Nekritiniai vertinimo kriterijai')]]")
    WebElement expansionPanelNotCritical;

    @FindBy(xpath = "//input[@type='radio' and @name='mat-radio-group-35' and @value='YES']")
    WebElement radioButton7;

    @FindBy(xpath = "//input[@type='radio' and @name='mat-radio-group-37' and @value='YES']")
    WebElement radioButton8_1;

    @FindBy(xpath = "//input[@type='radio' and @name='mat-radio-group-39' and @value='YES']")
    WebElement radioButton8_2;

    @FindBy(xpath = "//input[@type='radio' and @name='mat-radio-group-41' and @value='YES']")
    WebElement radioButton8_3;

    @FindBy(xpath = "//input[@type='radio' and @name='mat-radio-group-43' and @value='YES']")
    WebElement radioButton8_4;

    @FindBy(xpath = "//input[@type='radio' and @name='mat-radio-group-45' and @value='YES']")
    WebElement radioButton8_5;

    @FindBy(xpath = "//input[@type='radio' and @name='mat-radio-group-47' and @value='YES']")
    WebElement radioButton8_6;

    @FindBy(xpath = "//input[@type='radio' and @name='mat-radio-group-49' and @value='YES']")
    WebElement radioButton8_7;

    @FindBy(xpath = "//input[@type='radio' and @name='mat-radio-group-51' and @value='YES']")
    WebElement radioButton8_8;

    @FindBy(xpath = "//input[@type='radio' and @name='mat-radio-group-53' and @value='YES']")
    WebElement radioButton8_9;

    @FindBy(xpath = "//input[@type='radio' and @name='mat-radio-group-55' and @value='YES']")
    WebElement radioButton8_10;

    @FindBy(xpath = "//input[@type='radio' and @name='mat-radio-group-57' and @value='YES']")
    WebElement radioButton9;

    @FindBy(xpath = "//input[@type='radio' and @name='mat-radio-group-59' and @value='YES']")
    WebElement radioButton10_1;

    @FindBy(xpath = "//input[@type='radio' and @name='mat-radio-group-61' and @value='YES']")
    WebElement radioButton10_2;

    @FindBy(xpath = "//input[@type='radio' and @name='mat-radio-group-63' and @value='YES']")
    WebElement radioButton10_3;

    @FindBy(xpath = "//textarea[contains(@class, 'primary-control') and contains(@class, 'mat-mdc-form-field-input')]")
    WebElement inputExplanation1;


//---------------------

    @FindBy(xpath = "//input[contains(@class, 'mat-datepicker-input')]")
    WebElement inputCommissionDate;

    @FindBy(xpath = "//input[@value='false']")
    WebElement radioButtonWithoutCommissionNO;

    @FindBy(xpath = "//input[contains(@id, 'mat-radio') and @name='mat-radio-group-76' and @value='true']")
    WebElement radioButtonWithoutCommissionYES;


    @FindBy(xpath = "//label[contains(., 'Komisijos pirmininkas')]/following::input[1]")
    WebElement inputChairmanName;
//    Komisijos narys Nr. 1
    @FindBy(xpath = "//label[contains(., 'Komisijos narys Nr. 1')]/following::input[1]")
    WebElement inputMemberName;

    @FindBy(xpath = "//label[contains(., 'Balas')]/following::input[13]")
    WebElement inputScoreChairman;

    @FindBy(xpath = "//label[contains(., 'Balas')]/following::input[14]")
    WebElement inputScoreMember;

    @FindBy(xpath = "//label[contains(., 'Balas')]/following::input[1]")
    WebElement inputScoreEdition;

    @FindBy(xpath = "(//div[contains(@class, 'mat-mdc-form-field-infix')]//textarea)[5]")
    WebElement inputScoreConclusions;

    @FindBy(xpath = "(//div[contains(@class, 'mat-mdc-form-field-infix')]//textarea)[4]")
    WebElement inputScoreEditedConclusions;

    @FindBy(xpath = "(//div[contains(@class, 'mat-mdc-form-field-infix')]//textarea)[6]")
    WebElement inputExplanation2;

//---------------------

    @FindBy(xpath = "//label[contains(., 'Balas')]/following::input[1]")
    WebElement inputScoreCh1;

    @FindBy(xpath = "//label[contains(., 'Balas')]/following::input[2]")
    WebElement inputScoreM1;

    @FindBy(xpath = "(//div[contains(@class, 'mat-mdc-form-field-infix')]//textarea)[1]")
    WebElement inputScoreConclusions1;

    @FindBy(xpath = "//label[contains(., 'Balas')]/following::input[4]")
    WebElement inputScoreCh2;

    @FindBy(xpath = "//label[contains(., 'Balas')]/following::input[5]")
    WebElement inputScoreM2;

    @FindBy(xpath = "(//div[contains(@class, 'mat-mdc-form-field-infix')]//textarea)[2]")
    WebElement inputScoreConclusions2;

    @FindBy(xpath = "//label[contains(., 'Balas')]/following::input[7]")
    WebElement inputScoreCh3;

    @FindBy(xpath = "//label[contains(., 'Balas')]/following::input[8]")
    WebElement inputScoreM3;

    @FindBy(xpath = "(//div[contains(@class, 'mat-mdc-form-field-infix')]//textarea)[3]")
    WebElement inputScoreConclusions3;

    @FindBy(xpath = "//label[contains(., 'Balas')]/following::input[10]")
    WebElement inputScoreCh4;

    @FindBy(xpath = "//label[contains(., 'Balas')]/following::input[11]")
    WebElement inputScoreM4;

    @FindBy(xpath = "(//div[contains(@class, 'mat-mdc-form-field-infix')]//textarea)[4]")
    WebElement inputScoreConclusions4;

    @FindBy(xpath = "//label[contains(., 'Balas')]/following::input[13]")
    WebElement inputScoreCh5;

    @FindBy(xpath = "//label[contains(., 'Balas')]/following::input[14]")
    WebElement inputScoreM5;

    @FindBy(xpath = "(//div[contains(@class, 'mat-mdc-form-field-infix')]//textarea)[5]")
    WebElement inputScoreConclusions5;


    public void clickMenuEvaluations() {
        buttonMenuEvaluations.click();
    }

    public void clickLastEvaluation() {
        lastEvaluation.click();
    }

//    public void clickButtonEdit() {
//        buttonEdit.click();
//    }

        public void clickButtonConfirm1() {
        buttonConfirm1.click();
    }

    public void clickButtonSaveDraft() {
        buttonSaveDraft.click();
    }

    public void clickButtonConfirm2() {
        buttonConfirm2.click();
    }

    public void clickButtonConfirm3() {
        buttonConfirm3.click();
    }

    public void clickButtonContinue() {
        buttonContinue.click();
    }

    public void clickButtonConfirmConfirmation() {
        buttonConfirmConfirmation.click();
    }

    public String getLabelStatusConfirmed() {
        return labelStatusConfirmed.getText();
    }

    public void clickRadioButton1_1() {
        if (!radioButton1_1.isSelected()) {
            radioButton1_1.click();
        }
    }

    public void clickRadioButton1_2() {
        if (!radioButton1_2.isSelected()) {
            radioButton1_2.click();
        }
    }

    public void clickRadioButton1_3() {
        if (!radioButton1_3.isSelected()) {
            radioButton1_3.click();
        }
    }

    public void clickRadioButton1_4() {
        if (!radioButton1_4.isSelected()) {
            radioButton1_4.click();
        }
    }

    public void clickRadioButton1_5() {
        if (!radioButton1_5.isSelected()) {
            radioButton1_5.click();
        }
    }

    public void clickRadioButton2_1() {
        if (!radioButton2_1.isSelected()) {
            radioButton2_1.click();
        }
    }

    public void clickRadioButton3_1() {
        if (!radioButton3_1.isSelected()) {
            radioButton3_1.click();
        }
    }

    public void clickRadioButton4_2() {
        if (!radioButton4_2.isSelected()) {
            radioButton4_2.click();
        }
    }

    public void clickRadioButton5_1() {
        if (!radioButton5_1.isSelected()) {
            radioButton5_1.click();
        }
    }

    public void clickRadioButton5_2() {
        if (!radioButton5_2.isSelected()) {
            radioButton5_2.click();
        }
    }

    public void clickRadioButton6_1() {
        if (!radioButton6_1.isSelected()) {
            radioButton6_1.click();
        }
    }

    public void clickRadioButton6_2() {
        if (!radioButton6_2.isSelected()) {
            radioButton6_2.click();
        }
    }

    public void clickExpansionPanelNotCritical() {
        if (!expansionPanelNotCritical.isSelected()) {
            expansionPanelNotCritical.click();
        }
    }

    public void clickRadioButton7() {
        if (!radioButton7.isSelected()) {
            radioButton7.click();
        }
    }

    public void clickRadioButton8_1() {
        if (!radioButton8_1.isSelected()) {
            radioButton8_1.click();
        }
    }

    public void clickRadioButton8_2() {
        if (!radioButton8_2.isSelected()) {
            radioButton8_2.click();
        }
    }

    public void clickRadioButton8_3() {
        if (!radioButton8_3.isSelected()) {
            radioButton8_3.click();
        }
    }

    public void clickRadioButton8_4() {
        if (!radioButton8_4.isSelected()) {
            radioButton8_4.click();
        }
    }

    public void clickRadioButton8_5() {
        if (!radioButton8_5.isSelected()) {
            radioButton8_5.click();
        }
    }

    public void clickRadioButton8_6() {
        if (!radioButton8_6.isSelected()) {
            radioButton8_6.click();
        }
    }

    public void clickRadioButton8_7() {
        if (!radioButton8_7.isSelected()) {
            radioButton8_7.click();
        }
    }

    public void clickRadioButton8_8() {
        if (!radioButton8_8.isSelected()) {
            radioButton8_8.click();
        }
    }

    public void clickRadioButton8_9() {
        if (!radioButton8_9.isSelected()) {
            radioButton8_9.click();
        }
    }

    public void clickRadioButton8_10() {
        if (!radioButton8_10.isSelected()) {
            radioButton8_10.click();
        }
    }

    public void clickRadioButton9() {
        if (!radioButton9.isSelected()) {
            radioButton9.click();
        }
    }

    public void clickRadioButton10_1() {
        if (!radioButton10_1.isSelected()) {
            radioButton10_1.click();
        }
    }

    public void clickRadioButton10_2() {
        if (!radioButton10_2.isSelected()) {
            radioButton10_2.click();
        }
    }

    public void clickRadioButton10_3() {
        if (!radioButton10_3.isSelected()) {
            radioButton10_3.click();
        }
    }

    public void enterExplanation1(String text) {
        inputExplanation1.clear();
        inputExplanation1.sendKeys(text);
    }

    //------------------------

//    public void enterCommissionDate() {
//        LocalDate today = LocalDate.now();
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//        String formattedTodayDate = today.format(formatter);
//
//        if (inputCommissionDate.isDisplayed() && inputCommissionDate.isEnabled()) {
//            inputCommissionDate.clear();
//            inputCommissionDate.sendKeys(formattedTodayDate);
//        } else {
//            throw new RuntimeException("Date input is not interactable.");
//        }
//    }

    public void enterCommissionDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedTodayDate = today.format(formatter);

        if (inputCommissionDate.isDisplayed() && inputCommissionDate.isEnabled()) {
            // Patikriname, ar laukelis jau turi reikšmę
            String currentValue = inputCommissionDate.getAttribute("value");

            if (currentValue != null && !currentValue.isEmpty()) {
                inputCommissionDate.clear(); // Jei reikšmė yra, ją ištriname
            }

            inputCommissionDate.sendKeys(formattedTodayDate); // Įrašome naują datą
        } else {
            throw new RuntimeException("Date input is not interactable.");
        }
    }

    public void clickRadioButtonWithoutCommissionNO() {
        if (!radioButtonWithoutCommissionNO.isSelected()) {
            radioButtonWithoutCommissionNO.click();
        }
    }

    public void enterChairmanName(String chairman) {
        inputChairmanName.clear();
        inputChairmanName.sendKeys(chairman);
    }

    public void enterMemberName(String member) {
        inputMemberName.clear();
        inputMemberName.sendKeys(member);
    }

    public void enterScoreChairman(String score) {
        inputScoreChairman.clear();
        inputScoreChairman.sendKeys(score);
    }

    public void enterScoreMember(String score) {
        inputScoreMember.clear();
        inputScoreMember.sendKeys(score);
    }

    public void enterScoreEdition(String score) {
        inputScoreEdition.clear();
        inputScoreEdition.sendKeys(score);
    }

    public void enterScoreConclusions(String text) {
        inputScoreConclusions.clear();
        inputScoreConclusions.sendKeys(text);
    }

    public void enterScoreEditedConclusions(String text) {
        inputScoreEditedConclusions.sendKeys(text);
    }

    public void enterExplanation2(String text) {
        inputExplanation2.clear();
        inputExplanation2.sendKeys(text);
    }

    public void enterExplanation2_1(String text) {
        inputExplanation2.sendKeys(text);
    }

//------------------------

    public void enterScoreCh1(String text) {
        inputScoreCh1.clear();
        inputScoreCh1.sendKeys(text);
    }

    public void enterScoreM1(String text) {
        inputScoreM1.clear();
        inputScoreM1.sendKeys(text);
    }

    public void enterScoreConclusions1(String text) {
        inputScoreConclusions1.clear();
        inputScoreConclusions1.sendKeys(text);
    }

    public void enterScoreCh2(String text) {
        inputScoreCh2.clear();
        inputScoreCh2.sendKeys(text);
    }

    public void enterScoreM2(String text) {
        inputScoreM2.clear();
        inputScoreM2.sendKeys(text);
    }

    public void enterScoreConclusions2(String text) {
        inputScoreConclusions2.clear();
        inputScoreConclusions2.sendKeys(text);
    }

    public void enterScoreCh3(String text) {
        inputScoreCh3.clear();
        inputScoreCh3.sendKeys(text);
    }

    public void enterScoreM3(String text) {
        inputScoreM3.clear();
        inputScoreM3.sendKeys(text);
    }

    public void enterScoreConclusions3(String text) {
        inputScoreConclusions3.clear();
        inputScoreConclusions3.sendKeys(text);
    }

    public void enterScoreCh4(String text) {
        inputScoreCh4.clear();
        inputScoreCh4.sendKeys(text);
    }

    public void enterScoreM4(String text) {
        inputScoreM4.clear();
        inputScoreM4.sendKeys(text);
    }

    public void enterScoreConclusions4(String text) {
        inputScoreConclusions4.clear();
        inputScoreConclusions4.sendKeys(text);
    }

    public void enterScoreCh5(String text) {
        inputScoreCh5.clear();
        inputScoreCh5.sendKeys(text);
    }

    public void enterScoreM5(String text) {
        inputScoreM5.clear();
        inputScoreM5.sendKeys(text);
    }

    public void enterScoreConclusions5(String text) {
        inputScoreConclusions5.clear();
        inputScoreConclusions5.sendKeys(text);
    }

}
