package com.progressive.kherkin.espresso.steps.actions

import android.os.Build
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import androidx.annotation.IdRes
import androidx.annotation.IntRange
import androidx.annotation.RequiresApi
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.contrib.PickerActions.setDate
import androidx.test.espresso.contrib.PickerActions.setTime
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import com.progressive.kherkin.common.testcore.Gherkin
import org.hamcrest.CoreMatchers.equalTo
import java.time.LocalDateTime

/**
 * Finds a [DatePicker].
 * Sets the date to [year], [month], and [day].
 */
fun Gherkin.ISetDateOnDatePicker(year: Int, month: Int, day: Int) {
    onView(withClassName(equalTo(DatePicker::class.java.name))).perform(setDate(year, month, day))
}

/**
 * Finds a [DatePicker].
 * Sets the date to [dateTime] using [LocalDateTime].
 * Example usage: ISetDateOnCalendar(LocalDateTime.of(2020, 8, 15, 12, 12))
 */
@RequiresApi(Build.VERSION_CODES.O)
fun Gherkin.ISetDateOnCalendar(dateTime: LocalDateTime) {
    val year = dateTime.year
    val month = dateTime.monthValue
    val day = dateTime.dayOfMonth

    onView(isAssignableFrom(DatePicker::class.java))
            .inRoot(isDialog())
            .perform(setDate(year, month, day))
}

/**
 * Finds an [EditText] with [fieldViewId].
 * Scrolls to the view and clicks it.
 * Then finds a view with [text] and clicks it.
 */
fun Gherkin.ISelectDefaultDateForView(@IdRes fieldViewId: Int) {
    ITouchTextField(fieldViewId)
    ITouch("OK")
}

/**
 * Finds a [DatePicker].
 * Sets the date to [days] from today.
 */
@RequiresApi(Build.VERSION_CODES.O)
fun Gherkin.ISetDateOnCalendarToNDaysFromToday(days: Long) {
    ISetDateOnCalendar(LocalDateTime.now().plusDays(days))
}

/**
 * Finds a [TimePicker].
 * Sets the time to [hour] and [minute].
 * Example usage: ISetTimeOnPicker(13, 45)
 */
fun Gherkin.ISetTimeOnPicker(@IntRange(from = 0, to = 23) hour: Int, @IntRange(from = 0, to = 59) minute: Int) {
    onView(isAssignableFrom(TimePicker::class.java))
            .inRoot(isDialog())
            .perform(setTime(hour, minute))
}