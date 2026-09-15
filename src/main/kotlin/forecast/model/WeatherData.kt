package forecast.model

data class WeatherData(
    val city: City,
    val date: String,
    val minTemp: Double,
    val maxTemp: Double,
    val humidity: Double,
    val windSpeed: Double,
    val windDirection: String
)