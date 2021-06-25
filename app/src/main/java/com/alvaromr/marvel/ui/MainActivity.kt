package com.alvaromr.marvel.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.alvaromr.marvel.ui.nav.AppView
import com.alvaromr.marvel.ui.theme.MarvelCharactersTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelCharactersTheme {
                AppView()
            }
        }
    }
}

@Preview
@Composable
fun PreviewDemo() {
    AppView()
}