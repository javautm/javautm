plugins {
    id("org.jetbrains.dokka")
}

dokka {
    dokkaSourceSets.configureEach {
        sourceLink {
            localDirectory.set(rootDir)
            remoteUrl("https://github.com/javautm/javautm/tree/main")
        }
    }
}