package com.matterox.dictionary.extentions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun <T> throttleLatest(
    intervalMs: Long = 500L,
    scope: CoroutineScope,
    destinationFunction: (T) -> Unit
): (T) -> Unit {
    var throttleJob: Job? = null
    var latestParam: T
    return { param: T ->
        latestParam = param
        if (throttleJob?.isCompleted != false) {
            throttleJob = scope.launch {
                delay(intervalMs)
                latestParam.let(destinationFunction)
            }
        }
    }
}