package tacos.data.mongodb

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import tacos.mongodb.Order


interface OrderRepository: ReactiveMongoRepository<Order, Long>