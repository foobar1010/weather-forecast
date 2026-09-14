package forecast.data.weatherapi.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherApiResponse(
    val forecast: ForecastContainer
)

@Serializable
data class ForecastContainer(
    @SerialName("forecastday")
    val forecastDay: List<ForecastDay>
)

@Serializable
data class ForecastDay(
    val date: String,
    val day: DayData
)

@Serializable
data class DayData(
    @SerialName("mintemp_c") val minTempC: Double,
    @SerialName("maxtemp_c") val maxTempC: Double,
    @SerialName("avghumidity") val humidity: Double,
    @SerialName("maxwind_kph") val maxWindKph: Double
)