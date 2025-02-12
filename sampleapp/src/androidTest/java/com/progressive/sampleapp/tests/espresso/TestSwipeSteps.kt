package com.progressive.sampleapp.tests.espresso

import com.progressive.kherkin.common.testcore.And
import com.progressive.kherkin.common.testcore.Given
import com.progressive.kherkin.common.testcore.Then
import com.progressive.kherkin.common.testcore.When
import com.progressive.kherkin.espresso.steps.actions.ISwipeDown
import com.progressive.kherkin.espresso.steps.actions.ISwipeDownOn
import com.progressive.kherkin.espresso.steps.actions.ISwipeDownOnView
import com.progressive.kherkin.espresso.steps.actions.ISwipeLeft
import com.progressive.kherkin.espresso.steps.actions.ISwipeLeftOn
import com.progressive.kherkin.espresso.steps.actions.ISwipeLeftOnView
import com.progressive.kherkin.espresso.steps.actions.ISwipeRight
import com.progressive.kherkin.espresso.steps.actions.ISwipeRightOn
import com.progressive.kherkin.espresso.steps.actions.ISwipeRightOnView
import com.progressive.kherkin.espresso.steps.actions.ISwipeUp
import com.progressive.kherkin.espresso.steps.actions.ISwipeUpOn
import com.progressive.kherkin.espresso.steps.actions.ISwipeUpOnView
import com.progressive.kherkin.espresso.steps.actions.ISwipeUpUntilISee
import com.progressive.kherkin.espresso.steps.actions.ISwipeUpUntilISeeTheView
import com.progressive.kherkin.espresso.steps.actions.IWaitToSeeTextOnCurrentScreen
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeTextIsCompletelyDisplayed
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeTextIsNotCompletelyDisplayed
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeViewIsCompletelyDisplayed
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeViewIsNotCompletelyDisplayed
import com.progressive.kherkin.espresso.steps.setup.IRenderScreen
import com.progressive.kherkin.sampleapp.R
import com.progressive.sampleapp.screens.espresso.FinalScreen
import com.progressive.sampleapp.screens.espresso.ViewPagerScreen
import com.progressive.sampleapp.setup.SampleBaseIntegrationTestCase
import com.progressive.sampleapp.steps.ISetViewPagerOrientationToVertical
import org.junit.Test

class TestSwipeSteps : SampleBaseIntegrationTestCase() {

    @Test
    fun testSwipeLeftOnTextScrollsRight() {
        Given.IRenderScreen(ViewPagerScreen())
        And.IShouldSeeTextIsCompletelyDisplayed("3")
        When.ISwipeLeftOn("3")
        Then.IWaitToSeeTextOnCurrentScreen("4")
    }

    @Test
    fun testSwipeLeftOnViewScrollsRight() {
        Given.IRenderScreen(ViewPagerScreen())
        And.IShouldSeeTextIsCompletelyDisplayed("3")
        When.ISwipeLeftOnView(R.id.pager)
        Then.IWaitToSeeTextOnCurrentScreen("4")
    }

    @Test
    fun testSwipeRightOnTextScrollsLeft() {
        Given.IRenderScreen(ViewPagerScreen())
        And.IShouldSeeTextIsCompletelyDisplayed("3")
        When.ISwipeRightOn("3")
        Then.IWaitToSeeTextOnCurrentScreen("2")
    }

    @Test
    fun testSwipeRightOnViewScrollsLeft() {
        Given.IRenderScreen(ViewPagerScreen())
        And.IShouldSeeTextIsCompletelyDisplayed("3")
        When.ISwipeRightOnView(R.id.pager)
        Then.IWaitToSeeTextOnCurrentScreen("2")
    }

    @Test
    fun testSwipingLeftAndRight() {
        Given.IRenderScreen(ViewPagerScreen())
        And.ISwipeLeft()
        And.IShouldSeeTextIsCompletelyDisplayed("4")
        When.ISwipeRight()
        Then.IShouldSeeTextIsCompletelyDisplayed("3")
    }

    @Test
    fun testSwipingUpAndDown() {
        Given.IRenderScreen(FinalScreen())
        And.IShouldSeeViewIsNotCompletelyDisplayed(R.id.editTextBottom)
        And.ISwipeUp()
        And.IShouldSeeViewIsCompletelyDisplayed(R.id.editTextBottom)
        When.ISwipeDown()
        Then.IShouldSeeViewIsNotCompletelyDisplayed(R.id.editTextBottom)
    }

    @Test
    fun testSwipingUpUntilISee() {
        Given.IRenderScreen(FinalScreen())
        And.IShouldSeeViewIsNotCompletelyDisplayed(R.id.editTextBottom)
        When.ISwipeUpUntilISee("Nearly the bottom")
        Then.IShouldSeeViewIsCompletelyDisplayed(R.id.editTextBottom)
    }

    @Test
    fun testSwipingUpOnVerticalViewPager() {
        Given.ISetViewPagerOrientationToVertical()
        And.IRenderScreen(ViewPagerScreen())
        When.ISwipeDownOn("4")
        Then.IShouldSeeTextIsNotCompletelyDisplayed("4")
    }

    @Test
    fun testSwipingUpUntilISeeTheView() {
        Given.ISetViewPagerOrientationToVertical()
        And.IRenderScreen(ViewPagerScreen())
        And.ISwipeDownOn("3")
        And.IShouldSeeTextIsNotCompletelyDisplayed("4")
        When.ISwipeUpUntilISeeTheView(R.id.view_pager_item_5)
        Then.IShouldSeeViewIsCompletelyDisplayed(R.id.view_pager_item_5)
    }

    @Test
    fun testSwipeDownOnView() {
        Given.ISetViewPagerOrientationToVertical()
        And.IRenderScreen(ViewPagerScreen())
        When.ISwipeDownOnView(R.id.view_pager_item_3)
        And.ISwipeDownOnView(R.id.view_pager_item_3)
        Then.IShouldSeeViewIsNotCompletelyDisplayed(R.id.view_pager_item_3)
    }

    @Test
    fun testSwipingUpOnView() {
        Given.IRenderScreen(FinalScreen())
        And.IShouldSeeViewIsNotCompletelyDisplayed(R.id.editTextBottom)
        And.ISwipeUpOn("More lies below")
        When.ISwipeUpOnView(R.id.textViewMiddle)
        Then.IShouldSeeViewIsCompletelyDisplayed(R.id.editTextBottom)
    }
}