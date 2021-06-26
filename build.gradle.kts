// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val composeVersion by extra("1.0.0-beta09")
    val composeNavVersion by extra("2.4.0-alpha03")
    val hiltCoreVersion by extra("2.37")
    val hiltVersion by extra("1.0.0")
    val hiltComposeNavigation by extra("1.0.0-alpha02")
    val ktorVersion by extra("1.6.0")
    val roomVersion by extra("2.3.0")

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.0-alpha02")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10")
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hiltCoreVersion")


        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}