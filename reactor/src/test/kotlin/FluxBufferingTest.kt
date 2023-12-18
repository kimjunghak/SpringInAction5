import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import reactor.test.StepVerifier

class FluxBufferingTest {

    @Test
    fun buffer() {
        val fruitFlux = Flux.just("apple", "orange", "banana", "kiwi", "strawberry")

        val bufferedFlux: Flux<List<String>> = fruitFlux.buffer(3)

        StepVerifier.create(bufferedFlux)
            .expectNext(listOf("apple", "orange", "banana"))
            .expectNext(listOf("kiwi", "strawberry"))
            .verifyComplete()
    }

    @Test
    fun bufferToList() {
        Flux.just("apple", "orange", "banana", "kiwi", "strawberry")
            .buffer(3)
            .flatMap { x ->
                Flux.fromIterable(x)
                    .map { y -> y.uppercase() }
                    .subscribeOn(Schedulers.parallel())
                    .log()
            }
            .subscribe()
    }

    @Test
    fun collectionList() {
        val fruitFlux = Flux.just("apple", "orange", "banana", "kiwi", "strawberry")

        val fruitListMono: Mono<List<String>> = fruitFlux.collectList()

        StepVerifier.create(fruitListMono)
            .expectNext(listOf("apple", "orange", "banana", "kiwi", "strawberry"))
            .verifyComplete()
    }

    @Test
    fun collectMap() {
        val animalFlux = Flux.just("aardvark", "elephant", "koala", "eagle", "kangaroo")

        val animalMapMono: Mono<Map<Char, String>> = animalFlux.collectMap { a -> a[0] }

        StepVerifier.create(animalMapMono)
            .expectNextMatches { m ->
                m.size == 3 &&
                    m['a'] == "aardvark" &&
                    m['e'] == "eagle" && // elephant -> eagle
                    m['k'] == "kangaroo" // koala -> kangaroo
            }
            .verifyComplete()
    }

    @Test
    fun all() {
        // 모든 단어에서 포함여부
        val animalFlux = Flux.just("aardvark", "elephant", "koala", "eagle", "kangaroo")

        val hasAMono: Mono<Boolean> = animalFlux.all { a -> a.contains("a") }
        StepVerifier.create(hasAMono)
            .expectNext(true)
            .verifyComplete()

        val hasKMono: Mono<Boolean> = animalFlux.all { a -> a.contains("k") }
        StepVerifier.create(hasKMono)
            .expectNext(false)
            .verifyComplete()
    }

    @Test
    fun any() {
        // 적어도 하나의 단어에서 포함여부
        val animalFlux = Flux.just("aardvark", "elephant", "koala", "eagle", "kangaroo")

        val hasTMono = animalFlux.any { a -> a.contains("t") }
        StepVerifier.create(hasTMono)
            .expectNext(true)
            .verifyComplete()

        val hasZMono = animalFlux.any { a -> a.contains("z") }
        StepVerifier.create(hasZMono)
            .expectNext(false)
            .verifyComplete()
    }
}