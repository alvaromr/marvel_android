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
class GetMarvelCharacterById @Inject constructor(
    private val marvelCharacterRepository: MarvelCharacterRepository,
) {
    suspend operator fun invoke(
        id: String,
        block: suspend (value: Resource<out MarvelCharacter>) -> Unit,
    ) = flow {
        emit(Resource.Loading)
        marvelCharacterRepository.getMarvelCharacterById(id).catch {
            Log.e(this::class.simpleName, it.toString())
            emit(Resource.Error(type = MarvelCharacterDetailError))
        }.collect {
            if (it == null) {
                emit(Resource.Error(type = MarvelCharacterDetailNotFoundError))
            } else {
                emit(Resource.Success(it))
            }
        }
    }.collect(block)

    object MarvelCharacterDetailError : ErrorType
    object MarvelCharacterDetailNotFoundError : ErrorType

}