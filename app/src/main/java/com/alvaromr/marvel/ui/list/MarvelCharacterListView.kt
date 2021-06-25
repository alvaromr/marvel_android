package com.alvaromr.marvel.ui.list

import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alvaromr.marvel.R
import com.alvaromr.marvel.model.MarvelCharacter
import com.alvaromr.marvel.ui.common.MarvelCharacterImage
import com.alvaromr.marvel.ui.nav.DestinationKeys
import com.alvaromr.marvel.ui.theme.Purple700
import com.alvaromr.marvel.ui.theme.Teal200
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun MarvelCharacterListView(
    navigateToDetail: (marvelCharacter: MarvelCharacter) -> Unit,
    marvelCharacterListViewModel: MarvelCharacterListViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    val viewState = marvelCharacterListViewModel.viewState.collectAsState()

    val onMarvelCharacterSelected = { marvelCharacter: MarvelCharacter ->
        marvelCharacterListViewModel.onAction(
            MarvelCharacterListUIAction.MarvelCharacterSelected(
                marvelCharacter
            )
        )
    }

    LaunchedEffect(DestinationKeys.MarvelCharacterDetail) {
        marvelCharacterListViewModel.oneShotEvents
            .onEach {
                when (it) {
                    is MarvelCharacterListOneShotEvent.NavigateToMarvelCharacterDetail -> navigateToDetail(
                        it.marvelCharacter
                    )
                    MarvelCharacterListOneShotEvent.Error.MarvelCharacterList -> {
                        Toast.makeText(context, R.string.generic_error, LENGTH_SHORT).show()
                    }
                }
            }
            .collect()
    }
    val value = viewState.value
    if (value.loading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }

    val list = value.viewState.marvelCharacterList
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
        itemsIndexed(list) { i, item ->
            if (i >= list.lastIndex) {
                marvelCharacterListViewModel.onAction(
                    MarvelCharacterListUIAction.ListScrolledToEndPosition(i)
                )
            }
            MarvelCharacterRow(
                marvelCharacter = item,
                navigateToDetail = onMarvelCharacterSelected
            )
        }
    }
}

@Composable
fun MarvelCharacterRow(
    marvelCharacter: MarvelCharacter,
    navigateToDetail: (MarvelCharacter) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row(modifier = Modifier.clickable { navigateToDetail(marvelCharacter) }) {
            MarvelCharacterImage(marvelCharacter = marvelCharacter)
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = marvelCharacter.name,
                    style = MaterialTheme.typography.h6,
                    color = Purple700
                )
                Text(
                    text = stringResource(id = R.string.view_detail),
                    style = MaterialTheme.typography.caption,
                    color = Teal200
                )
            }
        }
    }
}