package tacos.web.react

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.Disposable
import reactor.core.publisher.Mono
import tacos.Ingredient
import java.time.Duration

@Service
class WebClientService(
    private val webClient: WebClient
) {

    /*
    .retrieve() -> ResponseSpec 타입 객체 반환, 응답의 헤더나 쿠키 값을 사용할 수 없다
     */
    fun getIngredientById(ingredientId: String) {
        val ingredientMono = webClient.get().uri("/ingredients/{id}", ingredientId)
            .retrieve()
            .bodyToMono(Ingredient::class.java)

        ingredientMono.subscribe {
            ingredient -> println(ingredient.name)
        }
    }

    fun getIngredients() {
        val ingredients = WebClient.create()
            .get()
            .uri("http://localhost:8080/ingredients")
            .retrieve()
            .bodyToFlux(Ingredient::class.java)

        ingredients.timeout(Duration.ofSeconds(1))
            .subscribe (
                {
                    ingredient -> println(ingredient.id)
                },
                {
                    error -> println(error.message)
                }
            )
    }

    fun postIngredientMono(ingredientMono: Mono<Ingredient>) {
        val result = webClient.post()
            .uri("/ingredients")
            .body(ingredientMono, Ingredient::class.java)
            .retrieve()
            .bodyToMono(Ingredient::class.java)

        result.subscribe {
            ingredient -> println(ingredient.id)
        }
    }

    fun postIngredient(ingredient: Ingredient) {
        val result = webClient.post()
            .uri("/ingredients")
            .bodyValue(ingredient)// syncBody -> bodyValue since 5.2
            .retrieve()
            .bodyToMono(Ingredient::class.java)

        result.subscribe {
                i -> println(i.id)
        }
    }

    fun deleteIngredient(ingredientId: Long) {
        val result: Disposable = webClient.delete()
            .uri("/ingredients/{id}", ingredientId)
            .retrieve()
            .bodyToMono(Void::class.java)
            .subscribe()
    }

    fun handleError(ingredientId: Long) {
        val ingredientMono = webClient.get().uri("/ingredients/{id}", ingredientId)
            .retrieve()
            .onStatus(
                {status -> status == HttpStatus.NOT_FOUND},
                { _ -> Mono.just(RuntimeException("4xx"))}
            )
            .bodyToMono(Ingredient::class.java)

        ingredientMono.subscribe (
            { ingredient -> println(ingredient.name) },
            { error -> println(error.message) } // WebClientResponseException 예외 발생
        )
    }

    /*
    Mono<ClientResponse> 를 이용하여 추가적인 작업을 할 때
     */
    fun exchange(ingredientId: Long) {
        webClient.get()
            .uri("http://localhost:8080/ingredients/{id}", ingredientId)
            .exchangeToMono { response ->
                if (response.headers().header("X_UNAVAILABLE").contains("true")) {
                    Mono.empty<Void>()
                }
                Mono.just(response)
            }
            .flatMap { response -> response.bodyToMono(Ingredient::class.java) }
    }
}