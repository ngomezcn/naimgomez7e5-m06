package cat.itb.naimgomez7e5.dam.m06.uf1.xml.SportTraker

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XML

@Serializable
data class Workouts(
    val workouts: List<Workout>)
}

@Serializable
data class Workout(
    val sportName: String,
    val duracio: Int) {
}

fun main() {
    val workouts : List<Workout> = listOf(
        Workout("Natacio", 10),
        Workout("Futbol", 90),
        Workout("Surf", 25),
        Workout("Escalada", 200))

    val xml = XML.Companion.encodeToString(workouts)

    println(xml)

}
