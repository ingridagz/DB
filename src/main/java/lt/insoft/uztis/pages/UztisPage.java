package lt.insoft.uztis.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class UztisPage {
    protected WebDriver driver;

    public UztisPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static void stay() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
        }
    }
}

