package com.tanjarine.automation.selenium;

import com.tanjarine.automation.utils.Constants;
import org.junit.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit test for simple LoginPage.
 */
public class LoginPageTest
        extends TestBase {

    public static LoginPage loginPage;
    public static AdminHomePage homePage;

    private final static Logger log = LoggerFactory.getLogger(LoginPageTest.class);

    @BeforeClass
    public static void init() {

        loginPage = PageFactory.initElements(driver, LoginPage.class);
        homePage = PageFactory.initElements(driver, AdminHomePage.class);


    }

    @Before
    public void logout() {

        if (isElementPresent(By.cssSelector(AdminHomePage.headerDownsCSS))) {
            homePage.userSectionClick();
        } else {
            Assert.assertFalse("LOGIN PAGE TEST, prerquisite: user section dropdown not found", true);
        }

        if (isElementPresent(By.cssSelector(AdminHomePage.userLogoutCSS))) {
            homePage.logoutButtonClick();
        } else {
            Assert.assertFalse("LOGIN PAGE TEST, prerquisite: logout button not found", true);
        }
    }

    @Test
    public void loginWithEmptyCredentialsPIL240() {
        //test case ID and it's short description upon the jira
        String jiraPIL240desctiption = "\"PIL-240:Validate that user cannot log into the app with empty User ID and Password\" ";

        String login = "";
        String pass = "";

        checkForElementPresence(jiraPIL240desctiption + "FAILED: login input not found\n", LoginPage.emailInputCSS);
        loginPage.enterLogin(login);
        checkForElementPresence(jiraPIL240desctiption + "FAILED: password input not found\n", LoginPage.passInputCSS);
        loginPage.enterPassword(pass);
        checkForElementPresence(jiraPIL240desctiption + "FAILED: submit form button not found\n", LoginPage.signInButtonCSS);
        loginPage.loginFormSubmit();

        boolean isExpectedValidationAppeared = isElementPresent(By.cssSelector(LoginPage.validationCSS));

        log.info(jiraPIL240desctiption + "PASSED- " + isExpectedValidationAppeared + "\n");
        Assert.assertTrue(jiraPIL240desctiption + "FAILED: validation is impoproper/absent", isExpectedValidationAppeared && loginPage.getValidation().getText().equals(LoginPage.validationMessageForEmptyInputs));

    }


    @Test
    public void loginWithExistingUserAndEmptyPasswordPIL262() {

//test case ID and it's short description upon the jira
        String jiraPIL262desctiption = "\"PIL-262: Validate that user cannot log into the app with existing User ID and empty Password field\" ";

        String login = Constants.testUser;
        String pass = "";

        checkForElementPresence(jiraPIL262desctiption + "FAILED: login input not found\n", LoginPage.emailInputCSS);
        loginPage.enterLogin(login);
        checkForElementPresence(jiraPIL262desctiption + "FAILED: password input not found\n", LoginPage.passInputCSS);
        loginPage.enterPassword(pass);
        checkForElementPresence(jiraPIL262desctiption + "FAILED: submit form button not found\n", LoginPage.signInButtonCSS);
        loginPage.loginFormSubmit();

        boolean isExpectedValidationAppeared = isElementPresent(By.cssSelector(LoginPage.validationCSS));

        log.info(jiraPIL262desctiption + "PASSED- " + isExpectedValidationAppeared + "\n");
        Assert.assertTrue(jiraPIL262desctiption + "FAILED: validation is impoproper/absent", isExpectedValidationAppeared && loginPage.getValidation().getText().equals(LoginPage.validationMessageForEmptyInputs));
    }


    @Test
    public void  loginWithEmptyUsernameAndProperPassPIL263(){
        //test case ID and it's short description upon the jira
        String jiraPIL263desctiption = "\"PIL-263: Validate that user cannot log into the app with empty User ID field and valid Password\" ";

        String login ="";
        String pass = Constants.testUserPass;

        checkForElementPresence(jiraPIL263desctiption + "FAILED: login input not found\n", LoginPage.emailInputCSS);
        loginPage.enterLogin(login);
        checkForElementPresence(jiraPIL263desctiption + "FAILED: password input not found\n", LoginPage.passInputCSS);
        loginPage.enterPassword(pass);
        checkForElementPresence(jiraPIL263desctiption + "FAILED: submit form button not found\n", LoginPage.signInButtonCSS);
        loginPage.loginFormSubmit();

        boolean isExpectedValidationAppeared = isElementPresent(By.cssSelector(LoginPage.validationCSS));

        log.info(jiraPIL263desctiption + "PASSED- " + isExpectedValidationAppeared + "\n");
        Assert.assertTrue(jiraPIL263desctiption + "FAILED: validation is impoproper/absent", isExpectedValidationAppeared && loginPage.getValidation().getText().equals(LoginPage.validationMessageForEmptyInputs));
    }

}
