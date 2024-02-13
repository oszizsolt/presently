import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "1.5.0"
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
                implementation(project(":service"))
                implementation(project(":common"))

                implementation(compose.desktop.currentOs) {
                    exclude("org.jetbrains.compose.material")
                }

                implementation("com.bybutter.compose:compose-jetbrains-expui-theme:2.2.0")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

                val exposedVersion = "0.46.0"

                implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
                implementation("org.jetbrains.exposed:exposed-crypt:$exposedVersion")
                implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
                implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
                implementation("org.jetbrains.exposed:exposed-kotlin-datetime:$exposedVersion")
                implementation("org.xerial:sqlite-jdbc:3.44.1.0")
                implementation("com.h2database:h2:2.2.224")

                // TODO do not use the intergrated module, but use the separated one, with automatic detection of installed ndi, and link to ndi installation if needed
                // https://github.com/WalkerKnapp/devolay#%EF%B8%8Flicensing-considerations%EF%B8%8F
                implementation("me.walkerknapp:devolay:2.1.0:integrated")
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "presently"
            packageVersion = "1.0.0"
        }
    }
}
