package com.progressive.kherkin.common.testcore

import android.os.Environment
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import org.junit.runner.Description
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * When a test fails, this class takes a screenshot and reports the directory of the file. It also
 * can write to a file to record that a test has been retried.
 */
class IntegrationTestFailureCapture {
    fun screenshotFailedTest(description: Description) {
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        val filename = description.methodName + ".png"
        device.takeScreenshot(File(getPath(description), filename))
    }

    companion object {
        fun logRetriedTest(description: Description) {
            try {
                val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault())
                val currentTime = format.format(Date())
                var data = currentTime + ": Retrying test - " + description.methodName
                val file = File(getPath(description), "retryTest.txt")
                if (!file.exists()) {
                    file.createNewFile()
                } else {
                    data = "\n" + data
                }
                FileWriter(file, true).use { writer ->
                    writer.append(data)
                    writer.flush()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        private fun getPath(description: Description): File {
            val filePath = File(Environment.getExternalStorageDirectory().absolutePath
                    + "/screenshots/" + description.className + "/")
            if (!filePath.exists()) {
                filePath.mkdirs()
            }
            return filePath
        }
    }
}