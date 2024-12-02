package com.progressive.sampleapp.tests.espresso

import com.progressive.kherkin.sampleapp.R
import com.progressive.kherkin.espresso.steps.actions.ITouchCheckbox
import com.progressive.kherkin.espresso.steps.actions.ITouchCheckboxWithLabelId
import com.progressive.kherkin.espresso.steps.actions.ITouchCheckboxWithText
import com.progressive.kherkin.espresso.steps.actions.IWaitToSeeScreen
import com.progressive.kherkin.espresso.steps.assertion.IShouldNotSeeCheckbox
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeCheckbox
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeCheckboxIsChecked
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeCheckboxIsDisabled
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeCheckboxIsEnabled
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeCheckboxIsNotChecked
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeCheckboxWithText
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeCheckboxWithTextIsChecked
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeCheckboxWithTextIsNotChecked
import com.progressive.kherkin.espresso.steps.setup.IRenderScreen
import com.progressive.kherkin.common.testcore.And
import com.progressive.kherkin.common.testcore.Given
import com.progressive.kherkin.common.testcore.Then
import com.progressive.kherkin.common.testcore.When
import com.progressive.sampleapp.screens.espresso.ButtonScreen
import com.progressive.sampleapp.setup.SampleBaseIntegrationTestCase
import org.junit.Test

class TestCheckboxSteps : SampleBaseIntegrationTestCase() {

    @Test
    fun testEnabledCheckboxDisplays() {
        Given.IRenderScreen(ButtonScreen())
        When.IWaitToSeeScreen(ButtonScreen())
        Then.IShouldSeeCheckbox(R.id.checkboxEnabled)
        And.IShouldSeeCheckboxWithText("Enabled")
        And.IShouldSeeCheckboxWithText(R.id.checkboxEnabled, "Enabled")
        And.IShouldSeeCheckboxIsEnabled(R.id.checkboxEnabled)
    }

    @Test
    fun testEnabledCheckboxFunctions() {
        Given.IRenderScreen(ButtonScreen())
        And.IShouldSeeCheckboxIsNotChecked(R.id.checkboxEnabled)
        When.ITouchCheckbox(R.id.checkboxEnabled)
        Then.IShouldSeeCheckboxIsChecked(R.id.checkboxEnabled)
    }

    @Test
    fun testEnabledCheckboxWithTextFunctions() {
        Given.IRenderScreen(ButtonScreen())
        And.IShouldSeeCheckboxWithTextIsNotChecked(R.id.checkboxEnabled, "Enabled")
        And.IShouldSeeCheckboxWithTextIsNotChecked("Enabled")
        When.ITouchCheckboxWithText("Enabled")
        Then.IShouldSeeCheckboxWithTextIsChecked(R.id.checkboxEnabled, "Enabled")
        And.IShouldSeeCheckboxWithTextIsChecked("Enabled")
    }

    @Test
    fun testDisabledCheckboxDisplays() {
        Given.IRenderScreen(ButtonScreen())
        When.IWaitToSeeScreen(ButtonScreen())
        Then.IShouldSeeCheckboxIsDisabled(R.id.checkboxDisabled)
    }

    @Test
    fun testInvisibleCheckboxDisplays() {
        Given.IRenderScreen(ButtonScreen())
        And.IShouldNotSeeCheckbox(R.id.checkboxInvisible)
        When.ITouchCheckbox(R.id.checkboxEnabled)
        Then.IShouldSeeCheckbox(R.id.checkboxInvisible)
    }

    @Test
    fun testCheckboxLayoutDisplays() {
        Given.IRenderScreen(ButtonScreen())
        When.IWaitToSeeScreen(ButtonScreen())
        Then.IShouldSeeCheckbox(R.id.checkControl)
        And.IShouldSeeCheckboxWithText(R.id.checkControl, "Check Box Label")
    }

    @Test
    fun testCheckboxLayoutFunctions() {
        Given.IRenderScreen(ButtonScreen())
        And.IShouldSeeCheckboxWithTextIsNotChecked(R.id.checkControl, "Check Box Label")
        When.ITouchCheckboxWithLabelId(R.id.checkControl, R.id.checkControlText)
        Then.IShouldSeeCheckboxWithTextIsChecked(R.id.checkControl, "Check Box Label")
    }
}