plugins {
    id("idea")
    id("eclipse")
}

allprojects {
    version = "1.0"
}

val appName by extra("BattleSpace")
val gdxVersion by extra("1.12.1")
val lwjgl3Version by extra("3.3.3")
