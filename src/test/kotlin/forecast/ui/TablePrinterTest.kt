package forecast.ui

import forecast.model.City
import forecast.model.WeatherData
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class TablePrinterTest {
    private val tablePrinter = TablePrinter()

    @Test
    fun `prints message when weather list is empty`() {
        val output = captureOutput {
            tablePrinter.print(emptyList())
        }

        assertEquals(
            "No weather data available to display.",
            output.trim()
        )
    }

    @Test
    fun `prints weather data as a table`() {
        val testDate = java.time.LocalDate.now().plusDays(1).toString()

        val weatherData = listOf(
            WeatherData(
                city = City.KYIV,
                date = testDate,
                minTemp = 10.0,
                maxTemp = 20.0,
                humidity = 60.0,
                windSpeed = 15.0,
                windDirection = "NW"
            )
        )

        val output = captureOutput {
            tablePrinter.print(weatherData)
        }

        assertTrue(output.contains("Kyiv"))
        assertTrue(output.contains(testDate))
        assertTrue(output.contains("Min Temp: 10.0°C"))
        assertTrue(output.contains("Max Temp: 20.0°C"))
        assertTrue(output.contains("Humidity: 60%"))
        assertTrue(output.contains("Wind Speed: 15.0 km/h"))
        assertTrue(output.contains("Wind Dir: NW"))
    }

    private fun captureOutput(block: () -> Unit): String {
        val originalOut = System.out
        val output = ByteArrayOutputStream()

        System.setOut(PrintStream(output))
        try {
            block()
        } finally {
            System.setOut(originalOut)
        }

        return output.toString()
    }
}