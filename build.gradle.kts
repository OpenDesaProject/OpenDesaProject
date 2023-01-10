// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        google()
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.0.2")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navVersion}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}")
    }
}

plugins {
    id("com.android.application") version "7.2.2" apply false
    id("com.android.library") version "7.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.7.10" apply false
}

tasks.register(name = "clean", type = Delete::class) {
    delete(rootProject.buildDir)
}