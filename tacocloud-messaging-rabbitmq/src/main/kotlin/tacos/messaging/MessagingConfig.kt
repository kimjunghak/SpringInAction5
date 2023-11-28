package tacos.messaging

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class MessagingConfig{

    @Bean
    fun messageConverter() = Jackson2JsonMessageConverter()

    @Bean
    fun queue(): Queue {
        return Queue("tacocloud.order.queue", false)
    }

    @Bean
    fun exchange(): TopicExchange {
        return TopicExchange("tacocloud.order")
    }

    @Bean
    fun binding(queue: Queue?, exchange: TopicExchange?): Binding {
        return BindingBuilder.bind(queue).to(exchange).with("kitchens.central.#")
    }
}