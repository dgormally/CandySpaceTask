plugins {
    kotlin
    id("org.jetbrains.kotlin.android.extensions")

}

dependencies {
    implementation(deps.jetbrains.coroutinesCore)
    implementation(deps.squareup.loggingInterceptor)
    implementation(deps.koin.core)
    implementation(deps.squareup.networkResponseAdapter)
}