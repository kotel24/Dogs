@file:Suppress("UnstableApiUsage")

rootProject.name = "Dogs"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

// APP
include(":app")

//// COMMON
//include(":common:mvi:mvi-general")
//include(":common:mvi:mvi-koin-voyager")
//include(":common:logger")
//include(":common:utils")
//
//// CORE
include(":core:network")
include(":core:database")
//include(":core:recources")
//
//// FEATURES
//include(":features:main-screen:main-screen-api")
//include(":features:main-screen:main-screen-impl")
//include(":features:dog-screens:dog-screens-api")
//include(":features:dog-screens:dog-screens-impl")
//
//// COMPONENTS
//include(":components:dogs:dogs")