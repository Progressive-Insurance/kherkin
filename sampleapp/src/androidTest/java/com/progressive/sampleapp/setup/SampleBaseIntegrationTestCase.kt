package com.progressive.sampleapp.setup

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.progressive.kherkin.common.injection.BaseIntegrationComponentHolder
import com.progressive.kherkin.common.testcore.BaseIntegrationTestCase
import com.progressive.kherkin.common.testcore.IntegrationTestCleanup
import org.junit.After
import org.junit.Before

open class SampleBaseIntegrationTestCase : BaseIntegrationTestCase() {

    @Before
    override fun setUp() {
        super.setUp()
        BaseIntegrationComponentHolder.component = BaseIntegrationComponent()
    }

    @After
    override fun tearDown() {
        val appContext: Context = ApplicationProvider.getApplicationContext()
        if (appContext is IntegrationTestCleanup) {
            appContext.cleanup()
        }
        super.tearDown()
    }

}