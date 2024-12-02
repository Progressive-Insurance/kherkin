package com.progressive.kherkin.compose.steps.setup

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import com.progressive.kherkin.common.screen.IScreen
import com.progressive.kherkin.common.screen.Screen
import com.progressive.kherkin.common.testcore.IntegrationTestLogger

/**
 * Trait verification is necessary to ensure the test has launched the correct [Screen].
 * For Compose, a trait is a string on the screen. Traits must be set on each [Screen].
 * Each trait should be unique to the screen it is defined on. If not, the verification of the trait
 * cannot be guaranteed to identify that specific screen.
 */
object TraitVerifier {

    @JvmStatic
    fun verifyTrait(screen: IScreen, composeTestRule: ComposeTestRule, timeoutInMillis: Long = 2000) {
        val trait = screen.trait
        if (trait.text == null) {
            val logger = IntegrationTestLogger()
            logger.info("ScreenActivityName: $screen")
            throw RuntimeException("Timed out waiting for activity: $screen, no trait text found.")
        }
        trait.text.let {
            if (it == null) {
                return
            }
            composeTestRule.waitUntil(timeoutInMillis) {
                composeTestRule
                    .onAllNodesWithText(it)
                    .fetchSemanticsNodes().size == 1
            }
            composeTestRule.onNodeWithText(it).assertIsDisplayed()
        }
    }
}