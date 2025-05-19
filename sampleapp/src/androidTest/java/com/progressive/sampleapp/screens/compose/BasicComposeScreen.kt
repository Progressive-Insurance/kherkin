package com.progressive.sampleapp.screens.compose

import android.app.Activity
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.test.core.app.ActivityScenario
import com.progressive.kherkin.common.screen.Screen
import com.progressive.kherkin.common.screen.Trait
import com.progressive.kherkin.common.testcore.Then
import com.progressive.kherkin.common.testcore.When
import com.progressive.kherkin.compose.steps.actions.IWaitToSeeScreen
import com.progressive.kherkin.compose.steps.testcore.ComposeNavigable
import com.progressive.kherkin.compose.steps.testcore.ComposePathSegment
import com.progressive.kherkin.espresso.steps.actions.ITouchButton
import com.progressive.kherkin.sampleapp.R
import com.progressive.sampleapp.activities.compose.BasicComposeActivity
import com.progressive.sampleapp.screens.espresso.MainScreen

class BasicComposeScreen : Screen(), ComposeNavigable {

    override lateinit var activityScenario: ActivityScenario<BasicComposeActivity>
    override val trait: Trait = Trait("Hello user!")
    override fun screenActivityClass(): Class<out Activity> = BasicComposeActivity::class.java

    override fun startMyActivity() {
        activityScenario = ActivityScenario.launch(BasicComposeActivity::class.java)
    }

    override fun pathsToScreen(composeTestRule: ComposeTestRule): List<ComposePathSegment> {
        return listOf(
            ComposePathSegment(MainScreen(), this, {
                When.ITouchButton(R.id.buttonNavCompose)
                Then.IWaitToSeeScreen(BasicComposeScreen(), composeTestRule)
            })
        )
    }
}