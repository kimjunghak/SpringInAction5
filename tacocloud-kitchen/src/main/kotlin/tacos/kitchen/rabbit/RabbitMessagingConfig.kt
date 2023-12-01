package tacos.kitchen.rabbit

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RabbitMessagingConfig {

    @Bean
    fun messageConverter() = Jackson2JsonMessageConverter()
}