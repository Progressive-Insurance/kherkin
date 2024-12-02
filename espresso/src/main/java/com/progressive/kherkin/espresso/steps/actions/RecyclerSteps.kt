package com.progressive.kherkin.espresso.steps.actions

import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.progressive.kherkin.espresso.custom.RecyclerViewMatcher.atPositionOnView
import com.progressive.kherkin.common.testcore.Gherkin
import org.hamcrest.CoreMatchers.allOf

/**
 * Finds an item with [viewId] at position [position] on a [RecyclerView] with [recyclerViewId].
 * Clicks on it.
 */
fun Gherkin.ITouchViewWithinRowInRecyclerView(@IdRes recyclerViewId: Int, position: Int, @IdRes viewId: Int) {
    onView(atPositionOnView(recyclerViewId, position - 1, viewId))
        .perform(click())
}

/**
 * Finds a [RecyclerView] with [recyclerViewId].
 * Clicks on the row at [rowIndex].
 */
fun Gherkin.ITouchRowInRecyclerView(@IdRes recyclerViewId: Int, rowIndex: Int) {
    onView(withId(recyclerViewId)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(rowIndex - 1, click()))
}

/** Scrolls to a view with [itemId] at position [itemNumber] on a [RecyclerView]. */
fun Gherkin.IScrollToItemOnRecyclerView(itemNumber: Int, @IdRes itemId: Int) {
    val intIndex = itemNumber - 1
    scrollToItem(intIndex, itemId)
}

private fun scrollToItem(index: Int, @IdRes id: Int) {
    onView(allOf(withId(id), isDisplayed()))
        .perform(scrollToPosition<RecyclerView.ViewHolder>(index))
}