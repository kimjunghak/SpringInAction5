package tacos.data.mongodb

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux
import tacos.mongodb.Taco

interface TacoRepository: ReactiveMongoRepository<Taco, Long> {

    /*
    ReactiveMongoRepository 다른 데이터베이스에는 사용할 수 없음, MongoDB 특화
    최적화된 insert() 메서드가 유용함
     */

    fun findByOrderByCreatedAtDesc(): Flux<Taco>
}