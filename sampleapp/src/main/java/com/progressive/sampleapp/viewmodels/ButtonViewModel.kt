package com.progressive.sampleapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.progressive.sampleapp.models.ButtonModel

class ButtonViewModel : ViewModel() {

    private val model = ButtonModel(clicks = 0)

    val uiClicksLiveData = MutableLiveData<Int>()

    fun setUpdatedClicks() {
        uiClicksLiveData.postValue(model.clicks++)
    }
}