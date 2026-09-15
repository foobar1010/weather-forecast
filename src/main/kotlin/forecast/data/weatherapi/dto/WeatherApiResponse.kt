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
    val day: DayData,
    val hour: List<HourData> = emptyList()
)

@Serializable
data class DayData(
    @SerialName("mintemp_c") val minTempC: Double,
    @SerialName("maxtemp_c") val maxTempC: Double,
    @SerialName("avghumidity") val humidity: Double,
    @SerialName("maxwind_kph") val maxWindKph: Double
)

@Serializable
data class HourData(
    val time: String,
    @SerialName("wind_dir") val windDir: String? = null
)