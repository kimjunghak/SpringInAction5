package tacos.data

import org.springframework.data.repository.CrudRepository
import tacos.Users
import java.util.*

interface UserRepository: CrudRepository<Users, Long> {
    fun findByUsername(username: String): Optional<Users>
}