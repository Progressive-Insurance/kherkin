package com.progressive.sampleapp.screens.espresso

import android.app.Activity
import androidx.test.core.app.ActivityScenario
import com.progressive.kherkin.sampleapp.R
import com.progressive.kherkin.common.screen.Screen
import com.progressive.kherkin.common.screen.Trait
import com.progressive.kherkin.common.testcore.Navigable
import com.progressive.kherkin.common.testcore.PathSegment
import com.progressive.sampleapp.activities.xml.TextFieldActivity

class TextFieldScreen : Screen(), Navigable {

    override lateinit var activityScenario: ActivityScenario<TextFieldActivity>
    override val trait: Trait = Trait(R.id.editTextEnabledLeft)
    override fun screenActivityClass(): Class<out Activity> = TextFieldActivity::class.java

    override fun startMyActivity() {
        activityScenario = ActivityScenario.launch(TextFieldActivity::class.java)
    }

    override fun pathsToScreen(): List<PathSegment> = emptyList()
}