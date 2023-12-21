package tacos.mongodb

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "ingredients")
class Ingredient(
    @field:Id
    var id: String,
    var name: String,
    var type: Type,
) {
    enum class Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}