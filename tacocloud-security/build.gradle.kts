dependencies {
    api(project(":tacocloud-domain"))
    api(project(":tacocloud-data"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    testImplementation("org.springframework.security:spring-security-test")
}
