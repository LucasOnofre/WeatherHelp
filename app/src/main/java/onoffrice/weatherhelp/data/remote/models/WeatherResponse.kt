package onoffrice.weatherhelp.data.remote.models

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "cidades")
class WeatherCitysResponse {
    @get:Element(name = "cidade")
    var citys: List<CityResumed>? = listOf()
}

@Root(name = "cidade")
class CityResumed {
    @Attribute(name = "nome")
    var name: String? = ""

    @Attribute(name = "uf")
    var uf: String? = ""

    @Attribute(name = "id")
    var id: String? = ""
}
