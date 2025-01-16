package com.progressive.sampleapp.tests.espresso

import androidx.test.espresso.matcher.ViewMatchers.withId
import com.progressive.kherkin.sampleapp.R
import com.progressive.kherkin.espresso.steps.actions.ITouchButton
import com.progressive.kherkin.espresso.steps.actions.IWaitLong
import com.progressive.kherkin.espresso.steps.actions.IWaitToDismissSharesheetContainingText
import com.progressive.kherkin.espresso.steps.actions.IWaitToSeeTextOnCurrentScreen
import com.progressive.kherkin.espresso.steps.actions.IWaitToSeeViewContainingTextOnCurrentScreen
import com.progressive.kherkin.espresso.steps.actions.IWaitToSeeViewOnCurrentScreen
import com.progressive.kherkin.espresso.steps.actions.IWaitToSeeViewWithTextOnCurrentScreen
import com.progressive.kherkin.espresso.steps.assertion.IShouldNotSee
import com.progressive.kherkin.espresso.steps.assertion.IShouldNotSeeText
import com.progressive.kherkin.espresso.steps.assertion.IShouldSee
import com.progressive.kherkin.espresso.steps.setup.IRenderScreen
import com.progressive.kherkin.common.testcore.And
import com.progressive.kherkin.common.testcore.Given
import com.progressive.kherkin.common.testcore.Then
import com.progressive.kherkin.common.testcore.When
import com.progressive.sampleapp.screens.espresso.ButtonScreen
import com.progressive.sampleapp.setup.SampleBaseIntegrationTestCase
import org.junit.Ignore
import org.junit.Test

class TestWaitSteps : SampleBaseIntegrationTestCase() {

    @Test
    fun testWaitToSeeText() {
        Given.IRenderScreen(ButtonScreen())
        And.IShouldNotSee(R.id.textViewDelay)
        When.ITouchButton(R.id.buttonVisibilityDelay)
        Then.IWaitToSeeTextOnCurrentScreen("Delayed Visibility")
    }

    @Test
    fun testWaitToSeeView() {
        Given.IRenderScreen(ButtonScreen())
        And.IShouldNotSee(R.id.textViewDelay)
        When.ITouchButton(R.id.buttonVisibilityDelay)
        Then.IWaitToSeeViewOnCurrentScreen(R.id.textViewDelay)
    }

    @Test
    fun testWaitToSeeViewWithDelay() {
        Given.IRenderScreen(ButtonScreen())
        And.IShouldNotSee(R.id.textViewDelay)
        When.ITouchButton(R.id.buttonVisibilityDelay)
        Then.IWaitToSeeViewOnCurrentScreen(R.id.textViewDelay, 1)
    }
    
    @Test
    fun testWaitToSeeViewViaMatcher() {
        Given.IRenderScreen(ButtonScreen())
        And.IShouldNotSee(R.id.textViewDelay)
        When.ITouchButton(R.id.buttonVisibilityDelay)
        Then.IWaitToSeeViewOnCurrentScreen(withId(R.id.textViewDelay))
    }

    @Test
    fun testWaitToSeeViewWithText() {
        Given.IRenderScreen(ButtonScreen())
        And.IShouldNotSee(R.id.textViewDelay)
        When.ITouchButton(R.id.buttonVisibilityDelay)
        Then.IWaitToSeeViewWithTextOnCurrentScreen(R.id.textViewDelay, "Delayed Visibility")
    }

    @Test
    fun testWaitToSeeViewContainingText() {
        Given.IRenderScreen(ButtonScreen())
        And.IShouldNotSee(R.id.textViewDelay)
        When.ITouchButton(R.id.buttonVisibilityDelay)
        Then.IWaitToSeeViewContainingTextOnCurrentScreen("yed Vis")
    }

    @Test
    fun testWaitLong() {
        Given.IRenderScreen(ButtonScreen())
        And.IShouldNotSee(R.id.textViewDelay)
        When.ITouchButton(R.id.buttonVisibilityDelay)
        And.IWaitLong()
        Then.IShouldSee(R.id.textViewDelay)
    }

    @Ignore("aosp-atd image does not have system UI")
    @Test
    fun testDismissShare() {
        Given.IRenderScreen(ButtonScreen())
        When.ITouchButton(R.id.buttonShare)
        Then.IWaitToDismissSharesheetContainingText("Bluetooth")
        And.IShouldNotSeeText("Bluetooth")
    }
}