package com.progressive.sampleapp.tests.espresso

import com.progressive.kherkin.sampleapp.R
import com.progressive.kherkin.espresso.steps.actions.IDoNotSelectAnythingFromSpinner
import com.progressive.kherkin.espresso.steps.actions.ISelectItemFromSpinnerAtIndex
import com.progressive.kherkin.espresso.steps.actions.ISelectTextFromSpinner
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeSpinner
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeTextInSpinner
import com.progressive.kherkin.espresso.steps.setup.IRenderScreen
import com.progressive.kherkin.common.testcore.And
import com.progressive.kherkin.common.testcore.Given
import com.progressive.kherkin.common.testcore.Then
import com.progressive.kherkin.common.testcore.When
import com.progressive.sampleapp.screens.espresso.MainScreen
import com.progressive.sampleapp.setup.SampleBaseIntegrationTestCase
import org.junit.Test

class TestSpinnerSteps : SampleBaseIntegrationTestCase() {

    @Test
    fun testSelectItemFromSpinner() {
        Given.IRenderScreen(MainScreen())
        And.IShouldSeeSpinner(R.id.spinner)
        When.ISelectTextFromSpinner(R.id.spinner, "Option 2")
        Then.IShouldSeeTextInSpinner(R.id.spinner, "Option 2")
    }

    @Test
    fun testSelectItemFromSpinnerAtIndex() {
        Given.IRenderScreen(MainScreen())
        When.ISelectItemFromSpinnerAtIndex(R.id.spinner, 2)
        Then.IShouldSeeTextInSpinner(R.id.spinner, "Option 3")
    }

    @Test
    fun testSelectNothingFromList() {
        Given.IRenderScreen(MainScreen())
        When.IDoNotSelectAnythingFromSpinner(R.id.spinner)
        Then.IShouldSeeTextInSpinner(R.id.spinner, "Default Spinner Item")
    }
}