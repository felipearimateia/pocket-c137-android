package br.com.dicasdoari.pocketc137.data.network

import br.com.dicasdoari.pocketc137.data.dto.CharacterDTO
import br.com.dicasdoari.pocketc137.data.dto.ResponseDTO
import retrofit2.Response
import retrofit2.http.GET

interface API {
    @GET("/api/character")
    suspend fun getCharacters(): Response<ResponseDTO<CharacterDTO>>
}