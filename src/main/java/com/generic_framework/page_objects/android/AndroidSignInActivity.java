package com.generic_framework.page_objects.android;

import com.generic_framework.TestBase;
import com.generic_framework.features.HomePage;
import com.generic_framework.features.SignInPage;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;

public class AndroidSignInActivity extends TestBase implements SignInPage {

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.Slack:id/reset_password_button\")")
    AndroidElement resetPasswordBtn;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.Slack:id/password_edit_text\")")
    AndroidElement passwordButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.Slack:id/next_button_url_entry\")")
    AndroidElement nextButtonUrlEntry;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.Slack:id/next_button\")")
    AndroidElement nextButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.Slack:id/next_button_password\")")
    AndroidElement nextButtonPassword;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.Slack:id/email_edit_text\")")
    AndroidElement emailIdField;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.Slack:id/google_account_button\")")
    AndroidElement googleAccountsButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.Slack:id/team_url_edit_text\")")
    AndroidElement team_url;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.Slack:id/reminder_button\")")
    AndroidElement sendReminder;

    public AndroidSignInActivity() {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    @Override
    public HomePage setPassword(String passwd) {
        passwordButton.setValue(passwd);
        nextButtonPassword.click();
        return new AndroidHomeActivity();
    }

    @Override
    public void clickNext() {
        nextButton.click();
    }

    @Override
    public void setEmailId(String emailId) {
        emailIdField.setValue(emailId);
        clickNext();
    }

    @Override
    public void clickGoogleAccount() {
        googleAccountsButton.click();
    }

    @Override
    public void setTeamUrl(String teamURL) {
        team_url.setValue(teamURL);
        nextButtonUrlEntry.click();
    }
}
