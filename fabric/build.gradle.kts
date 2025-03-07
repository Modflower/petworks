val xplat = project(":xplat")
val xplatMain = xplat.sourceSets.main.get()

val modrinthId: String by project
/*
loom {
	accessWidenerPath = file("src/main/resources/petworks.accesswidener")
}
*/

repositories {
	mavenLocal()
}

dependencies {
	modImplementation(libs.bundles.fabric)
	compileOnly(project(":xplat", configuration = "namedElements"))
	modCompileOnly(variantOf(rootProject.libs.emi.fabric) { classifier("api") })
	modRuntimeOnly(libs.bundles.fabric.runtime)
}

tasks {
	compileJava {
		source(xplatMain.allSource)
	}
	processResources {
		from(xplatMain.resources)
	}
	publish {
		dependsOn(modrinth)
	}
}

modrinth {
	token.set(System.getenv("MODRINTH_TOKEN"))
	projectId.set(modrinthId)
	versionType.set(meta.releaseType)
	versionName.set("${meta.projectVersion} - Fabric ${libs.versions.minecraft.version.get()}")
	versionNumber.set("${project.version}-fabric")
	changelog.set(meta.changelog)
	uploadFile.set(tasks.remapJar)
	gameVersions.set(meta.minecraftCompatible)
	loaders.addAll("fabric", "quilt")
}
