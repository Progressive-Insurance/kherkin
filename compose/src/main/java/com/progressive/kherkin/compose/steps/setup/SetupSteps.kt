package com.progressive.kherkin.compose.steps.setup

import androidx.compose.ui.test.junit4.ComposeTestRule
import com.progressive.kherkin.compose.steps.actions.IWaitToSeeScreen
import com.progressive.kherkin.common.injection.BaseIntegrationComponentHolder
import com.progressive.kherkin.common.screen.IntegrationSetupInterface
import com.progressive.kherkin.common.screen.Screen
import com.progressive.kherkin.common.steps.setup.IAmOnTheScreen
import com.progressive.kherkin.common.testcore.Gherkin
import com.progressive.kherkin.compose.steps.testcore.ComposeNavigable
import com.progressive.kherkin.compose.steps.testcore.ComposeStepNavigator

private val integrationSetup: IntegrationSetupInterface get() = BaseIntegrationComponentHolder.component.integrationSetup()

/**
 * Stores the [Screen] the user is starting to [screen].
 * Starts with activity represented by [screen] and verifies the trait.
 */
fun Gherkin.IRenderScreen(screen: Screen, composeTestRule: ComposeTestRule) {
    Screen.myCurrentScreen = screen
    startActivityAndVerifyTrait(composeTestRule)
}

/**
 * Navigates from the [ComposeNavigable] [fromScreen]
 * to the [ComposeNavigable] [toScreen] via the pathsToScreen() methods in each [ComposeNavigable] in the path.
 * Once the last screen is rendered, [IWaitToSeeScreen] is called to verify the [toScreen] renders properly.
 */
fun Gherkin.INavigateFromTo(fromScreen: ComposeNavigable, toScreen: ComposeNavigable, composeTestRule: ComposeTestRule) {
    val path = ComposeStepNavigator()
        .findPathToScreen(fromScreen, toScreen, composeTestRule)
    path.forEach { it.step() }
    IWaitToSeeScreen(toScreen as Screen, composeTestRule)
}

/**
 * Navigates from starting screen defined in [IRenderScreen] or [IAmOnTheScreen]
 * to the [ComposeNavigable] [screen] via the pathsToScreen() methods in each [ComposeNavigable] in the path.
 * Once the last screen is rendered, [IWaitToSeeScreen] is called to verify the [screen] renders properly.
 */
fun Gherkin.INavigateToScreen(screen: ComposeNavigable, composeTestRule: ComposeTestRule) {
    val path = ComposeStepNavigator()
        .findPathToScreen(integrationSetup.getStartScreen() as ComposeNavigable, screen, composeTestRule)
    path.forEach { it.step() }
    IWaitToSeeScreen(screen as Screen, composeTestRule)
}

/** Start activity and verify [Screen.trait] of starting [Screen]. */
fun startActivityAndVerifyTrait(composeTestRule: ComposeTestRule) {
    integrationSetup.startActivity()
    TraitVerifier.verifyTrait(Screen.myCurrentScreen, composeTestRule)
}