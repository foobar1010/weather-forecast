package forecast.domain

import forecast.model.City
import forecast.model.WeatherData

class Forecast(
    private val api: ForecastApi
) {
    suspend operator fun invoke(city: City): Result<WeatherData> = runCatching {
        api.getForecast(city)
    }
}