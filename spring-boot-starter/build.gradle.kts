plugins {
    id("javautm-conventions")
    id("dokka-conventions")
    id("publish-conventions")
}

dependencies {
    api(project(":javautm-core"))
    api(libs.spring.boot.autoconfigure)
}

mavenPublishing {
    pom {
        name.set("JavaUTM Spring Boot Starter")
        description.set("UTM tracking library for Java")
    }
}