package forecast.ui

import forecast.model.WeatherData

class TablePrinter {
    fun print(weatherList: List<WeatherData>) {
        if (weatherList.isEmpty()) {
            println("No weather data available to display.")
            return
        }

        val date = weatherList.first().date

        val rows = weatherList.map { item ->
            val minTemp = "Min Temp: %.1f°C".format(item.minTemp)
            val maxTemp = "Max Temp: %.1f°C".format(item.maxTemp)
            val humidity = "Humidity: %.0f%%".format(item.humidity)
            val windSpeed = "Wind Speed: %.1f km/h".format(item.windSpeed)
            val windDir = "Wind Dir: %s".format(item.windDirection)

            RowData(item.city.apiName, minTemp, maxTemp, humidity, windSpeed, windDir)
        }

        val cityWidth = (rows.map { it.city.length } + "City".length).maxOrNull() ?: 10
        val minWidth = rows.map { it.minTemp.length }.maxOrNull() ?: 15
        val maxWidth = rows.map { it.maxTemp.length }.maxOrNull() ?: 15
        val humWidth = rows.map { it.humidity.length }.maxOrNull() ?: 14
        val speedWidth = rows.map { it.windSpeed.length }.maxOrNull() ?: 20
        val dirWidth = rows.map { it.windDir.length }.maxOrNull() ?: 14

        val totalDataWidth = minWidth + maxWidth + humWidth + speedWidth + dirWidth + 12
        val headerRow = "| " + "City".padEnd(cityWidth) + " | " + date.center(totalDataWidth) + " |"
        val divider = "+-" + "-".repeat(cityWidth) + "-+-" + "-".repeat(totalDataWidth) + "-+"

        println(divider)
        println(headerRow)
        println(divider)

        for (row in rows) {
            val metricsFormatted = "%s | %s | %s | %s | %s".format(
                row.minTemp.padEnd(minWidth),
                row.maxTemp.padEnd(maxWidth),
                row.humidity.padEnd(humWidth),
                row.windSpeed.padEnd(speedWidth),
                row.windDir.padEnd(dirWidth)
            )
            println("| " + row.city.padEnd(cityWidth) + " | " + metricsFormatted + " |")
        }
        println(divider)
    }

    private data class RowData(
        val city: String,
        val minTemp: String,
        val maxTemp: String,
        val humidity: String,
        val windSpeed: String,
        val windDir: String
    )

    private fun String.center(width: Int): String {
        if (length >= width) return take(width)
        val left = (width - length) / 2
        val right = width - length - left
        return " ".repeat(left) + this + " ".repeat(right)
    }
}