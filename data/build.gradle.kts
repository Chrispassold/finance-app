plugins {
    alias(libs.plugins.convention.android.feature)
    alias(libs.plugins.convention.android.room)
}

android {
    namespace = "com.chrispassold.data"
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.androidx.datastore.preferences)

    implementation(libs.retrofit)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}
