package forecast.domain

import forecast.model.City
import forecast.model.WeatherData

interface ForecastApi {
    suspend fun getForecast(city: City): WeatherData
}