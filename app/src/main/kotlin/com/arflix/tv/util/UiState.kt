package com.arflix.tv.util

/**
 * Represents the state of UI data that can be in one of four states:
 * - [Idle]: Initial state, no data loaded yet
 * - [Loading]: Data is being fetched
 * - [Success]: Data loaded successfully
 * - [Error]: Failed to load data
 *
 * Use this in ViewModels with StateFlow to represent async data states.
 *
 * Example:
 * ```
 * class HomeViewModel : ViewModel() {
 *     private val _moviesState = MutableStateFlow<UiState<List<Movie>>>(UiState.Idle)
 *     val moviesState: StateFlow<UiState<List<Movie>>> = _moviesState.asStateFlow()
 *
 *     fun loadMovies() {
 *         viewModelScope.launch {
 *             _moviesState.value = UiState.Loading
 *             repository.getMovies()
 *                 .onSuccess { _moviesState.value = UiState.Success(it) }
 *                 .onError { _moviesState.value = UiState.Error(it) }
 *         }
 *     }
 * }
 * ```
 */
sealed class UiState<out T> {
    /**
     * Initial state before any data loading has been attempted.
     */
    object Idle : UiState<Nothing>()

    /**
     * Data is currently being loaded.
     *
     * @param message Optional message to display during loading (e.g., "Loading movies...")
     */
    data class Loading(val message: String? = null) : UiState<Nothing>()

    /**
     * Data loaded successfully.
     *
     * @param data The loaded data
     */
    data class Success<out T>(val data: T) : UiState<T>()

    /**
     * Failed to load data.
     *
     * @param exception The error that occurred
     * @param retryAction Optional action to retry the failed operation
     */
    data class Error(
        val exception: AppException,
        val retryAction: (() -> Unit)? = null
    ) : UiState<Nothing>() {
        val message: String get() = exception.message
        val errorCode: String? get() = exception.errorCode
        val isRetryable: Boolean get() = exception.isRetryable()
    }

    /**
     * Returns true if this is [Loading].
     */
    val isLoading: Boolean get() = this is Loading

    /**
     * Returns true if this is [Success].
     */
    val isSuccess: Boolean get() = this is Success

    /**
     * Returns true if this is [Error].
     */
    val isError: Boolean get() = this is Error

    /**
     * Returns true if this is [Idle].
     */
    val isIdle: Boolean get() = this is Idle

    /**
     * Returns the data if this is [Success], or null otherwise.
     */
    fun getOrNull(): T? = when (this) {
        is Success -> data
        else -> null
    }

    /**
     * Returns the exception if this is [Error], or null otherwise.
     */
    fun exceptionOrNull(): AppException? = when (this) {
        is Error -> exception
        else -> null
    }

    /**
     * Transforms the data if this is [Success].
     */
    inline fun <R> map(transform: (T) -> R): UiState<R> = when (this) {
        is Idle -> Idle
        is Loading -> Loading(message)
        is Success -> Success(transform(data))
        is Error -> Error(exception, retryAction)
    }

    companion object {
        /**
         * Creates a [Success] state with the given [data].
         */
        fun <T> success(data: T): UiState<T> = Success(data)

        /**
         * Creates an [Error] state from an [AppException].
         */
        fun <T> error(exception: AppException, retryAction: (() -> Unit)? = null): UiState<T> =
            Error(exception, retryAction)

        /**
         * Creates an [Error] state from a [Result.Error].
         */
        fun <T> fromResult(result: Result<T>, retryAction: (() -> Unit)? = null): UiState<T> =
            when (result) {
                is Result.Success -> Success(result.data)
                is Result.Error -> Error(result.exception, retryAction)
            }

        /**
         * Creates a [Loading] state with an optional message.
         */
        fun <T> loading(message: String? = null): UiState<T> = Loading(message)
    }
}

/**
 * Extension to convert a [Result] to a [UiState].
 */
fun <T> Result<T>.toUiState(retryAction: (() -> Unit)? = null): UiState<T> =
    UiState.fromResult(this, retryAction)
