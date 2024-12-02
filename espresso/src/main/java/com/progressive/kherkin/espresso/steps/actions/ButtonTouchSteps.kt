package com.progressive.kherkin.espresso.steps.actions

import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import androidx.annotation.IdRes
import androidx.appcompat.widget.Toolbar
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withTagValue
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.progressive.kherkin.espresso.custom.CustomViewAction.customClick
import com.progressive.kherkin.espresso.custom.CustomViewAction.safeScrollTo
import com.progressive.kherkin.common.testcore.Gherkin
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.Is.`is`

/**
 * Finds an enabled [Button] with [buttonViewId] and [text].
 * Scrolls to it and clicks it.
 */
fun Gherkin.ITouchButtonWithText(@IdRes buttonViewId: Int, text: String) {
    onView(allOf(isAssignableFrom(Button::class.java), withId(buttonViewId), withText(text), isEnabled()))
        .perform(safeScrollTo(), click())
}

/**
 * Finds an enabled [Button] with [text].
 * Scrolls to it and clicks it.
 */
fun Gherkin.ITouchButtonWithText(text: String) {
    onView(allOf(isAssignableFrom(Button::class.java), withText(text), isEnabled()))
        .perform(safeScrollTo(), click())
}

/**
 * Finds an enabled [Button] containing [text].
 * Scrolls to it and clicks it.
 */
fun Gherkin.ITouchButtonContainingText(text: String) {
    onView(allOf(isAssignableFrom(Button::class.java), withText(containsString(text)), isEnabled()))
        .perform(safeScrollTo(), click())
}

/**
 * Finds an enabled [Button] with [buttonViewId].
 * Scrolls to it and clicks it.
 */
fun Gherkin.ITouchButton(@IdRes buttonViewId: Int) {
    onView(allOf(isAssignableFrom(Button::class.java), withId(buttonViewId), isEnabled()))
        .perform(safeScrollTo(), click())
}

/**
 * Finds an enabled [RadioButton] with [buttonViewId].
 * Scrolls to it and clicks it.
 */
fun Gherkin.ITouchRadioButton(@IdRes buttonViewId: Int) {
    onView(allOf(isAssignableFrom(RadioButton::class.java), withId(buttonViewId), isEnabled()))
        .perform(safeScrollTo(), click())
}

/**
 * Finds an enabled [RadioButton] with [text].
 * Scrolls to it and clicks it.
 */
fun Gherkin.ITouchRadioButtonWithText(text: String) {
    onView(allOf(isAssignableFrom(RadioButton::class.java), withText(text), isEnabled()))
        .perform(safeScrollTo(), click())
}

/**
 * Finds a view on a [Toolbar] with [viewId].
 * Scrolls to it and clicks it.
 */
fun Gherkin.ITouchToolbarItem(@IdRes viewId: Int) {
    onView(allOf(isDescendantOfA(isAssignableFrom(Toolbar::class.java)), withId(viewId)))
        .perform(safeScrollTo(), click())
}

/**
 * Finds an enabled [Button] with [tag] and containing [text].
 * Scrolls to it and clicks it.
 */
fun Gherkin.ITouchButtonWithTagAndContainingText(tag: String, text: String) {
    onView(allOf(isAssignableFrom(Button::class.java), withTagValue(`is`(tag)), withText(containsString(text)), isEnabled()))
        .perform(safeScrollTo(), click())
}

/**
 * Finds an enabled [CheckBox] with [checkboxViewId].
 * Scrolls to it and clicks it.
 */
fun Gherkin.ITouchCheckbox(@IdRes checkboxViewId: Int) {
    onView(allOf(isAssignableFrom(CheckBox::class.java), withId(checkboxViewId), isEnabled()))
        .perform(safeScrollTo(), customClick())
}

/**
 * Finds an enabled [CheckBox] with [checkboxViewId] and a sibling with [labelId].
 * Scrolls to it and clicks it.
 */
fun Gherkin.ITouchCheckboxWithLabelId(@IdRes checkboxViewId: Int, @IdRes labelId: Int) {
    onView(allOf(isAssignableFrom(CheckBox::class.java), withId(checkboxViewId),
        hasSibling(withId(labelId)), isEnabled()))
        .perform(safeScrollTo(), customClick())
}

/**
 * Finds an enabled [CheckBox] with [text].
 * Scrolls to it and clicks it.
 */
fun Gherkin.ITouchCheckboxWithText(text: String) {
    onView(allOf(isAssignableFrom(CheckBox::class.java), withText(text), isEnabled()))
        .perform(safeScrollTo(), customClick())
}