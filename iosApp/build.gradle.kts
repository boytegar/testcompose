plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kmp.library)
}

kotlin {
    android {
        namespace = "apps.boytegar.dev.iosapp"
        compileSdk = 36
        minSdk = 23
    }

    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(project(":composeApp"))
            implementation(project(":core:common"))
            implementation(project(":core:ui"))
            implementation(project(":core:network"))
            implementation(project(":core:database"))
            implementation(project(":shared"))
            implementation(project(":features:auth"))
            implementation(project(":features:home"))
            implementation(project(":features:detail"))
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}
