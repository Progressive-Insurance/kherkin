package com.progressive.kherkin.compose.steps.actions

import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollToNode
import com.progressive.kherkin.common.testcore.ComposeTestLogger
import com.progressive.kherkin.common.testcore.Gherkin

/** Scrolls a scrollable container [tagOfScrollableContainer] to the matching [tag]. */
fun Gherkin.IScrollOnNodeUntilISee(tagOfScrollableContainer: String, tag: String, composeTestRule: ComposeTestRule) {
    ComposeTestLogger().info("${::IScrollOnNodeUntilISee.name}: onNodeWithTag($tagOfScrollableContainer).performScrollToNode(hasTestTag($tag))")
    composeTestRule.onNodeWithTag(tagOfScrollableContainer).performScrollToNode(hasTestTag(tag))
}

/** Scrolls a scrollable container [tagOfScrollableContainer] to the matching [text]. */
fun Gherkin.IScrollOnNodeUntilISeeText(tagOfScrollableContainer: String, text: String, composeTestRule: ComposeTestRule) {
    ComposeTestLogger().info("${::IScrollOnNodeUntilISeeText.name}: onNodeWithTag($tagOfScrollableContainer).performScrollToNode(hasText($text))")
    composeTestRule.onNodeWithTag(tagOfScrollableContainer).performScrollToNode(hasText(text))
}