package com.progressive.kherkin.espresso.steps.assertion

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.Visibility
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isSelected
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withTagValue
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.progressive.kherkin.common.testcore.Gherkin
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.endsWith
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.startsWith

/** Finds a visible view with [text] and checks that it is displayed. */
fun Gherkin.IShouldSeeText(text: String) {
    onView(allOf(withText(text), withEffectiveVisibility(Visibility.VISIBLE))).check(matches(isDisplayed()))
}

/** Checks that no displayed view with [text] is found. */
fun Gherkin.IShouldNotSeeText(text: String) {
    onView(allOf(withText(text), isDisplayed())).check(doesNotExist())
}

/** Finds a visible view with hint text [text] and checks that it is displayed. */
fun Gherkin.IShouldSeeHintText(text: String) {
    onView(allOf(withHint(text), withEffectiveVisibility(Visibility.VISIBLE))).check(matches(isDisplayed()))
}

/** Checks that no displayed view with text starts with [text] is found. */
fun Gherkin.IShouldNotSeeTextStartWith(text: String) {
    onView(allOf(withText(startsWith(text)), isDisplayed())).check(doesNotExist())
}

/** Finds a visible view with text starts with [text] and checks that it is displayed. */
fun Gherkin.IShouldSeeTextStartWith(text: String) {
    onView(allOf(withText(startsWith(text)), withEffectiveVisibility(Visibility.VISIBLE))).check(matches(isDisplayed()))
}

/** Finds a visible view with text ends with [text] and checks that it is displayed. */
fun Gherkin.IShouldSeeTextEndWith(text: String) {
    onView(allOf(withText(endsWith(text)), withEffectiveVisibility(Visibility.VISIBLE))).check(matches(isDisplayed()))
}

/** Finds a visible view with text contains [text] and checks that it is displayed. */
fun Gherkin.IShouldSeeTextContaining(text: String) {
    onView(allOf(withText(containsString(text)), withEffectiveVisibility(Visibility.VISIBLE))).check(matches(isDisplayed()))
}

/** Checks that no displayed view with text contains [text] is found. */
fun Gherkin.IShouldNotSeeTextContaining(text: String) {
    onView(allOf(withText(containsString(text)), isDisplayed())).check(doesNotExist())
}

/** Finds a visible view with [text] and checks that it is completely displayed. */
fun Gherkin.IShouldSeeTextIsCompletelyDisplayed(text: String) {
    onView(allOf(withText(text), withEffectiveVisibility(Visibility.VISIBLE))).check(matches(isCompletelyDisplayed()))
}

/** Finds a visible view with [text] and checks that it is not completely displayed. */
fun Gherkin.IShouldSeeTextIsNotCompletelyDisplayed(text: String) {
    onView(withText(text)).check(matches(not(isCompletelyDisplayed())))
}

/** Finds a visible view with [text] and with [tag], and checks that it is displayed. */
fun Gherkin.IShouldSeeTextWithTag(text: String, tag: String) {
    onView(allOf(withText(text), withTagValue(`is`(tag)), withEffectiveVisibility(Visibility.VISIBLE))).check(matches(isDisplayed()))
}

/** Finds a visible view with [text] and checks that it is enabled. */
fun Gherkin.IShouldSeeTextOnEnabledView(text: String) {
    onView(allOf(withText(text), withEffectiveVisibility(Visibility.VISIBLE))).check(matches(isEnabled()))
}

/** Finds a visible view with [text] and checks that it is disabled. */
fun Gherkin.IShouldSeeTextOnDisabledView(text: String) {
    onView(withText(text)).check(matches(not(isEnabled())))
}

/** Finds a visible view with [text] and checks that it is selected. */
fun Gherkin.IShouldSeeTextOnSelectedView(text: String) {
    onView(allOf(withText(text), withEffectiveVisibility(Visibility.VISIBLE))).check(matches(isSelected()))
}

/** Finds a visible view with [text] and checks that it is not selected. */
fun Gherkin.IShouldSeeTextOnNotSelectedView(text: String) {
    onView(withText(text)).check(matches(not(isSelected())))
}