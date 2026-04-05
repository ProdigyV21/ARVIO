import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val items = (1..2000).toList()

    val threadPoolTime = measureTimeMillis {
        val executor = Executors.newFixedThreadPool(20)
        val futures = items.map { item ->
            executor.submit {
                // simulate work
                Thread.sleep(10)
            }
        }
        executor.shutdown()
        executor.awaitTermination(60, java.util.concurrent.TimeUnit.SECONDS)
    }

    println("ThreadPool time: ${threadPoolTime}ms")

    val coroutineTime = measureTimeMillis {
        withContext(Dispatchers.IO.limitedParallelism(20)) {
            items.map { item ->
                async {
                    // simulate work
                    delay(10)
                }
            }.awaitAll()
        }
    }

    println("Coroutine time: ${coroutineTime}ms")
}
