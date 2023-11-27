package tacos.web.api

import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.core.Relation
import tacos.Taco
import java.util.*

@Relation(value = "taco", collectionRelation = "tacos")
class TacoEntityModel(
    taco: Taco,
): RepresentationModel<TacoEntityModel>() {
    private val ingredientAssembler = IngredientRepresentationModelAssembler()

    val name: String = taco.name!!
    val createdAt: Date = taco.createdAt!!
    val ingredients: List<IngredientEntityModel> = ingredientAssembler.toCollectionModel(taco.ingredients!!).content.toList()
}