package com.progressive.sampleapp.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.progressive.kherkin.sampleapp.R
import com.progressive.sampleapp.viewmodels.MainViewModel

class AlertFragment : DialogFragment() {

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(getString(R.string.alert_title))
        builder.setMessage(getString(R.string.alert_message))
        builder.apply {
            setPositiveButton(getString(R.string.alert_ok)) { _, _ ->
                mainViewModel.bannerText = getString(R.string.text_view_banner)
                mainViewModel.setBannerText()
                mainViewModel.alertButtonText = getString(R.string.alert_button_ok_clicked)
                mainViewModel.setAlertButtonText()
            }
            setNegativeButton(getString(R.string.alert_cancel)) { _, _ ->
                mainViewModel.bannerText = getString(R.string.text_view_banner_error)
                mainViewModel.setBannerText()
                mainViewModel.alertButtonText = getString(R.string.alert_button_cancel_clicked)
                mainViewModel.setAlertButtonText()
            }
        }

        return builder.create()
    }
}