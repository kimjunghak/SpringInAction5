package tacos.web

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import tacos.Ingredient
import tacos.data.IngredientRepository

@Component
class IngredientByIdConverter(
    private val ingredientRepository: IngredientRepository
) : Converter<String, Ingredient> {
    override fun convert(id: String): Ingredient? {
        return ingredientRepository.findById(id).let { if(it.isEmpty) null else it.get() }
    }
}