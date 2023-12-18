dependencies {
    implementation("io.projectreactor:reactor-core")
    testImplementation("io.projectreactor:reactor-test")
}

tasks.test {
    useJUnitPlatform()
}