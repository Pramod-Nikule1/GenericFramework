package com.generic_framework.page_objects.android;

import com.generic_framework.TestBase;
import com.generic_framework.features.LoginPage;
import com.generic_framework.features.SignInPage;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;

public class AndroidLoginActivity extends TestBase implements LoginPage {

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.Slack:id/send_magic_link_button\")")
    AndroidElement magicLink;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.Slack:id/sign_in_manually_button\")")
    AndroidElement manualSignIn;

    public AndroidLoginActivity() {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    @Override
    public SignInPage selectManualSignIn() {
        manualSignIn.click();
        return new AndroidSignInActivity();
    }
}
