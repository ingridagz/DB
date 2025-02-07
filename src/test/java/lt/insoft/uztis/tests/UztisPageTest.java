package lt.insoft.uztis.tests;

import lt.insoft.uztis.tests.utils.FailureWatcher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;

import java.time.Duration;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

@ExtendWith(FailureWatcher.class)
public class UztisPageTest {
    protected static WebDriver driver;

    private static final Logger log = getLogger(lookup().lookupClass());

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        String URL = "https://uztisdev.asseco.lt/home";
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        log.info("Navigated to {}", URL);
        FailureWatcher.driver = driver;
    }

    public static void stay() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
    }

    //@AfterEach
    public void tearDown() {
        driver.close();
    }
}
