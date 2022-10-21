package br.com.dicasdoari.pocketc137.data

sealed class ResultOf<out T> {
    data class Success<out R>(val value: R): ResultOf<R>()
    data class Failure(
        val code: Int,
        val message: String? = null,
        val throwable: Throwable? = null
    ): ResultOf<Nothing>()
}
