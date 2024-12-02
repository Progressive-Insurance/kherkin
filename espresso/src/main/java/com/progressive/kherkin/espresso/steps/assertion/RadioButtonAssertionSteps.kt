package com.progressive.kherkin.espresso.steps.assertion

import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.Visibility
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotChecked
import androidx.test.espresso.matcher.ViewMatchers.withChild
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.progressive.kherkin.common.testcore.Gherkin
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not

/** Finds a visible [RadioGroup] with [radioGroupViewId] and checks that it is displayed. */
fun Gherkin.IShouldSeeRadioGroup(@IdRes radioGroupViewId: Int) {
    onView(allOf(withId(radioGroupViewId), isAssignableFrom(RadioGroup::class.java), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}

/** Checks that no displayed [RadioGroup] with [radioGroupViewId] is found. */
fun Gherkin.IShouldNotSeeRadioGroup(@IdRes radioGroupViewId: Int) {
    onView(allOf(withId(radioGroupViewId), withChild(isAssignableFrom(RadioGroup::class.java)), isDisplayed()))
        .check(doesNotExist())
}

/** Finds a visible [RadioButton] with [radioGroupViewId] and checks that it is displayed. */
fun Gherkin.IShouldSeeRadioButton(@IdRes radioButtonViewId: Int) {
    onView(allOf(isAssignableFrom(RadioButton::class.java), withId(radioButtonViewId), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}

/** Finds a visible [RadioGroup] with [text] and checks that it is displayed. */
fun Gherkin.IShouldSeeRadioButtonWithText(text: String) {
    onView(allOf(isAssignableFrom(RadioButton::class.java), withText(text), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}

/** Checks that no displayed [RadioButton] with [text] is found. */
fun Gherkin.IShouldNotSeeRadioButtonWithText(text: String) {
    onView(allOf(isAssignableFrom(RadioButton::class.java), withText(text), isDisplayed()))
        .check(doesNotExist())
}

/** Finds a visible [RadioButton] with [text] and checks that it is enabled and displayed. */
fun Gherkin.IShouldSeeRadioButtonWithTextIsEnabled(text: String) {
    onView(allOf(isAssignableFrom(RadioButton::class.java), withText(text), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(allOf(isEnabled(), isDisplayed())))
}

/** Finds a visible [RadioButton] with [text] and checks that it is disabled and displayed. */
fun Gherkin.IShouldSeeRadioButtonWithTextIsDisabled(text: String) {
    onView(allOf(isAssignableFrom(RadioButton::class.java), withText(text), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(allOf(not(isEnabled()), isDisplayed())))
}

/** Finds a visible [RadioButton] with [text] and checks that it is checked and displayed. */
fun Gherkin.IShouldSeeRadioButtonWithTextIsSelected(text: String) {
    onView(allOf(isAssignableFrom(RadioButton::class.java), withText(text), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(allOf(isChecked(), isDisplayed())))
}

/** Finds a visible [RadioButton] with [text] and checks that it is not checked and displayed. */
fun Gherkin.IShouldSeeRadioButtonWithTextIsNotSelected(text: String) {
    onView(allOf(isAssignableFrom(RadioButton::class.java), withText(text), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(allOf(isNotChecked(), isDisplayed())))
}