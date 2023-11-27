package tacos.web.api

import org.springframework.hateoas.server.RepresentationModelAssembler
import tacos.Ingredient

class IngredientRepresentationModelAssembler: RepresentationModelAssembler<Ingredient, IngredientEntityModel> {

    override fun toModel(entity: Ingredient): IngredientEntityModel {
        return IngredientEntityModel(entity)
    }

}