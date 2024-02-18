import java.net.URI

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

android {
    namespace = "com.aanmfa.composablesearchdialog.lib"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    debugImplementation("androidx.compose.ui:ui-tooling")

    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
}

configure<PublishingExtension> {
    repositories {
        maven {
            name = "GitHubPackages"
            url = URI("https://maven.pkg.github.com/AanMFA/composable-search-dialog")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
