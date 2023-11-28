package tacos.restclient

import org.springframework.hateoas.client.Traverson
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import tacos.Ingredient

@Service
class TacoCloudClient(
    private val restTemplate: RestTemplate,
    private val traverson: Traverson,
) {

    fun getIngredientById(ingredientId: String): Ingredient? {
        return restTemplate.getForObject("http://localhost:8080/ingredients/{id}", Ingredient::class.java, ingredientId)
    }

//    fun getIngredientById(ingredientId: String): Ingredient? {
//        val urlVariables = HashMap<String, Any>()
//        urlVariables["id"] = ingredientId
//        return restTemplate.getForObject("http://localhost:8080/ingredients/{id}", Ingredient::class.java, urlVariables)
//    }

//    fun getIngredientById(ingredientId: String): Ingredient? {
//        val urlVariables = HashMap<String, Any>()
//        urlVariables["id"] = ingredientId
//        val url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/ingredients/{id}")
//            .build(urlVariables)
//        return restTemplate.getForObject(url.toString(), Ingredient::class.java)
//    }

//    fun getIngredientById(ingredientId: String): Ingredient? {
//        val responseEntity =
//            restTemplate.getForEntity("http://localhost:8080/ingredients/{id}", Ingredient::class.java, ingredientId)
//        return responseEntity.body
//    }

    fun updateIngredient(ingredient: Ingredient) {
        restTemplate.put("http://localhost:8080/ingredients/{id}", ingredient, ingredient.id)
    }

    fun deleteIngredient(ingredient: Ingredient) {
        restTemplate.delete("http://localhost:8080/ingredients/{id}", ingredient.id)
    }

    fun createIngredient(ingredient: Ingredient): Ingredient? {
        return restTemplate.postForObject("http://localhost:8080/ingredients", ingredient, Ingredient::class.java)
    }

//    fun createIngredient(ingredient: Ingredient): URI? {
//        return restTemplate.postForLocation("http://localhost:8080/ingredients", ingredient)
//    }

//    fun createIngredient(ingredient: Ingredient): Ingredient? {
//        val responseEntity =
//            restTemplate.postForEntity("http://localhost:8080/ingredients", ingredient, Ingredient::class.java)
//        return responseEntity.body
//    }
}