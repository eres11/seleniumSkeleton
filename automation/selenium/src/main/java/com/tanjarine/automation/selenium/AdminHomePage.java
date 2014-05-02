package com.tanjarine.automation.selenium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

/**
 * Created by ypolshchykau on 15.04.2014.
 */
public class AdminHomePage {
    public final static String headerDownsCSS = ".caret";

    public final static String userLogoutCSS = ".glyphicon.glyphicon-off";

    public final static String venueManagementCSS = "a[data-role=\"service\"][href=\"/web-ui/admin/venue/list\"]";

    @FindBy(how = How.CSS, using = userLogoutCSS)
    private WebElement logoutButton;


    @FindBy(how = How.CSS, using = headerDownsCSS)
    private List<WebElement> headerDropdowns;

    public List<WebElement> getHeaderDropdowns() {
        return headerDropdowns;
    }


    public WebElement getVenueManagementItem() {
        return venueManagementItem;
    }

    @FindBy(how = How.CSS, using = venueManagementCSS)
    private WebElement venueManagementItem;


    public void selectVenueClick() {
        headerDropdowns.get(0).click();
    }

    public void globalAdminClick() {
        headerDropdowns.get(1).click();
    }

    public void userSectionClick() {
        headerDropdowns.get(2).click();
    }

    public void logoutButtonClick() {
        logoutButton.click();
    }


    public void venueManagementSectionClick(){
        venueManagementItem.click();
    }
}
