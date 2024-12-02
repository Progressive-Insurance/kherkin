package com.progressive.kherkin.espresso.custom

import android.view.View
import androidx.test.espresso.util.TreeIterables
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

/**
 * A custom [Matcher] for getting the index or count of a [View]. Useful for views within a list.
 */
object CustomViewMatcher {
    fun withViewCount(viewMatcher: Matcher<View>, expectedCount: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            var actualCount = -1
            override fun describeTo(description: Description) {
                if (actualCount >= 0) {
                    description.appendText("With expected number of items: $expectedCount")
                    description.appendText("\n With matcher: ")
                    viewMatcher.describeTo(description)
                    description.appendText("\n But got: $actualCount")
                }
            }

            override fun matchesSafely(root: View): Boolean {
                actualCount = 0
                val iterable = TreeIterables.breadthFirstViewTraversal(root)
                for (view in iterable) {
                    if (viewMatcher.matches(view)) {
                        actualCount++
                    }
                }
                return actualCount == expectedCount
            }
        }
    }

    fun withIndex(matcher: Matcher<View>, index: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            var currentIndex = 0
            override fun describeTo(description: Description) {
                description.appendText("with index: ")
                description.appendValue(index)
                matcher.describeTo(description)
            }

            override fun matchesSafely(view: View): Boolean {
                return matcher.matches(view) && currentIndex++ == index
            }
        }
    }
}