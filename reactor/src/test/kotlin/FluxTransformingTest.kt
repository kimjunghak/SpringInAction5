import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import reactor.test.StepVerifier
import java.time.Duration
import java.util.*

class FluxTransformingTest {

    @Test
    fun skipAFew() {
        val skipFlux = Flux.just("one", "two", "skip a few", "ninety nine", "one hundred")
            .skip(3)

        StepVerifier.create(skipFlux)
            .expectNext("ninety nine", "one hundred")
            .verifyComplete()
    }

    @Test
    fun skipAFewSeconds() {
        val skipFlux = Flux.just("one", "two", "skip a few", "ninety nine", "one hundred")
            .delayElements(Duration.ofSeconds(1))
            .skip(Duration.ofSeconds(4))

        StepVerifier.create(skipFlux)
            .expectNext("ninety nine", "one hundred")
            .verifyComplete()
    }

    @Test
    fun take() {
        val nationalParkFlux = Flux.just("Yellowstone", "Yosemite", "Grand Canyon", "Zion", "grand Teton")
            .take(3)

        StepVerifier.create(nationalParkFlux)
            .expectNext("Yellowstone", "Yosemite", "Grand Canyon")
            .verifyComplete()
    }

    @Test
    fun takeAFewSeconds() {
        val nationalParkFlux = Flux.just("Yellowstone", "Yosemite", "Grand Canyon", "Zion", "grand Teton")
            .delayElements(Duration.ofSeconds(1))
            .take(Duration.ofMillis(3500))

        StepVerifier.create(nationalParkFlux)
            .expectNext("Yellowstone", "Yosemite", "Grand Canyon")
            .verifyComplete()
    }

    @Test
    fun filter() {
        val nationalParkFlux = Flux.just("Yellowstone", "Yosemite", "Grand Canyon", "Zion", "grand Teton")
            .filter {np -> !np.contains(" ")}

        StepVerifier.create(nationalParkFlux)
            .expectNext("Yellowstone", "Yosemite", "Zion")
            .verifyComplete()
    }

    @Test
    fun distinct() {
        val animalFlux = Flux.just("dog", "cat", "bird", "dog", "bird", "anteater")
            .distinct()

        StepVerifier.create(animalFlux)
            .expectNext("dog", "cat", "bird", "anteater")
            .verifyComplete()
    }

    companion object {
        data class Player(val firstName: String, val secondName: String)
    }

    @Test
    fun map() {
        val playerFlux: Flux<Player> = Flux.just("Michael Jordan", "Scottie Pippen", "Steve Kerr")
            .map { s ->
                val split = s.split("\\s".toRegex())
                Player(split[0], split[1])
            }

        StepVerifier.create(playerFlux)
            .expectNext(Player("Michael", "Jordan"))
            .expectNext(Player("Scottie", "Pippen"))
            .expectNext(Player("Steve", "Kerr"))
            .verifyComplete()
    }

    @Test
    fun flatMap() {
        val playerFlux = Flux.just("Michael Jordan", "Scottie Pippen", "Steve Kerr")
            .flatMap { n ->
                Mono.just(n)
                    .map { p ->
                        val split = p.split("\\s".toRegex())
                        Player(split[0], split[1])
                    }
                    .subscribeOn(Schedulers.parallel())
                /*
                Schedulers 동시성 모델
                .immediate(): 현재 스레드에서 구독 실행
                .single(): 단일의 재사용 가능한 스레드에서 구독 실행 -> 이후 모두 동일한 스레드 재사용
                .newSingle(): 매 호출마다 전용 스레드에서 구독 실행
                .elastic(): 무한하고 신축성 있는 풀에서 가져온 작업 스레드에서 구독 실행
                .parallel(): 고정된 크기의 풀(CPU 코어의 개수)에서 가져온 작업 스레드에서 구독 실행
                 */
            }

        val playerList: List<Player> = listOf(Player("Michael", "Jordan"), Player("Scottie", "Pippen"), Player("Steve", "Kerr"))

        //순서 보장 X
        StepVerifier.create(playerFlux)
            .expectNextMatches { p: Player -> playerList.contains(p) }
            .expectNextMatches { p: Player -> playerList.contains(p) }
            .expectNextMatches { p: Player -> playerList.contains(p) }
            .verifyComplete()
    }
}
