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

    Database.connect("jdbc:pgsql://localhost:5432/template1", driver = "com.impossibl.postgres.jdbc.PGDriver",
        user = "sjo", password = "p4ssword!")

    // 1ex
    /*transaction {
        addLogger(StdOutSqlLogger)
        val nameList = Teams.slice(Teams.name).selectAll().forEach {
            print(it[Teams.name] + ", ")
        }
    }*/

    // 2ex
    // select T0.city from teams T0 inner join players T1 on T0.name = T1.nameiteam where T1.name = 'Corey Brever';
    transaction {
        addLogger(StdOutSqlLogger)

        val query = Teams.slice(Teams.name).selectAll().adjustColumnSet {
            innerJoin(Players, {Teams.name}, {nameiteam}) }
            .andWhere { Players.name eq "Corey Brever"  }


        for(i in query){
            println(i[Teams.city])
        }
    }

    // 3ex
    // select T1.name, max(T0.height) from players T0 inner join teams T1 on T0.nameiteam = T1.name group by T1.name;
    transaction {
        addLogger(StdOutSqlLogger)

        Players.slice(Players.height.max(), Teams.name).selectAll().adjustColumnSet {
            innerJoin(Players, {Teams.name}, {nameiteam}) }
            .groupBy(Teams.name).forEach {
                println(it[Teams.name] + " " + it[Players.height.count()])
            }
    }

    // 3ex
    // select avg(T0.weight) from players T0;
    /*transaction {
        addLogger(StdOutSqlLogger)

        Players.slice(Players.weight.avg()).selectAll().forEach {
            println(it[Players.weight.avg()])
        }
    }*/
}

object Teams: Table() {
    val name = varchar("name", 20)
    val city = varchar("city", 20).isNull()
    val conference = varchar("conference", 4).isNull()
    val division = varchar("division", 9).isNull    ()

    override val primaryKey = PrimaryKey(name, name = "pk_team")
}

object Players: Table() {
    val id = integer("id")
    val name = varchar("name", 30)
    val origin = varchar("origin", 30)
    val height = varchar("height", 30)
    val weight = varchar("weight", 30)
    val position = varchar("position", 30)
    val nameiteam = varchar("nameiteam", 30)
}