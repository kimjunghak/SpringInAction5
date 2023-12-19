dependencies {
    api(project(":tacocloud-domain"))
    api(project(":tacocloud-api"))
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    api("org.springframework.boot:spring-boot-starter-data-rest")
    implementation("com.h2database:h2")
}