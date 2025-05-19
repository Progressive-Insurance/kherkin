package com.progressive.sampleapp.screens.espresso

import android.app.Activity
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import com.progressive.kherkin.sampleapp.R
import com.progressive.kherkin.common.screen.Screen
import com.progressive.kherkin.common.screen.Trait
import com.progressive.kherkin.espresso.steps.actions.ITouchButton
import com.progressive.kherkin.espresso.steps.actions.IWaitToSeeScreen
import com.progressive.kherkin.common.testcore.And
import com.progressive.kherkin.espresso.testcore.Navigable
import com.progressive.kherkin.espresso.testcore.PathSegment
import com.progressive.kherkin.common.testcore.PreconditionsData
import com.progressive.kherkin.common.testcore.When
import com.progressive.sampleapp.activities.xml.ViewPagerActivity
import com.progressive.sampleapp.fragments.ViewPagerFragment.Companion.IS_VERTICAL
import com.progressive.sampleapp.setup.SamplePreconditionsData
import com.progressive.sampleapp.viewmodels.Destinations

class ViewPagerScreen : Screen(), Navigable {

    override lateinit var activityScenario: ActivityScenario<ViewPagerActivity>
    override val trait: Trait = Trait(R.id.buttonViewPager)
    override fun screenActivityClass(): Class<out Activity> = ViewPagerActivity::class.java

    override fun startMyActivity() {
        val isVertical = PreconditionsData.preconditions[SamplePreconditionsData.verticalKey] == true

        val intent = Intent(
            ApplicationProvider.getApplicationContext(),
            ViewPagerActivity::class.java).apply {
                putExtra(IS_VERTICAL, isVertical)
        }

        activityScenario = ActivityScenario.launch(intent)
    }

    override fun pathsToScreen(): List<PathSegment> {
        val pathSegments = mutableListOf<PathSegment>()
        pathSegments.add(
            PathSegment(
                start = MainScreen(),
                end = this,
                step = {
                    And.IWaitToSeeScreen(MainScreen())
                    When.ITouchButton(R.id.buttonNav)
                },
                preconditions = mutableMapOf(SamplePreconditionsData.destinationKey to Destinations.ViewPager)
            )
        )
        return pathSegments
    }
}