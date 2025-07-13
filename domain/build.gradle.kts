plugins {
    alias(libs.plugins.convention.android.feature)
}

android {
    namespace = "com.chrispassold.domain"
}

dependencies {
    implementation(project(":core"))

//    implementation(libs.androidx.core.ktx)
//    implementation(libs.timber)
//    implementation(libs.hilt.android)
//    ksp(libs.hilt.android.compiler)
//    implementation(libs.kotlinx.datetime)
//
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
}
