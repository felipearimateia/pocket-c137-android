package br.com.dicasdoari.pocketc137

import androidx.lifecycle.Observer
import br.com.dicasdoari.pocketc137.data.dto.CharacterDTO
import br.com.dicasdoari.pocketc137.data.dto.ResponseDTO
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.mockk.spyk
import java.lang.reflect.Type
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*

fun <T> parseJson(jsonPath: String, type: Type): T {
    val resourceDirectory: Path = Paths.get("src", "test", "resources", jsonPath)
    val value = Scanner(resourceDirectory, "UTF-8").useDelimiter("\\A").next()
    val adapter: JsonAdapter<T> = createMoshi().adapter(type)
    return adapter.fromJson(value)!!
}

private fun createMoshi() =  Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()

fun <T> createMockObserver(): Observer<T> {
    val observer = Observer<T> { }
    return spyk(observer)
}

fun getCharactersMock() =
    parseJson<ResponseDTO<CharacterDTO>>(
        "character.json",
        Types.newParameterizedType(ResponseDTO::class.java, CharacterDTO::class.java)
    )