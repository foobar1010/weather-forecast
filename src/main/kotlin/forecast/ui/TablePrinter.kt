package forecast.ui

import forecast.model.WeatherData

class TablePrinter {

    private data class Column(
        val header: String,
        val width: Int,
        val getValue: (WeatherData) -> String
    )

    private val columns = listOf(
        Column("City", 12) { it.city.apiName },
        Column("Min Temp (°C)", 16) { "%.1f".format(it.minTemp) },
        Column("Max Temp (°C)", 16) { "%.1f".format(it.maxTemp) },
        Column("Humidity (%)", 14) { "%.1f".format(it.humidity) },
        Column("Wind (km/h)", 16) { "%.1f".format(it.windSpeed) },
        Column("Wind Dir (12:00)", 18) { it.windDirection }
    )

    fun print(weatherList: List<WeatherData>) {
        if (weatherList.isEmpty()) {
            println("No weather data available to display.")
            return
        }

        println("\nWeather forecast for ${weatherList.first().date}:")

        val divider = columns.joinToString(separator = "-+-", prefix = "+-", postfix = "-+") { "-".repeat(it.width) }
        val headerRow = columns.joinToString(separator = " | ", prefix = "| ", postfix = " |") { it.header.center(it.width) }

        println(divider)
        println(headerRow)
        println(divider)

        for (item in weatherList) {
            val row = columns.joinToString(separator = " | ", prefix = "| ", postfix = " |") { col ->
                col.getValue(item).center(col.width)
            }
            println(row)
        }

        println(divider)
    }

    private fun String.center(width: Int): String {
        if (length >= width) return take(width)
        val left = (width - length) / 2
        val right = width - length - left
        return " ".repeat(left) + this + " ".repeat(right)
    }
}