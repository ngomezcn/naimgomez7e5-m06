import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.serialization") version "1.6.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven ("https://jitpack.io")
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.ktor:ktor-client-core:2.1.0")
    implementation("io.ktor:ktor-client-cio:2.1.0")
    implementation("io.ktor:ktor-client-content-negotiation:2.1.0")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.1.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
    implementation("io.github.pdvrieze.xmlutil:core-jvm:0.84.2")
    implementation("io.github.pdvrieze.xmlutil:serialization-jvm:0.84.2")

    implementation("io.github.kotlin-telegram-bot.kotlin-telegram-bot:telegram:6.0.7")
    implementation("de.brudaswen.kotlinx.serialization:kotlinx-serialization-csv:2.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.4.0")

    implementation("com.h2database:h2:2.1.214")

    implementation("org.slf4j:slf4j-simple:2.0.3")
    implementation("com.impossibl.pgjdbc-ng", "pgjdbc-ng", "0.8.3")

    implementation("org.jetbrains.exposed", "exposed-core", "0.40.1")
    implementation("org.jetbrains.exposed", "exposed-dao", "0.40.1")
    implementation("org.jetbrains.exposed", "exposed-jdbc", "0.40.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}