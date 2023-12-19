package tacos.data

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono
import tacos.Users

interface UserReactiveRepository: ReactiveCrudRepository<Users, Long> {

    fun findByUsername(username: String): Mono<Users>
}