package com;

import com.generic_framework.TestBase;
import com.generic_framework.features.HomePage;
import com.generic_framework.features.LoginPage;
import com.generic_framework.features.SignInPage;
import com.generic_framework.features.WalkthroughPage;
import com.generic_framework.page_objects.android.AndroidWalkthroughActivity;
import com.generic_framework.page_objects.web.WebWalkthroughPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DemoTest extends TestBase {

    private SignInPage signInActivity;
    private WalkthroughPage walkthroughActivity;
    private LoginPage chooseLoginActivity;
    private HomePage homeActivity;

    public DemoTest() {
        super();
    }

    @BeforeMethod
    @Parameters("platform")
    public void setup(String platform) {
        initialization(platform);
        if (platform.equals("web"))
            walkthroughActivity = new WebWalkthroughPage();
        if (platform.equals("android"))
            walkthroughActivity = new AndroidWalkthroughActivity();
    }

    @AfterMethod
    @Parameters("platform")
    public void cleanup(String platform) {
        driver.quit();
        if (platform.equals("android"))
            stopServer();
    }

    @Test(priority = 1)
    public void signInTest() {
        chooseLoginActivity = walkthroughActivity.clickSignInBtn();
        signInActivity = chooseLoginActivity.selectManualSignIn();
        signInActivity.setTeamUrl(properties.getProperty("workspace"));
        signInActivity.setEmailId(properties.getProperty("emailID"));
        homeActivity = signInActivity.setPassword(properties.getProperty("password"));
        Assert.assertTrue(homeActivity.isTeamNameDisplayed());
    }
}
