package com.progressive.kherkin.compose.steps.setup

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.progressive.kherkin.common.screen.IScreen
import com.progressive.kherkin.common.screen.Screen
import com.progressive.kherkin.common.testcore.ComposeTestLogger
import com.progressive.kherkin.common.testcore.IntegrationTestLogger

/**
 * Trait verification is necessary to ensure the test has launched the correct [Screen].
 * For Compose, a trait is a string on the screen. Traits must be set on each [Screen].
 * Each trait should be unique to the screen it is defined on. If not, the verification of the trait
 * cannot be guaranteed to identify that specific screen.
 */
object TraitVerifier {

    @JvmStatic
    fun verifyTrait(
        screen: IScreen,
        composeTestRule: ComposeTestRule,
        timeoutInMillis: Long = 2000
    ) {
        val trait = screen.trait
        val tag = screen.trait.tag
        val text = screen.trait.text
        when {
            tag != null && text != null -> {
                val tag = trait.tag ?: return
                val text = trait.text ?: return
                composeTestRule.waitUntil(timeoutInMillis) {
                    composeTestRule
                        .onAllNodes(hasTestTag(tag).and(hasText(text)))
                        .fetchSemanticsNodes().size == 1
                }
                ComposeTestLogger().info("${::verifyTrait.name}: onNode(hasTestTag($tag).and(hasText($text))).assertIsDisplayed()")
                composeTestRule.onNode(hasTestTag(tag).and(hasText(text))).assertIsDisplayed()
            }

            text != null -> {
                composeTestRule.waitUntil(timeoutInMillis) {
                    composeTestRule
                        .onAllNodesWithText(text)
                        .fetchSemanticsNodes().size == 1
                }
                ComposeTestLogger().info("${::verifyTrait.name}: onNodeWithText($text).assertIsDisplayed()")
                composeTestRule.onNodeWithText(text).assertIsDisplayed()
            }

            tag != null -> {
                composeTestRule.waitUntil(timeoutInMillis) {
                    composeTestRule
                        .onAllNodesWithTag(tag)
                        .fetchSemanticsNodes().size == 1
                }
                ComposeTestLogger().info("${::verifyTrait.name}: onNodeWithTag($tag).assertIsDisplayed()")
                composeTestRule.onNodeWithTag(tag).assertIsDisplayed()
            }

            else -> {
                IntegrationTestLogger().info("ScreenActivityName: $screen")
                throw RuntimeException("Timed out waiting for activity: $screen, no traits found.")
            }
        }
    }
}