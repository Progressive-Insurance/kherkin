package com.progressive.kherkin.espresso.steps.actions

import androidx.test.espresso.device.DeviceInteraction.Companion.setScreenOrientation
import androidx.test.espresso.device.EspressoDevice.Companion.onDevice
import androidx.test.espresso.device.action.ScreenOrientation
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.UiDevice
import com.progressive.kherkin.common.testcore.Gherkin

/**
 * Rotates the emulator or device to landscape orientation.
 * Remember to rotate back to portrait, or use a rule to start tests in the desired orientation.
 */
@Deprecated("Use ISetOrientationToLandscape instead, which uses UiAutomator and is more reliable across different devices and API levels.")
fun Gherkin.IRotateToLandscape() {
    onDevice().setScreenOrientation(ScreenOrientation.LANDSCAPE)
}

/**
 * Rotates the emulator or device to portrait orientation.
 */
@Deprecated("Use ISetOrientationToLandscape instead, which uses UiAutomator and is more reliable across different devices and API levels.")
fun Gherkin.IRotateToPortrait() {
    onDevice().setScreenOrientation(ScreenOrientation.PORTRAIT)
}

/**
 * Rotates the emulator or device to landscape orientation.
 * Remember to rotate back to portrait, or use a rule to start tests in the desired orientation.
 */
fun Gherkin.ISetOrientationToLandscape() {
    UiDevice.getInstance(getInstrumentation()).setOrientationLandscape()
}

/**
 * Rotates the emulator or device to portrait orientation.
 */
fun Gherkin.ISetOrientationToPortrait() {
    UiDevice.getInstance(getInstrumentation()).setOrientationPortrait()
}