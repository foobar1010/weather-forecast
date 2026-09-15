package forecast.data.weatherapi

import forecast.data.weatherapi.dto.ForecastDay
import forecast.data.weatherapi.dto.HourData
import forecast.model.City
import forecast.model.WeatherData

internal fun ForecastDay.toDomain(city: City): WeatherData {
    return WeatherData(
        city = city,
        date = date,
        minTemp = day.minTempC,
        maxTemp = day.maxTempC,
        humidity = day.humidity,
        windSpeed = day.maxWindKph,
        windDirection = hour.extractWindDirectionAtNoon()
    )
}

private fun List<HourData>.extractWindDirectionAtNoon(): String {
    return firstOrNull { it.time.endsWith("12:00") }?.windDir
        ?: getOrNull(12)?.windDir
        ?: firstOrNull()?.windDir
        ?: "N/A"
}