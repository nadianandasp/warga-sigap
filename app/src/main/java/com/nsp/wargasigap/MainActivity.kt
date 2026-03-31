package com.nsp.wargasigap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.nsp.wargasigap.ui.theme.WargaSigapTheme
import androidx.navigation.compose.*
import com.nsp.wargasigap.navigation.AppNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WargaSigapTheme {
                val navController = rememberNavController()

                AppNavHost(navController)
                }
            }
        }
    }

