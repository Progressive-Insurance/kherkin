package com.progressive.kherkin.espresso.steps.actions

import androidx.test.espresso.device.DeviceInteraction.Companion.setScreenOrientation
import androidx.test.espresso.device.EspressoDevice.Companion.onDevice
import androidx.test.espresso.device.action.ScreenOrientation
import com.progressive.kherkin.common.testcore.Gherkin

fun Gherkin.IRotateToLandscape() {
    onDevice().setScreenOrientation(ScreenOrientation.LANDSCAPE)
}

fun Gherkin.IRotateToPortrait() {
    onDevice().setScreenOrientation(ScreenOrientation.PORTRAIT)
}