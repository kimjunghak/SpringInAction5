package tacos.data

import org.springframework.data.jpa.repository.JpaRepository
import tacos.Taco

interface TacoRepository: JpaRepository<Taco, Long>