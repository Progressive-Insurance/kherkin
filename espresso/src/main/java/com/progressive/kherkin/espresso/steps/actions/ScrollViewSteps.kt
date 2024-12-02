package com.progressive.kherkin.espresso.steps.actions

import android.view.View
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.Visibility
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.progressive.kherkin.espresso.custom.CustomViewAction.safeScrollTo
import com.progressive.kherkin.espresso.custom.CustomViewAction.waitForView
import com.progressive.kherkin.common.testcore.Gherkin
import com.progressive.kherkin.common.testcore.TimeoutConstants
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

/**
 * Finds a view with [text].
 * Scrolls to it and checks it is displayed.
 */
fun Gherkin.IScrollUntilISee(text: String) {
    onView(withText(text))
        .perform(safeScrollTo())
        .check(matches(isDisplayed()))
}

/**
 * Finds a view with [viewId].
 * Scrolls to it and checks it is displayed.
 */
fun Gherkin.IScrollUntilISee(@IdRes viewId: Int) {
    onView(withId(viewId))
        .perform(safeScrollTo())
        .check(matches(isDisplayed()))
}

/**
 * Finds a view with [text] and with [viewId].
 * Scrolls to it and checks it is displayed.
 */
fun Gherkin.IScrollUntilISee(text: String, viewId: Int) {
    onView(allOf(withText(text), withId(viewId)))
        .perform(safeScrollTo())
        .check(matches(isDisplayed()))
}

/**
 * Finds a view with hint text [text].
 * Scrolls to it and checks it is displayed.
 */
fun Gherkin.IScrollUntilISeeTheHint(hint: String?) {
    onView(withHint(hint))
        .perform(safeScrollTo())
        .check(matches(isDisplayed()))
}

/**
 * Waits to see a view with [text] for 500 milliseconds.
 * Scrolls to it and checks it is displayed.
 */
fun Gherkin.IScrollToTextWhenVisible(text: String?) {
    onView(isRoot())
        .perform(waitForView(allOf(withText(text), withEffectiveVisibility(Visibility.VISIBLE)), TimeoutConstants.LONG_TIMEOUT))

    onView(withText(text)).perform(safeScrollTo()).check(matches(isDisplayed()))
}

/**
 * Waits to see a view with [viewId] for 500 milliseconds.
 * Scrolls to it and checks it is displayed.
 */
fun Gherkin.IScrollToIdWhenVisible(viewId: Int) {
    onView(isRoot())
        .perform(waitForView(allOf(withId(viewId), withEffectiveVisibility(Visibility.VISIBLE)), TimeoutConstants.LONG_TIMEOUT))

    onView(withId(viewId)).perform(safeScrollTo()).check(matches(isDisplayed()))
}

/**
 * Waits to see a view with [viewId] and with [text] for 500 milliseconds.
 * Scrolls to it and checks it is displayed.
 */
fun Gherkin.IScrollToViewWithIdAndTextWhenVisible(viewId: Int, text: String) {
    onView(isRoot())
        .perform(waitForView(allOf(withId(viewId), withText(text), withEffectiveVisibility(Visibility.VISIBLE)), TimeoutConstants.LONG_TIMEOUT))

    onView(allOf(withId(viewId), withText(text)))
        .perform(safeScrollTo())
        .check(matches(isDisplayed()))
}

/**
 * Waits to see a view matching a custom [Matcher] by [viewMatcher] for 500 milliseconds.
 * Scrolls to it and checks it is displayed.
 */
fun Gherkin.IScrollToView(viewMatcher: Matcher<View>) {
    onView(isRoot()).perform(waitForView(viewMatcher, TimeoutConstants.LONG_TIMEOUT))
    onView(viewMatcher)
        .perform(scrollTo())
        .check(matches(isDisplayed()))
}

/**
 * Waits to see a view matching a custom [ViewInteraction] by [viewInteraction] for 500 milliseconds.
 * Scrolls to it and checks it is displayed.
 */
fun Gherkin.IScrollToView(viewInteraction: ViewInteraction) {
    viewInteraction
        .perform(safeScrollTo())
        .check(matches(isDisplayed()))
}