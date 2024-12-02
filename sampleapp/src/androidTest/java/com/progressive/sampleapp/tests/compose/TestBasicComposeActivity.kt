package com.progressive.sampleapp.tests.compose

import androidx.compose.ui.test.junit4.createEmptyComposeRule
import com.progressive.kherkin.compose.steps.actions.ITouchText
import com.progressive.kherkin.compose.steps.actions.IWaitToSeeScreen
import com.progressive.kherkin.compose.steps.assertion.IShouldSeeText
import com.progressive.kherkin.compose.steps.assertion.IShouldSeeTextIsClickable
import com.progressive.kherkin.compose.steps.setup.IRenderScreen
import com.progressive.kherkin.sampleapp.R
import com.progressive.kherkin.espresso.steps.actions.ITouchButton
import com.progressive.kherkin.espresso.steps.setup.IRenderScreen
import com.progressive.kherkin.common.testcore.And
import com.progressive.kherkin.common.testcore.Given
import com.progressive.kherkin.common.testcore.Then
import com.progressive.kherkin.common.testcore.When
import com.progressive.sampleapp.screens.compose.BasicComposeScreen
import com.progressive.sampleapp.screens.espresso.MainScreen
import com.progressive.sampleapp.setup.SampleBaseIntegrationTestCase
import org.junit.Rule
import org.junit.Test

class TestBasicComposeActivity : SampleBaseIntegrationTestCase() {

    @get:Rule
    val composeTestRule = createEmptyComposeRule()

    @Test
    fun testHybridTestCanNavigateFromXmlScreenToComposeScreen() {
        Given.IRenderScreen(MainScreen())
        When.ITouchButton(R.id.buttonNavCompose)
        Then.IWaitToSeeScreen(BasicComposeScreen(), composeTestRule)
        And.IShouldSeeText("Click Me", composeTestRule)
    }

    @Test
    fun testBasicComposeScreenDisplays() {
        Given.IRenderScreen(BasicComposeScreen(), composeTestRule)
        When.IWaitToSeeScreen(BasicComposeScreen(), composeTestRule)
        Then.IShouldSeeText("Hello user!", composeTestRule)
        And.IShouldSeeTextIsClickable("Click Me", composeTestRule)
    }

    @Test
    fun testButtonTextChangesOnClick() {
        Given.IRenderScreen(BasicComposeScreen(), composeTestRule)
        When.ITouchText("Click Me", composeTestRule)
        Then.IShouldSeeTextIsClickable("Button Clicked", composeTestRule)
    }

    @Test
    fun testXmlNavigatesToComposeScreen() {
        Given.IRenderScreen(MainScreen())
        When.ITouchButton(R.id.buttonNavCompose)
        Then.IWaitToSeeScreen(BasicComposeScreen(), composeTestRule)
    }
}