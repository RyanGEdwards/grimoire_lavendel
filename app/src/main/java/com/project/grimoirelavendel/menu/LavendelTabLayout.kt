package com.project.grimoirelavendel.menu

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.project.grimoirelavendel.dictionary.newDict.view.DictionaryTab
import com.project.grimoirelavendel.network.DeepLRepository
import com.project.grimoirelavendel.study.StudyVM
import com.project.grimoirelavendel.translate.TranslateVM

@Composable
fun LavendelTabLayout (
    pagerState: PagerState,
    contentPadding: PaddingValues,
    deepl: DeepLRepository,
) {
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.padding(contentPadding),
        userScrollEnabled = false,
        verticalAlignment = Alignment.Top
    ) { tab ->
        when (tab) {
            0 -> DictionaryTab()
            1 -> TranslateVM(
                repo = deepl
            )
            2 -> StudyVM()
        }
    }
}