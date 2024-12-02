package com.progressive.sampleapp.screens.compose

import android.app.Activity
import androidx.test.core.app.ActivityScenario
import com.progressive.kherkin.common.screen.Screen
import com.progressive.kherkin.common.screen.Trait
import com.progressive.kherkin.common.testcore.Navigable
import com.progressive.kherkin.common.testcore.PathSegment
import com.progressive.sampleapp.activities.compose.BasicComposeActivity

class BasicComposeScreen : Screen(), Navigable {

    override lateinit var activityScenario: ActivityScenario<BasicComposeActivity>
    override val trait: Trait = Trait("Hello user!")
    override fun screenActivityClass(): Class<out Activity> = BasicComposeActivity::class.java

    override fun startMyActivity() {
        activityScenario = ActivityScenario.launch(BasicComposeActivity::class.java)
    }

    override fun pathsToScreen(): List<PathSegment> = emptyList()
}