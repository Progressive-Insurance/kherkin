package com.progressive.kherkin.espresso.steps.assertion

import android.R.id.message
import android.content.Context
import android.os.SystemClock
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSubstring
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.progressive.kherkin.common.testcore.Gherkin
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.containsString

/** Finds an alert and checks that it is displayed. */
fun Gherkin.IShouldSeeAlert() {
    onView(isRoot())
        .inRoot(isDialog())
        .check(matches(isDisplayed()))
}

/** Finds an alert with a message and checks that it is displayed. */
fun Gherkin.IShouldSeeAlertWithMessage() {
    onView(withId(message))
        .inRoot(isDialog())
        .check(matches(isDisplayed()))
}

/** Checks that no displayed alert with the message [text] is found. */
fun Gherkin.IShouldNotSeeAlertWithMessage(text: String) {
    onView(allOf(withId(message), withText(text), isDisplayed()))
        .check(doesNotExist())
}

/** Finds an alert with title text [title] and checks that it is displayed. */
fun Gherkin.IShouldSeeAlertWithTitle(title: String) {
    val appContext: Context = ApplicationProvider.getApplicationContext()
    val alertTitle = appContext.resources.getIdentifier("alertTitle", "id", "android")
    onView(withId(alertTitle))
        .inRoot(isDialog())
        .check(matches(allOf(withText(title), isDisplayed())))
}

/** Finds an alert with the message [text] and checks that it is displayed. */
fun Gherkin.IShouldSeeAlertWithMessage(text: String) {
    onView(withId(message))
        .inRoot(isDialog())
        .check(matches(allOf(withText(text), isDisplayed())))
}

/** Finds an alert with [text] and checks that it is displayed. */
fun Gherkin.IShouldSeeAlertWithText(text: String) {
    onView(withText(text))
        .inRoot(isDialog())
        .check(matches(isDisplayed()))
}

/** Finds an alert with text containing [text] and checks that it is displayed. */
fun Gherkin.IShouldSeeAlertWithContainingText(text: String) {
    onView(withSubstring(text))
        .inRoot(isDialog())
        .check(matches(withText(containsString(text))))
        .check(matches(isDisplayed()))
}

/** Finds an alert with the message containing [text] and checks that it is displayed. */
fun Gherkin.IShouldSeeAlertMessageContainingText(partialMessage: String) {
    onView(withId(message))
        .inRoot(isDialog())
        .check(matches(allOf(withText(containsString(partialMessage)), isDisplayed())))
}

/** Checks that no displayed alert with title text [title] is found. */
fun Gherkin.IShouldNotSeeAlertWithTitle(title: String) {
    val appContext: Context = ApplicationProvider.getApplicationContext()
    val alertTitle = appContext.resources.getIdentifier("alertTitle", "id", "android")
    SystemClock.sleep(50)
    onView(allOf(withId(alertTitle), withText(title), isDisplayed()))
        .inRoot(isDialog())
        .check(doesNotExist())
}

/** Checks that no displayed alert with [text] is found. */
fun Gherkin.IShouldNotSeeAlertWithText(text: String) {
    onView(allOf(withText(text), isDisplayed()))
        .inRoot(isDialog())
        .check(doesNotExist())
}