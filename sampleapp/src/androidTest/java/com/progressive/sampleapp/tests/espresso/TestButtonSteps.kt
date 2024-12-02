package com.progressive.sampleapp.tests.espresso

import com.progressive.kherkin.sampleapp.R
import com.progressive.kherkin.espresso.steps.actions.ITouchButton
import com.progressive.kherkin.espresso.steps.actions.ITouchButtonContainingText
import com.progressive.kherkin.espresso.steps.actions.ITouchButtonWithTagAndContainingText
import com.progressive.kherkin.espresso.steps.actions.ITouchButtonWithText
import com.progressive.kherkin.espresso.steps.actions.ITouchRadioButton
import com.progressive.kherkin.espresso.steps.actions.ITouchRadioButtonWithText
import com.progressive.kherkin.espresso.steps.actions.ITouchToolbarItem
import com.progressive.kherkin.espresso.steps.actions.IWaitForViewToEnable
import com.progressive.kherkin.espresso.steps.actions.IWaitToNotSeeView
import com.progressive.kherkin.espresso.steps.actions.IWaitToSeeScreen
import com.progressive.kherkin.espresso.steps.assertion.IShouldNotSeeButton
import com.progressive.kherkin.espresso.steps.assertion.IShouldNotSeeButtonWithText
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeButton
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeButtonIsDisabled
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeButtonIsEnabled
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeButtonWithText
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeButtonWithTextIsChecked
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeButtonWithTextIsDisabled
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeButtonWithTextIsEnabled
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeButtonWithTextIsNotChecked
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeRadioButtonWithTextIsDisabled
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeRadioButtonWithTextIsEnabled
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeRadioButtonWithTextIsNotSelected
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeRadioButtonWithTextIsSelected
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeToolbarItem
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeToolbarItemWithText
import com.progressive.kherkin.espresso.steps.setup.IRenderScreen
import com.progressive.kherkin.common.testcore.And
import com.progressive.kherkin.common.testcore.Given
import com.progressive.kherkin.common.testcore.Then
import com.progressive.kherkin.common.testcore.When
import com.progressive.sampleapp.screens.espresso.ButtonScreen
import com.progressive.sampleapp.setup.SampleBaseIntegrationTestCase
import org.junit.Test

class TestButtonSteps : SampleBaseIntegrationTestCase() {

    @Test
    fun testClickCounterButton() {
        Given.IRenderScreen(ButtonScreen())
        And.IShouldSeeButtonWithText("Clicks: 0")
        And.ITouchButtonContainingText("Clicks:")
        And.IShouldSeeButtonWithText("Clicks: 1")
        When.ITouchButtonWithTagAndContainingText("counter", "Clicks:")
        Then.IShouldSeeButtonWithText("Clicks: 2")
    }

    @Test
    fun testVisibilityButtons() {
        Given.IRenderScreen(ButtonScreen())
        And.IShouldSeeButtonWithText(R.id.buttonVisibilityLeft, "Left Visibility")
        And.IShouldNotSeeButtonWithText("Right Visibility")
        And.IShouldNotSeeButtonWithText(R.id.buttonVisibilityRight, "Right Visibility")
        When.ITouchButtonWithText(R.id.buttonVisibilityLeft, "Left Visibility")
        Then.IWaitToNotSeeView(R.id.buttonVisibilityLeft)
        And.IShouldNotSeeButton(R.id.buttonVisibilityLeft)
        And.IShouldSeeButton(R.id.buttonVisibilityRight)
    }

    @Test
    fun testEnabledButtons() {
        Given.IRenderScreen(ButtonScreen())
        And.IShouldSeeButtonWithTextIsEnabled(R.id.buttonEnabledRight, "Right Enabled")
        And.IShouldSeeButtonWithTextIsDisabled(R.id.buttonEnabledLeft, "Left Enabled")
        When.ITouchButtonWithText("Right Enabled")
        Then.IWaitForViewToEnable(R.id.buttonEnabledLeft)
        And.IShouldSeeButtonIsDisabled(R.id.buttonEnabledRight)
        And.IShouldSeeButtonIsEnabled(R.id.buttonEnabledLeft)
    }

    @Test
    fun testRadioButtons() {
        Given.IRenderScreen(ButtonScreen())
        When.ITouchRadioButton(R.id.radioButtonA)
        Then.IShouldSeeRadioButtonWithTextIsSelected("Radio Button A")
        And.ITouchRadioButtonWithText("Radio Button B")
        And.IShouldSeeRadioButtonWithTextIsNotSelected("Radio Button A")
    }

    @Test
    fun testRadioButtonsDisabled() {
        Given.IRenderScreen(ButtonScreen())
        When.IWaitToSeeScreen(ButtonScreen())
        Then.IShouldSeeRadioButtonWithTextIsEnabled("Radio Button A")
        And.IShouldSeeRadioButtonWithTextIsDisabled("Radio Button C")
    }

    @Test
    fun testSegmentedButton() {
        Given.IRenderScreen(ButtonScreen())
        And.IShouldSeeButtonWithTextIsNotChecked(R.id.segmentButton1, "Button 1")
        When.ITouchButton(R.id.segmentButton1)
        Then.IShouldSeeButtonWithTextIsChecked(R.id.segmentButton1, "Button 1")
    }

    @Test
    fun testToolbarItem() {
        Given.IRenderScreen(ButtonScreen())
        And.IShouldSeeToolbarItem(R.id.toolbarItem1)
        And.IShouldSeeToolbarItemWithText(R.id.toolbarItem1, "TEST 1")
        When.ITouchToolbarItem(R.id.toolbarItem1)
        Then.IShouldSeeRadioButtonWithTextIsEnabled("Radio Button C")
    }
}