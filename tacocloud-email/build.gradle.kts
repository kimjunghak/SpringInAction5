dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.integration:spring-integration-mail")
    implementation("com.sun.mail:jakarta.mail:2.0.1")
    implementation("org.apache.commons:commons-text:1.11.0")
    implementation("org.springframework.boot:spring-boot-starter-integration")
    testImplementation("org.springframework.integration:spring-integration-test")
}