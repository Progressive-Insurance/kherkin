package com.progressive.kherkin.common.testcore

import android.util.Log

/**
 * Similar to [IntegrationTestLogger], this is a simple wrapper for use by the StepNavigator.
 */
interface Logger {
    fun debug(tag: String, message: String)
    fun info(tag: String, message: String)
}

class AndroidLogger : Logger {
    override fun debug(tag: String, message: String) {
        Log.d(tag + "AndroidLogger", message)
    }

    override fun info(tag: String, message: String) {
        Log.i(tag + "AndroidLogger", message)
    }
}

object TestLogger: Logger {
    override fun debug(tag: String, message: String) {
        println("$tag TestLogger: $message")
    }

    override fun info(tag: String, message: String) {
        println("$tag TestLogger: $message")
    }
}