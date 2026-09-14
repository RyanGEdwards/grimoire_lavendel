package com.project.grimoirelavendel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.project.grimoirelavendel.ui.theme.GrimoireLavendelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GrimoireLavendelTheme {
                GrimoireLavendelApp()
            }
        }
    }
}