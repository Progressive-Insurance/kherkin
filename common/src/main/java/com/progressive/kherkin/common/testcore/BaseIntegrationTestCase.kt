package com.progressive.kherkin.common.testcore

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.GrantPermissionRule
import androidx.test.uiautomator.UiDevice.getInstance
import androidx.test.uiautomator.UiSelector
import com.progressive.kherkin.common.screen.ActivityHelper
import com.progressive.kherkin.common.screen.Screen
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestName
import org.junit.rules.TestWatcher
import org.junit.rules.Timeout
import org.junit.runner.Description

/**
 * Each test class must inherit the base to run correctly.
 * Override to customize the timeouts, setting up app specific code in [setUp], resetting app
 * specific code in [tearDown], [granting Android permissions][GrantPermissionRule], and anything
 * else your tests need to run.
 */
open class BaseIntegrationTestCase {

    /** Default, overrideable timeout for each test. */
    open val timeout: Long = 60

    @JvmField
    @Rule
    val globalTimeout: Timeout = Timeout.seconds(timeout)

    protected var integrationTestLogger = IntegrationTestLogger()
    protected var integrationTestFailureCapture = IntegrationTestFailureCapture()

    @JvmField
    @Rule
    val name = TestName()

    @JvmField
    @Rule
    val watchman: TestWatcher = object : TestWatcher() {

        public override fun succeeded(description: Description) {
            integrationTestLogger.info("--------- TEST PASSED ---------")
            integrationTestLogger.info("Test " + name.methodName + " was Successful.")
        }

        public override fun failed(e: Throwable, description: Description) {
            integrationTestLogger.info("--------- TEST FAILED ---------")
            integrationTestLogger.info("Test " + name.methodName + " has Failed.\n" + description)
            integrationTestLogger.debug("Current Activity Visible -> " +
                    if (ActivityHelper.getCurrentActivity() == null) "Unknown"
                    else ActivityHelper.getCurrentActivity().toString())
            integrationTestFailureCapture.screenshotFailedTest(description)
        }
    }

    /**
     * Default [setUp] that runs before every test.
     * Dismisses "App not responding" alerts.
     * Override to set up any data specific to your app.
     */
    @Before
    open fun setUp() {
        integrationTestLogger.info("Starting Set Up for Integration Test -> " + name.methodName)
        dismissANRAlert()
        integrationTestLogger.info("Base Set Up Complete for Test -> " + name.methodName)
    }

    /**
     * Dismisses "App not responding" alerts which are not uncommon for slow loading emulators.
     * Looks for a wait button first, then a close button.
     */
    private fun dismissANRAlert() {
        val device = getInstance(getInstrumentation())
        val waitButton = device.findObject(UiSelector().textContains("wait"))
        val closeBtn = device.findObject(UiSelector().textContains("Close app"))
        if (waitButton.exists()) {
            Log.d("ANR", "App not responding - Clicking wait")
            integrationTestLogger.info("ANR found before running Integration Test -> " + name.methodName)
            waitButton.click()
        } else if (closeBtn.exists()) {
            Log.d("ANR ", "App not responding - Clicking close ")
            integrationTestLogger.info("ANR found before running Integration Test -> " + name.methodName)
            closeBtn.click()
        }
    }

    /**
     * Default [tearDown] that runs after every test.
     * Resets [PreconditionsData] and closes a destroyed [ActivityScenario] if there is one.
     * Override to reset any data specific to your app.
     */
    @After
    open fun tearDown() {
        PreconditionsData.reset()
        integrationTestLogger.info("Teardown Complete For Test -> " + name.methodName)
        if (Screen.myCurrentScreen.activityScenario.state == Lifecycle.State.DESTROYED) {
            Screen.myCurrentScreen.activityScenario.close()
        }
    }
}
