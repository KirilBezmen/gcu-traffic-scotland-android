import java.util.Properties

plugins {
    id("com.android.application")
}

// local.properties is already excluded from git. These values are only used on
// the developer's machine after Traffic Scotland approves access.
// WARNING: BuildConfig strings are extractable from an APK. Do not redistribute
// a credentials-bearing release build without first addressing this limitation.
val developerProps = Properties().apply {
    val localFile = rootProject.file("local.properties")
    if (localFile.exists()) localFile.inputStream().use { load(it) }
}
fun javaString(value: String): String =
    "\"" + value.replace("\\", "\\\\").replace("\"", "\\\"") + "\""

android {
    namespace = "com.kirilbezmen.trafficscotland"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.kirilbezmen.trafficscotland"
        minSdk = 26
        targetSdk = 34
        versionCode = 2
        versionName = "0.2.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "TRAFFIC_CLIENT_ID",
            javaString(developerProps.getProperty("TRAFFIC_CLIENT_ID", "")))
        buildConfigField("String", "TRAFFIC_CLIENT_KEY",
            javaString(developerProps.getProperty("TRAFFIC_CLIENT_KEY", "")))
        buildConfigField("String", "MAPS_API_KEY",
            javaString(developerProps.getProperty("MAPS_API_KEY", "")))
        manifestPlaceholders["MAPS_API_KEY"] = developerProps.getProperty("MAPS_API_KEY", "")
    }

    buildFeatures { buildConfig = true }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.fragment:fragment:1.8.5")
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.8.7")
    implementation("androidx.lifecycle:lifecycle-livedata:2.8.7")
    // Mapping SDK expressly allowed by GCU coursework brief.
    implementation("com.google.android.gms:play-services-maps:20.0.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}
