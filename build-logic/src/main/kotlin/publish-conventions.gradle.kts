plugins {
    id("dokka-conventions")
    id("com.vanniktech.maven.publish")
}

mavenPublishing {
    publishToMavenCentral()
    signAllPublications()

    pom {
        url.set("https://github.com/javautm/javautm")

        licenses {
            license {
                name.set("Apache License, Version 2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0")
            }
        }

        developers {
            developer {
                id.set("geronimogm")
                name.set("Gerónimo Gonzalez Martino")
            }
        }

        scm {
            connection.set("scm:git:git://github.com/javautm/javautm.git")
            developerConnection.set("scm:git:ssh://github.com/javautm/javautm.git")
            url.set("https://github.com/javautm/javautm")
        }
    }
}
