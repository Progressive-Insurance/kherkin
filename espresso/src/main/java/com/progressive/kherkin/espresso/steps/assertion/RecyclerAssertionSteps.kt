package com.progressive.kherkin.espresso.steps.assertion

import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.Visibility
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.progressive.kherkin.espresso.custom.RecyclerViewItemCountAssertion
import com.progressive.kherkin.espresso.custom.RecyclerViewMatcher.atPositionOnView
import com.progressive.kherkin.common.testcore.Gherkin
import org.hamcrest.CoreMatchers.allOf

/**
 * Finds a visible view with [recyclerViewId] and checks that it is a [RecyclerView] with
 * [expectedCount] items.
 */
fun Gherkin.IShouldSeeRecyclerCount(@IdRes recyclerViewId: Int, expectedCount: Int) {
    onView(allOf(withId(recyclerViewId), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(RecyclerViewItemCountAssertion(expectedCount))
}

/**
 * Finds a visible item with [viewId] at position [position] on a [RecyclerView] with
 * [recyclerViewId], and checks that it has [text] and is displayed.
 */
fun Gherkin.IShouldSeeViewAtPositionInRecycler(@IdRes recyclerViewId: Int, position: Int, @IdRes viewId: Int, text: String) {
    onView(allOf(atPositionOnView(recyclerViewId, position - 1, viewId), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(matches(allOf(withText(text), isDisplayed())))
}