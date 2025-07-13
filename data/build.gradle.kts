plugins {
    alias(libs.plugins.convention.android.feature)
    alias(libs.plugins.convention.android.room)
}

android {
    namespace = "com.chrispassold.data"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))

//    implementation(libs.androidx.core.ktx)
//    implementation(libs.timber)
//    implementation(libs.hilt.android)
//    ksp(libs.hilt.android.compiler)
//    implementation(libs.kotlinx.datetime)
//    implementation(libs.androidx.datastore.preferences)
//
//    implementation(libs.retrofit)
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    implementation(libs.kotlinx.serialization.json)
//    implementation(libs.androidx.room.runtime)
//    ksp(libs.androidx.room.compiler)
//    implementation(libs.androidx.room.ktx)
//    testImplementation(libs.androidx.room.testing)
}
