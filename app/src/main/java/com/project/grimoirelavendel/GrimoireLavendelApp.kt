package com.project.grimoirelavendel

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.project.grimoirelavendel.data.enums.AppDestinations
import com.project.grimoirelavendel.menu.LavendelBottomNavBar
import com.project.grimoirelavendel.menu.LavendelSideMenu
import com.project.grimoirelavendel.menu.LavendelTabLayout
import com.project.grimoirelavendel.menu.LavendelTopNavBar
import com.project.grimoirelavendel.network.DeepLRepository
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@PreviewScreenSizes
@Composable
fun GrimoireLavendelApp() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val pagerState = rememberPagerState(pageCount = { AppDestinations.entries.size })
    val coroutineScope = rememberCoroutineScope()
    val deepl = DeepLRepository()

    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.DICTIONARY) }

    ModalNavigationDrawer(
        drawerContent = { LavendelSideMenu() },
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen
    ) {
        LavendelBottomNavBar(
            currentDestination = currentDestination,
            onClick = {
                currentDestination = it
                coroutineScope.launch {
                    pagerState.animateScrollToPage(it.ordinal)
                }
            }
        ) {
            Scaffold(
                topBar = {
                    LavendelTopNavBar(
                        title = currentDestination.label,
                        onMenuClick = {
                            coroutineScope.launch { drawerState.open() }
                        }
                    )
                }
            ) { innerPadding ->
                val layoutDirection = LocalLayoutDirection.current
                val safeDrawing = WindowInsets.safeDrawing.asPaddingValues()
                
                // Calculate the maximum horizontal padding to ensure symmetry
                val maxHorizontalPadding = maxOf(
                    safeDrawing.calculateStartPadding(layoutDirection),
                    safeDrawing.calculateEndPadding(layoutDirection)
                )

                LavendelTabLayout(
                    pagerState = pagerState,
                    contentPadding = PaddingValues(
                        start = maxHorizontalPadding,
                        top = innerPadding.calculateTopPadding(),
                        end = maxHorizontalPadding,
                        bottom = innerPadding.calculateBottomPadding()
                    ),
                    deepl = deepl
                )
            }
        }
    }
}