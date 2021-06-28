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
class GetMarvelCharacterListImpl @Inject constructor(
    private val marvelCharacterRepository: MarvelCharacterRepository,
    private val logger: Logger,
) : GetMarvelCharacterList {

    override suspend operator fun invoke(
        offset: Int,
        block: suspend (value: Resource<out List<MarvelCharacter>>) -> Unit,
    ) = flow {
        emit(Resource.Loading)
        marvelCharacterRepository.getMarvelCharacters(offset).catch {
            logger.e(GetMarvelCharacterListImpl::class.simpleName) { it.toString() }
            emit(Resource.Error(type = GetMarvelCharacterList.MarvelCharacterListError))
        }.collect {
            emit(Resource.Success(it))
        }
    }.collect(block)

}