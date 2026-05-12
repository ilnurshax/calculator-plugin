plugins {
    kotlin("jvm") version "2.1.20"
    id("org.jetbrains.intellij.platform") version "2.6.0"
}

group = "com.ilnur.calculator"
version = "1.0.0"

repositories {
    mavenCentral()

    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    implementation("net.objecthunter:exp4j:0.4.8")

    intellijPlatform {
        phpstorm("2026.1")
        bundledPlugin("com.jetbrains.php")
    }
}

intellijPlatform {
    pluginConfiguration {
        ideaVersion {
            sinceBuild = "261"
        }

        name = "Calculator Popup"
        version = "1.0.0"

        vendor {
            name = "Ilnur"
        }
    }
}

kotlin {
    jvmToolchain(21)
}