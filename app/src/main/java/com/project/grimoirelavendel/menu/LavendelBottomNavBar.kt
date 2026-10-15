package com.project.grimoirelavendel.menu

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import com.project.grimoirelavendel.data.enums.AppDestinations

@Composable
fun LavendelBottomNavBar (
    currentDestination: AppDestinations,
    onClick: (AppDestinations) -> Unit,
    content: @Composable () -> Unit
) {
    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach { destination ->
                item(
                    icon = {
                        Icon(
                            imageVector = destination.icon,
                            contentDescription = destination.label
                        )
                    },
                    label = { Text(destination.label) },
                    selected = destination == currentDestination,
                    onClick = { onClick(destination) }
                )
            }
        },
        content = content
    )
}