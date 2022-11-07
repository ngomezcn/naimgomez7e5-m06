package cat.itb.naimgomez7e5.dam.m06.uf1.xml.SportTraker

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XML
import java.util.*
import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.io.path.writeText
/*
@kotlinx.serialization.Serializable
data class Workouts(val workouts: MutableList<Workout>)

@kotlinx.serialization.Serializable
class Workout(val nom: String, val duracio: Int) {

}

fun main() {
    val workoutPath = "/dades/NGOMEZ/M06/naimgomez7e5-m06/src/main/kotlin/cat/itb/naimgomez7e5/dam/m06/uf1/xml/SportTraker/receptes.xml";
    val scanner = Scanner(System.`in`)

    while (true) {
        println("Voleu enregistrar dades? si(0) no(1)")
        if(scanner.nextLine() != "si") {
            break
        }

        val xml = Path(workoutPath).readText()
        val workouts : Workouts = XML.decodeFromString(xml)

        print("Esport: "); val name = scanner.nextLine()
        print("Duration: "); val duration = scanner.nextLine().toInt()
        workouts.workouts.add(Workout(name, duration))
        Path(workoutPath).writeText(XML.encodeToString(workouts))
        println("Enregistrat")
    }
    val xml = Path(workoutPath).readText()
    val workouts : Workouts = XML.decodeFromString(xml)

    val totalData = workouts.workouts.groupBy { it.nom }

    println("[Esport] [Duracio total]")

    for (i in totalData) {
        println("${i.key} - ${i.value.sumOf { it.duracio }}")
    }

    println("\nTemps total: ${workouts.workouts.sumOf { it.duracio }}")
}
*/