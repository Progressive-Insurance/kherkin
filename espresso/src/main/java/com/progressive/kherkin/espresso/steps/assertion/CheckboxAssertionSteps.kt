package com.progressive.kherkin.espresso.steps.assertion

import android.widget.CheckBox
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.Visibility
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotChecked
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.progressive.kherkin.common.testcore.Gherkin
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anyOf
import org.hamcrest.CoreMatchers.not

/** Finds a visible [CheckBox] with [checkboxViewId] and checks that it is displayed. */
fun Gherkin.IShouldSeeCheckbox(@IdRes checkboxViewId: Int) {
    onView(allOf(isAssignableFrom(CheckBox::class.java), withId(checkboxViewId), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}

/** Checks that no displayed [CheckBox] with [checkboxViewId] is found. */
fun Gherkin.IShouldNotSeeCheckbox(@IdRes checkboxViewId: Int) {
    onView(allOf(isAssignableFrom(CheckBox::class.java), withId(checkboxViewId), isDisplayed()))
        .check(doesNotExist())
}

/**
 * Finds a visible [CheckBox] with [checkboxViewId] and with [text] or with a sibling view
 * with [text], and checks that it is displayed.
 */
fun Gherkin.IShouldSeeCheckboxWithText(@IdRes checkboxViewId: Int, text: String) {
    onView(allOf(isAssignableFrom(CheckBox::class.java), withId(checkboxViewId),
        (anyOf(hasSibling(withText(text)), withText(text))),
        withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}

/** Finds a visible [CheckBox] with [text] and checks that it is displayed. */
fun Gherkin.IShouldSeeCheckboxWithText(text: String) {
    onView(allOf(isAssignableFrom(CheckBox::class.java), withText(text), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}

/** Finds a visible [CheckBox] with [checkboxViewId] and checks that it is enabled and displayed. */
fun Gherkin.IShouldSeeCheckboxIsEnabled(@IdRes checkboxViewId: Int) {
    onView(allOf(isAssignableFrom(CheckBox::class.java), withId(checkboxViewId), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(allOf(isEnabled(), isDisplayed())))
}

/** Finds a visible [CheckBox] with [checkboxViewId] and checks that it is disabled and displayed. */
fun Gherkin.IShouldSeeCheckboxIsDisabled(@IdRes checkboxViewId: Int) {
    onView(allOf(isAssignableFrom(CheckBox::class.java), withId(checkboxViewId), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(allOf(not(isEnabled()), isDisplayed())))
}

/**
 * Finds a visible [CheckBox] with [checkboxViewId] and with [text] or with a sibling view
 * with [text], and checks that it is checked and displayed.
 */
fun Gherkin.IShouldSeeCheckboxWithTextIsChecked(@IdRes checkboxViewId: Int, text: String) {
    onView(allOf(isAssignableFrom(CheckBox::class.java), withId(checkboxViewId),
        (anyOf(hasSibling(withText(text)), withText(text))),
        withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(allOf(isChecked(), isDisplayed())))
}

/**
 * Finds a visible [CheckBox] with [checkboxViewId] and with [text] or with a sibling view
 * with [text], and checks that it is displayed and not checked.
 */
fun Gherkin.IShouldSeeCheckboxWithTextIsNotChecked(@IdRes checkboxViewId: Int, text: String) {
    onView(allOf(isAssignableFrom(CheckBox::class.java), withId(checkboxViewId),
        (anyOf(hasSibling(withText(text)), withText(text))), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(allOf(isNotChecked(), isDisplayed())))
}

/** Finds a visible [CheckBox] with [text] and checks that it is checked and displayed. */
fun Gherkin.IShouldSeeCheckboxWithTextIsChecked(text: String) {
    onView(allOf(isAssignableFrom(CheckBox::class.java), withText(text), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(allOf(isChecked(), isDisplayed())))
}

/** Finds a visible [CheckBox] with [text] and checks that it is displayed and not checked. */
fun Gherkin.IShouldSeeCheckboxWithTextIsNotChecked(text: String) {
    onView(allOf(isAssignableFrom(CheckBox::class.java), withText(text), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(allOf(isNotChecked(), isDisplayed())))
}

/** Finds a visible [CheckBox] with [checkboxViewId] and checks that it is checked and displayed. */
fun Gherkin.IShouldSeeCheckboxIsChecked(@IdRes checkboxViewId: Int) {
    onView(allOf(isAssignableFrom(CheckBox::class.java), withId(checkboxViewId), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(allOf(isChecked(), isDisplayed())))
}

/** Finds a visible [CheckBox] with [checkboxViewId] and checks that it is displayed and not checked. */
fun Gherkin.IShouldSeeCheckboxIsNotChecked(@IdRes checkboxViewId: Int) {
    onView(allOf(isAssignableFrom(CheckBox::class.java), withId(checkboxViewId), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(allOf(isNotChecked(), isDisplayed())))
}