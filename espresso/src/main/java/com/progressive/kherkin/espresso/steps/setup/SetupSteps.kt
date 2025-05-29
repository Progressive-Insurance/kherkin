package com.progressive.kherkin.espresso.steps.setup

import com.progressive.kherkin.common.injection.BaseIntegrationComponentHolder
import com.progressive.kherkin.common.screen.IntegrationSetupInterface
import com.progressive.kherkin.common.screen.Screen
import com.progressive.kherkin.espresso.steps.actions.IWaitToSeeScreen
import com.progressive.kherkin.common.steps.setup.IAmOnTheScreen
import com.progressive.kherkin.common.testcore.Gherkin
import com.progressive.kherkin.espresso.testcore.Navigable
import com.progressive.kherkin.espresso.testcore.StepNavigator

private val integrationSetup: IntegrationSetupInterface get() = BaseIntegrationComponentHolder.component.integrationSetup()

/**
 * Stores the [Screen] the user is starting to [screen].
 * Starts with activity represented by [screen] and verifies the trait.
 */
fun Gherkin.IRenderScreen(screen: Screen) {
    Screen.myCurrentScreen = screen
    startActivityAndVerifyTrait()
}

/**
 * Navigates from the [Navigable] [fromScreen]
 * to the [Navigable] [toScreen] via the pathsToScreen() methods in each [Navigable] in the path.
 * Once the last screen is rendered, [IWaitToSeeScreen] is called to verify the [screen] renders properly.
 */
fun Gherkin.INavigateFromTo(fromScreen: Navigable, toScreen: Navigable) {
    val path = StepNavigator().findPathToScreen(fromScreen, toScreen)
    path.forEach { it.step() }
    IWaitToSeeScreen(toScreen as Screen)
}

/**
 * Navigates from starting screen defined in [IRenderScreen] or [IAmOnTheScreen]
 * to the [Navigable] [screen] via the pathsToScreen() methods in each [Navigable] in the path.
 * Once the last screen is rendered, [IWaitToSeeScreen] is called to verify the [screen] renders properly.
 */
fun Gherkin.INavigateToScreen(screen: Navigable) {
    val pathSegments = StepNavigator()
        .findPathToScreen(integrationSetup.getStartScreen() as Navigable, screen)
    pathSegments.forEach { it.step() }
    IWaitToSeeScreen(pathSegments.last().end as Screen)
}

/** Start activity and verify [Screen.trait] of starting [Screen]. */
fun startActivityAndVerifyTrait() {
    integrationSetup.startActivity()
    TraitVerifier.verifyTrait(Screen.myCurrentScreen)
}