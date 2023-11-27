dependencies {
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    api(project(":tacocloud-domain"))
    implementation("org.springframework.boot:spring-boot-starter-data-rest")
    implementation("com.h2database:h2")
}