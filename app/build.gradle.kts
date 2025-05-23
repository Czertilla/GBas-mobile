plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.czertilla.gbas"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.czertilla.gbas"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation (libs.androidx.activity.ktx)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.annotation)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // Firebase BoM
    implementation (platform(libs.firebase.bom))
    implementation (libs.google.firebase.auth)

    // Google OAuth + Credential API
    implementation (libs.androidx.credentials)
    implementation(libs.credentials.play.services.auth)
    implementation(libs.googleid)

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    testImplementation(libs.androidx.room.testing)
    implementation(libs.androidx.room.paging)

    // Retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.logging.interceptor)

    // Kotlin coroutines for Tasks
    implementation( libs.kotlinx.coroutines.play.services)

    implementation (libs.androidx.security.crypto)
}