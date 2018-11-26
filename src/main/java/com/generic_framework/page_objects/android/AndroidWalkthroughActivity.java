package com.generic_framework.page_objects.android;

import com.generic_framework.TestBase;
import com.generic_framework.features.LoginPage;
import com.generic_framework.features.WalkthroughPage;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;

public class AndroidWalkthroughActivity extends TestBase implements WalkthroughPage {

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.Slack:id/sign_in_button\")")
    AndroidElement signInBtn;

    public AndroidWalkthroughActivity() {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    @Override
    public LoginPage clickSignInBtn() {
        signInBtn.click();
        return new AndroidLoginActivity();
    }
}
