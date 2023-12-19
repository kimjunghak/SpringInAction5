package tacos

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id

//@Entity
data class Ingredient(
    @field:Id
    var id: String,
    var name: String,
    @field:Enumerated(EnumType.STRING)
    var type: Type,
) {
    enum class Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}