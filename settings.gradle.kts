pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS) // Pastikan ini diatur
    repositories {
        google() // Repositori Google
        mavenCentral() // Repositori Maven Central
    }
}

rootProject.name = "TokoOnline"
include(":app")
