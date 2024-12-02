package com.progressive.sampleapp.tests.espresso

import com.progressive.kherkin.sampleapp.R
import com.progressive.kherkin.espresso.steps.actions.IEnterTextIntoField
import com.progressive.kherkin.espresso.steps.actions.IPressBack
import com.progressive.kherkin.espresso.steps.actions.ITouchClickableTextInView
import com.progressive.kherkin.espresso.steps.actions.ITouchIfShowing
import com.progressive.kherkin.espresso.steps.actions.ITouchImage
import com.progressive.kherkin.espresso.steps.actions.ITouchLink
import com.progressive.kherkin.espresso.steps.actions.ITouchViewWithContentDescription
import com.progressive.kherkin.espresso.steps.actions.ITouchViewWithTag
import com.progressive.kherkin.espresso.steps.actions.ITouchViewWithTagAndContainingText
import com.progressive.kherkin.espresso.steps.actions.ITouchViewWithText
import com.progressive.kherkin.espresso.steps.actions.ITouchViewWithinAListAtIndex
import com.progressive.kherkin.espresso.steps.assertion.IShouldNotSeeRadioButtonWithText
import com.progressive.kherkin.espresso.steps.assertion.IShouldNotSeeRadioGroup
import com.progressive.kherkin.espresso.steps.assertion.IShouldNotSeeText
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeRadioButton
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeRadioButtonWithText
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeRadioGroup
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeText
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeViewWithContentDescription
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeViewWithTag
import com.progressive.kherkin.espresso.steps.setup.IRenderScreen
import com.progressive.kherkin.common.testcore.And
import com.progressive.kherkin.common.testcore.Given
import com.progressive.kherkin.common.testcore.Then
import com.progressive.kherkin.common.testcore.When
import com.progressive.sampleapp.screens.espresso.ButtonScreen
import com.progressive.sampleapp.screens.espresso.ListScreen
import com.progressive.sampleapp.screens.espresso.MainScreen
import com.progressive.sampleapp.setup.SampleBaseIntegrationTestCase
import org.junit.Test

class TestTouchSteps : SampleBaseIntegrationTestCase() {

    @Test
    fun testTouchIfShowing() {
        Given.IRenderScreen(MainScreen())
        And.ITouchViewWithText(R.id.buttonAlert, "Button for Alert")
        When.ITouchIfShowing("Cancel")
        Then.IShouldSeeText("Cancel clicked")
    }

    @Test
    fun testTouchContentDescriptionAndPressBack() {
        Given.IRenderScreen(MainScreen())
        And.ITouchViewWithContentDescription("Shows alert")
        When.IPressBack()
        Then.IShouldSeeText("Button for Alert")
    }

    @Test
    fun testTouchContentDescription() {
        Given.IRenderScreen(MainScreen())
        When.ITouchViewWithContentDescription(R.id.button, "Button")
        Then.IShouldSeeViewWithContentDescription("Button")
    }

    @Test
    fun testTouchTagAndContainingText() {
        Given.IRenderScreen(MainScreen())
        When.ITouchViewWithTagAndContainingText("clicky button", "utto")
        Then.IShouldSeeViewWithTag(R.id.button, "clicky button")
        And.IShouldSeeText("Clicked: 1 times")
    }

    @Test
    fun testTouchTag() {
        Given.IRenderScreen(MainScreen())
        When.ITouchViewWithTag("clicky button")
        And.IShouldSeeText("Clicked: 1 times")
    }

    @Test
    fun testTouchTagAndId() {
        Given.IRenderScreen(MainScreen())
        When.ITouchViewWithTag("clicky button", R.id.button)
        Then.IShouldSeeText("Clicked: 1 times")
    }

    @Test
    fun testTouchWithinAList() {
        Given.IRenderScreen(ListScreen())
        When.ITouchViewWithinAListAtIndex(R.id.buttonItem, 5)
        Then.IShouldSeeText("Clicked")
    }

    @Test
    fun testTouchImage() {
        Given.IRenderScreen(MainScreen())
        And.IEnterTextIntoField(R.id.editText, "asdf")
        When.ITouchImage(R.id.buttonClearEditText)
        Then.IShouldNotSeeText("asdf")
    }

    @Test
    fun testTouchClickableTextWithinView() {
        Given.IRenderScreen(ButtonScreen())
        And.IShouldSeeRadioGroup(R.id.radioButtonGroup)
        And.IShouldSeeRadioButton(R.id.radioButtonA)
        And.IShouldSeeRadioButtonWithText("Radio Button A")
        And.ITouchLink(R.id.textViewWithLink)
        And.IShouldNotSeeRadioGroup(R.id.radioButtonGroup)
        And.IShouldNotSeeRadioButtonWithText("Radio Button A")
        When.ITouchClickableTextInView("this", R.id.textViewWithClickableSpan)
        Then.IShouldSeeRadioGroup(R.id.radioButtonGroup)
        And.IShouldSeeRadioButton(R.id.radioButtonA)
    }
}