package com.progressive.kherkin.espresso.steps.assertion

import android.widget.Button
import androidx.annotation.IdRes
import androidx.appcompat.widget.Toolbar
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.Visibility
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotChecked
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.progressive.kherkin.common.testcore.Gherkin
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not

/** Finds a visible [Button] with [buttonViewId] and checks that it is displayed. */
fun Gherkin.IShouldSeeButton(@IdRes buttonViewId: Int) {
    onView(allOf(isAssignableFrom(Button::class.java), withId(buttonViewId), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}

/** Checks that no displayed [Button] with [buttonViewId] is found. */
fun Gherkin.IShouldNotSeeButton(@IdRes buttonViewId: Int) {
    onView(allOf(isAssignableFrom(Button::class.java), withId(buttonViewId), isDisplayed()))
        .check(doesNotExist())
}

/** Finds a visible [Button] with [text] and checks that it is displayed. */
fun Gherkin.IShouldSeeButtonWithText(text: String) {
    onView(allOf(isAssignableFrom(Button::class.java), withText(text), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}

/** Checks that no displayed [Button] with [text] is found. */
fun Gherkin.IShouldNotSeeButtonWithText(text: String) {
    onView(allOf(isAssignableFrom(Button::class.java), withText(text), isDisplayed()))
        .check(doesNotExist())
}

/** Finds a visible [Button] with [buttonViewId] and with [text] and checks that it is displayed. */
fun Gherkin.IShouldSeeButtonWithText(@IdRes buttonViewId: Int, text: String) {
    onView(allOf(isAssignableFrom(Button::class.java), withId(buttonViewId), withText(text), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}

/** Checks that no displayed [Button] with [buttonViewId] and with [text] is found. */
fun Gherkin.IShouldNotSeeButtonWithText(@IdRes buttonViewId: Int, text: String) {
    onView(allOf(isAssignableFrom(Button::class.java), withId(buttonViewId), withText(text), isDisplayed()))
        .check(doesNotExist())
}

/** Finds a visible [Button] with [buttonViewId] and checks that it is enabled and displayed. */
fun Gherkin.IShouldSeeButtonIsEnabled(@IdRes buttonViewId: Int) {
    onView(allOf(isAssignableFrom(Button::class.java), withId(buttonViewId), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(allOf(isEnabled(), isDisplayed())))
}

/** Finds a visible [Button] with [buttonViewId] and checks that it is disabled and displayed. */
fun Gherkin.IShouldSeeButtonIsDisabled(@IdRes buttonViewId: Int) {
    onView(allOf(isAssignableFrom(Button::class.java), withId(buttonViewId), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(allOf(not(isEnabled()), isDisplayed())))
}

/**
 * Finds a visible [Button] with [buttonViewId] and with [text], and checks that it is enabled and
 * displayed.
 */
fun Gherkin.IShouldSeeButtonWithTextIsEnabled(@IdRes buttonViewId: Int, text: String) {
    onView(allOf(isAssignableFrom(Button::class.java), withId(buttonViewId), withText(text), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(allOf(isEnabled(), isDisplayed())))
}

/**
 * Finds a visible [Button] with [buttonViewId] and with [text], and checks that it is disabled and
 * displayed.
 */
fun Gherkin.IShouldSeeButtonWithTextIsDisabled(@IdRes buttonViewId: Int, text: String) {
    onView(allOf(isAssignableFrom(Button::class.java), withId(buttonViewId), withText(text), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(allOf(not(isEnabled()), isDisplayed())))
}

/**
 * Finds a visible [Button] with [buttonViewId] and with [text], and checks that it is
 * checked and displayed.
 */
fun Gherkin.IShouldSeeButtonWithTextIsChecked(@IdRes buttonViewId: Int, text: String) {
    onView(allOf(isAssignableFrom(Button::class.java), withId(buttonViewId), withText(text), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(allOf(isChecked(), isDisplayed())))
}

/**
 * Finds a visible [Button] with [buttonViewId] and with [text], and checks that it is
 * displayed and not checked.
 */
fun Gherkin.IShouldSeeButtonWithTextIsNotChecked(@IdRes buttonViewId: Int, text: String) {
    onView(allOf(isAssignableFrom(Button::class.java), withId(buttonViewId), withText(text), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(allOf(isNotChecked(), isDisplayed())))
}

/**
 * Finds a visible view on a [Toolbar] with [viewId] and with [text], and checks that it is
 * displayed.
 */
fun Gherkin.IShouldSeeToolbarItemWithText(@IdRes viewId: Int, text: String) {
    onView(allOf(isDescendantOfA(isAssignableFrom(Toolbar::class.java)), withId(viewId),
        withText(text), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}

/** Finds a visible view on a [Toolbar] with [viewId] and checks that it is displayed. */
fun Gherkin.IShouldSeeToolbarItem(@IdRes viewId: Int) {
    onView(allOf(isDescendantOfA(isAssignableFrom(Toolbar::class.java)), withId(viewId), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}