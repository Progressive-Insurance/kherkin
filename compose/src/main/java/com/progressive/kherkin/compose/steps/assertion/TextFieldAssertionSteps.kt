package com.progressive.kherkin.compose.steps.assertion

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isNotEnabled
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.progressive.kherkin.common.testcore.ComposeTestLogger
import com.progressive.kherkin.common.testcore.Gherkin

/** Finds a node [tag] and checks that it has EditableText. */
fun Gherkin.IShouldSeeTextField(tag: String, composeTestRule: ComposeTestRule) {
    ComposeTestLogger().info("${::IShouldSeeTextField.name}: onNodeWithTag($tag).assert(hasSetTextAction())")
    composeTestRule.onNodeWithTag(tag).assert(SemanticsMatcher.keyIsDefined(SemanticsProperties.EditableText))
}

/** Finds a node that contains [text] and checks that it has EditableText. */
fun Gherkin.IShouldSeeTextFieldWithText(text: String, composeTestRule: ComposeTestRule) {
    ComposeTestLogger().info("${::IShouldSeeTextFieldWithText.name}: onNodeWithText($text).assert(hasSetTextAction())")
    composeTestRule.onNodeWithText(text).assert(SemanticsMatcher.keyIsDefined(SemanticsProperties.EditableText))
}

/** Finds a node [tag] that contains [text] and checks that it has EditableText. */
fun Gherkin.IShouldSeeTextFieldWithTagAndText(tag: String, text: String, composeTestRule: ComposeTestRule) {
    ComposeTestLogger().info("${::IShouldSeeTextFieldWithTagAndText.name}: onNode(hasTestTag($tag).and(hasText($text))).assert(hasSetTextAction())")
    composeTestRule.onNode(hasTestTag(tag).and(hasText(text))).assert(SemanticsMatcher.keyIsDefined(SemanticsProperties.EditableText))
}

/** Finds a node [tag] and checks that it has EditableText and is not enabled. */
fun Gherkin.IShouldSeeDisabledTextField(tag: String, composeTestRule: ComposeTestRule) {
    ComposeTestLogger().info("${::IShouldSeeDisabledTextField.name}: onNodeWithTag($tag).assert(keyIsDefined(EditableText)).assert(isNotEnabled())")
    composeTestRule.onNodeWithTag(tag)
        .assert(SemanticsMatcher.keyIsDefined(SemanticsProperties.EditableText))
        .assert(isNotEnabled())
}

/** Finds a node that contains [text] and checks that it has EditableText and is not enabled. */
fun Gherkin.IShouldSeeDisabledTextFieldWithText(text: String, composeTestRule: ComposeTestRule) {
    ComposeTestLogger().info("${::IShouldSeeDisabledTextFieldWithText.name}: onNode(hasText($text)).assert(keyIsDefined(EditableText)).assert(isNotEnabled())")
    composeTestRule.onNode(hasText(text))
        .assert(SemanticsMatcher.keyIsDefined(SemanticsProperties.EditableText))
        .assert(isNotEnabled())
}

/** Finds a node [tag] that contains [text] and checks that it has EditableText and is not enabled. */
fun Gherkin.IShouldSeeDisabledTextFieldWithTagAndText(tag: String, text: String, composeTestRule: ComposeTestRule) {
    ComposeTestLogger().info("${::IShouldSeeDisabledTextFieldWithTagAndText.name}: onNode(hasTestTag($tag).and(hasText($text))).assert(keyIsDefined(EditableText)).assert(isNotEnabled())")
    composeTestRule.onNode(hasTestTag(tag).and(hasText(text)))
        .assert(SemanticsMatcher.keyIsDefined(SemanticsProperties.EditableText))
        .assert(isNotEnabled())
}