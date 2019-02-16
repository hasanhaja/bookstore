plugins {
    id("kotlin2js") version "1.3.21"
}

group = "com.example.kotlinjs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-js"))
}
