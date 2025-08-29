package com.progressive.kherkin.espresso.steps.actions

import androidx.test.espresso.device.DeviceInteraction.Companion.setScreenOrientation
import androidx.test.espresso.device.EspressoDevice.Companion.onDevice
import androidx.test.espresso.device.action.ScreenOrientation
import com.progressive.kherkin.common.testcore.Gherkin

/**
 * Rotates the emulator or device to landscape orientation.
 * Remember to rotate back to portrait, or use a rule to start tests in the desired orientation.
 */
fun Gherkin.IRotateToLandscape() {
    onDevice().setScreenOrientation(ScreenOrientation.LANDSCAPE)
}

/**
 * Rotates the emulator or device to portrait orientation.
 */
fun Gherkin.IRotateToPortrait() {
    onDevice().setScreenOrientation(ScreenOrientation.PORTRAIT)
}