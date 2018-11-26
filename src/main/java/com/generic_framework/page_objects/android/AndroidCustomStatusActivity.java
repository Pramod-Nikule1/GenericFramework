package com.generic_framework.page_objects.android;

import com.generic_framework.TestBase;
import com.generic_framework.features.HomePage;
import com.generic_framework.features.SetCustomStatusPage;
import com.generic_framework.utils.Status;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;

public class AndroidCustomStatusActivity extends TestBase implements SetCustomStatusPage {

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"In a meeting\")")
    private AndroidElement inMeeting;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Commuting\")")
    private AndroidElement commuting;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Out sick\")")
    private AndroidElement outSick;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Vacationing\")")
    private AndroidElement vacationing;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Working remotely\")")
    private AndroidElement workingRemotely;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.Slack:id/set_status_field\")")
    private AndroidElement customStatus;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.Slack:id/menu_item\")")
    private AndroidElement saveButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.Slack:id/clear_status_button\")")
    private AndroidElement clearStatusButton;

    public AndroidCustomStatusActivity() {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    @Override
    public HomePage setStatus(Status status) {
        switch (status) {
            case In_Meeting:
                inMeeting.click();
                break;
            case Out_Sick:
                outSick.click();
                break;
            case Commuting:
                commuting.click();
                break;
            case Vacationing:
                vacationing.click();
                break;
            case Custom_Status:
                customStatus.click();
                break;
            case Working_Remotely:
                workingRemotely.click();
                break;
        }
        saveButton.click();
        return new AndroidHomeActivity();
    }

    @Override
    public HomePage clearStatus() {
        clearStatusButton.click();
        saveButton.click();
        return new AndroidHomeActivity();
    }
}
