package com.tanjarine.automation.selenium;

import com.google.common.base.Function;
import com.tanjarine.automation.utils.Constants;
import com.thoughtworks.selenium.SeleneseTestBase;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by ypolshchykau on 09.04.2014.
 */
public class TestBase extends SeleneseTestBase {
    private static LoginPage loginPage;

    static WebDriver driver;
    private final static Logger log = LoggerFactory.getLogger(TestBase.class);

    @BeforeClass
    public static void openFirefox() throws IOException {

      /*  DesiredCapabilities capabilities =
                DesiredCapabilities.internetExplorer();
        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                true);
        driver = new InternetExplorerDriver(capabilities);
*/
        DesiredCapabilities capability = DesiredCapabilities.firefox();

// web driver instance for Remote machine run
        driver = new RemoteWebDriver(new URL("http://192.168.0.34:4444/wd/hub"), capability);


        //
//        driver = new RemoteWebDriver(new URL("http://strict1718:868a1647-7af3-4c07-bf98-f6bda458f130@ondemand.saucelabs.com:80/wd/hub"),capability);

//web driver instance for local Run with local ffox instance creation
//        driver = new FirefoxDriver();  //for local check


        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1920, 1080));

        loginPage = PageFactory.initElements(driver, LoginPage.class);
    }

    @Before
    public void homePageNavigation() throws IOException, InterruptedException {
        driver.manage().deleteAllCookies();
        driver.get(Constants.loginbaseUrl);

//        doAdminLogin();

        //check that eMail input is present
        if (isElementPresent(By.cssSelector(LoginPage.emailInputCSS))) {

            loginPage.enterLogin(Constants.testUser);
        } else {
            Assert.assertFalse("login input not found", true);
        }

        //check that pass input is present
        if (isElementPresent(By.cssSelector(LoginPage.passInputCSS))) {

            loginPage.enterPassword(Constants.testUserPass);
        } else {
            Assert.assertFalse("password input not found", true);
        }

        //check that login button is present

        if (isElementPresent(By.cssSelector(LoginPage.signInButtonCSS))) {
            loginPage.loginFormSubmit();
        } else {
            Assert.assertFalse("submit button not found", true);
        }


    }

    @AfterClass
    public static void closeFirefox() {

        driver.quit();
    }

    public WebElement fluentWait(final By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
//                .pollingEvery(5, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)

//                .ignoring(NoSuchElementException.class);
                .ignoring(org.openqa.selenium.NoSuchElementException.class);

        WebElement foo = wait.until(
                new Function<WebDriver, WebElement>() {
                    public WebElement apply(WebDriver driver) {
                        return driver.findElement(locator);
                    }
                }
        );
        return foo;
    }

    ;

    public boolean isElementPresent(By locatorKey) {
        try {
            driver.findElement(locatorKey);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void jsClickOnElementFoundByCSS(String cssSelector) throws IOException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("var x = $(\'" + cssSelector + "\');");
        stringBuilder.append("x.click();");
        js.executeScript(stringBuilder.toString());
    }


    public void checkForElementPresence(String alertMsg, String cssLocator) {
        if (!isElementPresent(By.cssSelector(cssLocator))) {
            Assert.assertFalse(alertMsg, true);
        }


    }

}
