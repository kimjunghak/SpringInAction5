dependencies {
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-data-rest")
    api(project(":tacocloud-data-mongodb"))
    api(project(":tacocloud-security"))
}