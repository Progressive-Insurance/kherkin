package com.progressive.sampleapp.tests.espresso

import com.progressive.kherkin.common.testcore.And
import com.progressive.kherkin.common.testcore.Given
import com.progressive.kherkin.common.testcore.Then
import com.progressive.kherkin.common.testcore.When
import com.progressive.kherkin.espresso.steps.actions.ITouchButton
import com.progressive.kherkin.espresso.steps.actions.IWaitToSeeScreen
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeViewStartingWithText
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeViewWithText
import com.progressive.kherkin.espresso.steps.setup.IRenderScreen
import com.progressive.kherkin.sampleapp.R
import com.progressive.sampleapp.screens.espresso.ListScreen
import com.progressive.sampleapp.screens.espresso.MainScreen
import com.progressive.sampleapp.screens.espresso.ViewPagerScreen
import com.progressive.sampleapp.setup.SampleBaseIntegrationTestCase
import com.progressive.sampleapp.steps.ICountFourEnabledButtons
import com.progressive.sampleapp.steps.ISetDestination
import com.progressive.sampleapp.viewmodels.Destinations
import org.junit.Test

class TestMainActivity : SampleBaseIntegrationTestCase() {

    @Test
    fun testMainScreenDisplays() {
        Given.IRenderScreen(MainScreen())
        When.IWaitToSeeScreen(MainScreen())
        Then.IShouldSeeViewWithText(R.id.textView, "Kherkin Sample App TextView")
        And.IShouldSeeViewStartingWithText(R.id.textView, "Kherkin Sample")
        And.ICountFourEnabledButtons()
    }

    @Test
    fun testMainScreenNavigatesToListScreenByDefault() {
        Given.IRenderScreen(MainScreen())
        When.ITouchButton(R.id.buttonNav)
        Then.IWaitToSeeScreen(ListScreen())
    }

    @Test
    fun testMainScreenNavigatesToViewPagerWithDestinationSetInViewModel() {
        Given.ISetDestination(Destinations.ViewPager)
        And.IRenderScreen(MainScreen())
        When.ITouchButton(R.id.buttonNav)
        Then.IWaitToSeeScreen(ViewPagerScreen())
    }
}