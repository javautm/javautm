plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation(kotlin("gradle-plugin"))
    implementation(libs.dokka.gradle.plugin)
    implementation(libs.vanniktech.maven.publish)
}