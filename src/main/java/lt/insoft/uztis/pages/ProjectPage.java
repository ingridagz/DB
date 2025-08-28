package lt.insoft.uztis.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class ProjectPage extends UztisPage {

    private static final Logger log = getLogger(lookup().lookupClass());

    public ProjectPage(WebDriver driver) {
        super(driver);

    }

    @FindBy(xpath = "//a[@href=\"/application/project\"]")
    WebElement buttonMenuProjects;

    @FindBy(xpath = "//h2[.='Projektai']")
    WebElement labelProjects;

    @FindBy(xpath = "(//tr[contains(@class, 'mdc-data-table__row')])[1]")
    WebElement lastProject;

    @FindBy(xpath = "//button[normalize-space(.)='Redaguoti']")
    WebElement buttonEditProject;

    @FindBy(xpath = "//button[normalize-space(.)='Patvirtinti']")
    WebElement buttonConfirmProject;

    @FindBy(xpath = "//button[normalize-space(.)='Saugoti ruošinį']")
    WebElement buttonSaveDocumentDraft;

    @FindBy(xpath = "//td[normalize-space(.)='Įsakymas skirti paramą ir sudaryti sutartį']")
    WebElement orderDocument;

    @FindBy(xpath = "//button[normalize-space(.)='Teikti pasirašymui']")
    WebElement buttonSendForSignature;

    @FindBy(xpath = "//button[normalize-space(.)='Pasirašyti']")
    WebElement buttonSign;

    @FindBy(xpath = "//button[normalize-space(.)='Teikti peržiūrai']")
    WebElement buttonReview;

    public void clickMenuProjects() {
        buttonMenuProjects.click();
    }

    public void clickProjectRow() {
        lastProject.click();
    }

    public String getProjectLabelText() {
        return labelProjects.getText();
    }

    public void clickButtonEditProject() {
        buttonEditProject.click();
    }

    public void clickButtonEditConfirmProjectDocument() {
        buttonConfirmProject.click();
    }

    public void clickButtonSaveDocumentDraft() {
        buttonSaveDocumentDraft.click();
    }

    public void clickOrderDocument() {
        orderDocument.click();
    }

    public void clickButtonSendForSignature() {
        buttonSendForSignature.click();
    }

    public void clickButtonSign() {
        buttonSign.click();
    }

    public void clickButtonReview() {
        buttonReview.click();
    }

}
