package com.matterox.dictionary.data.network

import com.squareup.moshi.Moshi
import retrofit2.Response

class RequestHandler(
    val moshi: Moshi
) {
    inline fun <reified SuccessDataModel, reified SuccessDomainModel> safeRequest(
        response: () -> Response<SuccessDataModel>,
        successTransform: (SuccessDataModel) -> SuccessDomainModel
    ): Result<String, SuccessDomainModel> {
        return safeRequest(
            response = response,
            successTransform = successTransform,
            failure = { it }
        )
    }

    inline fun <reified SuccessDataModel, reified SuccessDomainModel, Failure> safeRequest(
        response: () -> Response<SuccessDataModel>,
        successTransform: (SuccessDataModel) -> SuccessDomainModel,
        failure: (String) -> Failure
    ): Result<Failure, SuccessDomainModel> {
        return try {
            val result = response.invoke()
            when (result.isSuccessful) {
                true -> Result.Success(
                    successTransform((result.body() ?: Any() as SuccessDataModel))
                )
                false -> {
                        val errorBody = runCatching {
                            result.errorBody()!!.string()
                        }.getOrDefault("")

                        val responseError = moshi.adapter(ErrorDataModel::class.java)
                            .runCatching { fromJson(errorBody) }
                            .getOrDefault (ErrorDataModel.EMPTY )

                    Result.Failure(failure(
                        "${responseError?.title}: ${responseError?.status} - ${responseError?.detail}"
                    ))
                }
            }
        } catch (throwable: Throwable) {
            Result.Failure(failure(throwable.localizedMessage ?: "Unknown error"))
        }
    }
}