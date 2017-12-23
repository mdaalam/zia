import pageobjects.HomePage;
import pageobjects.SearchPage;

import java.io.IOException;

public class FSBase extends Base {

    public static String dataFilePath = "/Users/malam2/Desktop/FinalScore2/Application/src/test/TestData/test_Data.xlsx";
    public String userName = "alam.write@gmail.com";
    public String password = "Locker12";

    public void fsLogin() throws InterruptedException {
        click(HomePage.signInLinkXpath);
        enterText(HomePage.userNameFieldXPath, userName);
        enterText(HomePage.passwordFieldXpath, password);
        click(HomePage.signInButtonXpath);
    }

    public void fsSearch() throws InterruptedException, IOException {
        String testData = readFromExcel(dataFilePath, "product_name", "A1");
        sendTextAfterElementPresent(SearchPage.searchFieldXPath, testData);
        clickAfterElementPresent(SearchPage.searchIconXPath);
    }
}