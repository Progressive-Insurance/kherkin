package com.progressive.sampleapp.activities.xml

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.progressive.kherkin.sampleapp.R
import com.progressive.sampleapp.fragments.AlertFragment
import com.progressive.sampleapp.fragments.DatePickerFragment
import com.progressive.sampleapp.fragments.DateSpinnerFragment
import com.progressive.sampleapp.fragments.MainFragment
import com.progressive.sampleapp.fragments.TimePickerFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<MainFragment>(R.id.fragment_container_view)
            }
        }
    }

    @Suppress("UNUSED_PARAMETER")
    fun showAlertDialog(v: View) {
        AlertFragment().show(supportFragmentManager, "alertDialog")
    }

    @Suppress("UNUSED_PARAMETER")
    fun showDatePickerDialog(v: View) {
        DatePickerFragment().show(supportFragmentManager, "datePicker")
    }

    @Suppress("UNUSED_PARAMETER")
    fun showDateSpinnerDialog(v: View) {
        DateSpinnerFragment().show(supportFragmentManager, "dateSpinner")
    }

    @Suppress("UNUSED_PARAMETER")
    fun showTimePickerDialog(v: View) {
        TimePickerFragment().show(supportFragmentManager, "timePicker")
    }
}