package com.alvaromr.marvel.ui.nav

import androidx.navigation.NavHostController
import com.alvaromr.marvel.model.MarvelCharacter
import com.alvaromr.marvel.ui.nav.DestinationKeys.MarvelCharacterDetail

class NavAction(navController: NavHostController) {
    val toMarvelCharacterDetail: (marvelCharacter: MarvelCharacter) -> Unit = { marvelCharacter ->
        navController.navigate("${MarvelCharacterDetail}/${marvelCharacter.id}")
    }
    val navigateBack: () -> Unit = {
        navController.popBackStack()
    }
}