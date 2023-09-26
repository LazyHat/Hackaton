@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
    //alias(libs.plugins.tracer)
}

android {
    signingConfigs {
        create("release") {
            storeFile = file("/home/lazyhat/Desktop/AndroidStudioProjects/Keys/appkeystore.jks")
            storePassword = "seva2004"
            keyAlias = "lazyhatdev"
            keyPassword = "lazyhat2004"
        }
    }

    namespace = "ru.lazyhat.hackaton"
    compileSdk = 34

    defaultConfig {
        applicationId = "ru.lazyhat.hackaton"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.toVersion(libs.versions.java.version.get())
        targetCompatibility = JavaVersion.toVersion(libs.versions.java.version.get())
    }
    kotlinOptions {
        jvmTarget = libs.versions.java.version.get()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compilerExtensionVersion.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    //Kotlinx
    implementation(libs.koltinx.datetime)
    //Core
    implementation(libs.core.ktx)
    //Lifecycle
    implementation(libs.lifecycle.runtime.ktx)
    //Activity
    implementation(libs.activity.compose)
    //Boms
    implementation(platform(libs.compose.bom))
    //UI
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    //Navigation
    implementation(libs.navigation.compose)
    //Material
    implementation(libs.material3)
    //Koin
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.androidx.compose.navigation)
    implementation(libs.koin.ktor)
    implementation(libs.koin.logger.slf4j)
    //Coil
    implementation(libs.coil)
    //Ktor
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)
    //Room
    annotationProcessor(libs.room.compiler)
    ksp(libs.room.compiler)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    //Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    //Tracer
    //implementation(libs.tracer.crash.report)
}