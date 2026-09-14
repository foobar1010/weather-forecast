package forecast.data.weatherapi

import forecast.data.weatherapi.dto.WeatherApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface WeatherApi {
    @GET("v1/forecast.json")
    suspend fun getForecast(
        @Query("q") city: String,
        @Query("days") days: Int? = null,
        @Query("dt") date: String? = null
    ): WeatherApiResponse

    companion object {
        fun create(): WeatherApi = NetworkModule.retrofit.create(WeatherApi::class.java)
    }
}