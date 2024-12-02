package com.progressive.sampleapp.tests.espresso

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import com.progressive.kherkin.sampleapp.R
import com.progressive.kherkin.espresso.steps.actions.IScrollToIdWhenVisible
import com.progressive.kherkin.espresso.steps.actions.IScrollToTextWhenVisible
import com.progressive.kherkin.espresso.steps.actions.IScrollToView
import com.progressive.kherkin.espresso.steps.actions.IScrollToViewWithIdAndTextWhenVisible
import com.progressive.kherkin.espresso.steps.actions.IScrollUntilISee
import com.progressive.kherkin.espresso.steps.actions.IScrollUntilISeeTheHint
import com.progressive.kherkin.espresso.steps.assertion.IShouldSee
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeViewIsCompletelyDisplayed
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeViewIsNotCompletelyDisplayed
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeViewWithTextIsCompletelyDisplayed
import com.progressive.kherkin.espresso.steps.setup.IRenderScreen
import com.progressive.kherkin.common.testcore.And
import com.progressive.kherkin.common.testcore.Given
import com.progressive.kherkin.common.testcore.Then
import com.progressive.kherkin.common.testcore.When
import com.progressive.sampleapp.screens.espresso.FinalScreen
import com.progressive.sampleapp.setup.SampleBaseIntegrationTestCase
import org.junit.Test

class TestScrollSteps : SampleBaseIntegrationTestCase() {

    @Test
    fun testScrollUntilISeeText() {
        Given.IRenderScreen(FinalScreen())
        And.IShouldSeeViewWithTextIsCompletelyDisplayed(R.id.buttonFinal, "Return to Main")
        And.IShouldSeeViewIsNotCompletelyDisplayed(R.id.textViewBottom)
        When.IScrollUntilISee("Nearly the bottom")
        Then.IShouldSeeViewIsNotCompletelyDisplayed(R.id.buttonFinal)
    }

    @Test
    fun testScrollUntilISeeId() {
        Given.IRenderScreen(FinalScreen())
        And.IShouldSeeViewIsCompletelyDisplayed(R.id.buttonFinal)
        And.IShouldSeeViewIsNotCompletelyDisplayed(R.id.textViewBottom)
        When.IScrollUntilISee(R.id.textViewBottom)
        Then.IShouldSeeViewIsNotCompletelyDisplayed(R.id.buttonFinal)
    }

    @Test
    fun testScrollUntilISeeTextAndId() {
        Given.IRenderScreen(FinalScreen())
        And.IShouldSeeViewIsCompletelyDisplayed(R.id.buttonFinal)
        And.IShouldSeeViewIsNotCompletelyDisplayed(R.id.textViewBottom)
        When.IScrollUntilISee("Nearly the bottom", R.id.textViewBottom)
        Then.IShouldSeeViewIsNotCompletelyDisplayed(R.id.buttonFinal)
    }

    @Test
    fun testScrollUntilISeeTheHint() {
        Given.IRenderScreen(FinalScreen())
        And.IShouldSeeViewIsCompletelyDisplayed(R.id.buttonFinal)
        And.IShouldSeeViewIsNotCompletelyDisplayed(R.id.editTextBottom)
        When.IScrollUntilISeeTheHint("Bottom")
        Then.IShouldSeeViewIsNotCompletelyDisplayed(R.id.buttonFinal)
    }

    @Test
    fun testScrollToTextWhenVisible() {
        Given.IRenderScreen(FinalScreen())
        And.IShouldSeeViewIsCompletelyDisplayed(R.id.buttonFinal)
        And.IShouldSeeViewIsNotCompletelyDisplayed(R.id.textViewBottom)
        When.IScrollToTextWhenVisible("Nearly the bottom")
        Then.IShouldSeeViewIsNotCompletelyDisplayed(R.id.buttonFinal)
    }

    @Test
    fun testScrollToIdWhenVisible() {
        Given.IRenderScreen(FinalScreen())
        And.IShouldSeeViewIsCompletelyDisplayed(R.id.buttonFinal)
        And.IShouldSeeViewIsNotCompletelyDisplayed(R.id.textViewBottom)
        When.IScrollToIdWhenVisible(R.id.textViewBottom)
        Then.IShouldSeeViewIsNotCompletelyDisplayed(R.id.buttonFinal)
    }

    @Test
    fun testScrollToViewWithIdAndTextWhenVisible() {
        Given.IRenderScreen(FinalScreen())
        And.IShouldSeeViewIsCompletelyDisplayed(R.id.buttonFinal)
        And.IShouldSeeViewIsNotCompletelyDisplayed(R.id.textViewBottom)
        When.IScrollToViewWithIdAndTextWhenVisible(R.id.textViewBottom, "Nearly the bottom")
        Then.IShouldSeeViewIsNotCompletelyDisplayed(R.id.buttonFinal)
    }

    @Test
    fun testScrollToViewWithMatcher() {
        Given.IRenderScreen(FinalScreen())
        And.IShouldSeeViewIsCompletelyDisplayed(R.id.buttonFinal)
        And.IShouldSeeViewIsNotCompletelyDisplayed(R.id.textViewBottom)
        When.IScrollToView(withContentDescription("Nearly the bottom"))
        Then.IShouldSee(R.id.textViewBottom)
        And.IShouldSeeViewIsNotCompletelyDisplayed(R.id.buttonFinal)
    }

    @Test
    fun testScrollToViewWithViewInteraction() {
        Given.IRenderScreen(FinalScreen())
        And.IShouldSeeViewIsCompletelyDisplayed(R.id.buttonFinal)
        And.IShouldSeeViewIsNotCompletelyDisplayed(R.id.textViewBottom)
        When.IScrollToView(onView(withContentDescription("Nearly the bottom")))
        Then.IShouldSee(R.id.textViewBottom)
        And.IShouldSeeViewIsNotCompletelyDisplayed(R.id.buttonFinal)
    }
}