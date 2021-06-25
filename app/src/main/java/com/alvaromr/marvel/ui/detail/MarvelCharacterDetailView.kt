package com.alvaromr.marvel.ui.detail

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alvaromr.marvel.R
import com.alvaromr.marvel.ui.common.MarvelCharacterImage
import com.alvaromr.marvel.ui.nav.DestinationKeys
import com.alvaromr.marvel.ui.theme.Purple700
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun MarvelCharacterDetailView(
    marvelCharacterId: String?,
    marvelCharacterDetailViewModel: MarvelCharacterDetailViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    val viewState = marvelCharacterDetailViewModel.viewState.collectAsState()
    val value = viewState.value

    val marvelCharacter = value.viewState.marvelCharacter

    LaunchedEffect(DestinationKeys.MarvelCharacterDetail) {
        marvelCharacterDetailViewModel.oneShotEvents
            .onEach {
                when (it) {
                    MarvelCharacterDetailOneShotEvent.Error.GenericError ->
                        Toast.makeText(context, R.string.generic_error, Toast.LENGTH_SHORT).show()
                    MarvelCharacterDetailOneShotEvent.Error.MarvelCharacterNotFound ->
                        Toast.makeText(context, R.string.not_found, Toast.LENGTH_SHORT).show()
                }
            }
            .collect()
    }

    LaunchedEffect(marvelCharacterId) {
        if (marvelCharacter == null && marvelCharacterId != null) {
            marvelCharacterDetailViewModel.onAction(
                MarvelCharacterDetailUIAction.LoadMarvelCharacterById(
                    marvelCharacterId
                )
            )
        }
    }

    if (value.loading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }

    if (marvelCharacter != null) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = marvelCharacter.name,
                style = MaterialTheme.typography.h5,
                color = Purple700,
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(5.dp)
            )
            Row(modifier = Modifier) {
                MarvelCharacterImage(
                    marvelCharacter = marvelCharacter,
                    modifier = Modifier.align(CenterVertically),
                    small = false
                )
                Text(
                    text = if (marvelCharacter.description.isBlank()) {
                        stringResource(id = R.string.no_description_available)
                    } else {
                        marvelCharacter.description
                    },
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .align(CenterVertically)
                        .padding(5.dp)
                )
            }

            Column(
                modifier = Modifier.padding(5.dp)
            ) {
                Text(
                    text = stringResource(R.string.modified_on),
                    style = MaterialTheme.typography.overline,

                    )
                Text(
                    text = marvelCharacter.modified.toString(),
                    style = MaterialTheme.typography.caption,

                    )
            }
            Column(
                modifier = Modifier.padding(5.dp)
            ) {
                Text(
                    text = stringResource(R.string.updated_on),
                    style = MaterialTheme.typography.overline,

                    )
                Text(
                    text = marvelCharacter.updated.toString(),
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}