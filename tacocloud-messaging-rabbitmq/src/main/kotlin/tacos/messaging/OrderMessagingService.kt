package tacos.messaging

import tacos.Order

interface OrderMessagingService {
    fun sendOrder(order: Order)
}
