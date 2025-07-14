plugins {
    alias(libs.plugins.convention.android.feature)
}

android {
    namespace = "com.chrispassold.domain"
}

dependencies {
    implementation(project(":core"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}
