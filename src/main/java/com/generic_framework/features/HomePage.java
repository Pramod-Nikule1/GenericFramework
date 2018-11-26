package com.generic_framework.features;

import com.generic_framework.utils.OverflowOptions;
import com.generic_framework.utils.Status;

public interface HomePage {
    public String getProfileName();
    public SetCustomStatusPage selectOverflowOption(OverflowOptions option);
    public boolean verifyStatus(Status status);
    public boolean verifyCurrentStatusDisplayed();
    public boolean verifyOfflineIndicatorDisplayed();
    public void verifyOfflineIndicatorInvisible();
    public void sendMessage(String message);
    public boolean verifyMessageDisplayed(String message);
    public boolean isTeamNameDisplayed();
}
