import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Base {
    public WebDriver driver = null;
    public Logger log = Logger.getLogger(Base.class.getName());

    @Parameters({"useSauceLab", "userName", "key", "appUrl", "os", "browserName", "browserVersion"})
    @BeforeMethod
    public void setUp(boolean useSauceLab, String userName, String key, String appUrl, String os, String browserName, String browserVersion) throws IOException {
        if (useSauceLab == true) {
            getSauceLabDriver(userName, key, os, browserName, browserVersion);
        } else {
            getLocalDriver(os, browserName);
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to(appUrl);
//        driver.manage().window().maximize();
        log.info("browser loaded with App");
    }

    @AfterMethod
    public void cleanUp() throws InterruptedException {
        log.info("driver is quiting");
        driver.quit();
    }

    //get local driver
    public WebDriver getLocalDriver(String os, String browserName) {
        if (browserName.equalsIgnoreCase("firefox")) {
            //driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("chrome")) {
            if (os.equalsIgnoreCase("windows")) {
                System.setProperty("webdriver.chrome.driver", "/Users/malam2/IdeaProjects/FinalScore2/Generic/src/main/resources/driver/chromedriver");
            } else {
                System.setProperty("webdriver.chrome.driver", "/Users/malam2/Desktop/FinalScore2/Generic/src/main/resources/driver/chromedriver"); }
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("safari")) {
            //driver = new SafariDriver();
        } else if (browserName.equalsIgnoreCase("ie")) {
            System.setProperty("webdriver.ie.driver", "");
            //driver = new InternetExplorerDriver();
        } else if (browserName.equalsIgnoreCase("htmlunit")) {
            //driver = new HtmlUnitDriver();
        }
        return driver;
    }


    //get cloud driver
    public WebDriver getSauceLabDriver(String userName, String key, String os, String browserName,
                                       String browserVersion )throws IOException{

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("platform", os);
        cap.setBrowserName(browserName);
        cap.setCapability("version", browserVersion);

        driver = new RemoteWebDriver(new URL("http://"+ userName + ":" +  key +
                "@ondemand.saucelabs.com:80/wd/hub"), cap);

        return driver;
    }


    public void click(By by) throws InterruptedException {
        driver.findElement(by).click();
        Thread.sleep(3000);
    }

    public void enterText(By by, String text) throws InterruptedException {
        driver.findElement(by).sendKeys(text);
        Thread.sleep(3000);
    }

    private WebDriverWait waitSeconds = null;
    public void clickAfterElementPresent(By by) throws InterruptedException {
        waitSeconds = new WebDriverWait(driver, 30);
        waitSeconds.until(ExpectedConditions.visibilityOf(driver.findElement(by))).click();
        Thread.sleep(500);
    }

    public void sendTextAfterElementPresent(By by, String text) throws InterruptedException {
        waitSeconds = new WebDriverWait(driver, 30);
        waitSeconds.until(ExpectedConditions.visibilityOf(driver.findElement(by))).sendKeys(text);
        Thread.sleep(500);
    }

    public static String readFromExcel(String fileRef, String sheetRef, String cellRef) throws IOException {
        FileInputStream fis = new FileInputStream(fileRef);
        Workbook wb = new XSSFWorkbook(fis);
        Sheet sheet = wb.getSheet(sheetRef);
        DataFormatter formatter = new DataFormatter();
        CellReference cellReference = new CellReference(cellRef);
        Row row = sheet.getRow(cellReference.getRow());
        Cell cell = row.getCell(cellReference.getCol());
        String value = "";
        if (cell != null) {
            value = formatter.formatCellValue(cell);//cell.getStringCellValue();
        }
        return value;
    }
}