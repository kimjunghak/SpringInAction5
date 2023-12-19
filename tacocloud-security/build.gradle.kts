dependencies {
    api(project(":tacocloud-domain"))
    api(project(":tacocloud-data"))
    api(project(":tacocloud-api"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    api("org.springframework.boot:spring-boot-starter-security")
    testImplementation("org.springframework.security:spring-security-test")
}
