plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")

}
android {

    namespace = "com.jair.ventaspamsac"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.jair.ventaspamsac"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    //room
    implementation(libs.androidx.room.runtime)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.androidx.espresso.core)
    ksp(libs.androidx.room.compiler)
    //room con soporte para corrutinas
    implementation(libs.androidx.room.ktx)
    //coroutines
    implementation(libs.kotlinx.coroutines.android)
    //dagger hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    //firebase
    implementation(libs.firebase.bom)
    //firebase auth
    implementation(libs.firebase.auth)
    // firebase firestore
        implementation("com.google.firebase:firebase-firestore")




    //otras
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}

