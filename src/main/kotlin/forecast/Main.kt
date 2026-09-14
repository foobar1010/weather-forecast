import forecast.data.weatherapi.NetworkModule
import forecast.data.weatherapi.WeatherApiService
import forecast.domain.Forecast
import forecast.model.City
import forecast.ui.TablePrinter
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import java.time.LocalDate

fun main() = runBlocking {
    val tomorrowDate = LocalDate.now().plusDays(1).toString()
    val weatherApiService = WeatherApiService(targetDate = tomorrowDate)
    val weather = Forecast(weatherApiService)
    val tablePrinter = TablePrinter()

    try {
        val results = City.entries.map { city ->
            async { weather.call(city) }
        }.awaitAll()

        val weatherDataList = results.mapNotNull { it.getOrNull() }

        if (weatherDataList.isNotEmpty()) {
            tablePrinter.print(weatherDataList)
        } else {
            println("No weather data available to display.")
        }
    } finally {
        NetworkModule.close()
    }
}