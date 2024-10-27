package com.example.zealcasestudy

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.glance.LocalContext

@Composable
@ReadOnlyComposable
fun stringResource(@StringRes id: Int): String {
    return LocalContext.current.getString(id)
}