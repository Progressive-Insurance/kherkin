package com.progressive.kherkin.espresso.steps.assertion

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.PositionAssertions.isCompletelyAbove
import androidx.test.espresso.assertion.PositionAssertions.isCompletelyBelow
import androidx.test.espresso.assertion.PositionAssertions.isCompletelyLeftOf
import androidx.test.espresso.assertion.PositionAssertions.isCompletelyRightOf
import androidx.test.espresso.matcher.ViewMatchers.Visibility
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.progressive.kherkin.common.testcore.Gherkin
import org.hamcrest.Matchers.allOf

/** Finds a visible view with text [higher] and checks that it is above the text [lower]. */
fun Gherkin.IShouldSeeHigherIsAboveLower(higher: String, lower: String) {
    onView(allOf(withText(higher), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(isCompletelyAbove(withText(lower)))
}

/** Finds a visible view with text [lower] and checks that it is below the text [higher]. */
fun Gherkin.IShouldSeeLowerIsBelowHigher(lower: String, higher: String) {
    onView(allOf(withText(lower), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(isCompletelyBelow(withText(higher)))
}

/** Finds a visible view with text [left] and checks that it is left of the text [right]. */
fun Gherkin.IShouldSeeLeftIsLeftOfRight(left: String, right: String) {
    onView(allOf(withText(left), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(isCompletelyLeftOf(withText(right)))
}

/** Finds a visible view with text [right] and checks that it is right of the text [left]. */
fun Gherkin.IShouldSeeRightIsRightOfLeft(right: String, left: String) {
    onView(allOf(withText(right), withEffectiveVisibility(Visibility.VISIBLE)))
        .check(isCompletelyRightOf(withText(left)))
}