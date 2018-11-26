package com.generic_framework.features;

public interface SignInPage {
    HomePage setPassword(String passwd);
    void clickNext();
    void setEmailId(String emailId);
    void clickGoogleAccount();
    void setTeamUrl(String teamURL);
}
