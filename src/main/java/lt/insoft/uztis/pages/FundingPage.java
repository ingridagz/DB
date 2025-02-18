package lt.insoft.uztis.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class FundingPage extends UztisPage{

    private static final Logger log = getLogger(lookup().lookupClass());

    public FundingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@href='/application/funding-queue']")
    WebElement buttonMenuFundingQueue;



    public void clickMenuFundingQueue() {
        buttonMenuFundingQueue.click();
    }



}
