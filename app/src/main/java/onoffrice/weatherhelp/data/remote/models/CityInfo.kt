package onoffrice.weatherhelp.data.remote.models

data class CityInfo(
    val `by`: String? = "",
    val execution_time: Float? = 0f,
    val from_cache: Boolean? = false,
    val results: Results? = Results(),
    val valid_key: Boolean? = false
)

data class Results(
    val cid: String? = "",
    val city: String? = "",
    val city_name: String? = "",
    val condition_code: String? = "",
    val condition_slug: String? = "",
    val currently: String? = "",
    val date: String? = "",
    val description: String? = "",
    val forecast: List<Forecast>? = listOf(),
    val humidity: Float? = 0f,
    val img_id: String? = "",
    val latitude: Float? = 0f,
    val longitude: Float? = 0f,
    val sunrise: String? = "",
    val sunset: String? = "",
    val temp:Float? = 0f,
    val time: String? = "",
    val wind_speedy: String? = ""
)

data class Forecast(
    val condition: String? = "",
    val date: String? = "",
    val description: String? = "",
    val max: Float? = 0f,
    val min: Float? = 0f,
    val weekday: String? = ""
)