package com.progressive.kherkin.common.steps.setup

import com.progressive.kherkin.common.screen.Screen
import com.progressive.kherkin.common.testcore.Given

/** Sets the [Screen] the user is starting to [screen]. */
fun Given.IAmOnTheScreen(screen: Screen) {
    Screen.myCurrentScreen = screen
}