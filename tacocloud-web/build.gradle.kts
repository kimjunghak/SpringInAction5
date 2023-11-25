dependencies {
    api(project(":tacocloud-data"))
    api(project(":tacocloud-domain"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-configuration-processor")

    testImplementation("org.springframework.security:spring-security-test")
}