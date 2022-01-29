// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val kotlinVersion by extra("1.5.0")
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.1")
        classpath(kotlin("gradle-plugin", version = kotlinVersion))
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.4")
    }
}

allprojects {

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
            sourceCompatibility = JavaVersion.VERSION_1_8.toString()
            targetCompatibility = JavaVersion.VERSION_1_8.toString()
        }
    }

    repositories {
        google()
        jcenter()
        maven{ url = uri("https://jitpack.io")}
    }
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}