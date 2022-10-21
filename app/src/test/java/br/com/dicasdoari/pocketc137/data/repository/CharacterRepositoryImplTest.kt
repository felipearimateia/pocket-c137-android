package br.com.dicasdoari.pocketc137.data.repository

import br.com.dicasdoari.pocketc137.data.ResultOf
import br.com.dicasdoari.pocketc137.data.network.API
import br.com.dicasdoari.pocketc137.getCharactersMock
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterRepositoryImplTest {

    private val api: API = mockk()

    @Test
    fun `recuperar personagens na api`() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        val responseMock = getCharactersMock()

        //utilizamos o every para configurar o retorno das funções do objeto mock
        coEvery { api.getCharacters() } returns Response.success(responseMock)

        val repository = CharacterRepositoryImpl(api, dispatcher)
        val result = repository.getCharacters()

        //Verificar se as funções foram executadas
        coVerify { api.getCharacters() }

        assertEquals(responseMock.results, (result as ResultOf.Success).value)
    }

    @Test
    fun `retornar o status 404 do servidor`() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)

        coEvery { api.getCharacters() } returns Response.error(404, byteArrayOf().toResponseBody())

        val repository = CharacterRepositoryImpl(api, dispatcher)
        val result = repository.getCharacters()


        coVerify { api.getCharacters() }
        assertEquals(404, (result as ResultOf.Failure).code)

    }

    @Test
    fun `retornar uma exception quando recuperar os personagens`() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)

        coEvery { api.getCharacters() } throws IOException()

        val repository = CharacterRepositoryImpl(api, dispatcher)
        val result = repository.getCharacters()

        coVerify { api.getCharacters() }
        assertEquals(500, (result as ResultOf.Failure).code)
    }


}