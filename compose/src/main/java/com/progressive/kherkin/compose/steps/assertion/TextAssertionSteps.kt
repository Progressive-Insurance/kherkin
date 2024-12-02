package com.progressive.kherkin.compose.steps.assertion

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import com.progressive.kherkin.common.testcore.Gherkin

/** Finds a node with [text] and checks that it is displayed. */
fun Gherkin.IShouldSeeText(text: String, composeTestRule: ComposeTestRule) {
    composeTestRule.onNodeWithText(text).assertIsDisplayed()
}

/** Finds a node with [text] and checks that it is not displayed. */
fun Gherkin.IShouldNotSeeText(text: String, composeTestRule: ComposeTestRule) {
    composeTestRule.onNodeWithText(text).assertIsNotDisplayed()
}

/** Finds a node with [text] and checks that it is displayed and clickable. */
fun Gherkin.IShouldSeeTextIsClickable(text: String, composeTestRule: ComposeTestRule) {
    composeTestRule.onNodeWithText(text).assertHasClickAction().assertIsDisplayed()
}