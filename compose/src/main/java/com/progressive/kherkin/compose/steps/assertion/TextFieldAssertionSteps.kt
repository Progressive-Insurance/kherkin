package com.progressive.kherkin.compose.steps.assertion

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.progressive.kherkin.common.testcore.ComposeTestLogger
import com.progressive.kherkin.common.testcore.Gherkin

/** Finds a node [tag] and checks that it has text that is displayed. */
fun Gherkin.IShouldSeeTextField(tag: String, composeTestRule: ComposeTestRule) {
    ComposeTestLogger().info("${::IShouldSeeTextField.name}: onNodeWithTag($tag).assert(hasSetTextAction())")
    composeTestRule.onNodeWithTag(tag).assert(hasSetTextAction())
}

/** Finds a node text field that contains [text] and checks that it is displayed. */
fun Gherkin.IShouldSeeTextFieldWithText(text: String, composeTestRule: ComposeTestRule) {
    ComposeTestLogger().info("${::IShouldSeeTextFieldWithText.name}: onNodeWithText($text).assert(hasSetTextAction())")
    composeTestRule.onNodeWithText(text).assert(hasSetTextAction())
}

/** Finds a node [tag] that contains [text] and checks that it is displayed. */
fun Gherkin.IShouldSeeTextFieldWithTagAndText(tag: String, text: String, composeTestRule: ComposeTestRule) {
    ComposeTestLogger().info("${::IShouldSeeTextFieldWithTagAndText.name}: onNode(hasTestTag($tag).and(hasText($text))).assert(hasSetTextAction())")
    composeTestRule.onNode(hasTestTag(tag).and(hasText(text))).assert(hasSetTextAction())
}