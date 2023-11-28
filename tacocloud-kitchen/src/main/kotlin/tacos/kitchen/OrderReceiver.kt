package tacos.kitchen

import tacos.Order

interface OrderReceiver {
    fun receiveOrder(): Order?
}
