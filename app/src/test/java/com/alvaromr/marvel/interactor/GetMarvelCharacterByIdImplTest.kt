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
class GetMarvelCharacterByIdImplTest {

    private lateinit var useCase: GetMarvelCharacterByIdImpl
    private lateinit var repository: MarvelCharacterRepository
    private lateinit var logger: Logger


    @Before
    fun setUp() {
        repository = mock()
        logger = mock()
        useCase = GetMarvelCharacterByIdImpl(repository, logger)
    }

    @Test
    fun testNotFound() = runBlockingTest {
        whenever(repository.getMarvelCharacterById(any())).thenReturn(flow {
            emit(null)
        })

        val res = mutableListOf<Resource<out MarvelCharacter>>()
        useCase("0") { resource ->
            res.add(resource)
        }
        assertEquals(Resource.Loading, res[0])
        assertEquals(Resource.Error(GetMarvelCharacterById.MarvelCharacterDetailNotFoundError),
            res[1])
        assertEquals(2, res.size)
    }

    @Test
    fun testFound() = runBlockingTest {
        val character = MarvelCharacter(
            "id",
            "name",
            "desc",
            "fake url",
            Date(1000),
            Date(10000),
        )
        whenever(repository.getMarvelCharacterById(any())).thenReturn(flow {
            emit(character)
        })

        val res = mutableListOf<Resource<out MarvelCharacter>>()
        useCase("0") { resource ->
            res.add(resource)
        }
        assertEquals(Resource.Loading, res[0])
        assertEquals(Resource.Success(character), res[1])
        assertEquals(2, res.size)
    }

    @Test
    fun testError() = runBlockingTest {
        whenever(repository.getMarvelCharacterById(any())).thenReturn(flow {
            throw RuntimeException()
        })

        val res = mutableListOf<Resource<out MarvelCharacter>>()
        useCase("0") { resource ->
            res.add(resource)
        }
        assertEquals(Resource.Loading, res[0])
        assertEquals(Resource.Error(GetMarvelCharacterById.MarvelCharacterDetailError), res[1])
        assertEquals(2, res.size)
        verify(logger, times(1)).e(any(), any())
    }
}