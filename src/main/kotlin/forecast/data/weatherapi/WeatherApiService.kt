package forecast.data.weatherapi

import forecast.domain.ForecastApi
import forecast.model.City
import forecast.model.WeatherData

internal class WeatherApiService(
    private val weatherApi: WeatherApi = WeatherApi.create(),
    private val targetDate: String? = null,
    private val days: Int? = null
) : ForecastApi {
    override suspend fun getForecast(city: City): WeatherData {
        val response = weatherApi.getForecast(
            city = city.apiName,
            days = days,
            date = targetDate
        )

        val forecastDay = response.forecast.forecastDay.firstOrNull()
            ?: throw IllegalStateException("No forecast data returned for city: ${city.apiName}")

        return forecastDay.toDomain(city)
    }
}