plugins {
    kotlin("jvm") version "2.1.0"
    id("application")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.+")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.+")
}

tasks.test {
    useJUnitPlatform()
}
application {
    mainClass.set("org.example.MainKt")  // Make sure this matches your actual main class path
}