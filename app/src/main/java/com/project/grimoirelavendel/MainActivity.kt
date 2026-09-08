package com.project.grimoirelavendel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.HourglassBottom
import androidx.compose.material.icons.filled.SyncAlt
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.project.grimoirelavendel.Dictionary.DictionaryVM
import com.project.grimoirelavendel.SideMenu.SideMenu
import com.project.grimoirelavendel.Study.StudyVM
import com.project.grimoirelavendel.TopNavBar.TopNavBar
import com.project.grimoirelavendel.Translate.TranslateVM
import com.project.grimoirelavendel.ui.theme.GrimoireLavendelTheme
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
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@PreviewScreenSizes
@Composable
fun GrimoireLavendelApp() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.DICTIONARY) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { SideMenu() }
    ) {
        NavigationSuiteScaffold(
            navigationSuiteItems = {
                AppDestinations.entries.forEach {
                    item(
                        icon = {
                            Icon(
                                imageVector = it.icon,
                                contentDescription = it.label
                            )
                        },
                        label = { Text(it.label) },
                        selected = it == currentDestination,
                        onClick = { currentDestination = it }
                    )
                }
            }
        ) {
            Scaffold(
                topBar = {
                    TopNavBar(
                        title = currentDestination.label,
                        onMenuClick = {
                            scope.launch { drawerState.open() }
                        }
                    )
                }
            ) { innerPadding ->
                AnimatedContent(
                    targetState = currentDestination,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    transitionSpec = {
                        // Determine slide direction based on enum position comparison
                        val isMovingForward = targetState.ordinal > initialState.ordinal

                        if (isMovingForward) {
                            // New screen slides in from right, old screen slides out to left
                            (slideInHorizontally(animationSpec = tween(300)) { width -> width } + fadeIn()) togetherWith
                                    (slideOutHorizontally(animationSpec = tween(300)) { width -> -width } + fadeOut())
                        } else {
                            // New screen slides in from left, old screen slides out to right
                            (slideInHorizontally(animationSpec = tween(300)) { width -> -width } + fadeIn()) togetherWith
                                    (slideOutHorizontally(animationSpec = tween(300)) { width -> width } + fadeOut())
                        }
                    },
                ) { destination ->
                    when (destination) {
                        AppDestinations.DICTIONARY -> DictionaryVM()
                        AppDestinations.TRANSLATE -> TranslateVM()
                        AppDestinations.STUDY -> StudyVM()
                    }
                }
            }
        }
    }
}

enum class AppDestinations(
    val label: String,
    val icon: ImageVector,
) {
    DICTIONARY("Dictionary", icon = Icons.Default.Book),
    TRANSLATE("Translate", icon = Icons.Default.SyncAlt),
    STUDY("Study", icon = Icons.Default.HourglassBottom),
}