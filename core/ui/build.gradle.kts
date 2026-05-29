plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.kmp.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
}

kotlin {
    android {
        namespace = "apps.boytegar.dev.core.ui"
        compileSdk = 36
        minSdk = 23
        androidResources.enable = true
    }

    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            api(libs.compose.runtime)
            api(libs.compose.ui)
            api(libs.compose.foundation)
            api(libs.compose.material3)
            implementation(libs.materialKolor)
            implementation(project(":core:common"))
            implementation(project(":shared"))
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}
