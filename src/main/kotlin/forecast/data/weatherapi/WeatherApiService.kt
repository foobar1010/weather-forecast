package forecast.data.weatherapi

import forecast.domain.ForecastApi
import forecast.model.City
import forecast.model.WeatherData

internal class WeatherApiService(
    private val targetDate: String? = null,
    private val days: Int? = null
) : ForecastApi {
    private val weatherApi: WeatherApi = WeatherApi.create()

    override suspend fun getForecast(city: City): WeatherData {
        val response = weatherApi.getForecast(
            city = city.apiName,
            date = targetDate,
            days = days
        )

        val dayData = response.forecast.forecastDay.first()

        return WeatherData(
            city = city,
            date = dayData.date,
            minTemp = dayData.day.minTempC,
            maxTemp = dayData.day.maxTempC,
            humidity = dayData.day.humidity,
            windSpeed = dayData.day.maxWindKph
        )
    }
}