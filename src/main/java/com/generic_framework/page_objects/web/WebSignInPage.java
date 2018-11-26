package com.generic_framework.page_objects.web;

import com.generic_framework.TestBase;
import com.generic_framework.features.HomePage;
import com.generic_framework.features.SignInPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebSignInPage extends TestBase implements SignInPage {

    @FindBy(css = "input#domain")
    WebElement workspace;

    @FindBy(id = "submit_team_domain")
    WebElement continueButton;

    @FindBy(id = "email")
    WebElement emailID;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(id = "signin_btn")
    WebElement signInButton;

    public WebSignInPage() {
        PageFactory.initElements(driver, this);
    }

    @Override
    public HomePage setPassword(String passwd) {
        password.sendKeys(passwd);
        signInButton.click();
        return new WebHomePage();
    }

    @Override
    public void clickNext() {

    }

    @Override
    public void setEmailId(String emailId) {
        emailID.sendKeys(emailId);
    }

    @Override
    public void clickGoogleAccount() {

    }

    @Override
    public void setTeamUrl(String teamURL) {
        workspace.click();
        workspace.sendKeys(teamURL);
        continueButton.click();
    }
}
