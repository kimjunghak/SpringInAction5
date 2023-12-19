package tacos.data

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import tacos.Taco

//interface TacoRepository: JpaRepository<Taco, Long>
interface TacoRepository: ReactiveCrudRepository<Taco, Long>