package com.progressive.sampleapp.setup

import com.progressive.kherkin.common.injection.BaseIntegrationFrameworkComponent
import com.progressive.kherkin.common.screen.IntegrationSetupInterface
import com.progressive.kherkin.common.screen.Screen
import javax.inject.Inject

class IntegrationSetup @Inject constructor() : IntegrationSetupInterface {
    override fun startActivity() {
        Screen.myCurrentScreen.startMyActivity()
    }

    override fun getStartScreen(): Screen {
        return Screen.myCurrentScreen
    }
}

class BaseIntegrationComponent : BaseIntegrationFrameworkComponent {
    override fun integrationSetup(): IntegrationSetupInterface {
        return IntegrationSetup()
    }
}