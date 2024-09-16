// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.8.0")
    }
    repositories {
        mavenCentral()
    }
}
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.android.library") version "7.1.2" apply false
    id("org.jetbrains.kotlin.android.extensions") version "1.8.0" apply false
    id("com.google.dagger.hilt.android") version "2.51.1" apply false


}