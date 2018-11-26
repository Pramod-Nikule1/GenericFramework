package com.generic_framework.page_objects.web;

import com.generic_framework.features.HomePage;
import com.generic_framework.features.SetCustomStatusPage;
import com.generic_framework.page_objects.android.AndroidHomeActivity;
import com.generic_framework.utils.Status;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WebCustomStatusActivity implements SetCustomStatusPage {

    @FindBy(xpath = "//div[contains(text(), 'In a meeting')]")
    WebElement inMeeting;

    @FindBy(xpath = "//div[contains(text(), 'Commuting')]")
    WebElement commuting;

    @FindBy(xpath = "//div[contains(text(), 'Out sick')]")
    WebElement out_sick;

    @FindBy(xpath = "//div[contains(text(), 'Vacationing')]")
    WebElement vacationing;

    @FindBy(xpath = "//div[contains(text(), 'Working remotely')]")
    WebElement working_Remotely;

    @FindBy(xpath = "//button[@text='Save']")
    WebElement saveButton;

    @Override
    public HomePage setStatus(Status status) {
        switch (status) {
            case In_Meeting:
                inMeeting.click();
                break;
            case Out_Sick:
                out_sick.click();
                break;
            case Commuting:
                commuting.click();
                break;
            case Vacationing:
                vacationing.click();
                break;
            case Custom_Status:
                break;
            case Working_Remotely:
                working_Remotely.click();
                break;
        }
        saveButton.click();
        return new WebHomePage();
    }

    @Override
    public HomePage clearStatus() {
        return null;
    }
}
