package com.progressive.kherkin.compose.steps.testcore

import androidx.compose.ui.test.junit4.ComposeTestRule

interface ComposeNavigable {
    fun pathsToScreen(composeTestRule: ComposeTestRule): List<ComposePathSegment>
}