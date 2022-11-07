package cat.itb.naimgomez7e5.dam.m06.uf2.exercicis

import cat.itb.naimgomez7e5.dam.m06.uf2.exercicis.Dates.id
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.isNotNull
import org.jetbrains.exposed.sql.SqlExpressionBuilder.isNull
import org.jetbrains.exposed.sql.Table.Dual.primaryKey
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDate
import javax.swing.SpringLayout.Constraints

fun main(args: Array<String>) {
    //an example connection to H2 DB
    Database.connect("jdbc:pgsql://localhost:5432/postgres", driver = "com.impossibl.postgres.jdbc.PGDriver",
        user = "sjo", password = "p4ssword!")
    val currentDate = LocalDate.now().toString()


}

object Teams: Table() {
    val name = varchar("name", 20)
    val city = varchar("city", 20).isNull()
    val conference = varchar("conference", 4).isNull()
    val division = varchar("division", 9).isNull    ()

    override val primaryKey = PrimaryKey(name, name = "pk_team")
}

object Players: Table() {
    val id = integer("id").isNotNull()
    val name = varchar("name", 30).isNotNull()
    val origin = varchar("origin", 30).isNotNull()
    val height = varchar("height", 30).isNotNull()
    val weight = varchar("weight", 30).isNotNull()
    val position = varchar("position", 30).isNotNull()
    val nameIteam = varchar("nameIteam", 30).isNotNull()

}