package com.progressive.kherkin.common.steps.actions

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import com.progressive.kherkin.common.testcore.Gherkin
import com.progressive.kherkin.common.testcore.IntegrationTestLogger

/**
 * Finds a view with the text "OK" and clicks on it.
 * Use this to dismiss system alerts if they sometimes appear and cause UI tests to fail.
 * Unlike most steps, this one uses the UI Automator by using [UiDevice] to access views on the
 * screen regardless of the activity in focus.
 */
fun Gherkin.IDismissSystemAlertWithOKButton() {
    val logger = IntegrationTestLogger()
    val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    // To handle alerts
    val okButton = device.findObject(UiSelector().textContains("OK"))
    if (okButton.exists()) {
        logger.info("Dismiss Alert")
        okButton.click()
    }
}