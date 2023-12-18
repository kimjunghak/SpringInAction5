import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier
import java.time.Duration
import java.util.stream.Stream

class FluxCreationTest {

    @Test
    fun createAFlux_just() {
        val fruitFlux: Flux<String> = Flux.just("Apple", "Orange", "Grape", "Banana", "Strawberry")

        fruitFlux.subscribe {
            println("Here's some fruit: $it")
        }

        StepVerifier.create(fruitFlux)
            .expectNext("Apple")
            .expectNext("Orange")
            .expectNext("Grape")
            .expectNext("Banana")
            .expectNext("Strawberry")
            .verifyComplete()
    }

    @Test
    fun createAFlux_fromArray() {
        val fruits = arrayOf("Apple", "Orange", "Grape", "Banana", "Strawberry")

        val fruitFlux: Flux<String> = Flux.fromArray(fruits)

        StepVerifier.create(fruitFlux)
            .expectNext("Apple")
            .expectNext("Orange")
            .expectNext("Grape")
            .expectNext("Banana")
            .expectNext("Strawberry")
            .verifyComplete()
    }

    @Test
    fun createAFlux_fromIterable() {
        val fruitList = arrayListOf("Apple", "Orange", "Grape", "Banana", "Strawberry")

        val fruitFlux = Flux.fromIterable(fruitList)

        StepVerifier.create(fruitFlux)
            .expectNext("Apple")
            .expectNext("Orange")
            .expectNext("Grape")
            .expectNext("Banana")
            .expectNext("Strawberry")
            .verifyComplete()
    }

    @Test
    fun createAFlux_fromStream() {
        val fruitStream = Stream.of("Apple", "Orange", "Grape", "Banana", "Strawberry")

        val fruitFlux = Flux.fromStream(fruitStream)

        StepVerifier.create(fruitFlux)
            .expectNext("Apple")
            .expectNext("Orange")
            .expectNext("Grape")
            .expectNext("Banana")
            .expectNext("Strawberry")
            .verifyComplete()
    }

    @Test
    fun createAFlux_range() {
        val intervalFlux = Flux.range(1, 5)

        StepVerifier.create(intervalFlux)
            .expectNext(1)
            .expectNext(2)
            .expectNext(3)
            .expectNext(4)
            .expectNext(5)
            .verifyComplete()
    }

    @Test
    fun createAFlux_interval() {
        // 0부터 시작, 최댓값이 설정되지 않으면 무한정으로 증가
        val intervalFlux = Flux.interval(Duration.ofSeconds(1)).take(5)

        StepVerifier.create(intervalFlux)
            .expectNext(0L)
            .expectNext(1L)
            .expectNext(2L)
            .expectNext(3L)
            .expectNext(4L)
            .verifyComplete()
    }
}