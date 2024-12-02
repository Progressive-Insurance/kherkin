package com.progressive.sampleapp.fragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.progressive.kherkin.sampleapp.R.style
import com.progressive.sampleapp.viewmodels.MainViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar
import java.util.Locale

class DateSpinnerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(
            requireContext(),
            style.SpinnerDatePickerStyle,
            this,
            year,
            month,
            day
        )
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val resultDate: Date = GregorianCalendar(year, month, dayOfMonth).time
        val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.US)

        mainViewModel.dateSpinnerDate = formatter.format(resultDate)
        mainViewModel.setDateSpinnerDate()
    }
}