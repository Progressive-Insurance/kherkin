package com.progressive.kherkin.compose.steps.assertion

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import com.progressive.kherkin.common.testcore.ComposeTestLogger
import com.progressive.kherkin.common.testcore.Gherkin

/** Finds a node with [tag] and checks that it is displayed. */
fun Gherkin.IShouldSeeNode(tag: String, composeTestRule: ComposeTestRule) {
    ComposeTestLogger().info("${::IShouldSeeNode.name}: onNodeWithTag($tag).assertIsDisplayed()")
    composeTestRule.onNodeWithTag(tag).assertIsDisplayed()
}

/** Finds a node with [tag] and checks that it is not displayed. */
fun Gherkin.IShouldNotSeeNode(tag: String, composeTestRule: ComposeTestRule) {
    ComposeTestLogger().info("${::IShouldNotSeeNode.name}: onNodeWithTag($tag).assertIsNotDisplayed()")
    composeTestRule.onNodeWithTag(tag).assertIsNotDisplayed()
}

/** Finds a node with [tag] that contains [text] and checks that it is displayed. */
fun Gherkin.IShouldSeeNodeWithText(tag: String, text: String, composeTestRule: ComposeTestRule) {
    ComposeTestLogger().info("${::IShouldSeeNodeWithText.name}: onNode(hasTestTag($tag).and(hasText($text))).assertIsDisplayed()")
    composeTestRule.onNode(hasTestTag(tag).and(hasText(text))).assertIsDisplayed()
}

/** Finds a node with [tag] and checks that it is displayed and clickable. */
fun Gherkin.IShouldSeeNodeIsClickable(tag: String, composeTestRule: ComposeTestRule) {
    ComposeTestLogger().info("${::IShouldSeeNodeIsClickable.name}: onNodeWithTag($tag).assertHasClickAction().assertIsDisplayed()")
    composeTestRule.onNodeWithTag(tag).assertHasClickAction().assertIsDisplayed()
}

/** Finds a node with [tag] that contains [text] and checks that it is displayed and clickable. */
fun Gherkin.IShouldSeeNodeWithTextIsClickable(tag: String, text: String, composeTestRule: ComposeTestRule) {
    ComposeTestLogger().info("${::IShouldSeeNodeWithTextIsClickable.name}: onNode(hasTestTag($tag).and(hasText($text))).assertHasClickAction().assertIsDisplayed()")
    composeTestRule.onNode(hasTestTag(tag).and(hasText(text))).assertHasClickAction().assertIsDisplayed()
}