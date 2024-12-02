package com.progressive.kherkin.compose.steps.actions

import android.os.SystemClock
import androidx.compose.ui.test.junit4.ComposeTestRule
import com.progressive.kherkin.compose.steps.setup.TraitVerifier
import com.progressive.kherkin.common.screen.ActivityHelper
import com.progressive.kherkin.common.screen.IScreen
import com.progressive.kherkin.common.testcore.Gherkin
import com.progressive.kherkin.common.testcore.IntegrationTestLogger
import com.progressive.kherkin.common.testcore.TimeoutConstants

/**
 * Waits to see a Composable [Screen] for [timeoutInMillis] or a default of [LONG_TIMEOUT] milliseconds.
 * This step is for confirming the test is on the correct [Activity]. It is useful for any time
 * navigation to a new screen happens. It can also be used as filler for the [When] section of a
 * test without an action step.
 */
fun Gherkin.IWaitToSeeScreen(screen: IScreen, composeTestRule: ComposeTestRule, timeoutInMillis: Long = 2000) {
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

    TraitVerifier.verifyTrait(screen, composeTestRule, endTime - System.currentTimeMillis())
}