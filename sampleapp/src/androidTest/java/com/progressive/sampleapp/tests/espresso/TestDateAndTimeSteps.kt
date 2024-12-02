package com.progressive.sampleapp.tests.espresso

import com.progressive.kherkin.sampleapp.R
import com.progressive.kherkin.espresso.steps.actions.ISelectDefaultDateForView
import com.progressive.kherkin.espresso.steps.actions.ISetDateOnCalendar
import com.progressive.kherkin.espresso.steps.actions.ISetDateOnCalendarToNDaysFromToday
import com.progressive.kherkin.espresso.steps.actions.ISetDateOnDatePicker
import com.progressive.kherkin.espresso.steps.actions.ISetTimeOnPicker
import com.progressive.kherkin.espresso.steps.actions.ITouch
import com.progressive.kherkin.espresso.steps.actions.ITouchViewWithHint
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeViewWithDateNDaysFromToday
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeViewWithText
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeViewWithTextContaining
import com.progressive.kherkin.espresso.steps.setup.IRenderScreen
import com.progressive.kherkin.common.testcore.And
import com.progressive.kherkin.common.testcore.Given
import com.progressive.kherkin.common.testcore.Then
import com.progressive.kherkin.common.testcore.When
import com.progressive.sampleapp.screens.espresso.MainScreen
import com.progressive.sampleapp.setup.SampleBaseIntegrationTestCase
import org.junit.Test
import java.time.LocalDateTime

class TestDateAndTimeSteps : SampleBaseIntegrationTestCase() {

    @Test
    fun testDateSpinnerSetsDate() {
        Given.IRenderScreen(MainScreen())
        And.ITouchViewWithHint(R.id.textDateSpinner, "Date Spinner")
        When.ISetDateOnDatePicker(2018, 3, 27)
        And.ITouch("OK")
        Then.IShouldSeeViewWithText(R.id.textDateSpinner, "03/27/2018")
    }

    @Test
    fun testDatePickerSetsDate() {
        Given.IRenderScreen(MainScreen())
        And.ITouch(R.id.textDatePicker)
        When.ISetDateOnCalendar(LocalDateTime.of(2020, 8, 15, 12, 12))
        And.ITouch("OK")
        Then.IShouldSeeViewWithText(R.id.textDatePicker, "08/15/2020")
    }

    @Test
    fun testDatePickerDefaultDate() {
        Given.IRenderScreen(MainScreen())
        When.ISelectDefaultDateForView(R.id.textDatePicker)
        Then.IShouldSeeViewWithTextContaining(R.id.textDatePicker, "/")
    }

    @Test
    fun testDatePickerFutureDaysFromToday() {
        Given.IRenderScreen(MainScreen())
        And.ITouch(R.id.textDatePicker)
        When.ISetDateOnCalendarToNDaysFromToday(999)
        And.ITouch("OK")
        Then.IShouldSeeViewWithDateNDaysFromToday(R.id.textDatePicker, 999)
    }

    @Test
    fun testDatePickerPastDaysFromToday() {
        Given.IRenderScreen(MainScreen())
        And.ITouch(R.id.textDatePicker)
        When.ISetDateOnCalendarToNDaysFromToday(-62)
        And.ITouch("OK")
        Then.IShouldSeeViewWithDateNDaysFromToday(R.id.textDatePicker, -62)
    }

    @Test
    fun testTimePickerWith24HourTime() {
        Given.IRenderScreen(MainScreen())
        And.ITouch(R.id.textTimePicker)
        When.ISetTimeOnPicker(13, 45)
        And.ITouch("OK")
        Then.IShouldSeeViewWithText(R.id.textTimePicker, "1:45 PM")
    }

    @Test
    fun testTimePickerWithMidnightTime() {
        Given.IRenderScreen(MainScreen())
        And.ITouch(R.id.textTimePicker)
        When.ISetTimeOnPicker(0, 45)
        And.ITouch("OK")
        Then.IShouldSeeViewWithText(R.id.textTimePicker, "12:45 AM")
    }
}