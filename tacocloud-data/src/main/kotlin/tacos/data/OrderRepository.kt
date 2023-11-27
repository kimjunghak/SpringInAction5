package tacos.data

import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import tacos.Order
import tacos.Users

import java.util.*

interface OrderRepository: CrudRepository<Order, Long> {

    fun readOrdersByDeliveryZipAndPlacedAtBetween(deliveryZip: String, startDate: Date, endDate: Date): List<Order>

    fun findByUsersOrderByPlacedAtDesc(users: Users, pageable: Pageable): List<Order>
}