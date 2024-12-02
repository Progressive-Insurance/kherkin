package com.progressive.kherkin.espresso.steps.actions

import android.app.Activity
import android.content.Context
import android.os.SystemClock
import android.view.View
import androidx.annotation.IdRes
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.Visibility
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSubstring
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import com.progressive.kherkin.espresso.custom.CustomViewAction.waitForDialog
import com.progressive.kherkin.espresso.custom.CustomViewAction.waitForId
import com.progressive.kherkin.espresso.custom.CustomViewAction.waitForIdAndText
import com.progressive.kherkin.espresso.custom.CustomViewAction.waitForView
import com.progressive.kherkin.espresso.custom.CustomViewAction.waitToSee
import com.progressive.kherkin.espresso.custom.CustomViewAction.waitToSeeContainingText
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeAlert
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeAlertWithContainingText
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeAlertWithMessage
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeAlertWithTitle
import com.progressive.kherkin.espresso.steps.setup.TraitVerifier.verifyTrait
import com.progressive.kherkin.common.screen.ActivityHelper
import com.progressive.kherkin.common.screen.IScreen
import com.progressive.kherkin.common.screen.Screen
import com.progressive.kherkin.common.testcore.Gherkin
import com.progressive.kherkin.common.testcore.IntegrationTestLogger
import com.progressive.kherkin.common.testcore.TimeoutConstants
import com.progressive.kherkin.common.testcore.TimeoutConstants.EXTRA_LONG_TIMEOUT
import com.progressive.kherkin.common.testcore.TimeoutConstants.LONG_TIMEOUT
import com.progressive.kherkin.common.testcore.When
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher

/** Waits to see [text] for [timeoutInMillis] or a default of [LONG_TIMEOUT] milliseconds. */
fun Gherkin.IWaitToSeeTextOnCurrentScreen(text: String, timeoutInMillis: Long = LONG_TIMEOUT) {
    onView(isRoot()).perform(waitToSee(text, timeoutInMillis))
}

/** Waits to see [viewId] for [timeoutInMillis] or a default of [LONG_TIMEOUT] milliseconds. */
fun Gherkin.IWaitToSeeViewOnCurrentScreen(@IdRes viewId: Int, timeoutInMillis: Long = LONG_TIMEOUT) {
    onView(isRoot()).perform(waitForId(viewId, timeoutInMillis))
}

/**
 * Waits to see a view matching a custom [Matcher] by [viewMatcher] for [timeoutInMillis] or a
 * default of [LONG_TIMEOUT] milliseconds.
 */
fun Gherkin.IWaitToSeeViewOnCurrentScreen(viewMatcher: Matcher<View>, timeoutInMillis: Long = LONG_TIMEOUT) {
    onView(isRoot()).perform(waitForView(viewMatcher, timeoutInMillis))
}

/**
 * Waits to see [viewId] with [text] for [timeoutInMillis] or a default of [LONG_TIMEOUT]
 * milliseconds.
 */
fun Gherkin.IWaitToSeeViewWithTextOnCurrentScreen(@IdRes viewId: Int, text: String, timeoutInMillis: Long = LONG_TIMEOUT) {
    onView(isRoot()).perform(waitForIdAndText(viewId, text, timeoutInMillis))
}

/**
 * Waits to see view containing [text] for [timeoutInMillis] or a default of [LONG_TIMEOUT]
 * milliseconds.
 */
fun Gherkin.IWaitToSeeViewContainingTextOnCurrentScreen(text: String, timeoutInMillis: Long = LONG_TIMEOUT) {
    onView(isRoot()).perform(waitToSeeContainingText(text, timeoutInMillis))
}

/**
 * Waits to see view with [viewId] goes away within [timeoutInMillis] or a default of
 * [LONG_TIMEOUT] milliseconds.
 */
fun Gherkin.IWaitToNotSeeView(@IdRes viewId: Int, timeoutInMillis: Long = LONG_TIMEOUT) {
    onView(isRoot()).perform(waitForView(allOf(withId(viewId), not(withEffectiveVisibility(Visibility.VISIBLE))), timeoutInMillis))
}

/**
 * Waits to see [viewId] become enabled for [timeoutInMillis] or a default of [LONG_TIMEOUT]
 * milliseconds.
 */
fun Gherkin.IWaitForViewToEnable(@IdRes viewId: Int, timeoutInMillis: Long = LONG_TIMEOUT) {
    onView(isRoot()).perform(waitForView(allOf(withId(viewId), isEnabled()), timeoutInMillis))
}

/**
 * Waits to see an alert with text containing [text] for [timeoutInMillis] or a default of
 * [EXTRA_LONG_TIMEOUT] milliseconds.
 */
fun Gherkin.IWaitToSeeTextInAlert(text: String, timeoutInMillis: Long = EXTRA_LONG_TIMEOUT) {
    waitForDialog(withSubstring(text), timeoutInMillis)
    IShouldSeeAlertWithContainingText(text)
}

/**
 * Waits to see an alert with title text [title] for [timeoutInMillis] or a default of
 * [EXTRA_LONG_TIMEOUT] milliseconds.
 */
fun Gherkin.IWaitToSeeAlertWithTitle(title: String, timeoutInMillis: Long = EXTRA_LONG_TIMEOUT) {
    val appContext: Context = ApplicationProvider.getApplicationContext()
    val alertTitle = appContext.resources.getIdentifier("alertTitle", "id", "android")

    waitForDialog(allOf(withId(alertTitle),withText(title)), timeoutInMillis)
    IShouldSeeAlertWithTitle(title)
}

/**
 * Waits to see an alert with the message [text] for [timeoutInMillis] or a default of
 * [EXTRA_LONG_TIMEOUT] milliseconds.
 */
fun Gherkin.IWaitToSeeAlertWithMessage(text: String, timeoutInMillis: Long = EXTRA_LONG_TIMEOUT) {
    waitForDialog(allOf(withId(android.R.id.message), withText(text)), timeoutInMillis)
    IShouldSeeAlertWithMessage(text)
}

/**
 * Waits to see an alert for [timeoutInMillis] or a default of [EXTRA_LONG_TIMEOUT] milliseconds.
 */
fun Gherkin.IWaitToSeeAlert(timeoutInMillis: Long = EXTRA_LONG_TIMEOUT) {
    waitForDialog(isRoot(), timeoutInMillis)
    IShouldSeeAlert()
}

/**
 * Waits for [timeoutInMillis] or a default of [EXTRA_LONG_TIMEOUT] milliseconds. Recommended to
 * only use for debugging purposes.
 */
fun Gherkin.IWaitLong(timeoutInMillis: Long = EXTRA_LONG_TIMEOUT) {
    SystemClock.sleep(timeoutInMillis)
}

/**
 * Waits to see a view outside the context of the current activity with text containing
 * [expectedText] for [timeoutInMillis] or a default of [LONG_TIMEOUT] milliseconds and
 * dismisses it.
 * Unlike most steps, this one uses the UI Automator by using [UiDevice] to access views on the
 * screen regardless of the activity in focus.
 */
fun Gherkin.IWaitToDismissSharesheetContainingText(expectedText: String, timeoutInMillis: Long = LONG_TIMEOUT) {
    val mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    val view = mDevice.wait(Until.hasObject(By.text(expectedText)), timeoutInMillis)
    if (view) {
        mDevice.pressBack()
    } else {
        throw Exception(
            "After waiting for $timeoutInMillis millis, the text $expectedText was not found"
        )
    }
}

/**
 * Waits to see [Screen] for [timeoutInMillis] or a default of [LONG_TIMEOUT] milliseconds.
 * This step is for confirming the test is on the correct [Activity]. It is useful for any time
 * navigation to a new screen happens. It can also be used as filler for the [When] section of a
 * test without an action step.
 */
fun Gherkin.IWaitToSeeScreen(screen: IScreen, timeoutInMillis: Long = EXTRA_LONG_TIMEOUT) {
    val logger = IntegrationTestLogger()
    val endTime = System.currentTimeMillis() + timeoutInMillis

    var activityName = ActivityHelper.getCurrentActivity()?.localClassName?.split(".")?.last().toString()
    val screenActivityName = screen.screenActivityClass().simpleName

    while (activityName != screenActivityName) {
        SystemClock.sleep(TimeoutConstants.MEDIUM_TIMEOUT)
        if (System.currentTimeMillis() > endTime) {
            logger.info("ActivityName: $activityName")
            logger.info("ScreenActivityName: $screenActivityName")
            throw RuntimeException("Timed out waiting for activity: $screen")
        }
        activityName = ActivityHelper.getCurrentActivity()?.localClassName?.split(".")?.last().toString()
    }

    verifyTrait(screen, endTime - System.currentTimeMillis())
}