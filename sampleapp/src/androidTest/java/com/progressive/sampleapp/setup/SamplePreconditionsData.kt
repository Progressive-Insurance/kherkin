package com.progressive.sampleapp.setup

import com.progressive.kherkin.common.testcore.PreconditionsData
import com.progressive.sampleapp.viewmodels.Destinations

object SamplePreconditionsData {

    val destinationKey = "destination"
    val verticalKey = "vertical"

    fun addDestination(destination: Destinations) {
        PreconditionsData.addCondition(destinationKey, destination)
    }

    fun setVertical() {
        PreconditionsData.addCondition(verticalKey, true)
    }
}