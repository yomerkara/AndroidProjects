package com.arthas.lefrango.service.util

import android.content.Context
import androidx.annotation.WorkerThread
import com.arthas.lefrango.R
import com.arthas.lefrango.core.Constants
import com.arthas.lefrango.util.NetworkManager
import com.arthas.lefrango.util.extensions.dialog
import com.arthas.lefrango.util.extensions.getStr
import com.arthas.lefrango.util.extensions.noNetworkConnectivityError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

object NetworkHandler {

    @WorkerThread
    suspend fun <T> sendRequest(
        context: Context,
        request: suspend () -> Response<T>,
        isAsync: Boolean = false
    ): AppResult<T> {
        return withContext(Dispatchers.IO) {
            if (NetworkManager.isNetworkAvailable(context)) {
                if (!isAsync) {
                    Constants.App.lastActivity?.showLoading()
                }
                try {
                    val response = request.invoke()
                    if (!isAsync) {
                        Constants.App.lastActivity?.hideLoading()
                    }
                    if (response.isSuccessful) {
                        handleSuccess(response)
                    } else {
                        handleApiError(response)
                    }
                } catch (e: Exception) {
                    if (!isAsync) {
                        with(Constants.App.lastActivity) {
                            this?.hideLoading()
                            this?.runOnUiThread {
                                dialog(context.getStr(R.string.error_occurred)) {
                                    positiveButton(context.getStr(R.string.ok)) { }
                                }.show()
                            }
                        }
                    }
                    AppResult.Error(e)
                }
            } else {
                context.noNetworkConnectivityError()
            }
        }
    }

    fun <T> handleApiError(response: Response<T>): AppResult.Error {
        val error = ApiErrorUtils.parseError(response)
        with(Constants.App.lastActivity) {
            this?.runOnUiThread {
                dialog(error.message) {
                    positiveButton(getStr(R.string.ok)) { }
                }.show()
            }
        }
        return AppResult.Error(Exception(error.message))
    }

    fun <T> handleSuccess(response: Response<T>): AppResult<T> {
        response.body()?.let {
            return AppResult.Success(it)
        } ?: return handleApiError(response)
    }
}