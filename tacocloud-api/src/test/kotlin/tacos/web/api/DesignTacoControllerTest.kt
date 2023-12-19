package tacos.web.api

import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import tacos.Ingredient
import tacos.Taco
import tacos.data.TacoRepository

class DesignTacoControllerTest {

    @Test
    fun shouldReturnRecentTacos() {
        val tacos = arrayOf(
            testTaco(1),
            testTaco(2),
        )

        val tacoFlux = Flux.fromArray(tacos)

        val tacoRepository: TacoRepository = Mockito.mock(TacoRepository::class.java)

        given(tacoRepository.findAll()).willReturn(tacoFlux)

        val testClient = WebTestClient.bindToController(DesignTacoController(tacoRepository)).build()

        testClient.get().uri("/design/recent")
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$").isArray
            .jsonPath("$").isNotEmpty
            .jsonPath("$[0].id").isEqualTo(tacos[0].id.toString())
            .jsonPath("$[0].name").isEqualTo(tacos[0].name.toString())
            .jsonPath("$[1].id").isEqualTo(tacos[1].id.toString())
            .jsonPath("$[1].name").isEqualTo(tacos[1].name.toString())
            .jsonPath("$[3]").doesNotExist()
    }

    @Test
    fun shouldSaveATaco() {
        val tacoRepository = Mockito.mock(TacoRepository::class.java)

        val unsavedTacoMono = Mono.just(testTaco(null))
        val savedTaco = testTaco(null)
        savedTaco.id = 1L
        val savedTacoMono = Mono.just(savedTaco)

        given(tacoRepository.save(any())).willReturn(savedTacoMono)

        val testClient: WebTestClient = WebTestClient.bindToController(DesignTacoController(tacoRepository)).build()

        testClient.post()
            .uri("/design")
            .contentType(MediaType.APPLICATION_JSON)
            .body(unsavedTacoMono, Taco::class.java)
            .exchange()
            .expectStatus().isCreated
            .expectBody(Taco::class.java)
            .isEqualTo(savedTaco)
    }

    private fun testTaco(number: Long?): Taco {
        val taco = Taco()
        taco.id = number
        taco.name = "Taco $number"
        val ingredients = arrayListOf(
            Ingredient("INGA", "Ingredient A", Ingredient.Type.WRAP),
            Ingredient("INGB", "Ingredient B", Ingredient.Type.PROTEIN),
        )
        taco.ingredients = ingredients
        return taco
    }
}