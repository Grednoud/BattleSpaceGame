plugins {
    id("com.android.application") version "8.2.2"
}

val gdxVersion: String by rootProject.extra

android {
    namespace = "ru.codesteps"
    compileSdk = 34
    
    sourceSets {
        named("main") {
            manifest.srcFile("AndroidManifest.xml")
            java.srcDirs("src")
            aidl.srcDirs("src")
            renderscript.srcDirs("src")
            res.srcDirs("res")
            assets.srcDirs("assets")
            jniLibs.srcDirs("libs")
        }
    }
    
    packaging {
        resources {
            excludes += "META-INF/robovm/ios/robovm.xml"
        }
    }
    
    defaultConfig {
        applicationId = "ru.codesteps"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

val natives: Configuration by configurations.creating

dependencies {
    implementation(project(":core"))
    
    implementation("com.badlogicgames.gdx:gdx-backend-android:$gdxVersion")
    natives("com.badlogicgames.gdx:gdx-platform:$gdxVersion:natives-armeabi-v7a")
    natives("com.badlogicgames.gdx:gdx-platform:$gdxVersion:natives-arm64-v8a")
    natives("com.badlogicgames.gdx:gdx-platform:$gdxVersion:natives-x86")
    natives("com.badlogicgames.gdx:gdx-platform:$gdxVersion:natives-x86_64")
}

tasks.register("copyAndroidNatives") {
    doFirst {
        val libsDir = file("libs")
        listOf("armeabi-v7a", "arm64-v8a", "x86_64", "x86").forEach { abi ->
            file("$libsDir/$abi").mkdirs()
        }
        
        configurations["natives"].files.forEach { jar ->
            val outputDir = when {
                jar.name.endsWith("natives-arm64-v8a.jar") -> file("libs/arm64-v8a")
                jar.name.endsWith("natives-armeabi-v7a.jar") -> file("libs/armeabi-v7a")
                jar.name.endsWith("natives-x86_64.jar") -> file("libs/x86_64")
                jar.name.endsWith("natives-x86.jar") -> file("libs/x86")
                else -> null
            }
            
            outputDir?.let {
                copy {
                    from(zipTree(jar))
                    into(it)
                    include("*.so")
                }
            }
        }
    }
}

tasks.matching { it.name.contains("package", ignoreCase = true) }.configureEach {
    dependsOn("copyAndroidNatives")
}

eclipse.project.name = "${rootProject.extra["appName"]}-android"
