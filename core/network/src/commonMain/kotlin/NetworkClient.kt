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
) : NetworkClient {

    override suspend fun performRequest(
        path: String,
        queryParams: Map<String, Any>
    ): NetworkResult<HttpResponse> = safeRequest {
        httpClient.get(urlString = buildUrl(baseUrl, path)) {
            queryParams.forEach { (key, value) ->
                parameter(key, value)
            }
        }
    }

    private fun buildUrl(baseUrl: String, path: String): String {
        val base = baseUrl.trimEnd('/')
        val p = path.trimStart('/')
        return "$base/$p"
    }

    private suspend inline fun <T> safeRequest(
        crossinline block: suspend () -> T
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
            val response = result.data
            try {
                val data = response.body<T>()
                NetworkResult.Success(data)
            } catch (e: Exception) {
                NetworkResult.Error(
                    message = e.message,
                    statusCode = response.status.value,
                    throwable = e
                )
            }
        }

        is NetworkResult.Error -> result
    }
}