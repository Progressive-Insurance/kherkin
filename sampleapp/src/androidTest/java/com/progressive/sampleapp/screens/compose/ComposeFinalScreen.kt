package com.progressive.sampleapp.screens.compose

import android.app.Activity
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.test.core.app.ActivityScenario
import com.progressive.kherkin.common.screen.Screen
import com.progressive.kherkin.common.screen.Trait
import com.progressive.kherkin.common.testcore.And
import com.progressive.kherkin.compose.steps.actions.ITouchText
import com.progressive.kherkin.compose.steps.testcore.ComposeNavigable
import com.progressive.kherkin.compose.steps.testcore.ComposePathSegment
import com.progressive.kherkin.espresso.steps.actions.IWaitToSeeScreen
import com.progressive.kherkin.sampleapp.R
import com.progressive.sampleapp.activities.xml.FinalActivity

class ComposeFinalScreen : Screen(), ComposeNavigable {

    override lateinit var activityScenario: ActivityScenario<FinalActivity>
    override val trait: Trait = Trait(R.id.buttonFinal)
    override fun screenActivityClass(): Class<out Activity> = FinalActivity::class.java

    override fun startMyActivity() {
        activityScenario = ActivityScenario.launch(FinalActivity::class.java)
    }

    override fun pathsToScreen(composeTestRule: ComposeTestRule): List<ComposePathSegment> {
        val pathSegments = mutableListOf<ComposePathSegment>()
        pathSegments.add(
            ComposePathSegment(start = BasicComposeScreen(),
                end = this,
                step = {
                    And.ITouchText("Navigate", composeTestRule)
                    And.IWaitToSeeScreen(ComposeFinalScreen(), 5000)
                })
        )
        return pathSegments
    }
}