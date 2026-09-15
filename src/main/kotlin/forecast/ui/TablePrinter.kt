package forecast.ui

import forecast.model.WeatherData

class TablePrinter {
    fun print(weatherList: List<WeatherData>) {
        if (weatherList.isEmpty()) {
            println("No weather data available to display.")
            return
        }

        val dates = weatherList.map { it.date }.distinct()

        val cityWidth = 12
        val dataWidth = 55

        val headerRow = "| " + "City".padEnd(cityWidth) + " | " +
                dates.joinToString(" | ") { it.center(dataWidth) } + " |"

        val divider = "+-" + "-".repeat(cityWidth) + "-+-" +
                dates.joinToString("-+-") { "-".repeat(dataWidth) } + "-+"

        println(divider)
        println(headerRow)
        println(divider)

        for (item in weatherList) {
            val formattedMetrics = "%.1f/%.1f°C | Hum: %.0f%% | Wind: %.1f km/h (%s)".format(
                item.minTemp,
                item.maxTemp,
                item.humidity,
                item.windSpeed,
                item.windDirection
            )

            val row = "| " + item.city.apiName.padEnd(cityWidth) + " | " +
                    formattedMetrics.padEnd(dataWidth) + " |"
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