package com.progressive.kherkin.espresso.steps.actions

import android.widget.EditText
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.progressive.kherkin.espresso.custom.CustomViewAction.clearFocus
import com.progressive.kherkin.espresso.custom.CustomViewAction.safeScrollTo
import com.progressive.kherkin.common.testcore.Gherkin
import org.hamcrest.core.AllOf.allOf

/**
 * Finds an [EditText] with [fieldViewId].
 * Scrolls to the view and enters [text].
 * Presses the current action button and removes focus from the [EditText].
 */
fun Gherkin.IEnterTextIntoField(@IdRes fieldViewId: Int, text: String) {
    onView(allOf(isAssignableFrom(EditText::class.java), withId(fieldViewId)))
        .perform(safeScrollTo(), typeText(text), pressImeActionButton(), clearFocus())
}

/**
 * Finds an [EditText] with [fieldViewId] which has a parent view with [parentViewId].
 * Scrolls to the view and enters [text].
 * Presses the current action button and removes focus from the [EditText].
 */
fun Gherkin.IEnterTextIntoFieldWithParent(@IdRes fieldViewId: Int, @IdRes parentViewId: Int, text: String) {
    onView(allOf(
        isAssignableFrom(EditText::class.java), withId(fieldViewId), isDescendantOfA(withId(parentViewId))))
        .perform(safeScrollTo(), typeText(text), pressImeActionButton(), clearFocus())
}

/**
 * Finds an [EditText] with [fieldViewId] and a sibling view with [label].
 * Scrolls to the view and enters [text].
 * Closes the keyboard and removes focus from the [EditText].
 */
fun Gherkin.IEnterTextIntoFieldWithLabel(@IdRes fieldViewId: Int, label: String, text: String) {
    onView(
        allOf(isAssignableFrom(EditText::class.java), withId(fieldViewId), hasSibling(withText(label))))
        .perform(safeScrollTo(), typeText(text), closeSoftKeyboard(), clearFocus())
}

/**
 * Finds an [EditText] with [fieldViewId] which has a parent view with [parentId] and a sibling
 * view with [label].
 * Scrolls to the view and enters [text]
 * Closes the keyboard and removes focus from the [EditText].
 */
fun Gherkin.IEnterTextIntoFieldWithLabelWithParent(@IdRes fieldViewId: Int, @IdRes parentId: Int, label: String, text: String) {
    onView(allOf(
        isAssignableFrom(EditText::class.java), withId(fieldViewId), hasSibling(withText(label)),
            isDescendantOfA(withId(parentId))))
        .perform(safeScrollTo(), typeText(text), closeSoftKeyboard(), clearFocus())
}

/**
 * Finds an [EditText] with [fieldViewId].
 * Scrolls to the view and enters [text].
 * Closes the keyboard and removes focus from the [EditText].
 */
fun Gherkin.IEnterTextIntoFieldAndDismissKeyboard(@IdRes fieldViewId: Int, text: String) {
    onView(allOf(isAssignableFrom(EditText::class.java), withId(fieldViewId)))
        .perform(safeScrollTo(), typeText(text), closeSoftKeyboard(), clearFocus())
}

/**
 * Finds an [EditText] with [fieldViewId].
 * Scrolls to the view and clears the text.
 * Closes the keyboard and removes focus from the [EditText].
 */
fun Gherkin.IClearField(@IdRes fieldViewId: Int) {
    onView(allOf(isAssignableFrom(EditText::class.java), withId(fieldViewId)))
        .perform(safeScrollTo(), clearText(), closeSoftKeyboard(), clearFocus())
}

/**
 * Finds an [EditText] with [fieldViewId] which has a parent view with [parentViewId].
 * Scrolls to the view and clicks it.
 * Presses the current action button, closes the keyboard, and removes focus from the [EditText].
 * This is useful for testing error messages.
 */
fun Gherkin.ILeaveFieldWithParentEmpty(@IdRes fieldViewId: Int, @IdRes parentViewId: Int) {
    onView(allOf(isAssignableFrom(EditText::class.java), withId(fieldViewId), isDescendantOfA(withId(parentViewId))))
        .perform(safeScrollTo(), click(), pressImeActionButton(), closeSoftKeyboard(), clearFocus())
}

/**
 * Finds an [EditText] with [fieldViewId].
 * Scrolls to the view and clicks it.
 * Presses the current action button, closes the keyboard, and removes focus from the [EditText].
 * This is useful for testing error messages.
 */
fun Gherkin.ILeaveFieldEmpty(@IdRes fieldViewId: Int) {
    onView(allOf(isAssignableFrom(EditText::class.java), withId(fieldViewId)))
        .perform(safeScrollTo(), click(), pressImeActionButton(), closeSoftKeyboard(), clearFocus())
}

/**
 * Finds an [EditText] with [fieldViewId] and a sibling view with [label].
 * Scrolls to the view and clicks it.
 * Presses the current action button, closes the keyboard, and removes focus from the [EditText].
 * This is useful for testing error messages.
 */
fun Gherkin.ILeaveFieldWithLabelEmpty(@IdRes fieldViewId: Int, label: String) {
    onView(
        allOf(isAssignableFrom(EditText::class.java), withId(fieldViewId), hasSibling(withText(label))))
        .perform(safeScrollTo(), click(), pressImeActionButton(), clearFocus())
}

/**
 * Finds an [EditText] with [fieldViewId].
 * Scrolls to the view and clicks it.
 * Note that this step does not enter text into the field or leave the field.
 */
fun Gherkin.ITouchTextField(@IdRes fieldViewId: Int) {
    onView(allOf(isAssignableFrom(EditText::class.java), withId(fieldViewId)))
        .perform(safeScrollTo(), click())
}