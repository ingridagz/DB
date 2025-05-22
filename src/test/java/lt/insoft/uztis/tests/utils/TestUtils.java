package lt.insoft.uztis.tests.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class TestUtils {

    private static final Logger log = getLogger(lookup().lookupClass());


    public static void takeScreenshot(WebDriver driver, String fileName) {

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String directoryPath = System.getProperty("user.dir")
                + File.separator + "test-output"
                + File.separator + "screenshots"
                + File.separator + getTodaysDate();

        String filePath = directoryPath
                + File.separator + getSystemTime()
                + "_" + fileName + ".png";

        try {
            Path directory = Paths.get(directoryPath);
            Files.createDirectories(directory);

            Path destination = Paths.get(filePath);
            Files.move(scrFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
            log.info("Screenshot moved to {}", destination);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Error moving screenshot: ", e);
        }

    }


    private static String getTodaysDate() {
        return DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDate.now());

    }

    private static String getSystemTime() {
        return DateTimeFormatter.ofPattern("HHmmssSSS").format(LocalDateTime.now());
    }


    public static final String DEFAULT_USERNAME = "evaluation_chief";
    public static final String DEFAULT_PASSWORD = "test";

//    evaluation_chief / test — Vaitkuvienė Lijana
//    evaluation_specialist / test — Palikevičius Marius

    public static void loginAllTests(WebDriver driver) {
        login(driver, DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }


    public static void login(WebDriver driver, String uname, String pword) {
        try {
            WebElement username = driver.findElement(By.id("username"));
            WebElement password = driver.findElement(By.id("password"));
            WebElement buttonLogin = driver.findElement(By.id("kc-login"));

            username.sendKeys(uname);
            password.sendKeys(pword);
            buttonLogin.click();

            log.info("Login attempt with user: {}", uname);
        } catch (NoSuchElementException e) {
            log.error("Login failed – element not found: {}", e.getMessage());
            throw e;
        }
    }



//    public static String getRandomCode() {
//        // Generuojame 2 atsitiktinius skaitmenis
//        String randomDigits = RandomStringUtils.randomNumeric(2);
//        // Pridedame "TST-" prie atsitiktinių skaitmenų
//        return "TST-" + randomDigits;
//    }


}
