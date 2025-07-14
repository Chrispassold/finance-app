plugins {
    alias(libs.plugins.convention.android.application)
    alias(libs.plugins.convention.android.application.compose)
    alias(libs.plugins.convention.android.hilt)
}

android {
    namespace = ProjectConfig.appId
}

dependencies {
    implementation(project(":core"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))

//    implementation(libs.androidx.core.ktx)
//    implementation(libs.timber)
}
