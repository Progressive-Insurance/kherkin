package com.progressive.kherkin.common.injection

import com.progressive.kherkin.common.screen.IntegrationSetupInterface

object BaseIntegrationComponentHolder {
    lateinit var component: BaseIntegrationFrameworkComponent
}

interface BaseIntegrationFrameworkComponent {
    fun integrationSetup(): IntegrationSetupInterface
}