package tacos.email

class Order(
    val email: String,
    val tacos: MutableList<Taco> = mutableListOf()
) {

    fun addTaco(taco: Taco) {
        this.tacos.add(taco)
    }
}