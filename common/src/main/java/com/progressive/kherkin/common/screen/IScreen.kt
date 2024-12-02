package com.progressive.kherkin.common.screen

import android.app.Activity
import androidx.test.core.app.ActivityScenario

/**
 * IScreen defines the four necessary variables and functions for launching and identifying each
 * [Screen] being tested.
 * "[ActivityScenario] provides APIs to start and drive an Activity's lifecycle state for testing."
 * [Trait] is used to identify each screen by finding a unique string or view ID.
 * [screenActivityClass] is used to match the activity to the activity's simple class name.
 * [startMyActivity] is used to launch the screen's activity with any necessary setup, especially
 * by passing an intent.
 */
interface IScreen {
    val activityScenario: ActivityScenario<out Activity>
    val trait: Trait
    fun screenActivityClass(): Class<out Activity>
    fun startMyActivity()
}