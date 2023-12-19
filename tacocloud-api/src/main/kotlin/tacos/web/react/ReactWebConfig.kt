package tacos.web.react

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class ReactWebConfig {

    @Bean
    fun webClient(): WebClient {
        return WebClient.create("http://localhost:8080")
    }
}