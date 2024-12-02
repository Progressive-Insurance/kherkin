package com.progressive.sampleapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.progressive.sampleapp.models.DataModel

enum class Destinations {
    List,
    ViewPager
}

class MainViewModel : ViewModel() {

    companion object {
        const val DESTINATION = "destination"
    }

    private val model = DataModel(clear = "", clicks = 0)

    val uiBannerLiveData = MutableLiveData<String>()
    val uiTextLiveData = MutableLiveData<String>()
    val uiIntLiveData = MutableLiveData<Int>()
    val uiAlertButtonTextLiveData = MutableLiveData<String>()
    val datePickerTextLiveData = MutableLiveData<String>()
    val dateSpinnerTextLiveData = MutableLiveData<String>()
    val timePickerTextLiveData = MutableLiveData<String>()
    val destinationLiveData = MutableLiveData<Destinations>()

    lateinit var bannerText: String
    lateinit var alertButtonText: String
    lateinit var datePickerDate: String
    lateinit var dateSpinnerDate: String
    lateinit var timePickerTime: String

    fun setBannerText() {
        uiBannerLiveData.postValue(bannerText)
    }

    fun clearText() {
        uiTextLiveData.postValue(model.clear)
    }

    fun setUpdatedClicks() {
        uiIntLiveData.postValue(model.clicks++)
    }

    fun setAlertButtonText() {
        uiAlertButtonTextLiveData.postValue(alertButtonText)
    }

    fun setDatePickerDate() {
        datePickerTextLiveData.postValue(datePickerDate)
    }

    fun setDateSpinnerDate() {
        dateSpinnerTextLiveData.postValue(dateSpinnerDate)
    }

    fun setTimePickerTime() {
        timePickerTextLiveData.postValue(timePickerTime)
    }

    fun navigate(intentDest: Destinations?) {
        destinationLiveData.postValue(intentDest ?: Destinations.List)
    }
}