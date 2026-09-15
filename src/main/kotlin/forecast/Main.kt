import forecast.data.weatherapi.NetworkModule
import forecast.data.weatherapi.WeatherApiService
import forecast.domain.Forecast
import forecast.model.City
import forecast.model.WeatherData
import forecast.ui.TablePrinter
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import java.time.LocalDate

fun main() = runBlocking {
    val tomorrowDate = LocalDate.now().plusDays(1).toString()
    val getForecast = Forecast(WeatherApiService(targetDate = tomorrowDate))
    val tablePrinter = TablePrinter()

    try {
        val weatherDataList = fetchAllForecasts(City.entries, getForecast)
        tablePrinter.print(weatherDataList)
    } finally {
        NetworkModule.close()
    }
}

private suspend fun fetchAllForecasts(
    cities: List<City>,
    getForecast: Forecast
): List<WeatherData> = coroutineScope {
    cities
        .map { city -> async { city to getForecast(city) } }
        .awaitAll()
        .mapNotNull { (city, result) ->
            result.onFailure { error ->
                println("Failed to fetch weather for ${city.apiName}: ${error.localizedMessage}")
            }.getOrNull()
        }
}