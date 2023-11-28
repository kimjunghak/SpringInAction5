package tacos.restclient

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.hateoas.MediaTypes
import org.springframework.hateoas.client.Traverson
import org.springframework.web.client.RestTemplate
import java.net.URI

@Configuration
class RestConfig {

    @Bean
    fun restTemplate(): RestTemplate = RestTemplate()

    @Bean
    fun traverson(): Traverson = Traverson(
        URI.create("http://localhost:8080/api"),
        MediaTypes.HAL_JSON
    )
}