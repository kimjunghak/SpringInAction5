package tacos.kitchen.rabbit.listener

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import tacos.Order

@Profile("rabbitmq-listener")
@Component
class RabbitOrderListener {

    @RabbitListener(queues = ["tacocloud.order.queue"])
    fun receiveOrder(order: Order) {
        println("Received from queue: $order")
    }
}