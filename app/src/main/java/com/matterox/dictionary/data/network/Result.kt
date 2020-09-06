package com.matterox.dictionary.data.network

sealed class Result<out L, out R> {
    data class Failure<out L>(val error: L) : Result<L, Nothing>()
    data class Success<out R>(val data: R) : Result<Nothing, R>()

    val isSuccess get() = this is Success<R>
    val isFailure get() = this is Failure<L>

    fun <L> failure(a: L) = Failure(a)
    fun <R> success(b: R) = Success(b)

    fun fold(failure: (L) -> Unit = {}, success: (R) -> Unit = {}): Unit =
        when (this) {
            is Failure -> failure(error)
            is Success -> success(data)
        }
}