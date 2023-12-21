package tacos.data.mongodb

import org.springframework.boot.autoconfigure.security.SecurityProperties.User
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono
import tacos.mongodb.Users


interface UserRepository: ReactiveMongoRepository<Users, Long> {

    fun findByUsername(username: String): Mono<User>

    fun findByEmail(email: String): Mono<User>
}