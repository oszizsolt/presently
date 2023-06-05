plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.8.21"
}

group = "io.presently"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
//                implementation(kotlin("stdlib-jdk8"))
//                implementation(project(":common"))
//                implementation(compose.desktop.currentOs)

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
            }
        }
        val jvmTest by getting
    }
    jvmToolchain(11)
}