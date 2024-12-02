package com.progressive.kherkin.common.runner

import androidx.test.runner.AndroidJUnitRunner

class InstrumentationTestRunner : AndroidJUnitRunner() {

    override fun onStart() {
        waitForIdleSync()
        super.onStart()
    }

    public override fun waitForActivitiesToComplete() {
        super.waitForActivitiesToComplete()
    }
}