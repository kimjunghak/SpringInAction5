package tacos.kitchen.kafka.listener

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import tacos.Order

@Component
class KafkaOrderListener {

    @KafkaListener(topics = ["tacocloud.orders.topic"])
    fun handle(order: Order) {
        println("Received from topics: $order")
    }
}