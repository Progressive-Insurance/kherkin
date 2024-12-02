package com.progressive.sampleapp.tests.espresso

import com.progressive.kherkin.espresso.steps.actions.IWaitToSeeScreen
import com.progressive.kherkin.espresso.steps.setup.INavigateToScreen
import com.progressive.kherkin.espresso.steps.setup.IRenderScreen
import com.progressive.kherkin.common.testcore.And
import com.progressive.kherkin.common.testcore.Given
import com.progressive.kherkin.common.testcore.Then
import com.progressive.kherkin.common.testcore.When
import com.progressive.sampleapp.screens.espresso.FinalScreen
import com.progressive.sampleapp.screens.espresso.MainScreen
import com.progressive.sampleapp.setup.SampleBaseIntegrationTestCase
import com.progressive.sampleapp.steps.ISetDestination
import com.progressive.sampleapp.viewmodels.Destinations
import org.junit.Test

class TestEndToEndNavigation : SampleBaseIntegrationTestCase() {

    @Test
    fun testMainNavigatesToFinalThroughListScreenByDefault() {
        Given.IRenderScreen(MainScreen())
        When.INavigateToScreen(FinalScreen())
        Then.IWaitToSeeScreen(FinalScreen())
    }

    @Test
    fun testMainNavigatesToFinalThoughViewPagerWhenDestinationSet() {
        Given.ISetDestination(Destinations.ViewPager)
        And.IRenderScreen(MainScreen())
        When.INavigateToScreen(FinalScreen())
        Then.IWaitToSeeScreen(FinalScreen())
    }
}