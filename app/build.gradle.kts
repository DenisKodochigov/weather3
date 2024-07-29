plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.serialization)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.weather3"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.weather3"
        minSdk = 27
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.12"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    //DataStore
    implementation(libs.androidx.datastore)
    //LifeCycle
    implementation (libs.androidx.lifecycle.runtime.ktx)
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.lifecycle.viewmodel.compose)
    implementation (libs.androidx.fragment.ktx)
    //Jetpack  Compose
    implementation (libs.androidx.ui)
    implementation(libs.accompanist.permissions)
    //Tooling support (Previews, etc.)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    //Hilt
    implementation (libs.hilt.android)
    implementation (libs.androidx.hilt.navigation.compose)
    ksp (libs.dagger.compiler)
    ksp (libs.hilt.compiler)
    testImplementation (libs.hilt.android.testing)
    //Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation (libs.androidx.foundation)
    implementation (libs.androidx.foundation.layout)
    //Navigation
    implementation (libs.androidx.navigation.compose)
    // Material Design
    implementation (libs.androidx.material3)
    implementation (libs.androidx.ui.text.google.fonts)
    implementation (libs.androidx.material.icons.core)
    implementation (libs.androidx.material.icons.extended)
    //Color Palette
    implementation (libs.androidx.palette.ktx)
    // Retrofit
    implementation (libs.retrofit)
    implementation (libs.retrofit.moshi)
    implementation (libs.okhttp3)
    //Moshi
    implementation (libs.moshi)
    implementation (libs.moshi.kotlin)
    ksp (libs.moshi.kotlin.codegen)
    //GSM
    implementation(libs.gsm)
    //implementation(libs.androidx.datastore.core)
    implementation(libs.serialization.json)
    implementation(libs.immutable)
    debugImplementation(libs.androidx.ui.test.manifest)
    //Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}