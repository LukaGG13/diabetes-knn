import java.io.File
import java.io.BufferedReader
data class Patient(val pregnancies: Int, val glucose: Int, val bloodPressure: Int, val skinThickness: Int, val insulin: Int, val bmi: Double, val diabetesPedigreeFunction: Double, val age: Int, val outcome: Int)
val N : Int = 5

fun KNN(newPatient: Patient) = runBlocking {
    {
//        find the k nearest neighbors of the new data point among the data
//        predict the class of the new data point by taking the majority vote

    }
}


fun main() {
    val bufferedReader: BufferedReader = File("diabetes.csv").bufferedReader()
    val inputString = bufferedReader.use { it.readText() }

    val patients = mutableListOf<Patient>()
    val lines = inputString.split("\n")
    for (line in lines) {
        val values = line.split(",")
        if (values[0] != "Pregnancies" && line != lines.last() && values != emptyArray<String>()) {
            val patient = Patient(values[0].toInt(), values[1].toInt(), values[2].toInt(), values[3].toInt(), values[4].toInt(), values[5].toDouble(), values[6].toDouble(), values[7].toInt(), values[8][0].code - 48)
            patients.add(patient)
            println(patient)
        }
    }
}