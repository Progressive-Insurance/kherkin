package com.progressive.sampleapp.tests.compose

import androidx.compose.ui.test.junit4.createEmptyComposeRule
import com.progressive.kherkin.compose.steps.actions.IScrollOnNodeUntillISee
import com.progressive.kherkin.compose.steps.actions.IScrollOnNodeUntillISeeText
import com.progressive.kherkin.compose.steps.assertion.IShouldSeeNodeWithText
import com.progressive.kherkin.compose.steps.setup.IRenderScreen
import com.progressive.kherkin.common.testcore.Given
import com.progressive.kherkin.common.testcore.Then
import com.progressive.kherkin.common.testcore.When
import com.progressive.sampleapp.screens.compose.BasicComposeScreen
import com.progressive.sampleapp.setup.SampleBaseIntegrationTestCase
import org.junit.Rule
import org.junit.Test

class TestScrollSteps : SampleBaseIntegrationTestCase() {

    @get:Rule
    val composeTestRule = createEmptyComposeRule()

    @Test
    fun testScrollOnNode() {
        Given.IRenderScreen(BasicComposeScreen(), composeTestRule)
        When.IScrollOnNodeUntillISee("Scroll Box", "Scroll Item 9", composeTestRule)
        Then.IShouldSeeNodeWithText("Scroll Item 9", "Item 9", composeTestRule)
    }

    @Test
    fun testScrollOnNodeWithText() {
        Given.IRenderScreen(BasicComposeScreen(), composeTestRule)
        When.IScrollOnNodeUntillISeeText("Scroll Box", "Item 9", composeTestRule)
        Then.IShouldSeeNodeWithText("Scroll Item 9", "Item 9", composeTestRule)
    }
}