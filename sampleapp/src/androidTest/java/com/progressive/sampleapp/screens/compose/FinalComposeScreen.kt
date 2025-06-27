package com.progressive.sampleapp.screens.compose

import android.app.Activity
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.test.core.app.ActivityScenario
import com.progressive.kherkin.common.screen.Screen
import com.progressive.kherkin.common.screen.Trait
import com.progressive.kherkin.common.testcore.And
import com.progressive.kherkin.common.testcore.Then
import com.progressive.kherkin.compose.steps.actions.ITouchText
import com.progressive.kherkin.compose.steps.actions.IWaitToSeeScreen
import com.progressive.kherkin.compose.steps.testcore.ComposeNavigable
import com.progressive.kherkin.compose.steps.testcore.ComposePathSegment
import com.progressive.sampleapp.activities.compose.FinalComposeActivity

class FinalComposeScreen : Screen(), ComposeNavigable {

    override lateinit var activityScenario: ActivityScenario<FinalComposeActivity>
    override val trait: Trait = Trait("Final Compose Activity")
    override fun screenActivityClass(): Class<out Activity> = FinalComposeActivity::class.java

    override fun startMyActivity() {
        activityScenario = ActivityScenario.launch(FinalComposeActivity::class.java)
    }

    override fun pathsToScreen(composeTestRule: ComposeTestRule): List<ComposePathSegment> {
        val pathSegments = mutableListOf<ComposePathSegment>()
        pathSegments.add(
            ComposePathSegment(
                start = SecondComposeScreen(),
                end = this,
                step = {
                    And.IWaitToSeeScreen(SecondComposeScreen(), composeTestRule)
                    Then.ITouchText("Navigate to Final Activity", composeTestRule)
                }
            )
        )
        return pathSegments
    }
}