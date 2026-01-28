import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse

interface NetworkClient {
    suspend fun performRequest(
        path: String,
        queryParams: Map<String, Any> = emptyMap(),
    ): NetworkResult<HttpResponse>
}

class KtorNetworkClient(
    private val httpClient: HttpClient,
    private val baseUrl: String
): NetworkClient {
    override suspend fun performRequest(
        path: String,
        queryParams: Map<String, Any>
    ): NetworkResult<HttpResponse> = safeRequest {
        httpClient.get(urlString = baseUrl + path){
            queryParams.forEach {(key, value) ->
                parameter(key, value)
            }
        }
    }

    private suspend inline fun <T> safeRequest(
        block: () -> T
    ): NetworkResult<T> = try {
        NetworkResult.Success(block())
    } catch (e: Exception) {
        NetworkResult.Error(message = e.message, throwable = e)
    }
}

suspend inline fun <reified T> NetworkClient.request(
    path: String,
    queryParams: Map<String, Any> = emptyMap()
): NetworkResult<T> {

    return when (val result = performRequest(path, queryParams)) {
        is NetworkResult.Success -> {
            try {
                val data = result.data.body<T>()
                NetworkResult.Success(data)
            } catch (e: Exception) {
                NetworkResult.Error(e.message, e)
            }
        }
        is NetworkResult.Error -> NetworkResult.Error(result.message, result.throwable)
    }
}