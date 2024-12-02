package com.progressive.kherkin.espresso.steps.actions

import android.widget.AdapterView
import android.widget.Spinner
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.progressive.kherkin.espresso.custom.CustomViewAction.safeScrollTo
import com.progressive.kherkin.common.testcore.Gherkin
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.CoreMatchers.`is`

/**
 * Finds a view that acts like a [Spinner] (any view that displays an [AdapterView] on click) with
 * [spinnerId].
 * Scrolls to it and clicks it.
 * Then finds a view with [text] and clicks on it.
 */
fun Gherkin.ISelectTextFromSpinner(@IdRes spinnerId: Int, text: String) {
    onView(withId(spinnerId)).perform(safeScrollTo(), click())
    onData(allOf(`is`(instanceOf(String::class.java)), `is`(text))).perform(click())
}

/**
 * Finds a view that acts like a [Spinner] (any view that displays an [AdapterView] on click) with
 * [spinnerId].
 * Scrolls to it and clicks it.
 * Then finds a view at [index] and clicks on it.
 */
fun Gherkin.ISelectItemFromSpinnerAtIndex(@IdRes spinnerId: Int, index: Int) {
    onView(withId(spinnerId)).perform(safeScrollTo(), click())
    onData(allOf(`is`(instanceOf(String::class.java)))).atPosition(index).perform(click())
}

/**
 * Finds a view that acts like a [Spinner] (any view that displays an [AdapterView] on click) with
 * [spinnerId].
 * Scrolls to it and clicks it.
 * Then presses the default Android back button to dismiss the view the spinner created.
 */
fun Gherkin.IDoNotSelectAnythingFromSpinner(@IdRes spinnerId: Int) {
    onView(withId(spinnerId)).perform(safeScrollTo(), click())
    pressBack()
}