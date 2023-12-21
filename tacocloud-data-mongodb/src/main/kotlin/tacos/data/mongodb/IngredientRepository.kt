package tacos.data.mongodb

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.web.bind.annotation.CrossOrigin
import tacos.mongodb.Ingredient

@CrossOrigin("*")
interface IngredientRepository: ReactiveCrudRepository<Ingredient, String>