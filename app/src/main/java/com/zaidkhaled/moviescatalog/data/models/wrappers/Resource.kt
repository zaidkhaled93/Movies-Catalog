package com.zaidkhaled.moviescatalog.data.models.wrappers

import com.zaidkhaled.moviescatalog.common.enums.Status


data class Resource<out T>(val status: Status, val data: T?, val message: String?, val code: Int?) {
    companion object {
        fun <T> success(data: T): Resource<T> =
            Resource(status = Status.SUCCESS, data = data, message = null, code = 0)

        fun <T> error(data: T?, message: String): Resource<T> =
            Resource(status = Status.ERROR, data = data, message = message, code = 500)

        fun <T> customError(data: T?, message: String, code: Int): Resource<T> =
            Resource(status = Status.CUSTOM_ERROR, data = data, message = message, code = code)

        fun <T> loading(data: T?): Resource<T> =
            Resource(status = Status.LOADING, data = data, message = null, code = 0)
    }
}