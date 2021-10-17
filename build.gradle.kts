import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.spring") version "1.5.31"
}

group = "com.otienosamwel"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
    maven {
        url = uri("https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-gcp-starter-data-datastore")
    }
}

extra["springCloudGcpVersion"] = "2.0.4"
extra["springCloudVersion"] = "2020.0.4"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-rest:2.5.5")
    implementation("org.springframework.boot:spring-boot-starter-web:2.5.5")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0")
    implementation("com.google.cloud:spring-cloud-gcp-starter:2.0.4")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.5.5")

    implementation("org.springframework.cloud:spring-cloud-gcp-starter-data-datastore:1.2.8.RELEASE")
    implementation("org.springframework.cloud:spring-cloud-gcp-starter-storage:1.2.8.RELEASE")
    implementation("org.springframework.cloud:spring-cloud-gcp-starter-vision:1.2.8.RELEASE")



}

dependencyManagement {
    imports {
        mavenBom("com.google.cloud:spring-cloud-gcp-dependencies:${property("springCloudGcpVersion")}")
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
