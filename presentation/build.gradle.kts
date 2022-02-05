plugins {
    androidLib
    kotlinAndroid
    kotlin("kapt")
    id("kotlin-android-extensions")
    id("androidx.navigation.safeargs.kotlin")
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

    buildFeatures { viewBinding = true }
}

dependencies {
    implementation(domain)
    implementation(data)
    implementation(deps.androidx.viewFlowBindings)
    implementation(deps.androidx.paging)
    implementation(deps.androidx.coil)
    implementation(deps.androidx.appCompat)
    implementation(deps.androidx.coreKtx)
    implementation(deps.lifecycle.viewModelKtx)
    implementation(deps.lifecycle.runtimeKtx)
    implementation(deps.androidx.constraintLayout)
    implementation(deps.androidx.material)
    implementation(deps.jetbrains.coroutinesAndroid)
    implementation(deps.jetbrains.coroutinesCore)
    implementation(deps.koin.androidXViewModel)
    implementation(deps.androidx.navigationFragment)
    implementation(deps.androidx.navigationKtx)
    implementation(deps.squareup.loggingInterceptor)
    implementation(deps.androidx.appCompat)
    implementation(deps.akexorcist.cornerProgress)
    implementation(deps.squareup.moshiKotlin)
    implementation(deps.squareup.converterMoshi)
    implementation(deps.androidx.navigationFragment)
    /**-------------------testing libs------------------------------------------**/
    //UI Testing , AndroidJUnitRunner and JUnit Rules &Espresso dependencies
    //junit 5
    testImplementation ("org.junit.jupiter:junit-jupiter-engine:5.7.0")
    testImplementation ("io.mockk:mockk:1.10.5")
    androidTestImplementation("org.assertj:assertj-core:3.19.0")
    androidTestImplementation ("androidx.test.espresso:espresso-intents:3.30")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.2-native-mt")
    implementation ("androidx.arch.core:core-testing:2.1.0")
    implementation ("androidx.test.espresso:espresso-idling-resource:3.3.0")
    implementation ("androidx.test.espresso:espresso-contrib:3.3.0")
    /**-------------------------------------------------------------**/


}
