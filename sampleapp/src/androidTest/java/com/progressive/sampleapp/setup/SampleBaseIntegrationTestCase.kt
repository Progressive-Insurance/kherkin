package com.progressive.sampleapp.setup

import android.content.Context
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.test.core.app.ApplicationProvider
import com.progressive.kherkin.common.injection.BaseIntegrationComponentHolder
import com.progressive.kherkin.common.testcore.BaseIntegrationTestCase
import com.progressive.kherkin.common.testcore.IntegrationTestCleanup
import org.junit.After
import org.junit.Before
import org.junit.Rule

open class SampleBaseIntegrationTestCase : BaseIntegrationTestCase() {

    @get:Rule
    val composeTestRule = createEmptyComposeRule()

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
