package com.twopiradrian.auraluna

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import com.twopiradrian.auraluna.core.container.AppContainer
import com.twopiradrian.auraluna.ui.theme.AuralunaTheme
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.twopiradrian.auraluna.core.navigation.AppScreenManager
import com.twopiradrian.auraluna.ui.utils.ScreenType

class MainActivity : ComponentActivity() {

    private lateinit var appContainer: AppContainer

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        appContainer = AppContainer(this)

        setContent {
            AuralunaTheme {
                val windowSize = calculateWindowSizeClass(this)
                val screenType = when(windowSize.widthSizeClass){
                    WindowWidthSizeClass.Compact -> {
                        ScreenType.Mobile
                    }

                    WindowWidthSizeClass.Medium -> {
                        ScreenType.Foldable
                    }

                    WindowWidthSizeClass.Expanded -> {
                        ScreenType.Tablet
                    }
                    else -> {
                        ScreenType.Mobile
                    }
                }

                AppScreenManager(appContainer, screenType)
            }
        }
    }
}