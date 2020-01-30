import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.2.4.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    kotlin("jvm") version "1.3.61"
    kotlin("plugin.spring") version "1.3.61"

    id("org.springframework.cloud.contract") version "2.2.1.RELEASE"
}

group = "com.github.ackintosh"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()

    // You need to authenticate to GitHub Packages in order to install spring-cloud-contract-openapi.
    // https://help.github.com/en/github/managing-packages-with-github-packages/configuring-gradle-for-use-with-github-packages#installing-a-package
    // For further details on declaring custom repositories see the document in gradle below:
    // https://docs.gradle.org/current/userguide/declaring_repositories.html
    maven {
        url = uri("https://maven.pkg.github.com/ackintosh/spring-cloud-contract-openapi")
        credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("USER")
            password = project.findProperty("gpr.key") as String? ?: System.getenv("KEY")
        }
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }

    // Please check out the latest version of spring-cloud-openapi.
    // https://github.com/ackintosh/spring-cloud-contract-openapi/packages
    testImplementation("com.github.ackintosh:spring-cloud-contract-openapi:0.0.1-SNAPSHOT")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
