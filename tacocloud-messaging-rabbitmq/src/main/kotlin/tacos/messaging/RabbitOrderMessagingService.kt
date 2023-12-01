package tacos.messaging

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service
import tacos.Order

@Service
@Qualifier
class RabbitOrderMessagingService(
    private val rabbitTemplate: RabbitTemplate,
) : OrderMessagingService {

//    override fun sendOrder(order: Order) {
//        val message = rabbitTemplate.messageConverter
//            .toMessage(order, MessageProperties())
//        rabbitTemplate.send("tacocloud.order", message)
//    }

    override fun sendOrder(order: Order) {
        println("Sending order $order")
        rabbitTemplate.convertAndSend("tacocloud.order", "kitchens.central.#", order)
    }

//    override fun sendOrder(order: Order) {
//        val messageConverter = rabbitTemplate.messageConverter
//        val props = MessageProperties().apply {
//            setHeader("X_ORDER_SOURCE", "WEB")
//        }
//        val message = messageConverter.toMessage(order, props)
//        rabbitTemplate.send("tacocloud.order", message)
//    }

//    override fun sendOrder(order: Order) {
//        rabbitTemplate.convertAndSend("tacocloud.order", order) { message ->
//            message.messageProperties.setHeader("X_ORDER_SOURCE", "WEB")
//            message
//        }
//    }
}