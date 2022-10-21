package br.com.dicasdoari.pocketc137.data.repository

import br.com.dicasdoari.pocketc137.data.ResultOf
import br.com.dicasdoari.pocketc137.data.dto.CharacterDTO

interface CharacterRepository {
    suspend fun getCharacters(): ResultOf<List<CharacterDTO>>
}