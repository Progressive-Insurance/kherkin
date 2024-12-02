package com.progressive.sampleapp.screens.espresso

import android.app.Activity
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import com.progressive.kherkin.sampleapp.R
import com.progressive.kherkin.common.screen.Screen
import com.progressive.kherkin.common.screen.Trait
import com.progressive.kherkin.common.testcore.Navigable
import com.progressive.kherkin.common.testcore.PathSegment
import com.progressive.sampleapp.activities.xml.MainActivity
import com.progressive.sampleapp.steps.MainScreenDestination
import com.progressive.sampleapp.viewmodels.MainViewModel

class MainScreen : Screen(), Navigable {

    override lateinit var activityScenario: ActivityScenario<MainActivity>
    override val trait: Trait = Trait(R.id.textView)
    override fun screenActivityClass(): Class<out Activity> = MainActivity::class.java

    override fun startMyActivity() {

        val intent = Intent(
            ApplicationProvider.getApplicationContext(),
            MainActivity::class.java).apply {
                putExtra(MainViewModel.DESTINATION, MainScreenDestination.mainDestination)
        }

        activityScenario = ActivityScenario.launch(intent)
    }

    override fun pathsToScreen(): List<PathSegment> = emptyList()
}