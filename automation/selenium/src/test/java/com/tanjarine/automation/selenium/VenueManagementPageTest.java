package com.tanjarine.automation.selenium;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ypolshchykau on 18.04.2014.
 */
public class VenueManagementPageTest extends TestBase {

    public static VenueManagementPage venues;
    public static AdminHomePage homePage;

    private final static Logger log = LoggerFactory.getLogger(LoginPageTest.class);

    @BeforeClass
    public static void init() {

        venues = PageFactory.initElements(driver, VenueManagementPage.class);
        homePage = PageFactory.initElements(driver, AdminHomePage.class);


    }

    @Test
    public void navigateToVenueManagementSection(){
        String testCaseID ="User navigation to venue maangement ";
        checkForElementPresence("Global Admin dropdown is MISSING", AdminHomePage.headerDownsCSS);
        homePage.globalAdminClick();
        checkForElementPresence("Venue Management section is absent", AdminHomePage.venueManagementCSS);
        homePage.venueManagementSectionClick();

        boolean isExpectedVneHeaderAppeared = isElementPresent(By.cssSelector(VenueManagementPage.venueManagementMenuHeaderCSS));

        log.info(testCaseID + "PASSED- " + isExpectedVneHeaderAppeared + "\n");
        Assert.assertTrue(testCaseID + "FAILED: user failed to navigate to venue management section", isExpectedVneHeaderAppeared && venues.getVenueManagementHeader().getText().trim().equals("Venue management"));
    }
}
