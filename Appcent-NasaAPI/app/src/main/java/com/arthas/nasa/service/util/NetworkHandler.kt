package com.arthas.nasa.service.util

import android.content.Context
import androidx.annotation.WorkerThread
import com.arthas.nasa.R
import com.arthas.nasa.core.Constants
import com.arthas.nasa.core.SharedPref
import com.arthas.nasa.service.api.AppApi
import com.arthas.nasa.util.NetworkManager
import com.arthas.nasa.util.extensions.dialog
import com.arthas.nasa.util.extensions.getStr
import com.arthas.nasa.util.extensions.noNetworkConnectivityError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

object NetworkHandler {

    @WorkerThread
    suspend fun <T> sendRequest(
        context: Context,
        appApi: AppApi,
        sharedPref: SharedPref,
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