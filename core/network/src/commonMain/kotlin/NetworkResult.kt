import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
sealed class NetworkResult<out T> {
    @Serializable
    data class Success<out T>(val data: T) : NetworkResult<T>()

    @Serializable
    data class Error(
        val message: String? = null,
        @Transient
        val throwable: Throwable? = null)
        : NetworkResult<Nothing>()
}