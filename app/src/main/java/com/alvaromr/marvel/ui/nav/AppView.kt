package com.alvaromr.marvel.ui.nav

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.alvaromr.marvel.R
import com.alvaromr.marvel.ui.detail.MarvelCharacterDetailView
import com.alvaromr.marvel.ui.list.MarvelCharacterListView
import com.alvaromr.marvel.ui.nav.DestinationKeys.MarvelCharacterDetail
import com.alvaromr.marvel.ui.nav.DestinationKeys.MarvelCharacterList
import com.alvaromr.marvel.ui.theme.Purple700

@Composable
fun AppView() {
    val navController = rememberNavController()
    val actions = remember(navController) { NavAction(navController) }

    val navBackStackEntry = navController.currentBackStackEntryAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                navigationIcon = {
                    if (navBackStackEntry.value?.destination?.route != MarvelCharacterList) {
                        IconButton(onClick = actions.navigateBack) {
                            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                        }
                    }
                },
                backgroundColor = Purple700,
                contentColor = Color.White,
                elevation = 12.dp
            )
        },
        content = {

            NavHost(
                navController = navController,
                startDestination = MarvelCharacterList
            ) {
                composable(MarvelCharacterList) {
                    MarvelCharacterListView(navigateToDetail = actions.toMarvelCharacterDetail)
                }
                composable(
                    "$MarvelCharacterDetail/{${NavActionKeys.marvelCharacterId}}",
                    arguments = listOf(navArgument(NavActionKeys.marvelCharacterId) {
                        type = NavType.StringType
                    })
                ) {
                    MarvelCharacterDetailView(
                        it.arguments?.getString(NavActionKeys.marvelCharacterId)
                    )
                }
            }
        }
    )
}