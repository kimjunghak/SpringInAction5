package tacos

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.crypto.password.PasswordEncoder
import tacos.Ingredient.Type
import tacos.data.IngredientRepository
import tacos.data.OrderRepository
import tacos.data.TacoRepository
import tacos.data.UserRepository


@Profile("!prod")
@Configuration
class DevelopmentConfig {

    @Bean
    fun dataLoader(
        ingredientRepository: IngredientRepository,
        userRepository: UserRepository,
        tacoRepository: TacoRepository,
        orderRepository: OrderRepository,
        encoder: PasswordEncoder
    ): CommandLineRunner {
        return CommandLineRunner {
            val flourTortilla = Ingredient("FLTO", "Flour Tortilla", Type.WRAP)
            val cornTortilla = Ingredient("COTO", "Corn Tortilla", Type.WRAP)
            val groundBeef = Ingredient("GRBF", "Ground Beef", Type.PROTEIN)
            val carnitas = Ingredient("CARN", "Carnitas", Type.PROTEIN)
            val tomatoes = Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES)
            val lettuce = Ingredient("LETC", "Lettuce", Type.VEGGIES)
            val cheddar = Ingredient("CHED", "Cheddar", Type.CHEESE)
            val jack = Ingredient("JACK", "Monterrey Jack", Type.CHEESE)
            val salsa = Ingredient("SLSA", "Salsa", Type.SAUCE)
            val sourCream = Ingredient("SRCR", "Sour Cream", Type.SAUCE)
            ingredientRepository.save(flourTortilla)
            ingredientRepository.save(cornTortilla)
            ingredientRepository.save(groundBeef)
            ingredientRepository.save(carnitas)
            ingredientRepository.save(tomatoes)
            ingredientRepository.save(lettuce)
            ingredientRepository.save(cheddar)
            ingredientRepository.save(jack)
            ingredientRepository.save(salsa)
            ingredientRepository.save(sourCream)


            userRepository.save(
                Users(
                    username = "habuma",
                    password = encoder.encode("password"),
                    fullName = "Craig Walls",
                    street = "123 North Street",
                    city = "Cross Roads",
                    state = "TX",
                    zip = "76227",
                    phoneNumber = "123-123-1234"
                )
            )

            val taco1 = Taco()
            taco1.name = "Carnivore"
            taco1.ingredients = mutableListOf(flourTortilla, groundBeef, carnitas, sourCream, salsa, cheddar)
            tacoRepository.save(taco1)

            val taco2 = Taco()
            taco2.name = "Bovine Bounty"
            taco2.ingredients = mutableListOf(cornTortilla, groundBeef, cheddar, jack, sourCream)
            tacoRepository.save(taco2)

            val taco3 = Taco()
            taco3.name = "Veg-Out"
            taco3.ingredients = mutableListOf(flourTortilla, cornTortilla, tomatoes, lettuce, salsa)
            tacoRepository.save(taco3)


            val order = Order()
            order.deliveryName = "John Doe"
            order.deliveryStreet = "1234 Culinary Blvd."
            order.deliveryCity = "Foodsville"
            order.deliveryState = "CO"
            order.deliveryZip = "81019"
            order.ccNumber = "4111111111111111"
            order.ccExpiration = "10/19"
            order.ccCVV = "123"
            order.tacos = mutableListOf(taco1, taco2)
            orderRepository.save(order)
        }
    }
}