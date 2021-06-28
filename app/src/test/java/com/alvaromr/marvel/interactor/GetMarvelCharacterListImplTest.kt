package com.alvaromr.marvel.interactor

import com.alvaromr.marvel.model.MarvelCharacter
import com.alvaromr.marvel.repository.MarvelCharacterRepository
import com.alvaromr.marvel.utils.Logger
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*
import java.util.*

@ExperimentalCoroutinesApi
class GetMarvelCharacterListImplTest {

    private lateinit var useCase: GetMarvelCharacterListImpl
    private lateinit var repository: MarvelCharacterRepository
    private lateinit var logger: Logger

    private fun marvelFakeCharacterBuilder(i: Int) =
        MarvelCharacter(
            "$i-id",
            "$i-name",
            "$i-desc",
            "fake url",
            Date(1000),
            Date(10000),
        )


    @Before
    fun setUp() {
        repository = mock()
        logger = mock()
        useCase = GetMarvelCharacterListImpl(repository, logger)
    }

    @Test
    fun testFound() = runBlockingTest {
        val characters = Array(5) { i -> marvelFakeCharacterBuilder(i) }.toList()
        whenever(repository.getMarvelCharacters(any())).thenReturn(flow {
            emit(characters)
        })

        val res = mutableListOf<Resource<out List<MarvelCharacter>>>()
        useCase(0) { resource ->
            res.add(resource)
        }
        assertEquals(Resource.Loading, res[0])
        assertEquals(Resource.Success(characters), res[1])
        assertEquals(2, res.size)
    }

    @Test
    fun testError() = runBlockingTest {
        whenever(repository.getMarvelCharacters(any())).thenReturn(flow {
            throw RuntimeException()
        })

        val res = mutableListOf<Resource<out List<MarvelCharacter>>>()
        useCase(0) { resource ->
            res.add(resource)
        }
        assertEquals(Resource.Loading, res[0])
        assertEquals(Resource.Error(GetMarvelCharacterList.MarvelCharacterListError), res[1])
        assertEquals(2, res.size)
        verify(logger, times(1)).e(any(), any())
    }
}