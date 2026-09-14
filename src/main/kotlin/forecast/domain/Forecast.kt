package forecast.domain

import forecast.model.City
import forecast.model.WeatherData

class Forecast(
    private val api: ForecastApi
) {
    suspend fun call(city: City): Result<WeatherData> = runCatching {
        api.getForecast(city)
    }.onFailure { e ->
        println("Failed to fetch weather for ${city.apiName}: ${e.localizedMessage}")
    }
}