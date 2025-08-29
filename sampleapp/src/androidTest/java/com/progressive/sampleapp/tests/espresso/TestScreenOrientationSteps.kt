package com.progressive.sampleapp.tests.espresso

import com.progressive.kherkin.common.testcore.And
import com.progressive.kherkin.common.testcore.Given
import com.progressive.kherkin.common.testcore.Then
import com.progressive.kherkin.common.testcore.When
import com.progressive.kherkin.espresso.steps.actions.IRotateToLandscape
import com.progressive.kherkin.espresso.steps.actions.IRotateToPortrait
import com.progressive.kherkin.espresso.steps.assertion.IShouldNotSee
import com.progressive.kherkin.espresso.steps.assertion.IShouldSee
import com.progressive.kherkin.espresso.steps.setup.IRenderScreen
import com.progressive.kherkin.sampleapp.R
import com.progressive.sampleapp.screens.espresso.MainScreen
import com.progressive.sampleapp.setup.SampleBaseIntegrationTestCase
import org.junit.Test

class TestScreenOrientationSteps : SampleBaseIntegrationTestCase() {

    @Test
    fun testRotateToLandscapeStep() {
        Given.IRenderScreen(MainScreen())
        When.IRotateToLandscape()
        Then.IShouldSee(R.id.buttonNav)
        And.IShouldNotSee(R.id.kherkin_logo)
    }

    @Test
    fun testRotateToPortraitStep() {
        Given.IRenderScreen(MainScreen())
        And.IRotateToLandscape()
        And.IShouldNotSee(R.id.kherkin_logo)
        When.IRotateToPortrait()
        Then.IShouldSee(R.id.kherkin_logo)
    }
}