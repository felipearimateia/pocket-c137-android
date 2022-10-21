package br.com.dicasdoari.pocketc137.data.repository

import br.com.dicasdoari.pocketc137.data.ResultOf
import br.com.dicasdoari.pocketc137.data.dto.CharacterDTO
import br.com.dicasdoari.pocketc137.data.network.API
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class CharacterRepositoryImpl(private val api: API, private val dispatcher: CoroutineDispatcher): CharacterRepository {

    override suspend fun getCharacters(): ResultOf<List<CharacterDTO>> = withContext(dispatcher) {
        try {
            val response = api.getCharacters()
            if (response.isSuccessful) {
                return@withContext response.body()?.let { ResultOf.Success(it.results) } ?: ResultOf.Failure(500)
            }
            ResultOf.Failure(code = response.code(), message = response.message())
        } catch (ioe: IOException) {
            ResultOf.Failure(code = 500, message = ioe.message, throwable = ioe)
        } catch (he: HttpException) {
            ResultOf.Failure(code = 500, message = he.message, throwable = he)
        }
    }
}