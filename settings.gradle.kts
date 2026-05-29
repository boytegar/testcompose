rootProject.name = "test"

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
                includeGroupByRegex("android.*")
            }
        }
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
                includeGroupByRegex("android.*")
            }
        }
        mavenCentral()
    }
}

include(":composeApp")
include(":androidApp")
include(":iosApp")
include(":core:common")
include(":core:database")
include(":core:network")
include(":core:ui")
include(":core:testing")
include(":shared")
include(":features:auth")
include(":features:home")
include(":features:detail")
include(":features:favorites")

