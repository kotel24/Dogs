import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktlintConfig: Configuration by configurations.creating

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.room) apply false
    alias(libs.plugins.detekt) apply false
}

task("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}

tasks.withType(KotlinCompile::class.java).all {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_21
    }
}


tasks.register<JavaExec>("ktlint") {
    group = LifecycleBasePlugin.VERIFICATION_GROUP
    description = "Check Kotlin code style"
    classpath = ktlintConfig
    mainClass = "com.pinterest.ktlint.Main"
    args("**/src/**/*.kt", "**.kts", "!**/build/**")
}

dependencies {
    ktlintConfig(libs.ktlint.get().toString()) {
        attributes {
            attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
        }
    }
}