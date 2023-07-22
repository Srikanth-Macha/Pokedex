package com.application.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import com.application.pokedex.screens.NavGraphs
import com.application.pokedex.ui.theme.PokedexTheme
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PokedexTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val rootAnimations = RootNavGraphDefaultAnimations(
                        enterTransition = {
                            slideIn(animationSpec = tween(durationMillis = 500)) {
                                IntOffset(x = 300, y = 0)
                            }
                        },
                        exitTransition = {
                            slideOut(animationSpec = tween(durationMillis = 500)) {
                                IntOffset.Zero
                            }
                        }
                    )

                    val navHostEngine =
                        rememberAnimatedNavHostEngine(rootDefaultAnimations = rootAnimations)
                    DestinationsNavHost(navGraph = NavGraphs.root, engine = navHostEngine)
                }
            }
        }
    }
}