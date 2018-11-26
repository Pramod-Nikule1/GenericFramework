package com.generic_framework;

import com.generic_framework.utils.TestUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {
    public static Properties properties;
    public static WebDriver driver;
    public static AppiumDriverLocalService service;

    public TestBase() {
        properties = new Properties();
        try {
            properties.load(new FileReader("src/main/resources/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialization(String driverType) {
        if ("web".equals(driverType)) {
            driver = new ChromeDriver();
            driver.get(properties.getProperty("url"));
            driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        }
        if ("android".equals(driverType)) {
            startServer();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("deviceName", "Nexus 6");
            capabilities.setCapability(AndroidMobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
            capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, properties.getProperty("app_package"));
            capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, properties.getProperty("app_activity"));
            capabilities.setCapability("automationName", AutomationName.ANDROID_UIAUTOMATOR2);
            capabilities.setCapability("noReset", true);

            driver = new AndroidDriver(service.getUrl(), capabilities);
            driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICIT_WAIT, TimeUnit.SECONDS);
            ((AndroidDriver) driver).resetApp();
        }
    }

    public static void playStoreInit() {
        if (service.isRunning()) {

        }
        startServer();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Tab S");
        capabilities.setCapability(AndroidMobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.android.vending");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.google.android.finsky.activities.MainActivity");
        capabilities.setCapability("noReset", true);

        driver = new AndroidDriver(service.getUrl(), capabilities);
        driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICIT_WAIT, TimeUnit.SECONDS);
    }

    /**
     * Starts Appium server
     */
    public static void startServer() {
        //Set Appium Server Capabilities
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("noReset", "false");

        //Build the Appium service
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress("0.0.0.0");
        builder.usingAnyFreePort();
        builder.withCapabilities(cap);
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOCAL_TIMEZONE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "debug");

        //Start the server with the builder
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
    }

    /**
     * Stops Appium Server
     */
    public static void stopServer() {
        service.stop();
    }

    /**
     * Turns the WiFi On
     * @throws IOException
     */
    public void enableWiFi() throws IOException {
        Runtime.getRuntime().exec(TestUtils.ENABLE_WIFI);
    }

    /**
     * Turns the WiFi Off
     */
    public void disableWiFi() throws IOException {
        Runtime.getRuntime().exec(TestUtils.DISABLE_WIFI);
    }

    /**
     * Pulls the apk of provided package from the device
     */
    public void fetchApkFromDevice(String packageName) throws IOException {
        Process process = Runtime.getRuntime().exec(TestUtils.APK_PATH_COMMAND+packageName);
        BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String completePath = input.readLine();
        String[] apkPath = completePath.split(":");

        Runtime.getRuntime().exec(TestUtils.PULL_APK_COMMAND+apkPath[1]+ " pulled-file");
    }

    public void fetchApkInfo(String apkPath) throws IOException {
        Process process = Runtime.getRuntime().exec("aapt dump badging "+ apkPath+" | findstr -i \"package: name\"");
        BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String completePath = input.readLine();

        System.out.println(completePath);
    }
}
