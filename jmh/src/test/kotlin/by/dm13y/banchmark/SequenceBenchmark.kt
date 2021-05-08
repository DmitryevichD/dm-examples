package by.dm13y.banchmark

import org.junit.Test
import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.options.OptionsBuilder
import java.util.concurrent.TimeUnit

@BenchmarkMode(Mode.Throughput) // El modo de prueba de referencia, el modo de rendimiento general
@Warmup(iterations = 3) // Número de precalentamiento
@Measurement(iterations = 10, time = 5, timeUnit = TimeUnit.SECONDS) // Parámetros de prueba, iteraciones = 10 significa 10 rondas de prueba
@Threads(8) // El número de subprocesos de prueba en cada proceso
@Fork(2)  // El número de bifurcaciones indica que JMH desembolsará dos procesos para probar
@OutputTimeUnit(TimeUnit.MILLISECONDS) // Tipo de tiempo de los resultados comparativos
open class SequenceBenchmark {

    @Benchmark
    fun testSequence():Int {

        return sequenceOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .map { it * 2 }
            .filter { it % 3 == 0 }
            .map { it + 1 }
            .sum()
    }


    @Test
    fun `Sequence benchmark`() {
        val options = OptionsBuilder()
            .include(SequenceBenchmark::class.java.simpleName)
            .build()

        Runner(options).run()
    }
}
