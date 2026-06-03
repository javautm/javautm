plugins {
    id("javautm-conventions")
}

dependencies {
    api(project(":javautm-core"))
    api(libs.spring.boot.autoconfigure)
}