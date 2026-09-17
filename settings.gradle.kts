pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

rootProject.name = "BattleSpace"

include("core", "desktop")

// Include Android module only if Android SDK is available
val localPropertiesFile = file("local.properties")
val androidSdkAvailable = localPropertiesFile.exists() || System.getenv("ANDROID_HOME") != null || System.getenv("ANDROID_SDK_ROOT") != null

if (androidSdkAvailable) {
    include("android")
}
