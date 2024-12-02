package com.progressive.sampleapp.tests.espresso

import com.progressive.kherkin.sampleapp.R
import com.progressive.kherkin.espresso.steps.actions.IEnterTextIntoField
import com.progressive.kherkin.espresso.steps.actions.IScrollToItemOnRecyclerView
import com.progressive.kherkin.espresso.steps.actions.ISwipeLeft
import com.progressive.kherkin.espresso.steps.actions.ITouchButton
import com.progressive.kherkin.espresso.steps.actions.IWaitToSeeScreen
import com.progressive.kherkin.espresso.steps.assertion.IShouldNotSeeTextContaining
import com.progressive.kherkin.espresso.steps.assertion.IShouldNotSeeTextStartWith
import com.progressive.kherkin.espresso.steps.assertion.IShouldNotSeeViewStartingWithText
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeHintText
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeTextContaining
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeTextEndWith
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeTextIsCompletelyDisplayed
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeTextIsNotCompletelyDisplayed
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeTextOnDisabledView
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeTextOnEnabledView
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeTextOnNotSelectedView
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeTextOnSelectedView
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeTextStartWith
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeTextWithTag
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeViewWithAnyText
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeViewWithHint
import com.progressive.kherkin.espresso.steps.setup.IRenderScreen
import com.progressive.kherkin.common.testcore.And
import com.progressive.kherkin.common.testcore.Given
import com.progressive.kherkin.common.testcore.Then
import com.progressive.kherkin.common.testcore.When
import com.progressive.sampleapp.screens.espresso.ButtonScreen
import com.progressive.sampleapp.screens.espresso.ListScreen
import com.progressive.sampleapp.screens.espresso.MainScreen
import com.progressive.sampleapp.screens.espresso.TextFieldScreen
import com.progressive.sampleapp.screens.espresso.ViewPagerScreen
import com.progressive.sampleapp.setup.SampleBaseIntegrationTestCase
import org.junit.Test

class TestTextSteps : SampleBaseIntegrationTestCase() {

    @Test
    fun testTextFieldsTextAssertionSteps() {
        Given.IRenderScreen(TextFieldScreen())
        And.IShouldSeeViewWithHint(R.id.editTextVisibleLeft, "Visible")
        And.IShouldNotSeeViewStartingWithText(R.id.editTextInvisibleRight, "")
        When.IEnterTextIntoField(R.id.editTextVisibleLeft, "a")
        Then.IShouldSeeHintText("Invisible")
    }

    @Test
    fun testButtonsTextAssertionSteps() {
        Given.IRenderScreen(ButtonScreen())
        And.IShouldNotSeeTextStartWith("Right Vis")
        And.IShouldNotSeeTextContaining("ight Vis")
        And.IShouldSeeTextOnEnabledView("Right Enabled")
        And.IShouldSeeTextOnDisabledView("Left Enabled")
        When.ITouchButton(R.id.buttonVisibilityLeft)
        Then.IShouldSeeTextStartWith("Right Vis")
        And.IShouldSeeTextEndWith("ht Visibility")
        And.IShouldSeeTextContaining("ight Vis")
    }

    @Test
    fun testNotCompletelyDisplayed() {
        Given.IRenderScreen(ListScreen())
        And.IShouldSeeTextIsNotCompletelyDisplayed("This is list item number: 11/100")
        When.IScrollToItemOnRecyclerView(11, R.id.recyclerView)
        Then.IShouldSeeTextIsCompletelyDisplayed("This is list item number: 11/100")
    }

    @Test
    fun testSeeTags() {
        Given.IRenderScreen(MainScreen())
        When.IWaitToSeeScreen(MainScreen())
        Then.IShouldSeeViewWithAnyText(R.id.button)
        And.IShouldSeeTextWithTag("Button", "clicky button")
    }

    @Test
    fun testTextSelected() {
        Given.IRenderScreen(ViewPagerScreen())
        And.IShouldSeeTextOnSelectedView("Page 3")
        When.ISwipeLeft()
        Then.IShouldSeeTextOnSelectedView("Page 4")
        And.IShouldSeeTextOnNotSelectedView("Page 3")
    }
}