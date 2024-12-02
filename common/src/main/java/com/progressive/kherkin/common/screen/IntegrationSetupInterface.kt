package com.progressive.kherkin.common.screen

import com.progressive.kherkin.common.testcore.StepNavigator

/**
 * A simple interface for starting an activity and getting the start screen for navigation.
 * [startActivity] allows [Screen] to launch any activity through [Screen.myCurrentScreen].
 * [getStartScreen] is necessary for [StepNavigator] to find a path to a screen.
 */
interface IntegrationSetupInterface {
    fun startActivity()
    fun getStartScreen(): Screen
}