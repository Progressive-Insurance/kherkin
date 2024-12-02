package com.progressive.kherkin.espresso.steps.assertion

import android.os.Build
import android.webkit.WebView
import android.widget.Spinner
import androidx.annotation.IdRes
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.Visibility
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import androidx.test.espresso.matcher.ViewMatchers.withTagValue
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.progressive.kherkin.common.testcore.Gherkin
import com.progressive.kherkin.common.testcore.TimeoutConstants
import com.progressive.kherkin.espresso.custom.CustomViewAction.waitForView
import com.progressive.kherkin.espresso.custom.CustomViewMatcher.withIndex
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.startsWith
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Date
import java.util.GregorianCalendar
import java.util.Locale

/** Finds a visible view with [viewId] and checks that it is displayed. */
fun Gherkin.IShouldSee(@IdRes viewId: Int) {
    onView(allOf(withId(viewId), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}

/** Checks that no displayed view with [viewId] is found. */
fun Gherkin.IShouldNotSee(@IdRes viewId: Int) {
    onView(allOf(withId(viewId), isDisplayed())).check(doesNotExist())
}

/** Finds a visible view with [viewId] and checks that it is completely displayed. */
fun Gherkin.IShouldSeeViewIsCompletelyDisplayed(@IdRes viewId: Int) {
    onView(allOf(withId(viewId), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isCompletelyDisplayed()))
}

/** Finds a visible view with [viewId] and [text], and checks that it is completely displayed. */
fun Gherkin.IShouldSeeViewWithTextIsCompletelyDisplayed(@IdRes viewId: Int, text: String) {
    onView(allOf(withId(viewId), withText(text), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isCompletelyDisplayed()))
}

/** Finds a visible view with [viewId] and checks that it is not completely displayed. */
fun Gherkin.IShouldSeeViewIsNotCompletelyDisplayed(@IdRes viewId: Int) {
    onView(allOf(withId(viewId), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(not(isCompletelyDisplayed())))
}

/** Finds a visible view with [viewId] and with [tag], and checks that it is displayed. */
fun Gherkin.IShouldSeeViewWithTag(@IdRes viewId: Int, tag: String) {
    onView(allOf(withId(viewId), withTagValue(`is`(tag)), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}

/** Checks that no displayed view with [viewId] and with [tag] is found. */
fun Gherkin.IShouldNotSeeViewWithTag(@IdRes viewId: Int, tag: String) {
    onView(allOf(withId(viewId), withTagValue(`is`(tag)), isDisplayed())).check(doesNotExist())
}

/** Finds a visible view with [viewId], with [tag], and with [text], and checks that it is displayed. */
fun Gherkin.IShouldSeeViewWithTagAndText(@IdRes viewId: Int, tag: String, text: String) {
    onView(allOf(withId(viewId), withTagValue(`is`(tag)), withText(text), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}

/** Finds a visible view with [viewId] and with [text], and checks that it is displayed. */
fun Gherkin.IShouldSeeViewWithText(@IdRes viewId: Int, text: String) {
    onView(allOf(withId(viewId), withText(text), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}

/** Checks that no displayed view with [viewId] and with [text] is found. */
fun Gherkin.IShouldNotSeeViewWithText(@IdRes viewId: Int, text: String) {
    onView(allOf(withId(viewId), withText(text), isDisplayed())).check(doesNotExist())
}

/** Finds a visible view with [viewId] and text starts with [text], and checks that it is displayed. */
fun Gherkin.IShouldSeeViewStartingWithText(@IdRes viewId: Int, text: String) {
    onView(allOf(withId(viewId), withText(startsWith(text)), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}

/** Checks that no displayed view with [viewId] and text starts with [text] is found. */
fun Gherkin.IShouldNotSeeViewStartingWithText(@IdRes viewId: Int, text: String) {
    onView(allOf(withId(viewId), withText(startsWith(text)), isDisplayed())).check(doesNotExist())
}

/** Finds a visible view with [viewId] and text contains [text], and checks that it is displayed. */
fun Gherkin.IShouldSeeViewWithTextContaining(@IdRes viewId: Int, text: String) {
    onView(allOf(withId(viewId), withText(containsString(text)), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}

/** Finds a visible view with [viewId] and checks that it is displayed and has any text. */
fun Gherkin.IShouldSeeViewWithAnyText(@IdRes viewId: Int) {
    onView(allOf(withId(viewId), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(allOf(not(withText("")), isDisplayed())))
}

/** Finds a visible view with [viewId] and with hint text [text], and checks that it is displayed. */
fun Gherkin.IShouldSeeViewWithHint(@IdRes viewId: Int, text: String) {
    onView(allOf(withHint(text), withId(viewId), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}

/**
 * Finds a visible view with [viewId] and with content description [contentDesc], and checks that
 * it is displayed.
 */
fun Gherkin.IShouldSeeViewWithContentDescription(@IdRes viewId: Int, contentDesc: String) {
    onView(allOf(withId(viewId), withContentDescription(contentDesc), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}

/** Finds a visible view with content description [contentDesc], and checks that it is displayed. */
fun Gherkin.IShouldSeeViewWithContentDescription(contentDesc: String) {
    onView(allOf(withContentDescription(contentDesc), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}

/** Checks that no displayed view with content description [contentDesc] is found. */
fun Gherkin.IShouldNotSeeViewWithContentDescription(contentDesc: String) {
    onView(allOf(withContentDescription(contentDesc), isDisplayed())).check(doesNotExist())
}

/**
 * Finds a visible view with [viewId] which has a parent view with [parentId] and checks that it is
 * displayed.
 */
fun Gherkin.IShouldSeeViewWithParent(@IdRes viewId: Int, @IdRes parentId: Int) {
    onView(allOf(withId(viewId), isDescendantOfA(withId(parentId)), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}

/** Finds a visible [Spinner] with [spinnerId] and checks that it is displayed. */
fun Gherkin.IShouldSeeSpinner(@IdRes spinnerId: Int) {
    onView(allOf(withId(spinnerId), isAssignableFrom(Spinner::class.java), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}

/** Finds a visible [Spinner] with [spinnerId] and with [text], and checks that it is displayed. */
fun Gherkin.IShouldSeeTextInSpinner(@IdRes spinnerId: Int, text: String) {
    onView(allOf(withId(spinnerId), withSpinnerText(text), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}

/** Waits for a visible [WebView] for 1 second, then checks that it is displayed. */
fun Gherkin.IShouldSeeWebView() {
    onView(isRoot()).perform(waitForView(allOf(isAssignableFrom(WebView::class.java), withEffectiveVisibility(Visibility.VISIBLE)), TimeoutConstants.EXTRA_LONG_TIMEOUT))
        .check(matches(isDisplayed()))
}

/** Finds a visible view on a [Toolbar] with [title], and checks that it is completely displayed. */
fun Gherkin.IShouldSeeToolbarWithTitle(title: String) {
    onView(allOf(isDescendantOfA(isAssignableFrom(Toolbar::class.java)), withText(title), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isCompletelyDisplayed()))
}

/** Finds a visible view with [viewId] at position [index] and checks that it is displayed. */
fun Gherkin.IShouldSeeViewWithinListAtIndex(@IdRes viewId: Int, index: Int) {
    onView(allOf(withIndex(withId(viewId), index), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}

/**
 * Finds a visible view with [viewId] with a formatted date [days] from today and checks that it is
 * displayed.
 */
@RequiresApi(Build.VERSION_CODES.O)
fun Gherkin.IShouldSeeViewWithDateNDaysFromToday(@IdRes viewId: Int, days: Long) {
    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.US)
    val notToday = LocalDateTime.now().plusDays(days)
    val resultDate: Date = GregorianCalendar(notToday.year, notToday.monthValue - 1, notToday.dayOfMonth).time

    onView(allOf(withId(viewId), withText(containsString(formatter.format(resultDate))), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}