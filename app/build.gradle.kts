plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.dagger.hilt.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.mycalories"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mycalories"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Compose ConstraintLayout
    implementation(libs.constraintlayout)

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.compose.viewModel)
    ksp(libs.hilt.compiler)

    // Room
    implementation(libs.room.database)
    implementation(libs.room.database.coroutines.support)
    ksp(libs.room.database.compiler)

    // Gson
    implementation(libs.gson)

    // Coroutines
    implementation(libs.coroutines)

    // ViewModel + Coroutines Support
    implementation(libs.coroutines.viewmodel.support)

    // Lifecycle runtime for Compose (coroutine scope awareness)
    implementation(libs.coroutines.compose.lifecyle.awarreness)

    // kotlin serialization for navigation
    implementation(libs.navigation.compose)
    implementation(libs.kotlinx.serialization.json)

}