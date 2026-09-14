package com.project.grimoirelavendel.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.HourglassBottom
import androidx.compose.material.icons.filled.SyncAlt
import androidx.compose.ui.graphics.vector.ImageVector

enum class AppDestinations(
    val label: String,
    val icon: ImageVector,
) {
    DICTIONARY("Dictionary", icon = Icons.Default.Book),
    TRANSLATE("Translate", icon = Icons.Default.SyncAlt),
    STUDY("Study", icon = Icons.Default.HourglassBottom),
}