package com.progressive.sampleapp.tests.espresso

import com.progressive.kherkin.sampleapp.R
import com.progressive.kherkin.espresso.steps.actions.ITouch
import com.progressive.kherkin.espresso.steps.actions.ITouchButton
import com.progressive.kherkin.espresso.steps.actions.IWaitToSeeAlert
import com.progressive.kherkin.espresso.steps.actions.IWaitToSeeAlertWithMessage
import com.progressive.kherkin.espresso.steps.actions.IWaitToSeeAlertWithTitle
import com.progressive.kherkin.espresso.steps.actions.IWaitToSeeTextInAlert
import com.progressive.kherkin.espresso.steps.assertion.IShouldNotSeeAlertWithMessage
import com.progressive.kherkin.espresso.steps.assertion.IShouldNotSeeAlertWithText
import com.progressive.kherkin.espresso.steps.assertion.IShouldNotSeeAlertWithTitle
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeAlert
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeAlertMessageContainingText
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeAlertWithContainingText
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeAlertWithMessage
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeAlertWithText
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeAlertWithTitle
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeButtonWithText
import com.progressive.kherkin.espresso.steps.setup.IRenderScreen
import com.progressive.kherkin.common.testcore.And
import com.progressive.kherkin.common.testcore.Given
import com.progressive.kherkin.common.testcore.Then
import com.progressive.kherkin.common.testcore.When
import com.progressive.sampleapp.screens.espresso.MainScreen
import com.progressive.sampleapp.setup.SampleBaseIntegrationTestCase
import org.junit.Test

class TestAlertSteps : SampleBaseIntegrationTestCase() {

    @Test
    fun testAlertCancelButton() {
        Given.IRenderScreen(MainScreen())
        When.ITouchButton(R.id.buttonAlert)
        Then.IShouldSeeAlert()
        And.ITouch("Cancel")
        And.IShouldSeeButtonWithText(R.id.buttonAlert, "Cancel clicked")
    }

    @Test
    fun testAlertOkButton() {
        Given.IRenderScreen(MainScreen())
        When.ITouchButton(R.id.buttonAlert)
        Then.IShouldSeeAlert()
        And.ITouch("OK")
        And.IShouldSeeButtonWithText(R.id.buttonAlert, "OK clicked")
    }

    @Test
    fun testAlertTitle() {
        Given.IRenderScreen(MainScreen())
        When.ITouchButton(R.id.buttonAlert)
        Then.IShouldSeeAlertWithTitle("Title")
        And.IShouldNotSeeAlertWithTitle("Test")
    }

    @Test
    fun testAlertMessage() {
        Given.IRenderScreen(MainScreen())
        When.ITouchButton(R.id.buttonAlert)
        Then.IShouldSeeAlertWithMessage()
        And.IShouldSeeAlertWithMessage("Test message")
        And.IShouldSeeAlertMessageContainingText("est")
        And.IShouldNotSeeAlertWithMessage("Title")
    }

    @Test
    fun testAlertWithText() {
        Given.IRenderScreen(MainScreen())
        When.ITouchButton(R.id.buttonAlert)
        Then.IShouldSeeAlertWithText("Test message")
        And.IShouldSeeAlertWithContainingText("itle")
        And.IShouldNotSeeAlertWithText("Alert")
    }

    @Test
    fun testWaitForAlert() {
        Given.IRenderScreen(MainScreen())
        When.ITouchButton(R.id.buttonAlert)
        Then.IWaitToSeeAlert()
    }

    @Test
    fun testWaitForTitle() {
        Given.IRenderScreen(MainScreen())
        When.ITouchButton(R.id.buttonAlert)
        Then.IWaitToSeeAlertWithTitle("Title")
    }

    @Test
    fun testWaitForMessage() {
        Given.IRenderScreen(MainScreen())
        When.ITouchButton(R.id.buttonAlert)
        Then.IWaitToSeeAlertWithMessage("Test message")
    }

    @Test
    fun testWaitForAlertWithText() {
        Given.IRenderScreen(MainScreen())
        When.ITouchButton(R.id.buttonAlert)
        Then.IWaitToSeeTextInAlert("Test message")
    }
}