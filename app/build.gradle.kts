plugins {
  kotlin("kapt")
  alias(libs.plugins.androidApplication)
  alias(libs.plugins.jetbrainsKotlinAndroid)
  alias(libs.plugins.hiltPlugin)
}

android {
  namespace = "com.godslew.tlaloc"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.godslew.tlaloc"
    minSdk = 24
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
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    kotlinCompilerExtensionVersion = "1.5.1"
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

kapt {
  correctErrorTypes = true
}

dependencies {
  implementation(project(":designsystem"))

  implementation(libs.androidx.core.ktx)
  implementation(
    libs.androidx
      .lifecycle
      .runtime
      .ktx,
  )
  implementation(libs.androidx.activity.compose)
  implementation(libs.material)
  implementation(libs.androidx.appcompat)

  // compose
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(
    libs.androidx
      .ui
      .tooling
      .preview,
  )
  implementation(libs.androidx.material3)

  // hilt
  implementation(libs.dagger.hilt.android)
  kapt(libs.dagger.hilt.compiler)

  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(
    libs.androidx
      .ui
      .test
      .junit4,
  )
  debugImplementation(libs.androidx.ui.tooling)
  debugImplementation(
    libs.androidx
      .ui
      .test
      .manifest,
  )
}
