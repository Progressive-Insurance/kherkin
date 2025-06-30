package com.progressive.sampleapp.screens.compose

import android.app.Activity
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.test.core.app.ActivityScenario
import com.progressive.kherkin.common.screen.Screen
import com.progressive.kherkin.common.screen.Trait
import com.progressive.kherkin.common.testcore.And
import com.progressive.kherkin.compose.steps.testcore.ComposeNavigable
import com.progressive.kherkin.compose.steps.testcore.ComposePathSegment
import com.progressive.kherkin.espresso.steps.actions.ITouchButton
import com.progressive.kherkin.sampleapp.R
import com.progressive.sampleapp.activities.compose.SecondComposeActivity
import com.progressive.sampleapp.screens.espresso.TextFieldScreen

class SecondComposeScreen : Screen(), ComposeNavigable {

    override lateinit var activityScenario: ActivityScenario<SecondComposeActivity>
    override val trait: Trait = Trait("Second Compose Activity")
    override fun screenActivityClass(): Class<out Activity> = SecondComposeActivity::class.java

    override fun startMyActivity() {
        activityScenario = ActivityScenario.launch(SecondComposeActivity::class.java)
    }

    override fun pathsToScreen(composeTestRule: ComposeTestRule): List<ComposePathSegment> {
        val pathSegments = mutableListOf<ComposePathSegment>()
        pathSegments.add(
            ComposePathSegment(
                start = TextFieldScreen(),
                end = this,
                step =
                {
                    And.ITouchButton(R.id.buttonNavSecondCompose)
                }
            )
        )
        return pathSegments
    }
}