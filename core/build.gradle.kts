plugins {
    id("javautm-conventions")
}

dependencies {
    implementation(libs.spring.boot.dependencies)

    api(libs.spring.webmvc)
    compileOnly(libs.jakarta.servlet.api)

    testImplementation(libs.spring.boot.starter.webmvc.test)
}
