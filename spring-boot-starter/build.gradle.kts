plugins {
    id("javautm-conventions")
    id("dokka-conventions")
}

dependencies {
    api(project(":javautm-core"))
    api(libs.spring.boot.autoconfigure)
}