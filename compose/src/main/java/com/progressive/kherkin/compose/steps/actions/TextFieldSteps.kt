package com.progressive.kherkin.compose.steps.actions

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import com.progressive.kherkin.common.testcore.Gherkin

/** Finds a node with [tag] and inputs [text] into the text field. */
fun Gherkin.IEnterTextIntoField(tag: String, text: String, composeTestRule: ComposeTestRule) {
    composeTestRule.onNodeWithTag(tag).assert(hasSetTextAction()).performTextInput(text)
}

/** Finds a node with [tag] and leaves the text field empty. */
fun Gherkin.ILeaveFieldEmpty(tag: String, composeTestRule: ComposeTestRule) {
    composeTestRule.onNodeWithTag(tag).assert(hasSetTextAction()).performImeAction()
}

/** Finds a node with [tag] and clears the text field. */
fun Gherkin.IClearField(tag: String, composeTestRule: ComposeTestRule) {
    composeTestRule.onNodeWithTag(tag).assert(hasSetTextAction()).performTextClearance()
}