import org.gradle.api.JavaVersion

object ProjectConfig {
    const val appId = "com.chrispassold.askbuddy"
    const val minSdk = 24
    const val compileSdk = 35
    const val targetSdk = 35
    const val versionCode = 1
    const val majorVersion = 1
    const val minorVersion = 6
    const val patchVersion = 1
    const val versionName = "$majorVersion.$minorVersion.$patchVersion"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    val targetCompatibility = JavaVersion.VERSION_17
    val sourceCompatibility = JavaVersion.VERSION_17
    const val jvmTarget = "17"
}