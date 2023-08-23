plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = NameSpace.contraster
    compileSdk = Android.compileSdk

    defaultConfig {
        applicationId = Android.applicationId
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk
        versionCode = Android.versionCode
        versionName = Android.versionName

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
        kotlinCompilerExtensionVersion = Versions.KOTLIN_COMPILER_VERSION
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(AndroidX.coreKtx)
    implementation(project(Local.hslMakerMap))
    testImplementation(JUnit.jUnit)

    // Compose
    implementation(platform(Compose.bom))
    implementation(Compose.material3)
    implementation(Compose.uiToolingPreview)
    implementation(Compose.activityCompose)
    implementation(Compose.ui)
    implementation(Compose.uiGraphics)
    androidTestImplementation(platform(Compose.bom))
    debugImplementation(Compose.uiTooling)
    debugImplementation(Compose.uiTooling)
    debugImplementation(Compose.uiTestManifest)

    //ViewModel + Lifecycle
    implementation(Lifecycle.viewModelKtx)
    implementation(Lifecycle.viewModelCompose)
    implementation(Lifecycle.runTimeKtx)
    implementation(Lifecycle.runTimeCompose)
    implementation(Lifecycle.commonJava8)

    //Coroutines
    implementation(Kotlin.coroutine)

    //Koin
    implementation(Koin.koinCompose)

    //Navigation
    implementation(Navigation.navigation)
}