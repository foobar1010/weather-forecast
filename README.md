# Weather Forecast CLI

A Command Line Interface (CLI) application written in Kotlin that fetches tomorrow's weather forecast for selected cities using WeatherAPI and displays it as a formatted table in the terminal.

## Output Example

The application displays tomorrow's forecast in a table:

```text
+-----------+---------------------------------------------------------------------------------------------+
| City      |                                               2026-09-16                                    |
+-----------+---------------------------------------------------------------------------------------------+
| Chisinau  | Min Temp: 14.8°C | Max Temp: 23.0°C | Humidity: 52% | Wind Speed: 9.7 km/h  | Wind Dir: ENE |
| Madrid    | Min Temp: 17.4°C | Max Temp: 29.4°C | Humidity: 27% | Wind Speed: 28.4 km/h | Wind Dir: ENE |
| Kyiv      | Min Temp: 13.9°C | Max Temp: 22.5°C | Humidity: 53% | Wind Speed: 12.6 km/h | Wind Dir: ENE |
| Amsterdam | Min Temp: 11.6°C | Max Temp: 18.3°C | Humidity: 76% | Wind Speed: 17.3 km/h | Wind Dir: WNW |
+-----------+---------------------------------------------------------------------------------------------+

```

> **Note on Wind Metrics:**
>
> * **Wind Speed** represents the maximum projected wind speed for the day (`maxwind_kph`).
> * **Wind Direction** is retrieved specifically for **12:00 PM** (noon) local forecast time.

## Setup & Running

### Prerequisites

* JDK installed.
* A free API Key from [WeatherAPI](https://www.weatherapi.com/)

### 1. Set API Key

Set your WeatherAPI key as an environment variable:

**macOS / Linux:**

```bash
export WEATHERAPI_API_KEY="your_api_key_here"
```

**Windows (CMD):**

```dos
set WEATHERAPI_API_KEY=your_api_key_here
```

**Windows (PowerShell):**

```powershell
$env:WEATHERAPI_API_KEY="your_api_key_here"
```

### 2. Run Application

Execute the CLI using the Gradle Wrapper:

**macOS / Linux:**

```bash
./gradlew run
```

**Windows:**

```dos
gradlew.bat run
```

### 3. Run Tests

To execute unit tests:

**macOS / Linux:**

```bash
./gradlew test
```

**Windows:**

```dos
gradlew.bat test
```