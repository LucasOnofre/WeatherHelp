package onoffrice.weatherhelp


object NetworkConstants {

    //1xx Informational
    const val CODE_WITHOUT_NETWORK = 0

    //2xx Success
    const val CODE_RESPONSE_SUCCESS = 200

    //3xx Redirection
    const val CODE_NOT_FOUND = 340

    //4xx Client Error
    const val CODE_TIMEOUT = 408
    const val CODE_RESPONSE_UNAUTHORIZED = 401
    const val CODE_BAD_REQUEST = 400
    const val CODE_FORBIDDEN = 403

    //5xx Server Error
    const val CODE_UNKNOWN = 500

    //API URLs
    val BASE_URL : String = BuildConfig.BASE_URL

    val URL_LISTAR_CIDADES = "${BASE_URL}/listaCidades/"

}