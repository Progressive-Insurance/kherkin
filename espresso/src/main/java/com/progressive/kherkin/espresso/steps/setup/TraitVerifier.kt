package com.progressive.kherkin.espresso.steps.setup

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.Visibility
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.progressive.kherkin.espresso.custom.CustomViewAction.safeScrollTo
import com.progressive.kherkin.espresso.custom.CustomViewAction.waitForView
import com.progressive.kherkin.common.screen.IScreen
import com.progressive.kherkin.common.screen.Screen
import org.hamcrest.CoreMatchers.allOf

/**
 * Trait verification is necessary to ensure the test has launched the correct [Screen].
 * A trait is a string or view ID on the screen. Traits must be set on each [Screen].
 * Each trait should be unique to the screen it is defined on. If not, the verification of the trait
 * cannot be guaranteed to identify that specific screen.
 */
object TraitVerifier {

    @JvmStatic
    fun verifyTrait(screen: IScreen, timeoutInMillis: Long = 2000) {
        val trait = screen.trait
        when {
            trait.text == null -> {
                onView(isRoot()).perform(waitForView(allOf(withId(trait.viewId), withEffectiveVisibility(Visibility.VISIBLE)), timeoutInMillis))
                onView(withId(trait.viewId)).perform(safeScrollTo()).check(matches(isDisplayed()))
                onView(withId(trait.viewId)).check(matches(isDisplayed()))
            }
            trait.viewId == -1 -> {
                onView(isRoot()).perform(waitForView(allOf(withText(trait.text), withEffectiveVisibility(Visibility.VISIBLE)), timeoutInMillis))
                onView(withText(trait.text)).perform(safeScrollTo()).check(matches(isDisplayed()))
                onView(withText(trait.text)).check(matches(isDisplayed()))
            }
            else -> {
                onView(isRoot()).perform(waitForView(allOf(withId(trait.viewId), withText(trait.text), withEffectiveVisibility(Visibility.VISIBLE)), timeoutInMillis))
                onView(allOf(withId(trait.viewId), withText(trait.text))).perform(safeScrollTo()).check(matches(isDisplayed()))
                onView(allOf(withId(trait.viewId), withText(trait.text))).check(matches(isDisplayed()))
            }
        }
    }
}