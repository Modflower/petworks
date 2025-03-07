dependencyResolutionManagement {
	versionCatalogs {
		create("libs") {
			from(files("libs.versions.toml"))
		}
	}
}

pluginManagement {
	repositories {
		maven("https://maven.fabricmc.net/") { name = "Fabric" }
		maven("https://maven.architectury.dev/") { name = "Architectury" }
		maven("https://maven.neoforged.net/releases") { name = "Neoforged" }
		maven("https://files.minecraftforge.net/maven/") { name = "Forge" }
		gradlePluginPortal()
	}
}

plugins {
	// apply the foojay-resolver plugin to allow automatic download of jdks
	id("org.gradle.toolchains.foojay-resolver-convention") version "0.9.0"
}

rootProject.name = "petworks"
include("xplat")
include("fabric")
