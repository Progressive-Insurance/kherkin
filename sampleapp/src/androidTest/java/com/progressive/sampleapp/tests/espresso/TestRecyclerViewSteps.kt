package com.progressive.sampleapp.tests.espresso

import com.progressive.kherkin.sampleapp.R
import com.progressive.kherkin.espresso.steps.actions.IScrollToItemOnRecyclerView
import com.progressive.kherkin.espresso.steps.actions.ITouchRowInRecyclerView
import com.progressive.kherkin.espresso.steps.actions.ITouchViewWithinRowInRecyclerView
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeRecyclerCount
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeViewAtPositionInRecycler
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeViewWithText
import com.progressive.kherkin.espresso.steps.setup.IRenderScreen
import com.progressive.kherkin.common.testcore.And
import com.progressive.kherkin.common.testcore.Given
import com.progressive.kherkin.common.testcore.Then
import com.progressive.kherkin.common.testcore.When
import com.progressive.sampleapp.screens.espresso.ListScreen
import com.progressive.sampleapp.setup.SampleBaseIntegrationTestCase
import org.junit.Test

class TestRecyclerViewSteps : SampleBaseIntegrationTestCase() {

    @Test
    fun testListScreenScrolls() {
        Given.IRenderScreen(ListScreen())
        When.IScrollToItemOnRecyclerView(100, R.id.recyclerView)
        Then.IShouldSeeViewWithText(R.id.buttonItem, "This is list item number: 100/100")
        And.IShouldSeeRecyclerCount(R.id.recyclerView, 100)
    }

    @Test
    fun testTouchRecyclerViewRow() {
        Given.IRenderScreen(ListScreen())
        And.IShouldSeeViewAtPositionInRecycler(R.id.recyclerView, 3, R.id.buttonItem, "This is list item number: 3/100")
        When.ITouchRowInRecyclerView(R.id.recyclerView, 3)
        Then.IShouldSeeViewAtPositionInRecycler(R.id.recyclerView, 3, R.id.buttonItem, "Clicked")
    }

    @Test
    fun testTouchRecyclerViewItemInRow() {
        Given.IRenderScreen(ListScreen())
        And.IShouldSeeViewAtPositionInRecycler(R.id.recyclerView, 3, R.id.buttonItem, "This is list item number: 3/100")
        When.ITouchViewWithinRowInRecyclerView(R.id.recyclerView, 3, R.id.buttonItem)
        Then.IShouldSeeViewAtPositionInRecycler(R.id.recyclerView, 3, R.id.buttonItem, "Clicked")
    }
}