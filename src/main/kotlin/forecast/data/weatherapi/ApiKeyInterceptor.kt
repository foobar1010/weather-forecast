package forecast.data.weatherapi

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    private val apiKey: String = requireNotNull(System.getenv("WEATHERAPI_API_KEY")) {
        "WEATHERAPI_API_KEY environment variable is not set. Please set it before running the app."
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val url = original.url.newBuilder()
            .addQueryParameter("key", apiKey)
            .build()

        return chain.proceed(original.newBuilder().url(url).build())
    }
}