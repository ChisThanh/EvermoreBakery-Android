
plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "project.evermorebakery"
    compileSdk = 34

    defaultConfig {
        applicationId = "project.evermorebakery"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.coordinatorlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.picasso)
    implementation(libs.volley)
    implementation (libs.gson)
    implementation(libs.okhttpMockwebserver)
    implementation(files("libs/merchant-1.0.25.aar"))
    implementation("com.vnpay:vnpay-sdk-android:1.3.0") // Replace with the latest version
}
