package tacos.messaging

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import tacos.Order

@Service
@Qualifier
class KafkaOrderMessagingService(
    private val kafkaTemplate: KafkaTemplate<String, Order>,
): OrderMessagingService {
    override fun sendOrder(order: Order) {
        kafkaTemplate.send("tacocloud.orders.topic", order)
    }
}