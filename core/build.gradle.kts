plugins {
    alias(libs.plugins.convention.android.library)
    alias(libs.plugins.convention.android.library.compose)
    alias(libs.plugins.convention.android.hilt)
}

android {
    namespace = "com.chrispassold.core"
}

dependencies {
//    implementation(libs.androidx.core.ktx)
//    implementation(libs.timber)
//    implementation(libs.hilt.android)
//    ksp(libs.hilt.android.compiler)
}
