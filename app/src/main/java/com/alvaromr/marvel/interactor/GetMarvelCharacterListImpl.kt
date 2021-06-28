package com.alvaromr.marvel.interactor

import android.util.Log
import com.alvaromr.marvel.model.MarvelCharacter
import com.alvaromr.marvel.repository.MarvelCharacterRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMarvelCharacterListImpl @Inject constructor(
    private val marvelCharacterRepository: MarvelCharacterRepository,
) : GetMarvelCharacterList {

    override suspend operator fun invoke(
        offset: Int,
        block: suspend (value: Resource<out List<MarvelCharacter>>) -> Unit,
    ) = flow {
        emit(Resource.Loading)
        marvelCharacterRepository.getMarvelCharacters(offset).catch {
            Log.e(this::class.simpleName, it.toString())
            emit(Resource.Error(type = GetMarvelCharacterList.MarvelCharacterListError))
        }.collect {
            emit(Resource.Success(it))
        }
    }.collect(block)

}