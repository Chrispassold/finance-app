rootProject.name = "AskBuddy"
rootProject.buildFileName = "build.gradle.kts"

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage") repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    @Suppress("UnstableApiUsage") repositories {
        google()
        mavenCentral()
    }
}

include(":app")
