package com.progressive.kherkin.common.testcore

import android.util.Log

/**
 * Simple wrapper for [Logs][Log] to have the same tag.
 */
class IntegrationTestLogger {

    fun info(message: String) {
        Log.i(LOGCAT_TAG, message)
    }

    fun debug(message: String) {
        Log.d(LOGCAT_TAG, message)
    }

    fun verbose(message: String) {
        Log.v(LOGCAT_TAG, message)
    }

    companion object {
        var LOGCAT_TAG = "UI_TESTS"
    }
}