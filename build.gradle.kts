// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Build.androidBuildTools)
        classpath(Build.hiltAndroidGradlePlugin)
        classpath(Build.kotlinGradlePlugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20-RC")
        classpath("com.android.tools.build:gradle:7.4.2")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}