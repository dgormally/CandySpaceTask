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
    implementation(deps.androidx.navigationFragment)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.test.ext:junit-ktx:1.1.3")
    debugImplementation("androidx.fragment:fragment-testing:1.3.6")
    implementation("androidx.test.espresso:espresso-idling-resource:3.4.0")
    implementation("androidx.test.espresso:espresso-intents:3.4.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.test:runner:1.4.0")
    androidTestImplementation("androidx.test:rules:1.4.0")
    androidTestImplementation("org.koin:koin-test:2.1.6")
    androidTestImplementation("androidx.test:core:1.2.0")
    androidTestImplementation("org.mockito:mockito-core:2.25.1")
    androidTestImplementation("org.mockito:mockito-inline:2.25.1")
    androidTestImplementation ("io.mockk:mockk:1.10.0")
    androidTestImplementation("io.mockk:mockk-android:1.10.0") {
        exclude(module = "objenesis")
    }



}
