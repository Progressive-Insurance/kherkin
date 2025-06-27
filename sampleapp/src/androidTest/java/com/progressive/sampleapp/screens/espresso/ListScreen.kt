package com.progressive.sampleapp.screens.espresso

import android.app.Activity
import androidx.test.core.app.ActivityScenario
import com.progressive.kherkin.sampleapp.R
import com.progressive.kherkin.common.screen.Screen
import com.progressive.kherkin.common.screen.Trait
import com.progressive.kherkin.espresso.steps.actions.ITouchButton
import com.progressive.kherkin.espresso.testcore.Navigable
import com.progressive.kherkin.espresso.testcore.PathSegment
import com.progressive.kherkin.common.testcore.When
import com.progressive.sampleapp.activities.xml.ListActivity

class ListScreen : Screen(), Navigable {

    override lateinit var activityScenario: ActivityScenario<ListActivity>
    override val trait: Trait = Trait(R.id.buttonList)
    override fun screenActivityClass(): Class<out Activity> = ListActivity::class.java

    override fun startMyActivity() {
        activityScenario = ActivityScenario.launch(ListActivity::class.java)
    }

    override fun pathsToScreen(): List<PathSegment> {
        val pathSegments = mutableListOf<PathSegment>()
        pathSegments.add(
            PathSegment(
                start = MainScreen(),
                end = this,
                step = {
                    When.ITouchButton(R.id.buttonNav)
                })
        )
        return pathSegments
    }
}