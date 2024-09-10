plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    alias(libs.plugins.ksp)
    id("kotlin-parcelize")
    alias(libs.plugins.compose.compiler)
    id("kotlin-kapt")
}

android {
    namespace = "com.dotech.walmarthealthcareassignment"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dotech.walmarthealthcareassignment"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    android.buildFeatures.buildConfig = true
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "API_URL", "\"https://gist.githubusercontent.com/\"")
        }
        debug {
            buildConfigField("String", "API_URL", "\"https://gist.githubusercontent.com/\"")
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
        dataBinding = true
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
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.fragment.ktx)

    implementation(project(":views"))
    //CustomViews

    //Icons
    implementation(libs.androidx.material3.icons)

    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    //Coroutines
    implementation(libs.org.jetbrains.kotlinx.coroutine.core)
    implementation(libs.org.jetbrains.kotlinx.coroutine.android)


    //Coroutines Lifecycle Scoops
    implementation(libs.androidx.lifecyle.lifecyleViewmodel)
    implementation(libs.androidx.lifecycle.runtime.ktx)


    //Dagger Hilt
    implementation(libs.com.google.dagger)
    kapt(libs.com.google.dagger.compiler)
    kapt(libs.com.google.dagger.proccessor)

    //Retrofit2
    implementation(libs.com.squareup.retrofit2)
    implementation(libs.com.squareup.retrofit2.converter)

    // OkHTTP3
    implementation(libs.com.squareup.okhttp3)
    implementation(libs.com.squareup.okhttp3.logging)

    //Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.complier)
    implementation(libs.androidx.room.ext)

    //Swipe refresh layout
    implementation(libs.androidx.swiperefreshlayout)
    //recyclerview
    implementation(libs.androidx.recyclerview)
    //cardview
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    //Test-UnitTesting
    testImplementation(libs.junit)
    //Test-Mockito
    testImplementation(libs.org.mockito.mockito.core)
    testImplementation(libs.org.mockito.mockito.inline)
    testImplementation(libs.com.nhaarman.mockitokotlin2)
    //Test-Coroutine
    testImplementation(libs.org.jetbrains.kotlinx.coroutine.test)
    //LiveDAtaAndroidViewModel
    testImplementation(libs.androidx.arch.core.testing)

    //UI Testing
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)


    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)



}