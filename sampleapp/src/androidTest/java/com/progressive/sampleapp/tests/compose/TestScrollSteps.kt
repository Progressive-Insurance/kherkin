package com.progressive.sampleapp.tests.compose

import com.progressive.kherkin.common.testcore.Given
import com.progressive.kherkin.common.testcore.Then
import com.progressive.kherkin.common.testcore.When
import com.progressive.kherkin.compose.steps.actions.IScrollOnNodeUntilISee
import com.progressive.kherkin.compose.steps.actions.IScrollOnNodeUntilISeeText
import com.progressive.kherkin.compose.steps.assertion.IShouldSeeNodeWithText
import com.progressive.kherkin.compose.steps.setup.IRenderScreen
import com.progressive.sampleapp.screens.compose.BasicComposeScreen
import com.progressive.sampleapp.setup.SampleBaseIntegrationTestCase
import org.junit.Test

class TestScrollSteps : SampleBaseIntegrationTestCase() {

    @Test
    fun testScrollOnNode() {
        Given.IRenderScreen(BasicComposeScreen(), composeTestRule)
        When.IScrollOnNodeUntilISee("Scroll Box", "Scroll Item 9", composeTestRule)
        Then.IShouldSeeNodeWithText("Scroll Item 9", "Scroll item 9", composeTestRule)
    }

    @Test
    fun testScrollOnNodeWithText() {
        Given.IRenderScreen(BasicComposeScreen(), composeTestRule)
        When.IScrollOnNodeUntilISeeText("Scroll Box", "Scroll item 9", composeTestRule)
        Then.IShouldSeeNodeWithText("Scroll Item 9", "Scroll item 9", composeTestRule)
    }
}