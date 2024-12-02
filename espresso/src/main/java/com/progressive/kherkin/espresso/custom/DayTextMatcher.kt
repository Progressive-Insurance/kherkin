package com.progressive.kherkin.espresso.custom

import org.hamcrest.BaseMatcher
import org.hamcrest.Description

/**
 * Custom matcher for checking if text matches a weekday (M-F) or the weekend (Sat+Sun).
 */
class DayTextMatcher private constructor(private val isWeekday: Boolean) : BaseMatcher<String>() {
    companion object {
        private val weekDays = setOf(
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday"
        )
        private val weekendDays = setOf(
                "Saturday",
                "Sunday"
        )

        fun isWeekday(): DayTextMatcher = DayTextMatcher(true)
        fun isWeekend(): DayTextMatcher = DayTextMatcher(false)
    }

    override fun matches(item: Any): Boolean {
        return if (item is String) {
            if (isWeekday) {
                weekDays.contains(item)
            } else {
                weekendDays.contains(item)
            }
        } else {
            false
        }
    }

    override fun describeTo(description: Description) {
        description.appendText(if (isWeekday) "Weekday name" else "Weekend day name")
    }
}