plugins {
    androidLib
    kotlinAndroid
    kotlin("kapt")
}

android {
    compileSdkVersion(appConfig.compileSdkVersion)
    buildToolsVersion(appConfig.buildToolsVersion)

    defaultConfig {
        minSdkVersion(appConfig.minSdkVersion)
        targetSdkVersion(appConfig.targetSdkVersion)
        versionCode = appConfig.versionCode
        versionName = appConfig.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
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
    kotlinOptions { jvmTarget = JavaVersion.VERSION_1_8.toString() }
}
dependencies {
    implementation(domain)
    implementation(deps.jetbrains.coroutinesAndroid)
    implementation(deps.jetbrains.coroutinesCore)
    implementation(deps.squareup.retrofit)
    implementation(deps.squareup.moshiKotlin)
    implementation(deps.squareup.converterMoshi)
    implementation(deps.squareup.loggingInterceptor)
    implementation(deps.squareup.networkResponseAdapter)
    implementation(deps.squareup.okio)
    implementation(deps.koin.core)
    implementation(deps.koin.android)
}
