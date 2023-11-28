package tacos.kitchen

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TacoKitchenApplication

fun main(args: Array<String>) {
    runApplication<TacoKitchenApplication>(*args)
}