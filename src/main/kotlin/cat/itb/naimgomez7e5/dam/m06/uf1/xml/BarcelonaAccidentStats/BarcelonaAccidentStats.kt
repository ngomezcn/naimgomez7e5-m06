package cat.itb.naimgomez7e5.dam.m06.uf1.xml.BarcelonaAccidentStats

import kotlinx.serialization.Serializable
import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.csv.Csv
import kotlinx.serialization.csv.CsvConfiguration
import kotlinx.serialization.serializer
import java.io.BufferedReader
import java.io.FileReader
import kotlinx.serialization.builtins.ListSerializer


const val path = "/dades/NGOMEZ/M06/naimgomez7e5-m06/src/main/kotlin/cat/itb/naimgomez7e5/dam/m06/uf1/xml/BarcelonaAccidentStats/"

//@Serializable
//data class Accidents(val accidents: List<Accident>)

//@Serializable
//data class Causes(val causes: List<Causa>)

@Serializable
data class Accident(val Numero_expedient: String, val Codi_districte: String, val Nom_districte: String, val Codi_barri: String, val Nom_barri: String, val Codi_carrer: String,
                    val Nom_carrer: String, val Num_postal_caption: String, val Descripcio_dia_setmana: String, val NK_Any: String, val Mes_any: String, val Nom_mes:String,
                    val Dia_mes:String, val Hora_dia:String, val Descripcio_torn:String, val Descripcio_causa_vianant:String, val Numero_morts:String,
                    val Numero_lesionats_lleus:String, val Numero_lesionats_greus:String, val Numero_victimes:String, val Numero_vehicles_implicats: String,
                    val Coordenada_UTM_Y_ED50: String, val Coordenada_UTM_X_ED50: String, val Longitud_WGS84: String, val Latitud_WGS84: String) {}

@Serializable
data class Causa(val Numero_expedient: String, val Codi_districte: String, val Nom_districte: String, val Codi_barri: String, val Nom_barri: String, val Codi_carrer: String,
                 val Nom_carrer: String, val Num_postal: String, val Descripcio_dia_setmana: String, val NK_Any: String, val Mes_any: String, val Nom_mes:String,
                 val Dia_mes:String, val Hora_Dia:String, val Descripcio_torn:String, val Descripcio_causa_mediata:String, val Coordenada_UTM_X_ED50:String,
                 val Coordenada_UTM_Y_ED50: String, val Longitud_WGS84:String, val Latitud_WGS84:String) {}

@OptIn(ExperimentalSerializationApi::class)
fun main() {
    //val csvConf = CsvConfiguration(hasHeaderRecord = true);
    //val csv = Csv(csvConf)
    val csv = Csv { hasHeaderRecord = true }


    val accidentsCSV = Path(path,"accidents.csv").readText().replace("Sant Pere,", "Sant Pere").replace("Vallvidrera,", "Vallvidrera")
    val accidents : List<Accident> = csv.decodeFromString(ListSerializer(Accident.serializer()), accidentsCSV)

    println(accidents[0].Codi_barri)

    //val causesXml = Path(path, "causes.csv").readText().replace("Sant Pere,", "Sant Pere").replace("Vallvidrera,", "Vallvidrera")

    //val accidents = CSVFormat.decodeFromString(Accidents.serializer(), causesXml)
    //val causes = CSVFormat.decodeFromString(Causes.serializer(), accidentsXml)
}