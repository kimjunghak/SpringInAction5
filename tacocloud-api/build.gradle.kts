dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-hateoas")
    api(project(":tacocloud-data"))
    api(project(":tacocloud-messaging-rabbitmq"))
}