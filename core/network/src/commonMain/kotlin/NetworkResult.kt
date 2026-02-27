sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>()

    data class Error(
        val message: String? = null,
        val statusCode: Int? = null,
        val throwable: Throwable? = null
    ) : NetworkResult<Nothing>()
}