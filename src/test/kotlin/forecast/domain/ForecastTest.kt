package forecast.domain

import forecast.model.City
import forecast.model.WeatherData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.LocalDate

class ForecastTest {

    private val api: ForecastApi = mockk()
    private val forecast = Forecast(api)

    private val tomorrowDate = LocalDate.now().plusDays(1).toString()

    @Test
    fun `returns success result when API works correctly`() = runTest {
        val expectedData = WeatherData(
            city = City.KYIV,
            date = tomorrowDate,
            minTemp = 10.0,
            maxTemp = 20.0,
            humidity = 60.0,
            windSpeed = 15.0,
            windDirection = "NW"
        )
        coEvery { api.getForecast(City.KYIV) } returns expectedData

        val result = forecast(City.KYIV)

        assertTrue(result.isSuccess)
        assertEquals(expectedData, result.getOrNull())
    }

    @Test
    fun `returns failure result when API throws exception`() = runTest {
        coEvery { api.getForecast(City.KYIV) } throws RuntimeException("API connection error")

        val result = forecast(City.KYIV)

        assertTrue(result.isFailure)
        assertEquals("API connection error", result.exceptionOrNull()?.message)
    }
}