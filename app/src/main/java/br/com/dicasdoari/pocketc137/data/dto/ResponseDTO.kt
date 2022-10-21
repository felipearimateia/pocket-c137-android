package br.com.dicasdoari.pocketc137.data.dto

import androidx.annotation.Keep
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class ResponseDTO<T>(
    val info: InfoDTO,
    val results: List<T>
)
