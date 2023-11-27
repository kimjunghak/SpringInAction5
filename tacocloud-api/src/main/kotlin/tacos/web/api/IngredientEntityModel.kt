package tacos.web.api

import org.springframework.hateoas.RepresentationModel
import tacos.Ingredient

class IngredientEntityModel(
    ingredient: Ingredient,
): RepresentationModel<IngredientEntityModel>() {

    val name: String = ingredient.name
    val type: Ingredient.Type = ingredient.type
}