package cat.itb.naimgomez7e5.dam.m06.uf2.exercicis

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.Table.Dual.integer
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDate
import java.util.*

fun main(args: Array<String>) {
    //an example connection to H2 DB
    Database.connect("jdbc:h2:./lastTimeOpened", "org.h2.Driver")
    val currentDate = LocalDate.now().toString()

    transaction {
        SchemaUtils.create (Dates)

        // insert new city. SQL: INSERT INTO Cities (name) VALUES ('St. Petersburg')
        val stPeteId = Dates.insert {
            it[date] = currentDate
        } get Dates.id

        // 'select *' SQL: SELECT Cities.id, Cities.name FROM Cities
        Dates.selectAll().forEach {
            println(it[Dates.date])
        }
    }
}

object Dates: IntIdTable() {
    val date = varchar("date", 50)
}