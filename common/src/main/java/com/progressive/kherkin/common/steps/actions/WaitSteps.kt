package com.progressive.kherkin.common.steps.actions

import android.os.SystemClock
import com.progressive.kherkin.common.testcore.Gherkin
import com.progressive.kherkin.common.testcore.TimeoutConstants.EXTRA_LONG_TIMEOUT

/**
 * Waits for [timeoutInMillis] or a default of [EXTRA_LONG_TIMEOUT] milliseconds. Recommended to
 * only use for debugging purposes.
 */
fun Gherkin.IWaitLong(timeoutInMillis: Long = EXTRA_LONG_TIMEOUT) {
    SystemClock.sleep(timeoutInMillis)
}