plugins {
    id("java")
    id("application")
    id("eclipse")
}

val gdxVersion: String by rootProject.extra
val lwjgl3Version: String by rootProject.extra

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

sourceSets {
    main {
        java.srcDirs("src")
        resources.srcDirs("../android/assets")
    }
}

repositories {
    mavenCentral()
    maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots/") }
    maven { url = uri("https://oss.sonatype.org/content/repositories/releases/") }
}

application {
    mainClass.set("ru.codesteps.desktop.DesktopLauncher")
}

dependencies {
    implementation(project(":core"))
    
    implementation("com.badlogicgames.gdx:gdx-backend-lwjgl3:$gdxVersion")
    implementation("com.badlogicgames.gdx:gdx-platform:$gdxVersion:natives-desktop")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.named<JavaExec>("run") {
    workingDir = file("../android/assets")
    isIgnoreExitValue = true
    
    if (System.getProperty("os.name").lowercase().contains("mac")) {
        jvmArgs("-XstartOnFirstThread")
    }
}

tasks.register<Jar>("dist") {
    dependsOn(tasks.classes)
    
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    
    manifest {
        attributes["Main-Class"] = application.mainClass.get()
    }
    
    from(sourceSets.main.get().output)
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    from(file("../android/assets"))
    
    archiveBaseName.set("${rootProject.extra["appName"]}")
    archiveClassifier.set("desktop")
}

eclipse.project.name = "${rootProject.extra["appName"]}-desktop"
