package com.generic_framework.utils;

import com.generic_framework.TestBase;

public class TestUtils extends TestBase {
    public static long IMPLICIT_WAIT = 20;
    public static String APK_PATH_COMMAND = "adb shell pm path ";
    public static String PULL_APK_COMMAND = "adb pull ";
    public static String DISABLE_WIFI = "adb shell am broadcast -a io.appium.settings.wifi --es setstatus disable";
    public static String ENABLE_WIFI = "adb shell am broadcast -a io.appium.settings.wifi --es setstatus enable";
}
