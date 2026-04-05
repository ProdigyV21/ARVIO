package com.arflix.tv.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Test
import java.util.concurrent.Executors
import kotlin.system.measureTimeMillis

class IptvBenchmarkTest {

    @Test
    fun benchmarkThreadPoolVsCoroutines() = runBlocking {
        val items = (1..1000).toList()

        val threadPoolTime = measureTimeMillis {
            val executor = Executors.newFixedThreadPool(20)
            items.map { item ->
                executor.submit {
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
                        Thread.sleep(10)
                    }
                }.awaitAll()
            }
        }

        println("Coroutine time: ${coroutineTime}ms")
    }
}
