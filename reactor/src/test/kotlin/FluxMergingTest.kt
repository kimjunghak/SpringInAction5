import org.assertj.core.groups.Tuple
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier
import reactor.util.function.Tuple2
import java.time.Duration

class FluxMergingTest {

    @Test
    fun mergeFluxes() {
        val characterFlux = Flux.just("Garfield", "Kojak", "Barbossa")
            .delayElements(Duration.ofMillis(500))

        val foodFlux = Flux.just("Lasagna", "Lollipops", "Apples")
            .delaySubscription(Duration.ofMillis(250))
            .delayElements(Duration.ofMillis(500))

        val mergedFlux = characterFlux.mergeWith(foodFlux)

        StepVerifier.create(mergedFlux)
            .expectNext("Garfield")
            .expectNext("Lasagna")
            .expectNext("Kojak")
            .expectNext("Lollipops")
            .expectNext("Barbossa")
            .expectNext("Apples")
            .verifyComplete()
    }

    @Test
    fun zipFluxes() {
        val characterFlux = Flux.just("Garfield", "Kojak", "Barbossa")
        val foodFlux = Flux.just("Lasagna", "Lollipops", "Apples")

        val zippedFlux: Flux<Tuple2<String, String>> = Flux.zip(characterFlux, foodFlux)

        StepVerifier.create(zippedFlux)
            .expectNextMatches{ t: Tuple2<String, String> -> t.t1 == "Garfield" && t.t2 == "Lasagna" }
            .expectNextMatches{ t: Tuple2<String, String> -> t.t1 == "Kojak" && t.t2 == "Lollipops" }
            .expectNextMatches{ t: Tuple2<String, String> -> t.t1 == "Barbossa" && t.t2 == "Apples" }
            .verifyComplete()
    }

    @Test
    fun zipFluxesToObject() {
        val characterFlux = Flux.just("Garfield", "Kojak", "Barbossa")
        val foodFlux = Flux.just("Lasagna", "Lollipops", "Apples")

        val zippedFlux = Flux.zip(characterFlux, foodFlux) { c, f -> "$c eats $f" }

        StepVerifier.create(zippedFlux)
            .expectNext("Garfield eats Lasagna")
            .expectNext("Kojak eats Lollipops")
            .expectNext("Barbossa eats Apples")
            .verifyComplete()
    }

    @Test
    fun firstFlux() {
        val slowFlux = Flux.just("tortoise", "snail", "sloth")
            .delaySubscription(Duration.ofMillis(100))
        val fastFlux = Flux.just("hare", "cheetah", "squirrel")

        val firstFlux = Flux.firstWithSignal(slowFlux, fastFlux)

        StepVerifier.create(firstFlux)
            .expectNext("hare")
            .expectNext("cheetah")
            .expectNext("squirrel")
            .verifyComplete()
    }
}