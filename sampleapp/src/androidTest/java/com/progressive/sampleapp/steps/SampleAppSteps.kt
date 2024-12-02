package com.progressive.sampleapp.steps

import android.widget.Button
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import com.progressive.kherkin.common.testcore.Gherkin
import com.progressive.kherkin.espresso.custom.CustomViewMatcher.withViewCount
import com.progressive.sampleapp.setup.SamplePreconditionsData
import com.progressive.sampleapp.steps.MainScreenDestination.mainDestination
import com.progressive.sampleapp.viewmodels.Destinations
import org.hamcrest.CoreMatchers.allOf

object MainScreenDestination {
    @JvmField
    var mainDestination: Destinations? = null
    @JvmStatic
    fun reset() {
        mainDestination = null
    }
}

fun Gherkin.ISetDestination(destination: Destinations) {
    mainDestination = destination
    SamplePreconditionsData.addDestination(destination)
}

fun Gherkin.ISetViewPagerOrientationToVertical() {
    SamplePreconditionsData.setVertical()
}

fun Gherkin.ICountFourEnabledButtons() {
    onView(isRoot()).check(matches(withViewCount(allOf(isAssignableFrom(Button::class.java), isEnabled()), 4)))
}