package com.progressive.sampleapp.tests.espresso

import com.progressive.kherkin.espresso.steps.actions.IWaitToSeeScreen
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeHigherIsAboveLower
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeLeftIsLeftOfRight
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeLowerIsBelowHigher
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeRightIsRightOfLeft
import com.progressive.kherkin.espresso.steps.setup.IRenderScreen
import com.progressive.kherkin.common.testcore.And
import com.progressive.kherkin.common.testcore.Given
import com.progressive.kherkin.common.testcore.Then
import com.progressive.kherkin.common.testcore.When
import com.progressive.sampleapp.screens.espresso.ButtonScreen
import com.progressive.sampleapp.setup.SampleBaseIntegrationTestCase
import org.junit.Test

class TestPositionSteps : SampleBaseIntegrationTestCase() {

    @Test
    fun testPositionSteps() {
        Given.IRenderScreen(ButtonScreen())
        When.IWaitToSeeScreen(ButtonScreen())
        Then.IShouldSeeHigherIsAboveLower("Left Visibility", "Left Enabled")
        And.IShouldSeeLowerIsBelowHigher("Left Enabled", "Left Visibility")
        And.IShouldSeeLeftIsLeftOfRight("Left Enabled", "Right Enabled")
        And.IShouldSeeRightIsRightOfLeft("Right Enabled", "Left Enabled")
    }
}