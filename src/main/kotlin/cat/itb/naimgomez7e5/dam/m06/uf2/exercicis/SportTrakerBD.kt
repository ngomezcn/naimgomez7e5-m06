import nl.adaptivity.xmlutil.serialization.XML
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*
import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.io.path.writeText

fun main() {
    val workoutPath = "/dades/NGOMEZ/M06/naimgomez7e5-m06/src/main/kotlin/cat/itb/naimgomez7e5/dam/m06/uf1/xml/SportTraker/receptes.xml";
    val scanner = Scanner(System.`in`)
    Database.connect("jdbc:h2:./sportTrakerBD", "org.h2.Driver")

    transaction {
        addLogger(StdOutSqlLogger)
        SchemaUtils.create(Workouts)
    }

    while (true) {
        println("Voleu enregistrar dades? si no")
        if(scanner.nextLine() != "si") {
            break
        }

        print("Esport: "); val name = scanner.nextLine().lowercase()
        print("Duration: "); val duration = scanner.nextLine().toInt()


        transaction {
            addLogger(StdOutSqlLogger)

            val query : Query = Workouts.select(Workouts.nom.lowerCase() eq name)

            if (query.empty()) {
                Workouts.insert {
                    it[Workouts.nom] = name
                    it[Workouts.duracio] = duration
                }
            } else
            {
                query.forEach {
                        ib ->
                    Workouts.update({ Workouts.nom.lowerCase() eq name }) {
                        it[Workouts.nom] = name
                        it[Workouts.duracio] = duration + ib[duracio]
                    }
                }
            }
        }

        println("Enregistrat")
    }

    println("[Esport] [Duracio]")
    transaction {
        val query = Workouts.selectAll()

        var totalTime = 0
        query.forEach {
            totalTime += it[Workouts.duracio]
            println("${it[Workouts.nom]} - ${it[Workouts.duracio]}")
        }
        println("Temps total: $totalTime")
    }
}


object Workouts: IntIdTable() {
    val nom = varchar("nom", 50)
    val duracio = integer("duracio")
}
