package com.progressive.kherkin.compose.steps.actions

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performFirstLinkClick
import com.progressive.kherkin.common.testcore.Gherkin

/** Finds a node that contains [text] and clicks it. */
fun Gherkin.ITouchText(text: String, composeTestRule: ComposeTestRule) {
    composeTestRule.onNodeWithText(text).assertHasClickAction().performClick()
}

/** Finds a node with [tag] and clicks it. */
fun Gherkin.ITouchNode(tag: String, composeTestRule: ComposeTestRule) {
    composeTestRule.onNodeWithTag(tag).assertHasClickAction().performClick()
}

/** Finds a node with [tag] that contains [text] and clicks it. */
fun Gherkin.ITouchNodeWithText(tag: String, text: String, composeTestRule: ComposeTestRule) {
    composeTestRule.onNode(hasTestTag(tag).and(hasText(text))).assertHasClickAction().performClick()
}

/** Finds a node that contains an annotated link string with [text] and clicks it. */
fun Gherkin.ITouchLinkWithText(text: String, composeTestRule: ComposeTestRule) {
    composeTestRule.onNodeWithText(text).performFirstLinkClick()
}

/** Finds a node that contains an annotated link string with [tag] and clicks it. */
fun Gherkin.ITouchLinkWithTag(tag: String, composeTestRule: ComposeTestRule) {
    composeTestRule.onNodeWithTag(tag).performFirstLinkClick()
}