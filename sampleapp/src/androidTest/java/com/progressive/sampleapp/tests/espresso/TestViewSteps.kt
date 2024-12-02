package com.progressive.sampleapp.tests.espresso

import com.progressive.kherkin.sampleapp.R
import com.progressive.kherkin.espresso.steps.actions.ITouchButton
import com.progressive.kherkin.espresso.steps.actions.IWaitToSeeScreen
import com.progressive.kherkin.espresso.steps.assertion.IShouldNotSee
import com.progressive.kherkin.espresso.steps.assertion.IShouldNotSeeViewWithContentDescription
import com.progressive.kherkin.espresso.steps.assertion.IShouldNotSeeViewWithTag
import com.progressive.kherkin.espresso.steps.assertion.IShouldNotSeeViewWithText
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeImageWithContentDescription
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeToolbarWithTitle
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeViewWithContentDescription
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeViewWithImage
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeViewWithImageAndContentDescription
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeViewWithImageAndTag
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeViewWithParent
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeViewWithTagAndText
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeViewWithinListAtIndex
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeWebView
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

class TestViewSteps : SampleBaseIntegrationTestCase() {

    @Test
    fun testNotSeeSteps() {
        Given.IRenderScreen(ButtonScreen())
        And.IShouldNotSee(R.id.buttonVisibilityRight)
        And.IShouldNotSeeViewWithText(R.id.buttonVisibilityRight, "Right Visibility")
        And.IShouldNotSeeViewWithTag(R.id.buttonVisibilityRight, "invisible button")
        And.IShouldNotSeeViewWithContentDescription("Default invisible")
        When.ITouchButton(R.id.buttonVisibilityLeft)
        Then.IShouldSeeViewWithContentDescription(R.id.buttonVisibilityRight, "Default invisible")
    }

    @Test
    fun testImageAssertionSteps() {
        Given.IRenderScreen(MainScreen())
        When.IWaitToSeeScreen(MainScreen())
        Then.IShouldSeeToolbarWithTitle("Kherkin Sample App")
        And.IShouldSeeViewWithImage(R.id.buttonClearEditText, R.drawable.baseline_backspace_24)
        And.IShouldSeeViewWithImageAndTag(R.id.buttonClearEditText, R.drawable.baseline_backspace_24, "backspace")
        And.IShouldSeeViewWithImageAndContentDescription("Clear field", R.drawable.baseline_backspace_24)
        And.IShouldSeeImageWithContentDescription(R.id.buttonClearEditText, "Clear field")
    }

    @Test
    fun testTagStep() {
        Given.IRenderScreen(MainScreen())
        When.IWaitToSeeScreen(MainScreen())
        Then.IShouldSeeViewWithTagAndText(R.id.button, "clicky button", "Button")
    }

    @Test
    fun testViewWithParent() {
        Given.IRenderScreen(ButtonScreen())
        When.IWaitToSeeScreen(ButtonScreen())
        Then.IShouldSeeViewWithParent(R.id.radioButtonA, R.id.radioButtonGroup)
    }

    @Test
    fun testWithinListAtIndex() {
        Given.IRenderScreen(ListScreen())
        When.IWaitToSeeScreen(ListScreen())
        Then.IShouldSeeViewWithinListAtIndex(R.id.constraint, 3)
    }

    @Test
    fun testSeeWebView() {
        Given.IRenderScreen(ButtonScreen())
        When.ITouchButton(R.id.buttonWebView)
        Then.IShouldSeeWebView()
    }
}