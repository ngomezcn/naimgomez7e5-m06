package cat.itb.naimgomez7e5.dam.m06.uf1.xml.RecipesByIngredient

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import nl.adaptivity.xmlutil.serialization.XML
import nl.adaptivity.xmlutil.serialization.XmlElement


@Serializable
@SerialName("restaurant")
data class Restaurant(
    val type: String = "restaurant de menú",
    @XmlElement(true) val name: String,
    @XmlElement(true) val address: String,
    @XmlElement(true) val owner: String)

suspend fun main(){
    val url = "https://fp.mateuyabar.com/DAM-M06/UF1/exercicis/files/receptes.xml";
    val client = HttpClient(CIO){
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    val xml : String = client.get(url).body()
    val restaurant : Restaurant = XML.decodeFromString(xml)
    println(restaurant)
    println(XML.encodeToString(restaurant))
}
