package com.project.grimoirelavendel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.project.grimoirelavendel.data.newData.GrimoireDB
import com.project.grimoirelavendel.ui.theme.GrimoireLavendelTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GrimoireLavendelTheme {
                GrimoireLavendelApp()
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            GrimoireDB.startup(applicationContext)
        }
    }
}
