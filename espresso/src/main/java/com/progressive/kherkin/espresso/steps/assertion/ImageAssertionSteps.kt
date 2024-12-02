package com.progressive.kherkin.espresso.steps.assertion

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.Visibility
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withTagValue
import com.progressive.kherkin.espresso.custom.DrawableMatcher
import com.progressive.kherkin.common.testcore.Gherkin
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Matcher

/**
 * Finds a visible view with [imageViewId] and with a [Drawable] with [assetId], and checks that it
 * is displayed.
 */
fun Gherkin.IShouldSeeViewWithImage(@IdRes imageViewId: Int, @IdRes assetId: Int) {
    onView(allOf(withId(imageViewId), withDrawable(assetId), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}

/**
 * Finds a visible view with [imageViewId], with a [Drawable] with [assetId], and with [tag], and
 * checks that it is displayed.
 */
fun Gherkin.IShouldSeeViewWithImageAndTag(@IdRes imageViewId: Int, @IdRes assetId: Int, tag: String) {
    onView(allOf(withId(imageViewId), withDrawable(assetId), withTagValue(`is`(tag)), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}

/**
 * Finds a visible view with content description [contentDesc] and with a [Drawable] with [assetId],
 * and checks that it is displayed.
 */
fun Gherkin.IShouldSeeViewWithImageAndContentDescription(contentDesc: String, @IdRes assetId: Int) {
    onView(allOf(withContentDescription(contentDesc), withDrawable(assetId), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}

/**
 * Finds a visible [ImageView] with [imageViewId] and with content description [contentDesc], and
 * checks that it is displayed.
 */
fun Gherkin.IShouldSeeImageWithContentDescription(@IdRes imageViewId: Int, contentDesc: String) {
    onView(allOf(isAssignableFrom(ImageView::class.java), withId(imageViewId), withContentDescription(contentDesc), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(isDisplayed()))
}

private fun withDrawable(resourceId: Int): Matcher<View?> {
    return DrawableMatcher(resourceId)
}