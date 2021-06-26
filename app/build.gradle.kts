import com.google.common.base.Charsets
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.*

plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
    kotlin("plugin.serialization") version "1.5.0"
}

android {
    signingConfigs {
        create("release") {
            keyAlias = "release"
            keyPassword = getAppProperty("KEYSTORE_KEY_PASSWORD")
            storeFile = file("../keystore.jks")
            storePassword = getAppProperty("KEYSTORE_STORE_PASSWORD")
        }
    }
    compileSdk = 30
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "com.alvaromr.marvel"
        minSdk = 21
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "API_KEY", getAppProperty("API_KEY"))
        buildConfigField("String", "BASE_URL", "\"https://gateway.marvel.com:443/v1/public/\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
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
    }
    composeOptions {
        kotlinCompilerExtensionVersion = rootProject.extra["composeVersion"] as String
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.5.0")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("com.google.android.material:material:1.3.0")

    implementation("com.google.dagger:hilt-android:${rootProject.extra["hiltCoreVersion"]}")
    kapt("com.google.dagger:hilt-compiler:${rootProject.extra["hiltCoreVersion"]}")
    kapt("androidx.hilt:hilt-compiler:${rootProject.extra["hiltVersion"]}")
    androidTestImplementation("com.google.dagger:hilt-android-testing:${rootProject.extra["hiltCoreVersion"]}")
    kaptAndroidTest("com.google.dagger:hilt-compiler:${rootProject.extra["hiltCoreVersion"]}")

    implementation("androidx.hilt:hilt-navigation-compose:${rootProject.extra["hiltComposeNavigation"]}")

    implementation("androidx.compose.ui:ui:${rootProject.extra["composeVersion"]}")
    implementation("androidx.compose.material:material:${rootProject.extra["composeVersion"]}")
    implementation("androidx.compose.ui:ui-tooling:${rootProject.extra["composeVersion"]}")
    implementation("androidx.compose.runtime:runtime:${rootProject.extra["composeVersion"]}")
    implementation("androidx.compose.runtime:runtime-livedata:${rootProject.extra["composeVersion"]}")
    implementation("androidx.navigation:navigation-compose:${rootProject.extra["composeNavVersion"]}")

    implementation("io.ktor:ktor-client-core:${rootProject.extra["ktorVersion"]}")
    implementation("io.ktor:ktor-client-serialization:${rootProject.extra["ktorVersion"]}")
    implementation("io.ktor:ktor-client-okhttp:${rootProject.extra["ktorVersion"]}")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.1")

    implementation("com.google.accompanist:accompanist-coil:0.12.0")

    implementation("androidx.room:room-runtime:${rootProject.extra["roomVersion"]}")
    kapt("androidx.room:room-compiler:${rootProject.extra["roomVersion"]}")
    implementation("androidx.room:room-ktx:${rootProject.extra["roomVersion"]}")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${rootProject.extra["composeVersion"]}")

    debugImplementation("com.facebook.soloader:soloader:0.10.1")
    debugImplementation("com.facebook.flipper:flipper-network-plugin:0.95.0")
    debugImplementation("com.facebook.flipper:flipper:0.95.0")
}

fun getAppProperties(): Properties {
    val properties = Properties()
    val localProperties = File("app.properties")

    if (localProperties.isFile) {
        InputStreamReader(FileInputStream(localProperties),
            Charsets.UTF_8).use { reader ->
            properties.load(reader)
        }
    }
    return properties
}

fun getAppProperty(key: String) = try {
    getAppProperties()[key] as String
} catch (e: Exception) {
    "\"$key IS NOT IN VERSION CONTROL, USE YOUR OWN\"".also(::println)
}