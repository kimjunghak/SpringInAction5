dependencies {
    api("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    api("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo:4.12.0")

    api(project(":tacocloud-domain-mongodb"))

}