package com.progressive.sampleapp.screens.espresso

import android.app.Activity
import androidx.test.core.app.ActivityScenario
import com.progressive.kherkin.sampleapp.R
import com.progressive.kherkin.common.screen.Screen
import com.progressive.kherkin.common.screen.Trait
import com.progressive.kherkin.espresso.testcore.Navigable
import com.progressive.kherkin.espresso.testcore.PathSegment
import com.progressive.sampleapp.activities.xml.ButtonActivity

class ButtonScreen : Screen(), Navigable {

    override lateinit var activityScenario: ActivityScenario<ButtonActivity>
    override val trait: Trait = Trait(R.id.buttonClickCounter)
    override fun screenActivityClass(): Class<out Activity> = ButtonActivity::class.java

    override fun startMyActivity() {
        activityScenario = ActivityScenario.launch(ButtonActivity::class.java)
    }

    override fun pathsToScreen(): List<PathSegment> = emptyList()
}