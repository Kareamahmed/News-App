package com.example.newsapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.newsapp.presentation.navigation.NavGraph
import com.example.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    // this way to create viewModel in Activity
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.splashCondition
            }
        }

        // Don’t automatically push my app content below the status bar بعني لما اخليها false كده ال UI بتاخد حجم الشاشه كلها
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            NewsAppTheme {

                SetSystemBarsColor()

                Box(
                    Modifier
                        .background(color = MaterialTheme.colorScheme.background)
                        .fillMaxSize()
                ) {
                    NavGraph(startDestination = viewModel.startDestination)
                }
            }
        }
    }
}

@SuppressLint("ContextCastToActivity")
@Composable
private fun SetSystemBarsColor() {
    val view = LocalView.current
    val activity = LocalContext.current as ComponentActivity
    val isDarkTheme = isSystemInDarkTheme()

    SideEffect {
        val window = activity.window
        val controller = WindowInsetsControllerCompat(window, view)

        window.statusBarColor = Color.Transparent.toArgb()

        controller.isAppearanceLightStatusBars = !isDarkTheme

    }
}
