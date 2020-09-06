package com.matterox.dictionary.data.network

data class ErrorDataModel(
    val title: String,
    val status: String,
    val detail: String
) {
    companion object {
        val EMPTY = ErrorDataModel(
            title = "",
            status = "",
            detail = ""
        )
    }
}