package tacos.web.api

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DesignTacoControllerWebTest @Autowired constructor(private val testClient: WebTestClient) {

    @Test
    @Disabled
    @DisplayName("Not Test")
    fun shouldReturnRecentTacos() {
        testClient.get().uri("/design/recent")
            .accept(MediaType.APPLICATION_JSON).exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$[?(@.id == 'TACO1')].name")
            .isEqualTo("Canivore")
            .jsonPath("$[?(@.id == 'TACO2')].name")
            .isEqualTo("Bovine Bounty")
            .jsonPath("$[?(@.id == 'TACO3')].name")
            .isEqualTo("Veg-Out")
    }
}