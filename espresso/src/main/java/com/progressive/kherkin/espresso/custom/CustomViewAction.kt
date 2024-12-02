package com.progressive.kherkin.espresso.custom

import android.graphics.Rect
import android.text.SpannableString
import android.text.style.ClickableSpan
import android.view.InputDevice
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.webkit.WebView
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.widget.NestedScrollView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.CoordinatesProvider
import androidx.test.espresso.action.GeneralLocation
import androidx.test.espresso.action.PrecisionDescriber
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Tap
import androidx.test.espresso.action.Tapper
import androidx.test.espresso.action.ViewActions.actionWithAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.Visibility
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.util.HumanReadables
import androidx.test.espresso.util.TreeIterables
import androidx.viewpager.widget.ViewPager
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Description
import org.hamcrest.Matcher
import java.util.Locale
import java.util.concurrent.TimeoutException

/**
 * This class includes a number of custom [ViewActions][ViewAction].
 * Some actions include waiting for a view, a custom click, clicking a span of text, a safe scroll,
 * and clearing focus.
 */
object CustomViewAction {

    /** Waits for a view with [viewId] for [timeoutInMillis]. */
    fun waitForId(viewId: Int, timeoutInMillis: Long): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = isRoot()
            override fun getDescription(): String = "wait for a specific view with id <$viewId> during $timeoutInMillis millis."

            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadUntilIdle()
                val endTime = System.currentTimeMillis() + timeoutInMillis
                val viewMatcher = allOf(withId(viewId), isDisplayed())
                do {
                    if (TreeIterables.breadthFirstViewTraversal(view).find { viewMatcher.matches(it) } != null) {
                        return
                    }
                    uiController.loopMainThreadForAtLeast(50)
                } while (System.currentTimeMillis() < endTime)
                throw PerformException.Builder()
                    .withActionDescription(this.description)
                    .withViewDescription(HumanReadables.describe(view))
                    .withCause(TimeoutException())
                    .build()
            }
        }
    }

    /** Waits for a view matching a custom [Matcher] for [timeoutInMillis]. */
    fun waitForView(viewMatcher: Matcher<View>, timeoutInMillis: Long): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = isRoot()
            override fun getDescription(): String = "wait for a specific view <$viewMatcher> during $timeoutInMillis millis."

            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadUntilIdle()
                val endTime = System.currentTimeMillis() + timeoutInMillis
                do {
                    if (TreeIterables.breadthFirstViewTraversal(view).find { viewMatcher.matches(it) } != null) {
                        return
                    }
                    uiController.loopMainThreadForAtLeast(50)
                } while (System.currentTimeMillis() < endTime)
                throw PerformException.Builder()
                    .withActionDescription(this.description)
                    .withViewDescription(HumanReadables.describe(view))
                    .withCause(TimeoutException())
                    .build()
            }
        }
    }

    /** Waits for a view with [viewId] and [text] for [timeoutInMillis]. */
    fun waitForIdAndText(viewId: Int, text: String, timeoutInMillis: Long): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = isRoot()
            override fun getDescription(): String = "wait for a specific view with id <$viewId> and with text <$text> during $timeoutInMillis millis."

            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadUntilIdle()
                val endTime = System.currentTimeMillis() + timeoutInMillis
                val viewMatcher = allOf(withId(viewId), withText(text), isDisplayed())
                do {
                    if (TreeIterables.breadthFirstViewTraversal(view).find { viewMatcher.matches(it) } != null) {
                        return
                    }
                    uiController.loopMainThreadForAtLeast(50)
                } while (System.currentTimeMillis() < endTime)
                throw PerformException.Builder()
                    .withActionDescription(this.description)
                    .withViewDescription(HumanReadables.describe(view))
                    .withCause(TimeoutException())
                    .build()
            }
        }
    }

    /** Waits for a view with [viewId] but not with [text] for [timeoutInMillis]. */
    fun waitForIdAndNotText(viewId: Int, text: String, timeoutInMillis: Long): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = isRoot()
            override fun getDescription(): String = "wait for a specific view with id <$viewId>  and not with text <$text> during $timeoutInMillis millis."

            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadUntilIdle()
                val endTime = System.currentTimeMillis() + timeoutInMillis
                val viewMatcher = allOf(withId(viewId), not(withText(text)))
                do {
                    if (TreeIterables.breadthFirstViewTraversal(view).find { viewMatcher.matches(it) } != null) {
                        return
                    }
                    uiController.loopMainThreadForAtLeast(50)
                } while (System.currentTimeMillis() < endTime)
                throw PerformException.Builder()
                    .withActionDescription(this.description)
                    .withViewDescription(HumanReadables.describe(view))
                    .withCause(TimeoutException())
                    .build()
            }
        }
    }

    /** Waits for a view with content description [contentDescription] for [timeoutInMillis]. */
    fun waitForContentDescription(contentDescription: String, timeoutInMillis: Long): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = isRoot()
            override fun getDescription(): String = "wait for a specific view with contentDescription <$contentDescription> during $timeoutInMillis millis."

            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadUntilIdle()
                val endTime = System.currentTimeMillis() + timeoutInMillis
                val viewMatcher = allOf(
                    withContentDescription(contentDescription),
                    withEffectiveVisibility(Visibility.VISIBLE))
                do {
                    if (TreeIterables.breadthFirstViewTraversal(view).find { viewMatcher.matches(it) } != null) {
                        return
                    }
                    uiController.loopMainThreadForAtLeast(50)
                } while (System.currentTimeMillis() < endTime)
                throw PerformException.Builder()
                    .withActionDescription(this.description)
                    .withViewDescription(HumanReadables.describe(view))
                    .withCause(TimeoutException())
                    .build()
            }
        }
    }

    /** Waits for a view with [text] for [timeoutInMillis]. */
    fun waitToSee(text: String, timeoutInMillis: Long): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = isRoot()
            override fun getDescription(): String = "wait for a specific view with text <$text> during $timeoutInMillis millis."

            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadUntilIdle()
                val endTime = System.currentTimeMillis() + timeoutInMillis
                val viewMatcher = allOf(withText(text), isDisplayed())
                do {
                    if (TreeIterables.breadthFirstViewTraversal(view).find { viewMatcher.matches(it) } != null) {
                        return
                    }
                    uiController.loopMainThreadForAtLeast(50)
                } while (System.currentTimeMillis() < endTime)
                throw PerformException.Builder()
                    .withActionDescription(this.description)
                    .withViewDescription(HumanReadables.describe(view))
                    .withCause(TimeoutException())
                    .build()
            }
        }
    }

    /** Waits for a view with text containing [text] for [timeoutInMillis]. */
    fun waitToSeeContainingText(text: String, timeoutInMillis: Long): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = isRoot()
            override fun getDescription(): String = "wait for a specific view containing text <$text> during $timeoutInMillis millis."

            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadUntilIdle()
                val endTime = System.currentTimeMillis() + timeoutInMillis
                val viewMatcher = allOf(withText(containsString(text)), isDisplayed())
                do {
                    if (TreeIterables.breadthFirstViewTraversal(view).find { viewMatcher.matches(it) } != null) {
                        return
                    }
                    uiController.loopMainThreadForAtLeast(50)
                } while (System.currentTimeMillis() < endTime)
                throw PerformException.Builder()
                    .withActionDescription(this.description)
                    .withViewDescription(HumanReadables.describe(view))
                    .withCause(TimeoutException())
                    .build()
            }
        }
    }

    /** Waits for a dialog matching a custom [Matcher] for [timeoutInMillis]. */
    fun waitForDialog(viewMatcher: Matcher<View>, timeoutInMillis: Long) {
        val endTime = System.currentTimeMillis() + timeoutInMillis
        var exception: java.lang.Exception? = null
        while (System.currentTimeMillis() < endTime) {
            try {
                onView(viewMatcher).inRoot(isDialog()).check(matches(isDisplayed()))
                return
            } catch (e: java.lang.Exception) {
                exception = e
            }
        }
        if (exception != null) {
            throw java.lang.RuntimeException(exception.message)
        }
    }

    /** Waits for a view with [index] for [timeoutInMillis]. */
    fun withIndex(index: Int): Matcher<View> {
        return object : BoundedMatcher<View, ViewPager>(ViewPager::class.java) {
            public override fun matchesSafely(viewPager: ViewPager): Boolean = index == viewPager.currentItem

            override fun describeTo(description: Description) {
                description.appendText("with page index: ")
            }
        }
    }

    /** Performs a custom click action. Useful for checkboxes. */
    fun customClick(): ViewAction = actionWithAssertions(
        CustomGeneralClickAction(Tap.SINGLE, GeneralLocation.VISIBLE_CENTER, Press.FINGER)
    )

    /** Performs a custom scroll action in a view with nested scrolling. */
    fun nestedScrollTo(): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return allOf(
                    isDescendantOfA(isAssignableFrom(NestedScrollView::class.java)),
                    withEffectiveVisibility(Visibility.VISIBLE)
                )
            }

            override fun getDescription(): String = "View is not NestedScrollView"
            override fun perform(uiController: UiController, view: View) {
                try {
                    val nestedScrollView =
                        findFirstParentLayoutOfClass(view, NestedScrollView::class.java)
                    nestedScrollView?.scrollTo(0, view.bottom)
                        ?: throw Exception("Unable to find NestedScrollView parent.")

                } catch (e: Exception) {
                    throw PerformException.Builder()
                        .withActionDescription(this.description)
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(e)
                        .build()
                }
                uiController.loopMainThreadUntilIdle()
            }
        }
    }

    private fun <T : View> findFirstParentLayoutOfClass(view: View, parentClass: Class<T>): T? {
        var p = view.parent
        while (p != null && p.javaClass != parentClass) {
            p = p.parent
        }

        @Suppress("UNCHECKED_CAST")
        return p as? T
    }

    /**
     * Performs a custom scroll action that requires the target view be [Visibility.VISIBLE] and
     * works on both [ScrollView] and [NestedScrollView].
     */
    fun safeScrollTo(): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> =
                withEffectiveVisibility(Visibility.VISIBLE)

            override fun getDescription(): String = "safely scroll to"

            override fun perform(uiController: UiController, view: View) {
                val scrollViewParent = findFirstParentLayoutOfClass(view, ScrollView::class.java)
                if (scrollViewParent != null) {
                    val bounds = Rect()
                    view.getDrawingRect(bounds)
                    scrollViewParent.offsetDescendantRectToMyCoords(view, bounds)
                    scrollViewParent.scrollTo(0, bounds.top)
                    uiController.loopMainThreadUntilIdle()
                    return
                }
                val nestedScrollViewParent =
                    findFirstParentLayoutOfClass(view, NestedScrollView::class.java)
                if (nestedScrollViewParent != null) {
                    val bounds = Rect()
                    view.getDrawingRect(bounds)
                    nestedScrollViewParent.offsetDescendantRectToMyCoords(view, bounds)
                    nestedScrollViewParent.scrollTo(0, bounds.top)
                    uiController.loopMainThreadUntilIdle()
                    return
                }
            }
        }
    }

    /** Performs a custom click on a [SpannableString]. */
    fun clickClickableSpan(text: String): ViewAction {
        return object : ViewAction {

            override fun getConstraints(): Matcher<View> = instanceOf(TextView::class.java)
            override fun getDescription(): String = "clicking on ClickableSpan \"$text\""

            override fun perform(uiController: UiController, view: View) {
                val textView = view as TextView
                val spannableString = textView.text as SpannableString

                if (spannableString.isEmpty()) {
                    throw NoMatchingViewException.Builder()
                        .includeViewHierarchy(true)
                        .withRootView(textView)
                        .build()
                }

                val spans =
                    spannableString.getSpans(0, spannableString.length, ClickableSpan::class.java)
                if (spans.isNotEmpty()) {
                    var spanCandidate: ClickableSpan?
                    for (span in spans) {
                        spanCandidate = span
                        val start = spannableString.getSpanStart(spanCandidate)
                        val end = spannableString.getSpanEnd(spanCandidate)
                        val sequence = spannableString.subSequence(start, end)
                        if (text == sequence.toString()) {
                            span.onClick(textView)
                            return
                        }
                    }
                }

                throw NoMatchingViewException.Builder()
                    .includeViewHierarchy(true)
                    .withRootView(textView)
                    .build()
            }
        }
    }

    /** Performs a custom action that clears the focus on a view. */
    fun clearFocus(): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isDisplayed()
            }

            override fun getDescription(): String {
                return "clear focus"
            }

            override fun perform(uiController: UiController, view: View) {
                view.clearFocus()
                uiController.loopMainThreadUntilIdle()
            }
        }
    }
}

class CustomGeneralClickAction
@JvmOverloads
constructor(
    private val tapper: Tapper,
    private val coordinatesProvider: CoordinatesProvider,
    private val precisionDescriber: PrecisionDescriber,
    private val rollbackAction: ViewAction? = null
) : ViewAction {

    override fun getConstraints(): Matcher<View> {
        val standardConstraint = isDisplayingAtLeast(20)
        return rollbackAction?.let { allOf(standardConstraint, rollbackAction.constraints) }
            ?: standardConstraint
    }

    override fun perform(uiController: UiController, view: View) {
        val coordinates = coordinatesProvider.calculateCoordinates(view)
        val precision = precisionDescriber.describePrecision()
        var status = Tapper.Status.FAILURE
        var loopCount = 0
        while (status != Tapper.Status.SUCCESS && loopCount < 3) {
            status = try {
                tapper.sendTap(uiController, coordinates, precision, InputDevice.SOURCE_UNKNOWN, MotionEvent.BUTTON_PRIMARY)
            } catch (re: RuntimeException) {
                throw PerformException.Builder()
                    .withActionDescription(
                        String.format(
                            "%s - At Coordinates: %d, %d and precision: %d, %d",
                            this.description,
                            coordinates[0].toInt(),
                            coordinates[1].toInt(),
                            precision[0].toInt(),
                            precision[1].toInt()
                        )
                    )
                    .withViewDescription(HumanReadables.describe(view))
                    .withCause(re)
                    .build()
            }
            val duration = ViewConfiguration.getPressedStateDuration()
            if (duration > 0) {
                uiController.loopMainThreadForAtLeast(duration.toLong())
            }
            if (status == Tapper.Status.WARNING) {
                rollbackAction?.perform(uiController, view) ?: break
            }
            loopCount++
        }
        if (status == Tapper.Status.FAILURE) {
            throw PerformException.Builder()
                .withActionDescription(this.description)
                .withViewDescription(HumanReadables.describe(view))
                .withCause(
                    RuntimeException(
                        String.format(
                            "Couldn't click at: %s,%s precision: %s, %s . Tapper: %s coordinate" +
                                    " provider: %s precision describer: %s. Tried %s times." +
                                    " With Rollback? %s",
                            coordinates[0],
                            coordinates[1],
                            precision[0],
                            precision[1],
                            tapper,
                            coordinatesProvider,
                            precisionDescriber,
                            loopCount,
                            rollbackAction
                        )
                    )
                )
                .build()
        }
        if (tapper === Tap.SINGLE && view is WebView) { // WebViews will not process click events until double tap
            // timeout. Not the best place for this - but good for now.
            uiController.loopMainThreadForAtLeast(ViewConfiguration.getDoubleTapTimeout().toLong())
        }
    }

    override fun getDescription(): String {
        return tapper.toString().lowercase(Locale.ENGLISH) + " click"
    }

}