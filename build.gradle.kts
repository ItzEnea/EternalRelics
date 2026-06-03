plugins {
    java
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "dev.eternalrelics"
version = "1.0.0"
description = "A legendary items and faction conflict plugin for Paper 1.21.11"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.aikar.co/content/groups/aikar/")
    maven("https://repo.extendedclip.com/contents/releases/")
    maven("https://libraries.minecraft.net")
}

dependencies {
    // Paper API
    compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
    
    // Adventure API
    implementation("net.kyori:adventure-api:4.14.0")
    implementation("net.kyori:adventure-text-minimessage:4.14.0")
    
    // Lombok for cleaner code
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    
    // JSON persistence
    implementation("com.google.code.gson:gson:2.10.1")
    
    // SQLite (optional, for future expansion)
    implementation("org.xerial:sqlite-jdbc:3.44.0.0")
    
    // PlaceholderAPI (optional)
    compileOnly("me.clip:placeholderapi:2.11.5")
    
    // Logging
    implementation("org.slf4j:slf4j-api:2.0.9")
    
    // Testing
    testImplementation("junit:junit:4.13.2")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
        options.release = 21
    }
    
    shadowJar {
        archiveFileName = "EternalRelics-${version}-all.jar"
        minimize()
    }
    
    build {
        dependsOn(shadowJar)
    }
    
    processResources {
        filesMatching("plugin.yml") {
            expand(project.properties)
        }
    }
}
