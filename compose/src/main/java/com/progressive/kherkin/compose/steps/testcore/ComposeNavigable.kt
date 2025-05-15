package com.progressive.kherkin.compose.steps.testcore

import androidx.compose.ui.test.junit4.ComposeTestRule
import com.progressive.kherkin.common.screen.Screen

/**
 * Interface that must be inherited for any [Screen] to be used in end-to-end tests.
 */
interface ComposeNavigable {
    fun pathsToScreen(composeTestRule: ComposeTestRule): List<ComposePathSegment>
}