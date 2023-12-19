package tacos

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.springframework.data.rest.core.annotation.RestResource
import java.util.Date

//@Entity
@RestResource(rel = "tacos", path = "tacos")
data class Taco(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @field:NotNull
    @field:Size(min = 5, message = "Name must be at least 5 characters long")
    var name: String? = null,

    @field:ManyToMany(targetEntity = Ingredient::class)
    @field:Size(min = 1, message = "You must choose at least 1 ingredient")
    var ingredients: MutableList<Ingredient>? = mutableListOf(),

    var createdAt: Date? = Date(),
) {

    constructor(name: String): this() {
        this.name = name
    }
    @PrePersist
    fun createdAt() {
        this.createdAt = Date()
    }
}