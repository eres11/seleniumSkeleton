package com.tanjarine.automation.selenium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by ypolshchykau on 18.04.2014.
 */
public class VenueManagementPage {
    final static String venueManagementMenuHeaderCSS=".nav-header>a[href=\"/web-ui/admin/venue/list\"]";

    public WebElement getVenueManagementHeader() {
        return venueManagementHeader;
    }

    @FindBy(how = How.CSS, using =venueManagementMenuHeaderCSS)
    private WebElement venueManagementHeader;

}
