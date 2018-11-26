package com.generic_framework.page_objects.web;

import com.generic_framework.features.LoginPage;
import com.generic_framework.features.WalkthroughPage;

public class WebWalkthroughPage implements WalkthroughPage {
    @Override
    public LoginPage clickSignInBtn() {
        return new WebLoginPage();
    }
}
