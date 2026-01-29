package com.arflix.tv.util

/**
 * A sealed class that encapsulates successful outcomes with a value of type [T]
 * or a failure with an [AppException].
 *
 * Use this for repository methods instead of returning nullable types or throwing exceptions.
 *
 * Example:
 * ```
 * suspend fun getMovie(id: Int): Result<Movie> {
 *     return try {
 *         val movie = api.getMovie(id)
 *         Result.Success(movie)
 *     } catch (e: Exception) {
 *         Result.Error(e.toAppException())
 *     }
 * }
 * ```
 */
sealed class Result<out T> {
    /**
     * Represents a successful operation with [data].
     */
    data class Success<out T>(val data: T) : Result<T>()

    /**
     * Represents a failed operation with [exception].
     */
    data class Error(val exception: AppException) : Result<Nothing>()

    /**
     * Returns true if this is a [Success].
     */
    val isSuccess: Boolean get() = this is Success

    /**
     * Returns true if this is an [Error].
     */
    val isError: Boolean get() = this is Error

    /**
     * Returns the encapsulated value if this is [Success], or null otherwise.
     */
    fun getOrNull(): T? = when (this) {
        is Success -> data
        is Error -> null
    }

    /**
     * Returns the encapsulated exception if this is [Error], or null otherwise.
     */
    fun exceptionOrNull(): AppException? = when (this) {
        is Success -> null
        is Error -> exception
    }

    /**
     * Returns the encapsulated value if this is [Success], or [default] otherwise.
     */
    fun getOrDefault(default: @UnsafeVariance T): T = when (this) {
        is Success -> data
        is Error -> default
    }

    /**
     * Returns the encapsulated value if this is [Success], or the result of [onError] otherwise.
     */
    inline fun getOrElse(onError: (AppException) -> @UnsafeVariance T): T = when (this) {
        is Success -> data
        is Error -> onError(exception)
    }

    /**
     * Transforms the encapsulated value if this is [Success].
     */
    inline fun <R> map(transform: (T) -> R): Result<R> = when (this) {
        is Success -> Success(transform(data))
        is Error -> this
    }

    /**
     * Transforms the encapsulated value if this is [Success], allowing the transform to return a Result.
     */
    inline fun <R> flatMap(transform: (T) -> Result<R>): Result<R> = when (this) {
        is Success -> transform(data)
        is Error -> this
    }

    /**
     * Performs the given [action] on the encapsulated value if this is [Success].
     * Returns the original Result unchanged.
     */
    inline fun onSuccess(action: (T) -> Unit): Result<T> {
        if (this is Success) action(data)
        return this
    }

    /**
     * Performs the given [action] on the encapsulated exception if this is [Error].
     * Returns the original Result unchanged.
     */
    inline fun onError(action: (AppException) -> Unit): Result<T> {
        if (this is Error) action(exception)
        return this
    }

    companion object {
        /**
         * Creates a [Success] with the given [value].
         */
        fun <T> success(value: T): Result<T> = Success(value)

        /**
         * Creates an [Error] with the given [exception].
         */
        fun <T> error(exception: AppException): Result<T> = Error(exception)

        /**
         * Creates an [Error] from a generic [Throwable].
         */
        fun <T> error(throwable: Throwable): Result<T> = Error(throwable.toAppException())

        /**
         * Creates an [Error] with a [message].
         */
        fun <T> error(message: String): Result<T> = Error(AppException.Unknown(message))
    }
}

/**
 * Converts a Throwable to an [AppException].
 */
fun Throwable.toAppException(): AppException = when (this) {
    is AppException -> this
    is java.net.UnknownHostException -> AppException.Network("No internet connection", this)
    is java.net.SocketTimeoutException -> AppException.Network("Connection timed out", this)
    is java.net.ConnectException -> AppException.Network("Could not connect to server", this)
    is javax.net.ssl.SSLException -> AppException.Network("Secure connection failed", this)
    is retrofit2.HttpException -> {
        when (code()) {
            401 -> AppException.Auth("Session expired. Please sign in again.", this)
            403 -> AppException.Auth("Access denied", this)
            404 -> AppException.Server("Not found", code(), this)
            in 500..599 -> AppException.Server("Server error. Please try again later.", code(), this)
            else -> AppException.Server("Request failed", code(), this)
        }
    }
    else -> AppException.Unknown(message ?: "An unexpected error occurred", this)
}

/**
 * Wraps a suspend function in a Result, catching any exceptions.
 */
suspend inline fun <T> runCatching(block: () -> T): Result<T> {
    return try {
        Result.Success(block())
    } catch (e: Exception) {
        Result.Error(e.toAppException())
    }
}
