package tacos.web.api

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tacos.data.IngredientRepository

@RestController
@RequestMapping(path = ["/ingredients"], produces = ["application/json"])
@CrossOrigin("*")
class IngredientController(
    private val ingredientRepository: IngredientRepository
) {

    @GetMapping
    fun allIngredients() = ingredientRepository.findAll().toList()

}