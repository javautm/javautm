plugins {
    id("javautm-conventions")
    id("dokka-conventions")
}

dependencies {
    dokka(project(":javautm-core"))
    dokka(project(":spring-boot-starter-javautm"))
}

dokka {
    moduleName.set("Java UTM")
}