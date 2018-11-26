package com.generic_framework.page_objects.android;

import com.generic_framework.TestBase;
import com.generic_framework.features.HomePage;
import com.generic_framework.features.SetCustomStatusPage;
import com.generic_framework.utils.OverflowOptions;
import com.generic_framework.utils.Status;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AndroidHomeActivity extends TestBase implements HomePage {

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.Slack:id/team_name\")")
    private AndroidElement teamName;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.Slack:id/search_button\")")
    private AndroidElement searchButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.Slack:id/overflow_button\")")
    private AndroidElement overflowButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.Slack:id/name\")")
    private AndroidElement profileName;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Set a status\")")
    private AndroidElement setStatus;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Edit status\")")
    private AndroidElement editStatus;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.Slack:id/status\")")
    private AndroidElement currentStatus;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.Slack:id/retry_bar\")")
    private AndroidElement offlineIndicator;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.Slack:id/message_input_field\")")
    private AndroidElement messageInput;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.Slack:id/message_send_btn\")")
    private AndroidElement messageSendButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.Slack:id/message_layout\")")
    private List<AndroidElement> messages;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.Slack:id/avatar_button\")")
    private AndroidElement navDrawerBtn;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"random\")")
    private AndroidElement randomChannel;

    public AndroidHomeActivity() {
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    @Override
    public String getProfileName() {
        overflowButton.click();
        return profileName.getText();
    }

    @Override
    public SetCustomStatusPage selectOverflowOption(OverflowOptions option) {
        overflowButton.click();
        switch (option) {
            case Snooze:
                break;
            case Activity:
                break;
            case Settings:
                break;
            case Directory:
                break;
            case Set_Status:
                setStatus.click();
                return new AndroidCustomStatusActivity();
            case Edit_Status:
                editStatus.click();
                return new AndroidCustomStatusActivity();
            case Invite_Members:
                break;
        }
        return null;
    }

    @Override
    public boolean verifyStatus(Status status) {
        overflowButton.click();
        String expectedStatus = null;
        String actualStatus = currentStatus.getText();
        switch (status) {
            case In_Meeting:
                expectedStatus = Status.In_Meeting.toString();
                break;
            case Out_Sick:
                expectedStatus = Status.Out_Sick.toString();
                break;
            case Commuting:
                expectedStatus = Status.Custom_Status.toString();
                break;
            case Vacationing:
                expectedStatus = Status.Vacationing.toString();
                break;
            case Custom_Status:
                expectedStatus = Status.Custom_Status.toString();
                break;
            case Working_Remotely:
                expectedStatus = Status.Working_Remotely.toString();
                break;
        }
        return actualStatus.contains(expectedStatus);
    }

    @Override
    public boolean verifyCurrentStatusDisplayed() {
        overflowButton.click();
        try {
            return currentStatus.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public boolean verifyOfflineIndicatorDisplayed() {
        try {
            return offlineIndicator.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public void verifyOfflineIndicatorInvisible() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("com.Slack:id/retry_bar")));
    }

    @Override
    public void sendMessage(String message) {
        messageInput.setValue(message);
        messageSendButton.click();
    }

    @Override
    public boolean verifyMessageDisplayed(String message) {
        ((AndroidDriver) driver).hideKeyboard();
        String actualMessage = messages.get(messages.size()-1).findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.Slack:id/msg_text\")").getText();
        return actualMessage.equals(message);
    }

    @Override
    public boolean isTeamNameDisplayed() {
        return teamName.isDisplayed();
    }

    public void openNavDrawer() {
        navDrawerBtn.click();
    }
}
