package com.project.grimoirelavendel.menu

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.project.grimoirelavendel.dictionary.DictionaryVM
import com.project.grimoirelavendel.study.StudyVM
import com.project.grimoirelavendel.translate.TranslateVM

@Composable
fun LavendelTabLayout (
    pagerState: PagerState,
    contentPadding: PaddingValues
) {
    HorizontalPager(
        state = pagerState,
        contentPadding = contentPadding,
        modifier = Modifier,
        userScrollEnabled = false
    ) { tab ->
        when (tab) {
            0 -> DictionaryVM()
            1 -> TranslateVM()
            2 -> StudyVM()
        }
    }
}