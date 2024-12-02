package com.progressive.sampleapp.tests.compose

import androidx.compose.ui.test.junit4.createEmptyComposeRule
import com.progressive.kherkin.compose.steps.actions.ITouchNode
import com.progressive.kherkin.compose.steps.actions.ITouchNodeWithText
import com.progressive.kherkin.compose.steps.actions.ITouchText
import com.progressive.kherkin.compose.steps.assertion.IShouldNotSeeNode
import com.progressive.kherkin.compose.steps.assertion.IShouldNotSeeText
import com.progressive.kherkin.compose.steps.assertion.IShouldSeeNode
import com.progressive.kherkin.compose.steps.assertion.IShouldSeeNodeIsClickable
import com.progressive.kherkin.compose.steps.assertion.IShouldSeeNodeWithText
import com.progressive.kherkin.compose.steps.assertion.IShouldSeeNodeWithTextIsClickable
import com.progressive.kherkin.compose.steps.setup.IRenderScreen
import com.progressive.kherkin.common.testcore.And
import com.progressive.kherkin.common.testcore.Given
import com.progressive.kherkin.common.testcore.Then
import com.progressive.kherkin.common.testcore.When
import com.progressive.sampleapp.screens.compose.BasicComposeScreen
import com.progressive.sampleapp.setup.SampleBaseIntegrationTestCase
import org.junit.Rule
import org.junit.Test

class TestTouchSteps : SampleBaseIntegrationTestCase() {

    @get:Rule
    val composeTestRule = createEmptyComposeRule()

    @Test
    fun testTouchText() {
        Given.IRenderScreen(BasicComposeScreen(), composeTestRule)
        And.IShouldSeeNode("Compose Button", composeTestRule)
        When.ITouchText("Click Me", composeTestRule)
        Then.IShouldSeeNodeWithText("Compose Button", "Button Clicked", composeTestRule)
    }

    @Test
    fun testTouchNodes() {
        Given.IRenderScreen(BasicComposeScreen(), composeTestRule)
        And.IShouldSeeNodeIsClickable("Compose Button", composeTestRule)
        When.ITouchNode("Compose Button", composeTestRule)
        And.ITouchNode("Hidden Button", composeTestRule)
        Then.IShouldSeeNodeWithText("Compose Button", "Button Clicked", composeTestRule)
        And.IShouldNotSeeNode("Hidden Button", composeTestRule)
    }

    @Test
    fun testTouchNodeWithText() {
        Given.IRenderScreen(BasicComposeScreen(), composeTestRule)
        And.IShouldSeeNodeWithTextIsClickable("Compose Button", "Click Me", composeTestRule)
        When.ITouchNodeWithText("Compose Button", "Click Me", composeTestRule)
        Then.IShouldNotSeeText("Click Me", composeTestRule)
    }
}