package com.progressive.kherkin.espresso.steps.actions

import android.text.style.ClickableSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withTagValue
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.progressive.kherkin.espresso.custom.CustomViewAction.clickClickableSpan
import com.progressive.kherkin.espresso.custom.CustomViewAction.safeScrollTo
import com.progressive.kherkin.espresso.custom.CustomViewMatcher.withIndex
import com.progressive.kherkin.common.testcore.Gherkin
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.`is`

/**
 * Finds a view with [text].
 * Scrolls to it and clicks it.
 */
fun Gherkin.ITouch(text: String) {
    onView(withText(text)).perform(safeScrollTo(), click())
}

/**
 * Attempts to find a view with [text].
 * Scrolls to it and clicks it, if found.
 */
fun Gherkin.ITouchIfShowing(text: String) {
    try {
        ITouch(text)
    } catch (e: Exception) {
        // do nothing
    }
}

/**
 * Finds a view with [viewID] which has a parent with [parentViewID].
 * Scrolls to it and clicks it.
 */
fun Gherkin.ITouch(@IdRes viewID: Int, @IdRes parentViewID: Int) {
    onView(allOf(withId(viewID), isDescendantOfA(withId(parentViewID))))
        .perform(safeScrollTo(), click())
}

/**
 * Finds a view with [viewID].
 * Scrolls to it and clicks it.
 */
fun Gherkin.ITouch(@IdRes viewID: Int) {
    onView(withId(viewID)).perform(safeScrollTo(), click())
}

/**
 * Finds a view with [viewId] and with [text].
 * Scrolls to it and clicks it.
 */
fun Gherkin.ITouchViewWithText(@IdRes viewID: Int, text: String) {
    onView(allOf(withId(viewID), withText(text))).perform(safeScrollTo(), click())
}

/**
 * Finds a view with [viewId] and with hint text [text].
 * Scrolls to it and clicks it.
 */
fun Gherkin.ITouchViewWithHint(@IdRes viewID: Int, text: String) {
    onView(allOf(withId(viewID), withHint(text))).perform(safeScrollTo(), click())
}

/**
 * Finds a view with [viewId] and with content description [contentDesc].
 * Scrolls to it and clicks it.
 */
fun Gherkin.ITouchViewWithContentDescription(@IdRes viewID: Int, contentDesc: String) {
    onView(allOf(withId(viewID), withContentDescription(contentDesc)))
        .perform(safeScrollTo(), click())
}

/**
 * Finds a view with content description [contentDesc].
 * Scrolls to it and clicks it.
 */
fun Gherkin.ITouchViewWithContentDescription(contentDesc: String) {
    onView(withContentDescription(contentDesc)).perform(safeScrollTo(), click())
}

/** Presses the default Android back button. */
fun Gherkin.IPressBack() {
    pressBack()
}

/** Closes the software keyboard. */
fun Gherkin.IDismissKeyboard() {
    closeSoftKeyboard()
}

/**
 * Finds an [ImageView] with [viewId].
 * Scrolls to it and clicks it.
 */
fun Gherkin.ITouchImage(@IdRes viewId: Int) {
    onView(allOf(isAssignableFrom(ImageView::class.java), withId(viewId)))
        .perform(safeScrollTo(), click())
}

/**
 * Finds an enabled view with [tag] and with [text].
 * Scrolls to it and clicks it.
 */
fun Gherkin.ITouchViewWithTagAndContainingText(tag: String, text: String) {
    onView(allOf(withTagValue(`is`(tag)), withText(containsString(text)), isEnabled(), isDisplayed()))
        .perform(safeScrollTo(), click())
}

/**
 * Finds an enabled view with [tag].
 * Scrolls to it and clicks it.
 */
fun Gherkin.ITouchViewWithTag(tag: String) {
    onView(allOf(withTagValue(`is`(tag)), isEnabled(), isDisplayed()))
        .perform(safeScrollTo(), click())
}

/**
 * Finds an enabled view with [tag] and with [viewID].
 * Scrolls to it and clicks it.
 */
fun Gherkin.ITouchViewWithTag(tag: String, @IdRes viewID: Int) {
    onView(allOf(withTagValue(`is`(tag)), withId(viewID), isEnabled(), isDisplayed()))
        .perform(safeScrollTo(), click())
}

/**
 * Finds a view with [viewId] at position [index].
 * Clicks it.
 */
fun Gherkin.ITouchViewWithinAListAtIndex(@IdRes viewId: Int, index: Int) {
    onView(withIndex(withId(viewId), index)).perform(click())
}

/**
 * Finds an enabled [TextView] with [viewId].
 * Clicks it.
 */
fun Gherkin.ITouchLink(@IdRes viewId: Int) {
    onView(allOf(isAssignableFrom(TextView::class.java), withId(viewId), isEnabled()))
        .perform(safeScrollTo(), click())
}

/**
 * Finds a [ClickableSpan] with [spannableText] and [viewId].
 * Clicks it.
 */
fun Gherkin.ITouchClickableTextInView(spannableText: String, @IdRes viewId: Int) {
    onView(withId(viewId)).perform(clickClickableSpan(spannableText))
}