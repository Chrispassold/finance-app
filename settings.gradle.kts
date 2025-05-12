rootProject.name = "AskBuddy"
rootProject.buildFileName = "build.gradle.kts"

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage") repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    @Suppress("UnstableApiUsage") repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

include(":app")
include(":data")
include(":domain")
include(":presentation")
include(":core")
