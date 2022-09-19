package cat.itb.naimgomez7e5.dam.m06.uf1.xml.RestaurantsByType

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
import nl.adaptivity.xmlutil.serialization.XmlChildrenName
import nl.adaptivity.xmlutil.serialization.XmlElement
import java.util.Scanner

@Serializable
@SerialName("restaurants")
data class Restaurants(@SerialName("restaurants") val restaurants : List<Restaurant>)

@Serializable
@SerialName("restaurant")
data class Restaurant(
    val type: String,
    @XmlElement(true) val name: String,
    @XmlElement(true) val address: String,
    @XmlElement(true) val owner: String)

suspend fun main(){
    val scanner = Scanner(System.`in`)

    println("Quin tipus de restaurant vols?")
    val restaurants = getRestaurantsByType(scanner.nextLine())

    for (res in restaurants) {
        println("${res.name} - ${res.address} - ${res.owner}")
    }
}

suspend fun getRestaurantsByType(type: String) : List<Restaurant> {
    val xml = getXMLRestaurants();
    val restaurants : Restaurants = XML.decodeFromString(xml)

    return restaurants.restaurants.filter { it.type == type }
}

suspend fun getXMLRestaurants() : String {
    val url = "https://fp.mateuyabar.com/DAM-M06/UF1/exercicis/files/restaurants.xml";
    val client = HttpClient(CIO){
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    return client.get(url).body()
}