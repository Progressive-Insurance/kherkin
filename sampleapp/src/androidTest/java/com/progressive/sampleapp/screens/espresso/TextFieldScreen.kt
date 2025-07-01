package com.progressive.sampleapp.screens.espresso

import android.app.Activity
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.test.core.app.ActivityScenario
import com.progressive.kherkin.common.screen.Screen
import com.progressive.kherkin.common.screen.Trait
import com.progressive.kherkin.common.testcore.When
import com.progressive.kherkin.compose.steps.actions.ITouchText
import com.progressive.kherkin.compose.steps.testcore.ComposeNavigable
import com.progressive.kherkin.compose.steps.testcore.ComposePathSegment
import com.progressive.kherkin.espresso.testcore.Navigable
import com.progressive.kherkin.espresso.testcore.PathSegment
import com.progressive.kherkin.sampleapp.R
import com.progressive.sampleapp.activities.xml.TextFieldActivity
import com.progressive.sampleapp.screens.compose.BasicComposeScreen

class TextFieldScreen : Screen(), Navigable, ComposeNavigable {

    override lateinit var activityScenario: ActivityScenario<TextFieldActivity>
    override val trait: Trait = Trait(R.id.editTextEnabledLeft)
    override fun screenActivityClass(): Class<out Activity> = TextFieldActivity::class.java

    override fun startMyActivity() {
        activityScenario = ActivityScenario.launch(TextFieldActivity::class.java)
    }

    override fun pathsToScreen(): List<PathSegment> = emptyList()

    override fun pathsToScreen(composeTestRule: ComposeTestRule): List<ComposePathSegment> {
        val pathSegments = mutableListOf<ComposePathSegment>()
        pathSegments.add(
            ComposePathSegment(
                BasicComposeScreen(),
                this,
                {
                    When.ITouchText("Navigate", composeTestRule)
                }
            )
        )
        return pathSegments
    }
}