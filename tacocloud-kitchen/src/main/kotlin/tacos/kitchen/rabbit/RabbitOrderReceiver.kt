package tacos.kitchen.rabbit

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.context.annotation.Profile
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Component
import tacos.Order
import tacos.kitchen.OrderReceiver

@Profile("rabbitmq-template")
@Component("templateOrderReceiver")
class RabbitOrderReceiver(
    private val rabbitTemplate: RabbitTemplate,
): OrderReceiver {

    override fun receiveOrder(): Order? {
        return rabbitTemplate.receiveAndConvert("tacocloud.order.queue", object: ParameterizedTypeReference<Order>() {})
    }
}