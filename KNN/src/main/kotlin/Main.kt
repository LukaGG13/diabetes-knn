import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import java.io.File
data class Patient(val pregnancies: Int, val glucose: Int, val bloodPressure: Int, val skinThickness: Int, val insulin: Int, val bmi: Double, val diabetesPedigreeFunction: Double, val age: Int, val outcome: Int)
val N : Int = 10

fun distance(patient1: Patient, patient2: Patient): Double {
    return Math.sqrt(Math.pow((patient1.pregnancies - patient2.pregnancies).toDouble(), 2.0) + Math.pow((patient1.glucose - patient2.glucose).toDouble(), 2.0) +
            Math.pow((patient1.bloodPressure - patient2.bloodPressure).toDouble(), 2.0) + Math.pow((patient1.skinThickness - patient2.skinThickness).toDouble(), 2.0) + Math.pow((patient1.insulin - patient2.insulin).toDouble(), 2.0) + Math.pow((patient1.bmi - patient2.bmi).toDouble(), 2.0) + Math.pow((patient1.diabetesPedigreeFunction - patient2.diabetesPedigreeFunction).toDouble(), 2.0) + Math.pow((patient1.age - patient2.age).toDouble(), 2.0))
}

suspend fun KNN(newPatient: Patient) = coroutineScope {
    val inputString = File("src/main/kotlin/diabetes.csv").readText()
    val patients = mutableListOf<Patient>()
    val lines = inputString.split("\n")
    for (line in lines) {
        val values = line.split(",")
        if (values[0] != "Pregnancies" && line != lines.last() && values != emptyArray<String>()) {
            val patient = Patient(values[0].toInt(), values[1].toInt(), values[2].toInt(), values[3].toInt(), values[4].toInt(), values[5].toDouble(), values[6].toDouble(), values[7].toInt(), values[8][0].code - 48)
            patients.add(patient)
        }
    }
    //println(patients)
    val distances = mutableListOf<Double>()
    for (patient in patients) {
        distances.add(distance(newPatient, patient))
    }
    val sortedDistances = distances.sortedBy{it}
    val kNearest = mutableListOf<Patient>()
    for (i in 0 until N) {
        kNearest.add(patients[distances.indexOf(sortedDistances[i])])
    }
    var positive = 0
    var negative = 0
    for (patient in kNearest) {
        if (patient.outcome == 1) {
            positive++
        } else {
            negative++
        }
    }
    if (positive > negative) {
        println("diabetes")
    } else {
        println("no diabetes")
    }
}

fun main() = runBlocking {
    KNN(Patient(0, 100, 62, 35, 3, 33.6, 0.627, 25, -1))
    KNN(Patient(6, 120, 72, 32, 0, 30.3, 0.598, 50, 1))
}


