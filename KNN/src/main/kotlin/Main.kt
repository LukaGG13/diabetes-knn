import java.io.File
import java.io.BufferedReader
import kotlinx.coroutines.*

data class Patient(val pregnancies: Int, val glucose: Int, val bloodPressure: Int, val skinThickness: Int, val insulin: Int, val bmi: Double, val diabetesPedigreeFunction: Double, val age: Int, val outcome: Int)
val N : Int = 5

fun KNN(newPatient: Patient) = runBlocking {

        // to a single "core", let's call it "Main Core"
        launch { // Start another "thread" pinned to "Main Core". The "thread" is
            // in a suspended state, waiting for "Main Core" to get free
            println("in sub coroutine ${Thread.currentThread().name}")
        }
        // `launch` is just a function, it completed after creating the new "thread",
        //  move on to the code below it
        println("before coroutine in main ${Thread.currentThread().name}")
        // Start a context where "threads" are pinned to another "core", the
        // "IO Core". It executes its "threads" concurrently to "Main Core".
        // However, the particular "thread" that creates the context gets suspended
        // until it is done. Other "threads" pinned to "Main Core" can run.
        withContext(Dispatchers.IO) {
            println("hello from coroutine ${Thread.currentThread().name}")
            delay(1500)
            println("hello from coutoutine after delay ${Thread.currentThread().name}")
        }
        // Now the "thread" that created the "IO Core" context can go on.
        println("after coroutine in main ${Thread.currentThread().name}")

    }



fun main() {
    val inputString = File("src/main/kotlin/diabetes.csv").readText()
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
    println()
    KNN(patients[0])
}
//    logger.quiet("An info log message which is always logged.")
