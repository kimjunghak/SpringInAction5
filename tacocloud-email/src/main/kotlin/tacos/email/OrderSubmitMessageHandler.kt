package tacos.email

import org.springframework.integration.core.GenericHandler
import org.springframework.messaging.MessageHeaders
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class OrderSubmitMessageHandler(
    private val apiProperties: ApiProperties,
    private val restTemplate: RestTemplate,
): GenericHandler<Order> {

    override fun handle(payload: Order?, headers: MessageHeaders?): Any? {
        restTemplate.postForObject(apiProperties.url!!, payload, String::class.java)
        return null
    }

}
