dependencies {
//    implementation("org.springframework.boot:spring-boot-starter-web")
    api("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-hateoas")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    runtimeOnly("io.netty:netty-resolver-dns-native-macos:4.1.104.Final:osx-aarch_64")
    api(project(":tacocloud-data"))
    api(project(":tacocloud-domain"))
    api(project(":tacocloud-messaging-rabbitmq"))
    api(project(":tacocloud-data-mongodb"))
    api(project(":tacocloud-domain-mongodb"))
}