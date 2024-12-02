package com.progressive.kherkin.espresso.steps.assertion

import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.Visibility
import androidx.test.espresso.matcher.ViewMatchers.hasFocus
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.progressive.kherkin.common.testcore.Gherkin
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

/** Finds a visible [EditText] with [fieldViewId] and checks that it is displayed. */
fun Gherkin.IShouldSeeTextField(@IdRes fieldViewId: Int) {
    onView(allOf(isAssignableFrom(EditText::class.java), withId(fieldViewId), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}

/** Checks that no displayed [EditText] with [fieldViewId] is found. */
fun Gherkin.IShouldNotSeeTextField(@IdRes fieldViewId: Int) {
    onView(allOf(isAssignableFrom(EditText::class.java), withId(fieldViewId), isDisplayed()))
        .check(doesNotExist())
}

/** Finds a visible [EditText] with [fieldViewId] and with [text], and checks that it is displayed. */
fun Gherkin.IShouldSeeTextFieldWithText(@IdRes fieldViewId: Int, text: String) {
    onView(allOf(isAssignableFrom(EditText::class.java), withId(fieldViewId), withText(text), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}

/**
 * Finds a visible [EditText] with [fieldViewId], with [text], and a sibling view with [label], and
 * checks that it is displayed.
 */
fun Gherkin.IShouldSeeTextFieldWithLabelAndText(@IdRes fieldViewId: Int, label: String, text: String) {
    onView(allOf(isAssignableFrom(EditText::class.java), withId(fieldViewId),
        hasSibling(withText(label)), withText(text), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}

/**
 * Checks that no displayed [EditText] with [fieldViewId], with [text], and with a sibling view with
 * [label] is found.
 */
fun Gherkin.IShouldNotSeeTextFieldWithLabelAndText(@IdRes fieldViewId: Int, label: String, text: String) {
    onView(allOf(isAssignableFrom(EditText::class.java), withId(fieldViewId),
        hasSibling(withText(label)), withText(text), isDisplayed()))
        .check(doesNotExist())
}

/**
 * Finds a visible [EditText] with [fieldViewId] and text contains [text], and checks that it is
 * displayed.
 */
fun Gherkin.IShouldSeeTextFieldContaining(@IdRes fieldViewId: Int, text: String) {
    onView(allOf(isAssignableFrom(EditText::class.java), withId(fieldViewId),
            withText(containsString(text)), withEffectiveVisibility(Visibility.VISIBLE)))
            .check(matches(isDisplayed()))
}

/**
 * Finds a visible [EditText] with [fieldViewId] and with hint text [hint], and checks that it is
 * displayed.
 */
fun Gherkin.IShouldSeeTextFieldWithHint(@IdRes fieldViewId: Int, hint: String) {
    onView(allOf(isAssignableFrom(EditText::class.java), withId(fieldViewId), withHint(hint), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}

/**
 * Finds a visible [EditText] with [fieldViewId] which has a parent view with [parentId], and checks
 * that it is displayed.
 */
fun Gherkin.IShouldSeeTextFieldWithParent(@IdRes viewId: Int, @IdRes parentId: Int) {
    onView(allOf(isAssignableFrom(EditText::class.java), withId(viewId), isDescendantOfA(withId(parentId)), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}

/** Finds a visible [EditText] with [fieldViewId] and no text, and checks that it is displayed. */
fun Gherkin.IShouldSeeTextFieldIsEmpty(@IdRes fieldViewId: Int) {
    onView(allOf(isAssignableFrom(EditText::class.java), withId(fieldViewId), withText(""), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}

/** Finds a visible [EditText] with [fieldViewId] and checks that it is focused. */
fun Gherkin.IShouldSeeTextFieldHasFocus(@IdRes fieldViewId: Int) {
    onView(allOf(isAssignableFrom(EditText::class.java), withId(fieldViewId), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(hasFocus()))
}

/** Finds a visible [EditText] with [fieldViewId] and checks that it is enabled. */
fun Gherkin.IShouldSeeTextFieldIsEnabled(@IdRes fieldViewId: Int) {
    onView(allOf(isAssignableFrom(EditText::class.java), withId(fieldViewId), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isEnabled()))
}

/** Finds a visible [EditText] with [fieldViewId] and checks that it is disabled. */
fun Gherkin.IShouldSeeTextFieldIsDisabled(@IdRes fieldViewId: Int) {
    onView(allOf(isAssignableFrom(EditText::class.java), withId(fieldViewId), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(not(isEnabled())))
}

/** Finds a visible [EditText] with [fieldViewId] and checks that it is not focused. */
fun Gherkin.IShouldSeeTextFieldDoesNotHaveFocus(@IdRes fieldViewId: Int) {
    onView(allOf(isAssignableFrom(EditText::class.java), withId(fieldViewId), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(not(hasFocus())))
}

/** Finds a visible [EditText] with text masked as [obscuredText]. */
fun Gherkin.IShouldSeeMaskedText(obscuredText: String) {
    onView(allOf(withText(obscuredText), isAssignableFrom(EditText::class.java), withEffectiveVisibility(Visibility.VISIBLE)))
            .check(matches(object : TypeSafeMatcher<View>() {
                override fun matchesSafely(item: View): Boolean {
                    return if (item is TextView) {
                        item.transformationMethod === PasswordTransformationMethod.getInstance()
                    } else false
                }

                override fun describeTo(description: Description) {
                    description.appendText("text is obscured using the PasswordTransformationMethod")
                }
            }))
}

/** Finds a visible [EditText] with [fieldViewId] and with text masked as [obscuredText]. */
fun Gherkin.IShouldSeeFieldWithMaskedText(@IdRes fieldViewId: Int, obscuredText: String) {
    onView(allOf(withId(fieldViewId), withText(obscuredText), isAssignableFrom(EditText::class.java), withEffectiveVisibility(Visibility.VISIBLE)))
            .check(matches(object : TypeSafeMatcher<View>() {
                override fun matchesSafely(item: View): Boolean {
                    return if (item is TextView) {
                        item.transformationMethod === PasswordTransformationMethod.getInstance()
                    } else false
                }

                override fun describeTo(description: Description) {
                    description.appendText("text is obscured using the PasswordTransformationMethod")
                }
            }))
}

/** Finds an [EditText] with [fieldViewId] and checks that its text length is [expectedSize]. */
fun Gherkin.IVerifyTextFieldLength(@IdRes fieldViewId: Int, expectedSize: Int) {
    onView(allOf(isAssignableFrom(EditText::class.java), withId(fieldViewId)))
        .check(matches(checkTextLength(expectedSize)))
}

private fun checkTextLength(expectedLength: Int): TypeSafeMatcher<View> {
    return object : TypeSafeMatcher<View>() {
        override fun matchesSafely(item: View): Boolean {
            val textLength = if (item is EditText) item.length() else -1
            return textLength == expectedLength
        }

        override fun describeTo(description: Description) {
            description.appendText("checkTextLength")
        }
    }
}