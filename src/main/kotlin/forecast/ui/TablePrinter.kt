package forecast.ui

import forecast.model.WeatherData

class TablePrinter {
    fun print(weatherList: List<WeatherData>) {
        if (weatherList.isEmpty()) {
            println("No data to display.")
            return
        }

        val date = weatherList.first().date
        println("\nWeather forecast for $date:")

        val col1Width = 12 // City
        val col2Width = 16 // Min Temp (°C)
        val col3Width = 16 // Max Temp (°C)
        val col4Width = 14 // Humidity (%)
        val col5Width = 16 // Wind (km/h)
        val col6Width = 18 // Wind Dir (12:00)

        val divider = "+-${"-".repeat(col1Width)}-+-${"-".repeat(col2Width)}-+-${"-".repeat(col3Width)}-+-${"-".repeat(col4Width)}-+-${"-".repeat(col5Width)}-+-${"-".repeat(col6Width)}-+"

        println(divider)
        println(
            "| ${center("City", col1Width)} " +
                    "| ${center("Min Temp (°C)", col2Width)} " +
                    "| ${center("Max Temp (°C)", col3Width)} " +
                    "| ${center("Humidity (%)", col4Width)} " +
                    "| ${center("Wind (km/h)", col5Width)} " +
                    "| ${center("Wind Dir (12:00)", col6Width)} |"
        )
        println(divider)

        for (item in weatherList) {
            println(
                "| ${center(item.city.apiName, col1Width)} " +
                        "| ${center(item.minTemp.toString(), col2Width)} " +
                        "| ${center(item.maxTemp.toString(), col3Width)} " +
                        "| ${center(item.humidity.toString(), col4Width)} " +
                        "| ${center(item.windSpeed.toString(), col5Width)} " +
                        "| ${center(item.windDirection, col6Width)} |"
            )
        }
        println(divider)
    }

    private fun center(text: String, width: Int): String {
        if (text.length >= width) return text.take(width)
        val padding = width - text.length
        val leftPadding = padding / 2
        val rightPadding = padding - leftPadding
        return " ".repeat(leftPadding) + text + " ".repeat(rightPadding)
    }
}