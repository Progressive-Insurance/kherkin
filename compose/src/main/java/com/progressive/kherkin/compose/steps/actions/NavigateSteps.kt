package com.progressive.kherkin.compose.steps.actions

import androidx.compose.ui.test.junit4.ComposeTestRule
import com.progressive.kherkin.common.injection.BaseIntegrationComponentHolder
import com.progressive.kherkin.common.screen.IntegrationSetupInterface
import com.progressive.kherkin.common.testcore.Gherkin
import com.progressive.kherkin.compose.steps.testcore.ComposeNavigable
import com.progressive.kherkin.compose.steps.testcore.ComposeStepNavigator

private val integrationSetup: IntegrationSetupInterface get() = BaseIntegrationComponentHolder.component.integrationSetup()

fun Gherkin.INavigateToScreenWithCompose(screen: ComposeNavigable, composeTestRule: ComposeTestRule) {
    val path = ComposeStepNavigator()
        .findPathToScreen(integrationSetup.getStartScreen() as ComposeNavigable, screen, composeTestRule)
    path.forEach { it.step() }
}

fun Gherkin.INavigateBetweenScreensWithCompose(fromScreen: ComposeNavigable, toScreen: ComposeNavigable, composeTestRule: ComposeTestRule) {
    val path = ComposeStepNavigator().findPathToScreen(fromScreen, toScreen, composeTestRule)
    path.forEach { it.step() }
}