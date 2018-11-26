package com.generic_framework.page_objects.web;

import com.generic_framework.TestBase;
import com.generic_framework.features.HomePage;
import com.generic_framework.features.SetCustomStatusPage;
import com.generic_framework.utils.OverflowOptions;
import com.generic_framework.utils.Status;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebHomePage extends TestBase implements HomePage {

    @FindBy(id = "team_name")
    WebElement teamName;

    @FindBy(css = "div.ql-editor.ql-blank")
    WebElement searchButton;

    @FindBy(id = "team_menu")
    WebElement overflowButton;

    @FindBy(id = "team_menu_user_name")
    WebElement profileName;

    @FindBy(id = "member_current_status_item")
    WebElement setStatus;

    public WebHomePage() {
        PageFactory.initElements(driver, this);
    }

    @Override
    public String getProfileName() {
        return profileName.getText();
    }

    @Override
    public SetCustomStatusPage selectOverflowOption(OverflowOptions option) {
        overflowButton.click();
        return new WebCustomStatusActivity();
    }

    @Override
    public boolean verifyStatus(Status status) {
        return false;
    }

    @Override
    public boolean verifyCurrentStatusDisplayed() {
        return false;
    }

    @Override
    public boolean verifyOfflineIndicatorDisplayed() {
        return false;
    }

    @Override
    public void verifyOfflineIndicatorInvisible() {

    }

    @Override
    public void sendMessage(String message) {

    }

    @Override
    public boolean verifyMessageDisplayed(String message) {
        return false;
    }

    @Override
    public boolean isTeamNameDisplayed() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        webDriverWait.until(ExpectedConditions.visibilityOf(teamName));
        return teamName.isDisplayed();
    }
}
