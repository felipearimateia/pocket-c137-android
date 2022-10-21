package br.com.dicasdoari.pocketc137.data.dto

import androidx.annotation.Keep
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class InfoDTO(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)
