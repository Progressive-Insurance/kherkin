package com.progressive.sampleapp.tests.compose

import androidx.compose.ui.test.junit4.createEmptyComposeRule
import com.progressive.kherkin.compose.steps.actions.IClearField
import com.progressive.kherkin.compose.steps.actions.IEnterTextIntoField
import com.progressive.kherkin.compose.steps.actions.ILeaveFieldEmpty
import com.progressive.kherkin.compose.steps.assertion.IShouldSeeTextField
import com.progressive.kherkin.compose.steps.assertion.IShouldSeeTextFieldWithText
import com.progressive.kherkin.compose.steps.setup.IRenderScreen
import com.progressive.kherkin.common.testcore.And
import com.progressive.kherkin.common.testcore.Given
import com.progressive.kherkin.common.testcore.Then
import com.progressive.kherkin.common.testcore.When
import com.progressive.sampleapp.screens.compose.BasicComposeScreen
import com.progressive.sampleapp.setup.SampleBaseIntegrationTestCase
import org.junit.Rule
import org.junit.Test

class TestTextFieldSteps : SampleBaseIntegrationTestCase() {

    @get:Rule
    val composeTestRule = createEmptyComposeRule()

    @Test
    fun testEnterTextIntoField() {
        Given.IRenderScreen(BasicComposeScreen(), composeTestRule)
        And.IShouldSeeTextField("Text Field", composeTestRule)
        When.IEnterTextIntoField("Text Field", "Test", composeTestRule)
        Then.IShouldSeeTextFieldWithText("Test", composeTestRule)
        And.IShouldSeeTextFieldWithText("Text Field", "Test", composeTestRule)
    }

    @Test
    fun testLeaveFieldEmpty() {
        Given.IRenderScreen(BasicComposeScreen(), composeTestRule)
        And.IShouldSeeTextField("Prefilled Field", composeTestRule)
        When.ILeaveFieldEmpty("Prefilled Field", composeTestRule)
        Then.IShouldSeeTextFieldWithText("Default text", composeTestRule)
    }

    @Test
    fun testClearField() {
        Given.IRenderScreen(BasicComposeScreen(), composeTestRule)
        And.IShouldSeeTextField("Prefilled Field", composeTestRule)
        When.IClearField("Prefilled Field", composeTestRule)
        Then.IShouldSeeTextFieldWithText("Prefilled Field","", composeTestRule)
    }
}