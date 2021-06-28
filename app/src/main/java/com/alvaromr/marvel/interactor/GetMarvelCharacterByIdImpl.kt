package com.alvaromr.marvel.interactor

import com.alvaromr.marvel.model.MarvelCharacter
import com.alvaromr.marvel.repository.MarvelCharacterRepository
import com.alvaromr.marvel.utils.Logger
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMarvelCharacterByIdImpl @Inject constructor(
    private val marvelCharacterRepository: MarvelCharacterRepository,
    private val logger: Logger,
) : GetMarvelCharacterById {
    override suspend operator fun invoke(
        id: String,
        block: suspend (value: Resource<out MarvelCharacter>) -> Unit,
    ) = flow {
        emit(Resource.Loading)
        marvelCharacterRepository.getMarvelCharacterById(id).catch {
            logger.e(GetMarvelCharacterByIdImpl::class.simpleName) { it.toString() }
            emit(Resource.Error(type = GetMarvelCharacterById.MarvelCharacterDetailError))
        }.collect {
            if (it == null) {
                emit(Resource.Error(type = GetMarvelCharacterById.MarvelCharacterDetailNotFoundError))
            } else {
                emit(Resource.Success(it))
            }
        }
    }.collect(block)
}