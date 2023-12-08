package tacos.email

import jakarta.mail.Message
import jakarta.mail.internet.InternetAddress
import org.apache.commons.text.similarity.LevenshteinDistance
import org.springframework.integration.mail.transformer.AbstractMailMessageTransformer
import org.springframework.integration.support.AbstractIntegrationMessageBuilder
import org.springframework.integration.support.MessageBuilder
import org.springframework.stereotype.Component
import java.util.*
import java.util.logging.Logger


@Component
class EmailToOrderTransformer: AbstractMailMessageTransformer<Order>() {
    override fun doTransform(mailMessage: Message): AbstractIntegrationMessageBuilder<Order> {
        val tacoOrder = processPayload(mailMessage)
        return MessageBuilder.withPayload(tacoOrder!!)
    }

    private fun processPayload(mailMessage: Message): Order? {
        try {
            val subject = mailMessage.subject
            if (subject.uppercase(Locale.getDefault()).contains("TACO ORDER")) {
                val email = (mailMessage.from[0] as InternetAddress).address
                val content = mailMessage.content.toString()
                return parseEmailToOrder(email, content)
            }
        } catch (e: Exception) {
            logger.info(e.message)
        }
        return null
    }

    private fun parseEmailToOrder(email: String, content: String): Order {
        val order = Order(email)
        val lines = content.split("\\r?\\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (line in lines) {
            if (line.trim { it <= ' ' }.isNotEmpty() && line.contains(":")) {
                val lineSplit = line.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val tacoName = lineSplit[0].trim { it <= ' ' }
                val ingredients = lineSplit[1].trim { it <= ' ' }
                val ingredientsSplit = ingredients.split(",".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()
                val ingredientCodes: MutableList<String> = ArrayList()
                for (ingredientName in ingredientsSplit) {
                    val code = lookupIngredientCode(ingredientName.trim { it <= ' ' })
                    if (code != null) {
                        ingredientCodes.add(code)
                    }
                }
                val taco = Taco(tacoName)
                taco.ingredients = ingredientCodes
                order.addTaco(taco)
            }
        }
        return order
    }

    private fun lookupIngredientCode(ingredientName: String): String? {
        for (ingredient in ALL_INGREDIENTS) {
            val ucIngredientName = ingredientName.uppercase(Locale.getDefault())
            if (LevenshteinDistance.getDefaultInstance().apply(ucIngredientName, ingredient.name) < 3 ||
                ucIngredientName.contains(ingredient.name) ||
                ingredient.name.contains(ucIngredientName)
            ) {
                return ingredient.code
            }
        }
        return null
    }

    companion object {
        private val logger = Logger.getLogger(EmailToOrderTransformer::class.java.name)

        private val ALL_INGREDIENTS = arrayOf(
            Ingredient("FLTO", "FLOUR TORTILLA"),
            Ingredient("COTO", "CORN TORTILLA"),
            Ingredient("GRBF", "GROUND BEEF"),
            Ingredient("CARN", "CARNITAS"),
            Ingredient("TMTO", "TOMATOES"),
            Ingredient("LETC", "LETTUCE"),
            Ingredient("CHED", "CHEDDAR"),
            Ingredient("JACK", "MONTERREY JACK"),
            Ingredient("SLSA", "SALSA"),
            Ingredient("SRCR", "SOUR CREAM")
        )
    }
}
