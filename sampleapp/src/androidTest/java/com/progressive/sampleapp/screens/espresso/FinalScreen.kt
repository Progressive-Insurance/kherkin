package com.progressive.sampleapp.screens.espresso

import android.app.Activity
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.test.core.app.ActivityScenario
import com.progressive.kherkin.sampleapp.R
import com.progressive.kherkin.common.screen.Screen
import com.progressive.kherkin.common.screen.Trait
import com.progressive.kherkin.common.testcore.And
import com.progressive.kherkin.common.testcore.Then
import com.progressive.kherkin.espresso.steps.actions.ITouchButton
import com.progressive.kherkin.espresso.testcore.Navigable
import com.progressive.kherkin.espresso.testcore.PathSegment
import com.progressive.kherkin.common.testcore.When
import com.progressive.kherkin.compose.steps.actions.ITouchText
import com.progressive.kherkin.compose.steps.actions.IWaitToSeeScreen
import com.progressive.kherkin.compose.steps.testcore.ComposeNavigable
import com.progressive.kherkin.compose.steps.testcore.ComposePathSegment
import com.progressive.kherkin.espresso.steps.actions.IWaitToSeeScreen
import com.progressive.sampleapp.activities.xml.FinalActivity
import com.progressive.sampleapp.screens.compose.BasicComposeScreen
import com.progressive.sampleapp.setup.SamplePreconditionsData
import com.progressive.sampleapp.viewmodels.Destinations

class FinalScreen : Screen(), Navigable, ComposeNavigable {

    override lateinit var activityScenario: ActivityScenario<FinalActivity>
    override val trait: Trait = Trait(R.id.buttonFinal)
    override fun screenActivityClass(): Class<out Activity> = FinalActivity::class.java

    override fun startMyActivity() {
        activityScenario = ActivityScenario.launch(FinalActivity::class.java)
    }

    override fun pathsToScreen(): List<PathSegment> {
        val pathSegments = mutableListOf<PathSegment>()
        pathSegments.add(
            PathSegment(start = ListScreen(),
                end = this,
                step = {
                    When.ITouchButton(R.id.buttonList)
                })
        )
        pathSegments.add(
            PathSegment(
                start = ViewPagerScreen(),
                end = this,
                step = {
                    When.ITouchButton(R.id.buttonViewPager)
                },
                preconditions = mutableMapOf(SamplePreconditionsData.destinationKey to Destinations.ViewPager)
            )
        )

        return pathSegments
    }

    override fun pathsToScreen(composeTestRule: ComposeTestRule): List<ComposePathSegment> {
        val pathSegments = mutableListOf<ComposePathSegment>()
        pathSegments.add(
            ComposePathSegment(start = BasicComposeScreen(),
                end = this,
                step = {
                    And.ITouchText("Navigate", composeTestRule)
                })
        )
        return pathSegments
    }
}