package com.progressive.kherkin.compose.steps.assertion

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import com.progressive.kherkin.common.testcore.ComposeTestLogger
import com.progressive.kherkin.common.testcore.Gherkin

/** Finds a node with [text] and checks that it is displayed. */
fun Gherkin.IShouldSeeText(text: String, composeTestRule: ComposeTestRule) {
    ComposeTestLogger().info("${::IShouldSeeText.name}: onNodeWithText($text).assertIsDisplayed()")
    composeTestRule.onNodeWithText(text).assertIsDisplayed()
}

/** Finds a node with [text] and checks that it is not displayed. */
fun Gherkin.IShouldNotSeeText(text: String, composeTestRule: ComposeTestRule) {
    ComposeTestLogger().info("${::IShouldNotSeeText.name}: onNodeWithText($text).assertIsNotDisplayed()")
    composeTestRule.onNodeWithText(text).assertIsNotDisplayed()
}

/** Finds a node with [text] and checks that it is displayed and clickable. */
fun Gherkin.IShouldSeeTextIsClickable(text: String, composeTestRule: ComposeTestRule) {
    ComposeTestLogger().info("${::IShouldSeeTextIsClickable.name}: onNodeWithText($text).assertHasClickAction().assertIsDisplayed()")
    composeTestRule.onNodeWithText(text).assertHasClickAction().assertIsDisplayed()
}