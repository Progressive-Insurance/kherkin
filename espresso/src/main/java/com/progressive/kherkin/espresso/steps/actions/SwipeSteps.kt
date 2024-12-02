package com.progressive.kherkin.espresso.steps.actions

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.progressive.kherkin.common.testcore.Gherkin
import org.hamcrest.CoreMatchers.allOf

/** Swipes from right to left on the screen. */
fun Gherkin.ISwipeLeft() {
    onView(isRoot()).perform(swipeLeft())
}

/** Swipes from right to left on a displayed view with [viewId]. */
fun Gherkin.ISwipeLeftOnView(@IdRes viewId: Int) {
    onView(allOf(withId(viewId), isDisplayed())).perform(swipeLeft())
}

/** Swipes from right to left on a displayed view with [text]. */
fun Gherkin.ISwipeLeftOn(text: String) {
    onView(allOf(withText(text), isDisplayed())).perform(swipeLeft())
}

/** Swipes from left to right on the screen. */
fun Gherkin.ISwipeRight() {
    onView(isRoot()).perform(swipeRight())
}

/** Swipes from left to right on a displayed view with [viewId]. */
fun Gherkin.ISwipeRightOnView(@IdRes viewId: Int) {
    onView(allOf(withId(viewId), isDisplayed())).perform(swipeRight())
}

/** Swipes from left to right on a displayed view with [text]. */
fun Gherkin.ISwipeRightOn(text: String) {
    onView(allOf(withText(text), isDisplayed())).perform(swipeRight())
}

/** Swipes from bottom to top on the screen. */
fun Gherkin.ISwipeUp() {
    onView(isRoot()).perform(swipeUp())
}

/** Swipes from bottom to top until a view with [text] is completely displayed. */
fun Gherkin.ISwipeUpUntilISee(text: String) {
    for (i in 1..40) {
        onView(isRoot()).perform(swipeUp())
        try {
            onView(withText(text)).check(matches(isCompletelyDisplayed()))
            break
        } catch (e: Exception) {
        }
    }
    onView(withText(text)).check(matches(isDisplayed()))
}

/** Swipes from bottom to top until a view with [viewId] is completely displayed. */
fun Gherkin.ISwipeUpUntilISeeTheView(@IdRes viewId: Int) {
    for (i in 1..40) {
        onView(isRoot()).perform(swipeUp())
        try {
            onView(withId(viewId)).check(matches(isCompletelyDisplayed()))
            break
        } catch (e: Exception) {
        }
    }
    onView(withId(viewId)).check(matches(isDisplayed()))
}

/** Swipes from bottom to top on a displayed view with [text]. */
fun Gherkin.ISwipeUpOn(text: String) {
    onView(allOf(withText(text), isDisplayed())).perform(swipeUp())
}

/** Swipes from bottom to top on a displayed view with [viewId]. */
fun Gherkin.ISwipeUpOnView(@IdRes viewId: Int) {
    onView(allOf(withId(viewId), isDisplayed())).perform(swipeUp())
}

/** Swipes from top to bottom on the screen. */
fun Gherkin.ISwipeDown() {
    onView(isRoot()).perform(swipeDown())
}

/** Swipes from top to bottom on a displayed view with [text]. */
fun Gherkin.ISwipeDownOn(text: String) {
    onView(allOf(withText(text), isDisplayed())).perform(swipeDown())
}

/** Swipes from top to bottom on a displayed view with [viewId]. */
fun Gherkin.ISwipeDownOnView(@IdRes viewId: Int) {
    onView(allOf(withId(viewId), isDisplayed())).perform(swipeDown())
}