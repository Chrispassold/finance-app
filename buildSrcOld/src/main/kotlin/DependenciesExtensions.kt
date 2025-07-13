import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

fun DependencyHandler.core() {
    implementation(project(":core"))
}

fun DependencyHandler.presentation() {
    implementation(project(":presentation"))
}

fun DependencyHandler.data() {
    implementation(project(":data"))
}

fun DependencyHandler.domain() {
    implementation(project(":domain"))
}