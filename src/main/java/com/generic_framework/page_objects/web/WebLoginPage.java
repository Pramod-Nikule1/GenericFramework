package com.generic_framework.page_objects.web;

import com.generic_framework.TestBase;
import com.generic_framework.features.LoginPage;
import com.generic_framework.features.SignInPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebLoginPage extends TestBase implements LoginPage {

    @FindBy(id = "domain")
    WebElement workspace;

    public WebLoginPage() {
        PageFactory.initElements(driver, this);
    }

    @Override
    public SignInPage selectManualSignIn() {
        return new WebSignInPage();
    }
}
