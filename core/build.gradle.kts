plugins {
    id("javautm-conventions")
    id("dokka-conventions")
    id("publish-conventions")
}

dependencies {
    implementation(libs.spring.boot.dependencies)

    api(libs.spring.webmvc)
    compileOnly(libs.jakarta.servlet.api)

    testImplementation(libs.spring.boot.starter.webmvc.test)
}

mavenPublishing {
    pom {
        name.set("Java UTM Core")
        description.set("UTM tracking library for Java")
    }
}