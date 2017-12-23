package pageobjects;

import org.openqa.selenium.By;

public class HomePage {

    public static By signInLinkXpath = By.cssSelector(".c-btn.c-btn--link.c-btn--underline");
    public static By userNameFieldXPath = By.cssSelector(".c-form-field.c-form-field--email>label>input");
    public static By passwordFieldXpath = By.cssSelector(".c-form-field.c-form-field--password>label>input");
    public static By signInButtonXpath = By.cssSelector(".c-btn.c-btn--primary");

}
