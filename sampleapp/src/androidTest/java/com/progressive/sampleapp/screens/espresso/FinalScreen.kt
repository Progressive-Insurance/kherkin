package com.progressive.sampleapp.screens.espresso

import android.app.Activity
import androidx.test.core.app.ActivityScenario
import com.progressive.kherkin.sampleapp.R
import com.progressive.kherkin.common.screen.Screen
import com.progressive.kherkin.common.screen.Trait
import com.progressive.kherkin.espresso.steps.actions.ITouchButton
import com.progressive.kherkin.common.testcore.Navigable
import com.progressive.kherkin.common.testcore.PathSegment
import com.progressive.kherkin.common.testcore.When
import com.progressive.sampleapp.activities.xml.FinalActivity
import com.progressive.sampleapp.setup.SamplePreconditionsData
import com.progressive.sampleapp.viewmodels.Destinations

class FinalScreen : Screen(), Navigable {

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
}