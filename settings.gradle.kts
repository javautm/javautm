dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    @Suppress("UnstableApiUsage")
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "javautm-parent"

include(":javautm-core")
include(":spring-boot-starter-javautm")
project(":javautm-core").projectDir = file("core")
project(":spring-boot-starter-javautm").projectDir = file("spring-boot-starter")

includeBuild("build-logic")
