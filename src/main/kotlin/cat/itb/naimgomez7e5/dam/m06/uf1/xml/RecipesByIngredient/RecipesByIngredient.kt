package cat.itb.naimgomez7e5.dam.m06.uf1.xml.RecipesByIngredient

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XML
import nl.adaptivity.xmlutil.serialization.XmlChildrenName
import nl.adaptivity.xmlutil.serialization.XmlElement
import kotlin.io.path.Path
import kotlin.io.path.readText

@Serializable
@SerialName("recipes")
data class Recipes(val recipes : List<Recipe>)

@Serializable
@SerialName("recipe")
data class Recipe(
    val dificulty: String,
    @XmlElement(true) val name: String,
    @XmlChildrenName("ingredient", "", "") @SerialName("ingredients")  val ingredients : List<Ingredient>)

@Serializable
@SerialName("ingredient")
data class Ingredient(
    val ammount: String,
    val unit: String,
    val name: String)

fun main(){
    val xml = getXMLRecipes()

    val recipes = getRecipesByIngredientName(xml, "llet")

    recipes.forEach { println("${it.name} ${it.dificulty}") }
}

fun getRecipesByIngredientName(xml: String, ingName: String) : List<Recipe> {
    val recipes : Recipes = XML.decodeFromString(xml)


    val filteredRecipes = recipes.recipes.filter { recipe ->
        recipe.ingredients.any { it.name == ingName}
    }.sortedBy { it.dificulty }

    return filteredRecipes
}

fun getXMLRecipes() : String {
//  val path = Path("/dades/NGOMEZ/M06/naimgomez7e5-m06/src/main/kotlin/cat/itb/naimgomez7e5/dam/m06/uf1/xml/RecipesByIngredient/receptes.xml")
//  val xml : String = path.readText()
    val xml = Recipes::class.java.getResource("/receptes.xml").readText()
    return xml
}