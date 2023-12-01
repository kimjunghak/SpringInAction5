package tacos.web.api

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import tacos.Order
import tacos.data.OrderRepository
import tacos.messaging.OrderMessagingService

@RestController
@RequestMapping(path = ["/orders"], produces = ["application/json"])
@CrossOrigin(origins = ["*"])
class OrderApiController(
    private val orderRepository: OrderRepository,
    @Qualifier("rabbitOrderMessagingService")
    private val rabbitOrderMessage: OrderMessagingService,
    @Qualifier("kafkaOrderMessagingService")
    private val kafkaOrderMessage: OrderMessagingService,
) {
    @GetMapping(produces = ["application/json"])
    fun allOrders() = orderRepository.findAll().toList()

    @PostMapping(produces = ["application/json"])
    @ResponseStatus(HttpStatus.CREATED)
    fun postOrder(@RequestBody order: Order) {
//        rabbitOrderMessage.sendOrder(order)
        kafkaOrderMessage.sendOrder(order)
        orderRepository.save(order)
    }

    @PutMapping(path = ["/{orderId}"], produces = ["application/json"])
    fun pathOrder(@PathVariable orderId: Long,
                  @RequestBody patch: Order): Order {
        val order = orderRepository.findById(orderId).get()
        patch.apply {
            if(deliveryName != null) order.deliveryName = deliveryName
            if(deliveryStreet != null) order.deliveryStreet = deliveryStreet
            if(deliveryCity != null) order.deliveryCity = deliveryCity
            if(deliveryState != null) order.deliveryState = deliveryState
            if(deliveryZip != null) order.deliveryZip = deliveryZip
            if(ccNumber != null) order.ccNumber = ccNumber
            if(ccExpiration != null) order.ccExpiration = ccExpiration
            if(ccCVV != null) order.ccCVV = ccCVV
        }
        return orderRepository.save(order)
    }

    @DeleteMapping(path = ["/{orderId}"])
    fun deleteOrder(@PathVariable orderId: Long) {
        try {
            orderRepository.deleteById(orderId)
        } catch (_: EmptyResultDataAccessException) { }
    }

}